import request from '@/utils/request'

// 查询商品价格管理列表
export function listGoodsPrice(query) {
  return request({
    url: '/system/goodsPrice/list',
    method: 'get',
    params: query
  })
}

// 查询商品价格管理详细
export function getGoodsPrice(id) {
  return request({
    url: '/system/goodsPrice/' + id,
    method: 'get'
  })
}

// 查询商品价格管理详细
export function selectGoodsPrice(goodsCode,khCode) {
  return request({
    url: '/system/goodsPrice/selectGoodsPrice/' + goodsCode+"/"+khCode,
    method: 'get'
  })
}


// 新增商品价格管理
export function addGoodsPrice(data) {
  return request({
    url: '/system/goodsPrice',
    method: 'post',
    data: data
  })
}

// 修改商品价格管理
export function updateGoodsPrice(data) {
  return request({
    url: '/system/goodsPrice',
    method: 'put',
    data: data
  })
}

// 删除商品价格管理
export function delGoodsPrice(id) {
  return request({
    url: '/system/goodsPrice/' + id,
    method: 'delete'
  })
}

// 导出商品价格管理
export function exportGoodsPrice(query) {
  return request({
    url: '/system/goodsPrice/export',
    method: 'get',
    params: query
  })
}