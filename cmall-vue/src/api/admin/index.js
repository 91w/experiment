/*
 * @Descripttion:
 * @Author: congz
 * @Date: 2020-07-20 11:08:33
 * @LastEditors: congz
 * @LastEditTime: 2020-08-05 15:32:59
 */
import axios from 'axios'

// 获取数据
const getGoods = () =>
  axios.get('/api/admin/sale').then(res => res.data)

// 接收
const acceptGood = id =>
  axios.post('/api/admin/sale', {
    data: {id: id}
  }).then(res => res.data)

// 删除
const deleteGood = id =>
  axios
    .delete('/api/admin/sale', {
      data: { id: id }
    })
    .then(res => res.data)

// 删除
const deleteUser = id =>
    axios
      .delete('/api/admin/user', {
        data: { id: id }
      })
      .then(res => res.data)

      // 下线
const kickUser = id =>
    axios
      .post('/api/admin/user', {
        data: { id: id }
      })
      .then(res => res.data)

      // 获取
const getUser = () =>
        axios.get('/api/admin/user').then(res => res.data)

export { getGoods, acceptGood, deleteGood, deleteUser, kickUser, getUser }
