<!--
 * @Descripttion: 
 * @Author: congz
 * @Date: 2020-08-09 12:53:22
 * @LastEditors: congz
 * @LastEditTime: 2020-08-09 21:31:44
-->
<template>
  <div class="user-pass" id="user-pass" name="user-pass">
    <div class="user-pass-layout">
      <el-row :gutter="10">
        <div>
          <CenterMenu></CenterMenu>
        </div>
        <el-col :span="20">
          <div class="user-pass-content">
            <div class="extra"></div>
            <div class="user-pass-title">
              <p>修改密码</p>
            </div>
            <div class="user-pass-form" >
              <el-form :model="form" status-icon :rules="rules" ref="form" label-width="80px">
                <el-form-item label="密码" prop="password">
                  <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="password_confirm">
                  <el-input type="password" v-model="form.password_confirm" autocomplete="off"></el-input>
                </el-form-item>
              </el-form>
              <div style="margin-top:15px">
                <a href="javascript:;" class="btn-gradient blue block" @click="login('form')">登录</a>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import CenterMenu from '../components/CenterMenu'
import * as userAPI from '@/api/users'
export default {
  name: 'UpdatePass',
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else if (value.length < 8 || value.length > 16) {
        callback(new Error('密码长度需在8到16之间'))
      } else {
        if (this.form.password_confirm !== '') {
          this.$refs.form.validateField('password_confirm')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.form.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      form: {
        password: '',
        password_confirm: '',
      },
      rules: {
        password: [{ validator: validatePass, trigger: 'blur' }],
        password_confirm: [{ validator: validatePass2, trigger: 'blur' }]
      },
    }
  },
  methods: { 
      login(formName) {
        this.$refs[formName].validate(valid => {
          if (!valid) {
            return
          }
            userAPI
              .changePassword(this.form)
              .then(res => {
                if (res.status === 404) {
                  this.notifyError('验证失败', res.data.msg)
                } else if (res.msg == "成功") {
                  this.notifySucceed('修改成功')
                } else {
                  
                  this.notifyError('修改失败', res.data.msg)
                }
              })
              .catch(error => {
                this.notifyError('修改失败', error)
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