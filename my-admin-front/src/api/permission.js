import request from '@/utils/request'
import axios from 'axios'
import { getToken } from '@/utils/auth'

export function getPermissions() {
  return request({
    url: '/permissions/getPermissions',
    method: 'GET'
  })
}

export function getSwaggerData() {
  return new Promise((resolve, reject) => {
    axios.get('/v2/api-docs').then(resp => {
      resolve(resp.data)
    }).catch(error => {
      reject(error)
    })
  })
}

export function syncPermission(data) {
  return request({
    url: '/permissions/syncPermission',
    method: 'POST',
    data
  })
}
