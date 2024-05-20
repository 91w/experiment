/*
 * @Descripttion:调用后端商品接口的函数
 * @Author: congz
 * @Date: 2020-06-10 18:59:36
 * @LastEditors: congz
 * @LastEditTime: 2020-08-12 20:57:07
 */

import axios from 'axios'

// 创建商品
const postProduct = form =>
  axios.post('/api/product/add', form).then(res => res.data)

// 读商品详情
const showProduct = id =>
  axios.get(`/api/product/${id}`).then(res => res.data)

// 读取商品列表
const listProducts = (category, start, limit) =>
  axios
    .get('/api/product/get', { params: { category, start, limit } })
    .then(res => res.data)

//读取商品的图片
const showPictures = id => axios.get(`/api/product/image/${id}`).then(res => res.data)

const showImageList = () => axios.get('/api/test').then(res => res.data)
//搜索商品
const searchProducts = form =>
  axios.post('/api/product/search', form).then(res => res.data)

// 排行榜详情
const showRanking = () => axios.get('/api/v1/ranking/').then(res => res.data)

export {
  postProduct,
  showProduct,
  listProducts,
  showPictures,
  searchProducts,
  showRanking,
  showImageList
}
