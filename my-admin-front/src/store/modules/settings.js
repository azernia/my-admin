import defaultSettings from '@/settings'
import { getMenus } from '@/api/menu'

const { showSettings, fixedHeader, sidebarLogo } = defaultSettings

const state = {
  showSettings: showSettings,
  fixedHeader: fixedHeader,
  sidebarLogo: sidebarLogo,
  menuList: []
}

const mutations = {
  CHANGE_SETTING: (state, { key, value }) => {
    // eslint-disable-next-line no-prototype-builtins
    if (state.hasOwnProperty(key)) {
      state[key] = value
    }
  },

  INIT_MENU(state, data) {
    state.menuList = data
  }
}

const actions = {
  changeSetting({ commit }, data) {
    commit('CHANGE_SETTING', data)
  },

  // 初始化菜单
  initMenu({ commit }) {
    getMenus().then(resp => {
      commit('INIT_MENU', resp.data)
    }).catch(error => {
      console.error(error)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

