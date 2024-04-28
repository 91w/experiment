<!--
 * @Descripttion: 登录页面组件
 * @Author: congz
 * @Date: 2020-06-11 11:08:34
 * @LastEditors: congz
 * @LastEditTime: 2020-08-19 10:14:30
--> 

<template>
  <div class="login">
    <div>
      <el-card class="box-card" shadow="hover">
        <router-link to="/">
          <img src="../assets/imgs/clogo.png" alt />
        </router-link>
        <div class="clearfix">
          <span>欢迎回来</span>
        </div>
        <div class="item">
          <el-form :model="form" status-icon :rules="rules" ref="form">
            <el-form-item prop="username">
              <el-input v-model="form.username" placeholder="用户名/手机号"></el-input>
            </el-form-item>
            <el-form-item >
              <el-input type="password" v-model="form.password" placeholder="密码"></el-input>
            </el-form-item>
            <el-form-item prop="captcha"> 
              <el-input v-model="form.captcha" placeholder="验证码"></el-input>
            </el-form-item>
            <div>
              <img src="" id="codeImg" @click="refreshCaptcha()">
              <button type="button" @click="refreshCaptcha()">看不清楚？</button>
            </div>
          </el-form>
          <div style="margin-top:15px">
            <a href="javascript:;" class="btn-gradient blue block" @click="login('form')">登录</a>
          </div>
          <el-link
            type="primary"
            href="/#/register"
            style="float:right;margin-bottom:10px;"
          >没有账号？请先注册></el-link>
        </div>
        <div class="line"></div>
      </el-card>
    </div>
  </div>
</template>
<script src="../assets/gt.js"></script>
<script>
import { mapActions } from 'vuex'
import * as userAPI from '@/api/users'
require('../assets/gt.js')
var captcha
export default {
  name: 'Login',
  data() {
    var validateUser = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'))
      } 
      callback()
    }
    var validateCode = (rule, value, callback) => {
      value = value.trim().toLowerCase()
      var expectCode = this.captchaCode.trim().toLowerCase()
      if(value === '') {
        callback(new Error('请输入验证码'))
      } else if(value !== expectCode) {
        callback(new Error('验证码错误'))
      } else {
        callback()
      }
    }
    return {
      form: {
        username: '',
        password: '',
        captcha: '',
      },
      captchaSrc: '',
      captchaCode: '',
      rules: {
        username: [{ validator: validateUser, trigger: 'blur' }],
        captcha: [{ validator: validateCode, trigger: 'blur' }]
      }
    }
  },
  methods: {
    ...mapActions(['setUser']),
    refreshCaptcha() {
      // 获取验证码
      userAPI.getCode()
        .then(res => {
          // 假设后端返回的是一个带有验证码key的图片
          this.captchaCode = res.data.code;
          // 创建一个URL对象表示指向由参数创建的Blob对象
          this.captchaSrc = res.data.img;
          document.getElementById('codeImg').src = "data:image/png;base64," + this.captchaSrc;
        })
        .catch(error => {
          console.error('Error fetching captcha image:', error);
        });
    }, 

    login(formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return
        }
          userAPI
            .postLogin(this.form)
            .then(res => {
              console.log(res)
              if (res.status === 404) {
                this.notifyError('验证失败', res.data.msg)
              } else if (res.data.msg == "成功") {
                // 登录信息存到本地
                let user = JSON.stringify(res.data.user)
                localStorage.setItem('user', user)
                localStorage.setItem('token', res.data.token)
                // 登录信息存到vuex
                this.setUser(user)
                // 弹出通知框提示登录成功信息
                this.notifySucceed('登录成功')
                this.$router.push({
                  name: 'Home'
                })
              } else {
                console.log(res.data.msg)
                this.notifyError('登录失败', res.data.msg)
              }
            })
            .catch(error => {
              this.notifyError('登录失败', error)
            })
      })
    },
  },
  mounted() {
    this.refreshCaptcha()
  },

  components: {}
}
</script>

<style scoped>
@import '../assets/css/button.css';
.text {
  font-size: 14px;
}

.item {
  margin: 0 auto;
  width: 300px;
}

.clearfix {
  font-size: 20px;
  margin-bottom: 20px;
}

.box-card {
  margin: 0 auto;
  text-align: center;
  width: 400px;
  margin-top: 100px;
  margin-bottom: 300px;
  border-radius: 10px;
}
.box-card .line {
  height: 30px;
  width: 300px;
  margin: 0 auto;
  border-bottom: 1px solid #c0c0c0;
}
/*其他账号登录*/
.box-card .logo {
  width: 300px;
  margin: 0 auto;
  height: 30px;
  display: flex;
  align-items: center;
}
.box-card .logo .logo-info {
  font-size: 14px;
  color: #757575;
}
.box-card .logo .logo-login {
  height: 15px;
  margin-left: 10px;
}
/*其他账号登录END*/
</style>
