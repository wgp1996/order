import request from '@/utils/request'

// 查询采购订单主表列表
export function listCgddInfo(query) {
  return request({
    url: '/system/cgddInfo/list',
    method: 'get',
    params: query
  })
}


// 查询首页待办数量
export function index() {
  return request({
    url: '/system/cgddInfo/index',
    method: 'get'
  })
}

// 查询首页待办列表
export function indexList(status,query) {
  return request({
    url: '/system/cgddInfo/indexList/'+status,
    method: 'get',
    params: query
  })
}


// 删除进货单子表
export function delCgddInfoChild(id) {
  return request({
    url: '/system/cgddInfoChild/' + id,
    method: 'delete'
  })
}
// 查询订单列表
export function orderList(query) {
  return request({
    url: '/system/cgrkdSingle/orderList',
    method: 'get',
    params: query
  })
}

// 查询采购订单列表
export function cgddList(query) {
  return request({
    url: '/system/cgddInfo/cgddList',
    method: 'get',
    params: query
  })
}

// 查询采购订单主表详细
export function getCgddInfo(id) {
  return request({
    url: '/system/cgddInfo/' + id,
    method: 'get'
  })
}

// 查询进货单明细

export function getCgddInfoChild(dj_number) {
  return request({
    url: '/system/cgddInfoChild/getCgddInfoChild?djNumber=' + dj_number,
    method: 'get'
  })
}

// 新增采购订单主表
export function addCgddInfo(data) {
  return request({
    url: '/system/cgddInfo',
    method: 'post',
    data: data
  })
}

// 修改采购订单主表
export function updateCgddInfo(data) {
  return request({
    url: '/system/cgddInfo',
    method: 'put',
    data: data
  })
}

// 删除采购订单主表
export function delCgddInfo(id) {
  return request({
    url: '/system/cgddInfo/' + id,
    method: 'delete'
  })
}
// 生效采购订单
export function updateCgddInfoStatus(id) {
  return request({
    url: '/system/cgddInfo/effect/' + id,
    method: 'delete'
  })
}
// 取消生效订单
export function cancelCgddInfoStatus(id) {
  return request({
    url: '/system/cgddInfo/cancel/' + id,
    method: 'delete'
  })
}
// 导出采购订单主表
export function exportCgddInfo(query) {
  return request({
    url: '/system/cgddInfo/export',
    method: 'get',
    params: query
  })
}