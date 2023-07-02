<script setup lang="ts">
import { reactive, ref } from 'vue'
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { login } from "../../api/user";
import { useRouter } from "vue-router";
import { useUserStore } from "../../store/user"
const router = useRouter()
const store = useUserStore()
const labelPosition = ref("top")
interface Form {
  username: string
  password: string
}
const form = reactive<Form>({
  username: '',
  password: '',
})
// async 和 await 配合使用，await，等待ruleFormRef校验完成后，再执行接下来的代码
const onSubmit = async () => {
    is_loading.value = true
    // 前端，先简单校验
    await ruleFormRef.value?.validate().catch((err) => {
        ElMessage.error("数据有误")
        is_loading.value = false
        throw err // 一定要抛出，不然会继续执行接下来的代码
    })

    // 正式发送登录请求
    const data = await login(form).then((res) => {
        if(res.data.msg === "success") {
            // 变更 state
            store.update_user(res.data)
            store.saveAToken(res.data.access_token) 
            store.saveRToken(res.data.refresh_token) 
            ElMessage.success("登录成功") 
            router.push({path: "/", name: ""})
            return res.data // 获得后端，返回的数据
            
        }
    }).catch((error) =>{
        // 捕获 错误信息，如403
        is_loading.value = false;
        ElMessage.error("登录信息有误")
        throw new Error("错误的登录信息")
    })





}
// Form标签的ref 属性，获得输入的用户表单数据，可以在浏览器先进行简单的判断
const ruleFormRef = ref<FormInstance>()
// 定义表单校验规则, 在form中 :rules = rules，item中，prop="指定属性"
const rules = reactive<FormRules>({
  username: [
    {   required: true, 
        message: '请输入合法用户名或邮箱', 
        trigger: 'blur'  // blur,失去聚焦的时候
    },
    { min: 1, max: 100, message: '长度不合格', trigger: 'blur' },
  ],
  password: [
    {
      required: true,
      message: '请输入正确的密码',
      trigger: 'blur',
    },
    { min: 6, max: 100, message: '长度不合格', trigger: 'blur' }
  ],
})

// 设置变量，防止用户持续点击登录按钮
let is_loading = ref(false)
</script>

<template>
<div class="login">  
  <div style="margin: 20px" />
    <el-form
        label-position="top"
        label-width="100px"
        :model="form"
        style="max-width: 460px"
        size="large"
        :rules="rules"
        ref="ruleFormRef"
    > <!--:model 绑定数据，form中的数据修改，在ts中也修改-->
        <el-form-item label="用户名/邮箱" prop="username">
            <el-input v-model="form.username" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
            <el-input v-model="form.password" type="password" show-password/>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit" :loading="is_loading">登录</el-button>
        </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>
.login {
    background-color: azure;
    align-items: center;
    justify-content: center;
    height: 100vh;
    display: flex;
}
.el-form {
    width: 300px;
    background-color: aliceblue;
    .el-button{
        width: 100%;
        margin-top: 15px;
    }
}
</style>
