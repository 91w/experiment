import axios from 'axios'

// 创建分类
const postChat = question =>
  axios.post('/api/chat', question).then(res => res.data)

// 读取分类列表
const listHistory = () =>
  axios.get('/api/chat/history').then(res => res.data)

export { postChat, listHistory }