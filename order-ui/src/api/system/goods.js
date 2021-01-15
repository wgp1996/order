import request from '@/utils/request'

// 查询商品建档列表
export function listGoods(query) {
  return request({
    url: '/system/goods/list',
    method: 'get',
    params: query
  })
}
// 查询商品建档列表
export function priceList(query) {
  return request({
    url: '/system/goods/priceList',
    method: 'get',
    params: query
  })
}

// 查询所有商品建档列表
export function goodsListAll(query) {
  return request({
    url: '/system/goods/goodsListAll',
    method: 'get',
    params: query
  })
}

// 查询商品建档详细
export function getGoods(id) {
  return request({
    url: '/system/goods/' + id,
    method: 'get'
  })
}

// 新增商品建档
export function addGoods(data) {
  return request({
    url: '/system/goods',
    method: 'post',
    data: data
  })
}

// 修改商品建档
export function updateGoods(data) {
  return request({
    url: '/system/goods',
    method: 'put',
    data: data
  })
}

// 删除商品建档
export function delGoods(id) {
  return request({
    url: '/system/goods/' + id,
    method: 'delete'
  })
}

// 导出商品建档
export function exportGoods(query) {
  return request({
    url: '/system/goods/export',
    method: 'get',
    params: query
  })
}