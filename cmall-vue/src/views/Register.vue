<!--
 * @Descripttion: 注册页面组件
 * @Author: congz
 * @Date: 2020-06-11 10:01:19
 * @LastEditors: congz
 * @LastEditTime: 2020-08-18 19:58:16
--> 

<template>
  <div class="register">
    <div>
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <span>注册</span>
        </div>
        <div class="item">
          <el-form :model="form" status-icon :rules="rules" ref="form">
            <el-form-item prop="username">
              <el-input v-model="form.username" placeholder="用户名"></el-input>
            </el-form-item>
            <el-form-item prop="phone">
              <el-input v-model="form.phone" placeholder="手机号"></el-input>
            </el-form-item>
            <el-form-item prop="email">
              <el-input v-model="form.email" placeholder="邮箱"></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input v-model="form.password" placeholder="密码" type="password"></el-input>
            </el-form-item>
            <el-form-item prop="password_confirm">
              <el-input v-model="form.password_confirm" placeholder="确认密码" type="password"></el-input>
            </el-form-item>
            <el-form-item prop="captcha">
              <el-input  v-model="form.captcha" placeholder="验证码"></el-input>
            </el-form-item>
            <div>
              <img src="" id="codeImg" @click="refreshCaptcha()">
              <button type="button" @click="refreshCaptcha()">看不清楚？</button>
            </div>
          </el-form>
          <div style="margin-top:15px">
            <a href="javascript:;" class="btn-gradient blue block" @click="register('form')">注册</a>
          </div>
          <el-link type="primary" href="/#/login" style="float:right;margin-bottom:20px;">已有账号？直接登录></el-link>
        </div>
      </el-card>
    </div>
  </div>
</template>
<script src="../assets/gt.js"></script>
<script>
import * as userAPI from '@/api/users'
require('../assets/gt.js')
var captcha
export default {
  name: 'Register',
  data() {
    var validateUser = (rule, value, callback) => {
      if (value === '' || value === null) {
        callback(new Error('用户名不能为空'))
      } 
      callback()
    }
    var validatePass = (rule, value, callback) => {
      if (value === '' || value === null) {
        callback(new Error('请输入密码'))
      } else if (value.length < 8 || value.length > 16) {
        callback(new Error('密码长度需在8到16之间'))
      } else {
        if (this.form.password_confirm !== '') {
          this.$refs.form.validateField('checkPass')
        } 
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '' || value === null) {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.form.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    var validatePhone = (rule, value, callback) => {
      if (value === '' || value === null) {
        callback(new Error('手机号不能为空'))
      } else {
        // 编写正则表达式，验证中国大陆的手机号
        const phoneRegex = /^1[3-9]\d{9}$/;
        if (!phoneRegex.test(value)) {
        callback(new Error('请输入有效的手机号'));
        }  
      }
      callback()
    }
    var validateEmail = (rule, value, callback) => {
      if (value === '' || value === null) {
        callback(new Error('邮箱不能为空'));
      } else {
        // 正则表达式验证邮箱
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (!emailRegex.test(value)) {
          callback(new Error('请输入有效的邮箱地址'));
        } else {
          callback();
        }
      }
    }
    var validateCode = (rule, value, callback) => {
      if(value === '') {
        callback(new Error('请输入验证码'))
      } else if(value !== this.captchaCode) {
        callback(new Error('验证码错误'))
      }
      callback()
    }
    return {
      imageUrl: '',
      form: {
        username: '',
        password: '',
        password_confirm: '',
        phone: '',
        email: '',
        captcha: '',
      },
      rules: {
        username: [{ validator: validateUser, trigger: 'blur' }],
        password: [{ validator: validatePass, trigger: 'blur' }],
        password_confirm: [{ validator: validatePass2, trigger: 'blur' }],
        phone: [{ validator: validatePhone, trigger: 'blur' }],
        email: [{ validator: validateEmail, trigger: 'blur' }],
        captcha: [{ validator: validateCode, trigger: 'blur' }],
      }
    }
  },
  methods: {
    register(formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return
        }
          userAPI
            .postUser(this.form)
            .then(res => {
              
              if (res.status === 404) {
                this.notifyError('验证失败', res.msg)
              } else if (res.data.msg === "成功") {
                this.notifySucceed('注册成功')
                this.$router.push({
                  name: 'Login'
                })
              } else {
                this.notifyError('注册失败', res.data.msg)
              }
            })
            .catch(error => {
              this.notifyError('注册失败', error)
            })
      })
    },
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
}

.box-card {
  margin: 0 auto;
  text-align: center;
  width: 400px;
  margin-top: 100px;
  margin-bottom: 200px;
  border-radius: 10px;
}
</style>
