import request from '@/utils/request'

// 查询付款单列表
export function listPayInfo(query) {
  return request({
    url: '/system/payInfo/list',
    method: 'get',
    params: query
  })
}

// 查询付款单详细
export function getPayInfo(id) {
  return request({
    url: '/system/payInfo/' + id,
    method: 'get'
  })
}

export function getPayInfoChild(dj_number) {
  return request({
    url: '/system/payInfo/getPayInfoChild?djNumber=' + dj_number,
    method: 'get'
  })
}

// 新增付款单
export function addPayInfo(data) {
  return request({
    url: '/system/payInfo',
    method: 'post',
    data: data
  })
}

// 修改付款单
export function updatePayInfo(data) {
  return request({
    url: '/system/payInfo',
    method: 'put',
    data: data
  })
}

// 删除付款单
export function delPayInfo(id) {
  return request({
    url: '/system/payInfo/' + id,
    method: 'delete'
  })
}
// 删除进货单子表
export function delPayInfoChild(id) {
  return request({
    url: '/system/payInfoChild/' + id,
    method: 'delete'
  })
}
// 生效采购订单
export function updatePayInfoStatus(id) {
  return request({
    url: '/system/payInfo/effect/' + id,
    method: 'delete'
  })
}
// 取消生效订单
export function cancelPayInfoStatus(id) {
  return request({
    url: '/system/payInfo/cancel/' + id,
    method: 'delete'
  })
}
// 导出付款单
export function exportPayInfo(query) {
  return request({
    url: '/system/payInfo/export',
    method: 'get',
    params: query
  })
}