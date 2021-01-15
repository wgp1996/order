import request from '@/utils/request'

// 查询发票建档列表
export function listFpInfo(query) {
  return request({
    url: '/system/fpInfo/list',
    method: 'get',
    params: query
  })
}

// 查询发票建档详细
export function getFpInfo(id) {
  return request({
    url: '/system/fpInfo/' + id,
    method: 'get'
  })
}

export function getFpInfoByKh(khCode) {
  return request({
    url: '/system/fpInfo/getFpInfoByKh/' + khCode,
    method: 'get'
  })
}



// 新增发票建档
export function addFpInfo(data) {
  return request({
    url: '/system/fpInfo',
    method: 'post',
    data: data
  })
}

// 修改发票建档
export function updateFpInfo(data) {
  return request({
    url: '/system/fpInfo',
    method: 'put',
    data: data
  })
}

// 删除发票建档
export function delFpInfo(id) {
  return request({
    url: '/system/fpInfo/' + id,
    method: 'delete'
  })
}

// 导出发票建档
export function exportFpInfo(query) {
  return request({
    url: '/system/fpInfo/export',
    method: 'get',
    params: query
  })
}