import request from '@/utils/request'

export function pageList(data) {
  return request({
    url: '/role/pageList',
    method: 'GET',
    data
  })
}
