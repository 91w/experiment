<script lang="ts" setup>
import { is_collapse } from "./IsCollapse.vue";
import {
  Expand,
  Fold,
} from "@element-plus/icons-vue";
import { ArrowDown } from '@element-plus/icons-vue'

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
      <el-avatar :size="30" :src="'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" />
      <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item>用户信息</el-dropdown-item>

        <el-dropdown-item divided>退出</el-dropdown-item>
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
