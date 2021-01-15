import request from '@/utils/request'

// 查询客户建档列表
export function listKh(query) {
  return request({
    url: '/system/kh/list',
    method: 'get',
    params: query
  })
}
// 查询客户下拉列表
export function getKhList(query) {
  return request({
    url: '/system/kh/getKhList',
    method: 'get',
    params: query
  })
}

// 查询客户建档详细
export function getKh(id) {
  return request({
    url: '/system/kh/' + id,
    method: 'get'
  })
}

// 新增客户建档
export function addKh(data) {
  return request({
    url: '/system/kh',
    method: 'post',
    data: data
  })
}

// 修改客户建档
export function updateKh(data) {
  return request({
    url: '/system/kh',
    method: 'put',
    data: data
  })
}

// 删除客户建档
export function delKh(id) {
  return request({
    url: '/system/kh/' + id,
    method: 'delete'
  })
}

// 导出客户建档
export function exportKh(query) {
  return request({
    url: '/system/kh/export',
    method: 'get',
    params: query
  })
}