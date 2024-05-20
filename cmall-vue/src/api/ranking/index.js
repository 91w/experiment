/*
 * @Descripttion:调用后端热门商品&排行榜的函数
 * @Author: congz
 * @Date: 2020-07-23 14:16:19
 * @LastEditors: congz
 * @LastEditTime: 2020-07-23 15:16:56
 */
import axios from 'axios'

// 读取热门家电
const listPadRanking = () =>
  axios.get('/api/padrank').then(res => res.data)

// 读取热门配件
const listAndroidRanking = () =>
  axios.get('/api/androidrank').then(res => res.data)

export { listPadRanking, listAndroidRanking }
