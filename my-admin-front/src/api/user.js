import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'POST',
    data: data
  })
}

export function getSidebarMenus() {
  return request({
    url: '/sidebarMenus',
    method: 'GET'
  })
}

export function getUserInfo() {
  return request({
    url: '/getUserInfo',
    method: 'GET'
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'GET'
  })
}
