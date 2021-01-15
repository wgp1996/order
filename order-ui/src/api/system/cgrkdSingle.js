import request from '@/utils/request'

// 查询进货单列表
export function listCgrkdSingle(query) {
  return request({
    url: '/system/cgrkdSingle/list',
    method: 'get',
    params: query
  })
}


// 查询进货单详细
export function getCgrkdSingle(id) {
  return request({
    url: '/system/cgrkdSingle/' + id,
    method: 'get'
  })
}
// 删除进货单子表
export function delCgrkdSingleChild(id) {
  return request({
    url: '/system/cgrkdSingleChild/' + id,
    method: 'delete'
  })
}
// 查询进货单明细

export function getCgrkdSingleChild(dj_number) {
  return request({
    url: '/system/cgrkdSingleChild/getCgrkdChild?djNumber=' + dj_number,
    method: 'get'
  })
}

// 新增进货单
export function addCgrkdSingle(data) {
  return request({
    url: '/system/cgrkdSingle',
    method: 'post',
    data: data
  })
}

// 修改进货单
export function updateCgrkdSingle(data) {
  return request({
    url: '/system/cgrkdSingle',
    method: 'put',
    data: data
  })
}
// 修改进货单发票
export function updateCgrkdSingleFp(data) {
  return request({
    url: '/system/cgrkdSingle/editFp',
    method: 'put',
    data: data
  })
}
// 查询首页待办列表
export function listIndex(status,query) {
  return request({
    url: '/system/cgrkdSingle/indexList/'+status,
    method: 'get',
    params: query
  })
}


// 删除进货单
export function delCgrkdSingle(id) {
  return request({
    url: '/system/cgrkdSingle/' + id,
    method: 'delete'
  })
}

// 生效进货单
export function updateCgrkdSingleStatus(id) {
  return request({
    url: '/system/cgrkdSingle/effect/' + id,
    method: 'delete'
  })
}
// 取消生效订单
export function updateCgrkdSingleCanceltatus(id) {
  return request({
    url: '/system/cgrkdSingle/cancel/' + id,
    method: 'delete'
  })
}
// 导出进货单
export function exportCgrkdSingle(query) {
  return request({
    url: '/system/cgrkdSingle/export',
    method: 'get',
    params: query
  })
}