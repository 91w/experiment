/*
 * @Descripttion:调用后端用户接口的函数
 * @Author: congz
 * @Date: 2020-06-11 09:39:58
 * @LastEditors: congz
 * @LastEditTime: 2020-08-19 10:16:06
 */

import axios from 'axios'

// 获取验证码
const getCode = () => 
  axios.get('/api/captcha').then(res => res)
//注册
const postUser = form =>
  axios.post('/api/user/register', form).then(res => res)
//登录
const postLogin = form =>
  axios.post('/api/user/login', form).then(res => res)
//检验token
const checkToken = () => axios.get('/api/ping').then(res => res.data)
//修改信息
const updateUser = form => axios.put('/api/user', form).then(res => res.data)

//发送邮件
const sendEmail = form =>
  axios.post('/api/user/sending-email', form).then(res => res.data)

//绑定或解绑邮箱
const vaildEmail = val =>
  axios.post('/api/user/vaild-email', { token: val }).then(res => res.data)



export {
  postUser,
  postLogin,
  checkToken,
  updateUser,
  sendEmail,
  vaildEmail,
  getCode
}
