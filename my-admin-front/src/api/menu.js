import request from '@/utils/request'

export function getMenus() {
  return request({
    url: '/menuList',
    method: 'GET'
  })
}
