import axios from 'axios'

// 读取评论
const showComment = id =>
  axios.get(`/api/comment/${id}`).then(res => res.data)

// 写评论
const addComment = form =>
  axios.post('/api/comment/add', form).then(res => res.data)  

// 删除评论
const deleteComment = id =>
  axios.delete(`/api/comment/delet/${id}`).then(res => res.data)


export { deleteComment, showComment, addComment }