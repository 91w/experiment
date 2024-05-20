/*
 * @Descripttion:调用后端上传接口的函数
 * @Author: congz
 * @Date: 2020-07-12 15:44:02
 * @LastEditors: congz
 * @LastEditTime: 2020-08-05 15:36:07
 */

import axios from 'axios'

// 请求图片签名
const UploadForm = form =>
  axios.post('/api/sale', form).then(res => res.data)

export { UploadForm }
