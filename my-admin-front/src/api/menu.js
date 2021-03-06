import request from '@/utils/request'

export function getMenus() {
  return request({
    url: '/menu/menuList',
    method: 'GET'
  })
}

export function add(data) {
  return request({
    url: '/menu/add',
    method: 'POST',
    data
  })
}

export function edit(data) {
  return request({
    url: '/menu/edit',
    method: 'PUT',
    data
  })
}
