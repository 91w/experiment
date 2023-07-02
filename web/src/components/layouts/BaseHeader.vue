<script lang="ts" setup>
import { is_collapse } from "./IsCollapse.vue";
import {
  Expand,
  Fold,
} from "@element-plus/icons-vue";
import { ArrowDown } from '@element-plus/icons-vue'
import { useUserStore} from "~/store/user"
import { reactive, ref } from 'vue'
import { getInfo} from '../../api/user'
import { useRouter } from "vue-router";
import { ElMessage, ElMessageBox } from "element-plus";
const router = useRouter()
const store = useUserStore()
// 用户信息
const userInfo = ref({profile: '', username: ''})
getInfo().then((res) => {
  userInfo.value.profile = res.data.profile
  userInfo.value.username = res.data.username
})
// 具体的用户页面
const show = () => {
  router.push('/account/info')
}

// 登出操作
const logout = async () => {
                              // 问询
  await ElMessageBox.confirm("确定吗", "退出询问", {
    // 点击按钮的信息
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: 'warning'
  }).catch(() => {
    ElMessage.info("取消退出")
    throw new Error('')
  })
  store.saveAToken("")
  store.saveRToken("")
  window.location.reload()
}

</script>

<template>
 <el-header>
  <el-icon @click="is_collapse = !is_collapse">
    <Expand v-if="is_collapse"/>
    <Fold v-else />
  </el-icon>
  
  <!--面包屑，显示路径-->
  <el-breadcrumb separator="/">
    <el-breadcrumb-item :to="{ path: '/' }">homepage</el-breadcrumb-item>
    <el-breadcrumb-item
      ><a href="/">promotion management</a></el-breadcrumb-item
    >
    <el-breadcrumb-item>promotion list</el-breadcrumb-item>
    <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
  </el-breadcrumb>

    <!--下拉菜单，显示头像-->
  <el-dropdown>
    <span class="el-dropdown-link">
      <el-avatar :size="30" :src="userInfo.profile" />
      <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item @click="show">{{ userInfo.username }}</el-dropdown-item>

        <el-dropdown-item divided @click="logout">退出</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>




 </el-header>
</template>

<style scoped>
.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
.el-header {
  display: flex;
  align-items: center;
  background-color: #eee;
}
  .el-icon {
    margin-right: 17px;
  }

  .el-dropdown {
    margin-left: auto;
  }

</style>
