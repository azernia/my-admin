import request from '@/utils/request'

export function getPermissions() {
  return request({
    path: '/permissions/getPermissions',
    method: 'GET'
  })
}
