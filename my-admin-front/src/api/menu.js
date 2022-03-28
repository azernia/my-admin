import request from '@/utils/request'

export function getMenus() {
  return request({
    url: '/list',
    method: 'GET'
  })
}
