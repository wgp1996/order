import request from '@/utils/request'

// 查询采购订单子表列表
export function listCgddInfoChild(query) {
  return request({
    url: '/system/cgddInfoChild/list',
    method: 'get',
    params: query
  })
}


// 查询采购订单汇总
export function allList(query) {
  return request({
    url: '/system/cgddInfoChild/allList',
    method: 'get',
    params: query
  })
}



// 采购订单确认入库
export function updateRkStatus(id) {
  return request({
    url: '/system/cgddInfoChild/rkEffect/' + id,
    method: 'delete'
  })
}
// 采购订单确认交货
export function updateJhStatus(id) {
  return request({
    url: '/system/cgddInfoChild/shEffect/' + id,
    method: 'delete'
  })
}

// 查询采购订单子表详细
export function getCgddInfoChild(id) {
  return request({
    url: '/system/cgddInfoChild/' + id,
    method: 'get'
  })
}

// 新增采购订单子表
export function addCgddInfoChild(data) {
  return request({
    url: '/system/cgddInfoChild',
    method: 'post',
    data: data
  })
}

// 修改采购订单子表
export function updateCgddInfoChild(data) {
  return request({
    url: '/system/cgddInfoChild',
    method: 'put',
    data: data
  })
}

// 修改采购订单子表
export function updateFp(data) {
  return request({
    url: '/system/cgddInfoChild/editFp',
    method: 'put',
    data: data
  })
}

// 删除采购订单子表
export function delCgddInfoChild(id) {
  return request({
    url: '/system/cgddInfoChild/' + id,
    method: 'delete'
  })
}

// 导出采购订单子表
export function exportCgddInfoChild(query) {
  return request({
    url: '/system/cgddInfoChild/export',
    method: 'get',
    params: query
  })
}