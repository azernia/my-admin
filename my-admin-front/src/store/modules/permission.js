import { asyncRoutes, constantRoutes } from '@/router'
import { getSidebarMenus } from '@/api/user'
import Layout from '@/layout'
// 获取路由工具
const _import = require('@/router/_import_' + process.env.NODE_ENV)

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, roles) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(roles, tmp)) {
      const component = tmp.component
      if (route.component) {
        if (component === 'Layout') {
          tmp.component = Layout
        } else {
          tmp.component = _import(tmp.component)
        }
      }
      // 判断是否有下级
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })

  return res
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  generateRoutes({ commit }, roles) {
    return new Promise((resolve, reject) => {
      getSidebarMenus().then(resp => {
        let accessedRoutes
        if (resp.code === 200) {
          accessedRoutes = filterAsyncRoutes(resp.data, roles)
        }
        resolve(accessedRoutes)
      }).catch(error => {
        reject(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
