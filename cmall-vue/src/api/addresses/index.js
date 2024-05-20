/*
 * @Descripttion:
 * @Author: congz
 * @Date: 2020-07-20 11:08:33
 * @LastEditors: congz
 * @LastEditTime: 2020-08-05 15:32:59
 */
import axios from 'axios'

// 创建购物车
const postAddress = form =>
  axios.post('/api/addresses', form).then(res => res.data)

// 读取购物车
const showAddresses = user_id =>
  axios.get(`/api/addresses/${user_id}`).then(res => res.data)

// 更新购物车
const updateAddress = form =>
  axios.put('/api/addresses', form).then(res => res.data)

// 删除购物车
const deleteAddress = addressID =>
  axios
    .delete('/api/addresses', {
      data: { address_id: addressID }
    })
    .then(res => res.data)

export { postAddress, showAddresses, updateAddress, deleteAddress }
