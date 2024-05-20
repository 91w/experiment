<template>
    <div class="common-layout">
        <el-container>
          <el-header>
            <el-row :gutter="20">
                <el-col :span="12" :offset="6">
                  <h2>官方客服，有什么需要直接询问</h2>
                </el-col>
            </el-row>
          </el-header>    
            <div class="chat-container">
                <div class="chat-history" style="height: 500px; overflow-y: auto">
                    <ul v-infinite-scroll  class="infinite-list" style="overflow: auto">
                        <li v-for="(item, index) in history" :key="index" class="infinite-list-item">
                          <el-divider />
                          <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" size="40"></el-avatar>
                          <span>{{ item.usercontent }}</span>
                          <el-divider />
                          <span>{{ item.servicecontent }}</span>
                        </li>
                    </ul>
                </div>
                <div class="input-area" >
                    <el-input 
                        v-model="question" 
                        placeholder="请输入问题" 
                        class="input-field" :rows="2"
                        type="textarea"
                        autosize/>
                    <el-button @click="fetchAnswer(question)" type="primary" class="sumbit-btn">提交</el-button>
                </div>
            </div>  
            
        </el-container>
    </div>
</template>
  
<script>
import * as chatAPI from '@/api/chat'
export default {
  data() {
    return { 
      question: '',
      history: '',
      answer: '',
      loading: false,
      noMore: false,
      count: 0,
      userid: JSON.parse(localStorage.getItem('user')).id
    };
  },
  activated() {
    // 获取首页数据
    this.getHistory()
  },
  methods: {
    getHistory() {
      // 获取历史数据
      chatAPI
        .listHistory()
        .then(res => {
          if (res.msg === '成功') {
            this.history = res.data
          }  else {
            this.notifyError('获取历史记录失败', res.msg)
          }
        })
        .catch(err => {
          this.notifyError('获取历史记录失败1', err)
        })
    },
    fetchAnswer(question) {
      chatAPI
        .postChat(question)
        .then(res => {
            console.log("问", res)
            this.answer = res.answer
            this.history.push({
                usercontent: question,
                servicecontent: res.answer
            });
            this.question = ''; // 清空输入框
        })
    },
  },
}
</script>

<style scoped>
.chat-container {
    display: flex;
    flex-direction: column;
    height: 100vh;
    justify-content: space-between;
  }
  
  .chat-history {
    overflow-y: auto;
    padding: 10px;
    height: 90%;
  }
  
  .input-area {
    position: relative;
    top: 20%;
    justify-content: center;
    display: flex;
    padding: 10px;
    align-items: center;
  }
  
  .input-field {
    flex-grow: 1;
    margin-right: 10px;
  }
  
  .submit-btn {
    padding: 10px 20px;
  }
</style>

 