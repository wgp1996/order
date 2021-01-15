import request from '@/utils/request'

// 查询商品分类列表
export function listGoodsType(query) {
  return request({
    url: '/system/goodsType/list',
    method: 'get',
    params: query
  })
}

// 查询商品分类树
export function goodsTypeTree(query) {
  return request({
    url: '/system/goodsType/treeselect',
    method: 'get',
    params: query
  })
}



// 查询商品分类详细
export function getGoodsType(goodsTypeId) {
  return request({
    url: '/system/goodsType/' + goodsTypeId,
    method: 'get'
  })
}

// 新增商品分类
export function addGoodsType(data) {
  return request({
    url: '/system/goodsType',
    method: 'post',
    data: data
  })
}

// 修改商品分类
export function updateGoodsType(data) {
  return request({
    url: '/system/goodsType',
    method: 'put',
    data: data
  })
}

// 删除商品分类
export function delGoodsType(goodsTypeId) {
  return request({
    url: '/system/goodsType/' + goodsTypeId,
    method: 'delete'
  })
}

// 导出商品分类
export function exportGoodsType(query) {
  return request({
    url: '/system/goodsType/export',
    method: 'get',
    params: query
  })
}