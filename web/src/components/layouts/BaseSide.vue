<template>
      <el-aside >
        <el-scrollbar>
          <el-menu  router unique-opened :collapse="is_collapse"> <!-- 实现router，添加index标签，点击跳转指定页面-->
            <a href="/" class="logo">
              <img src="../../assets/logo.svg" alt=""/>
              <h1>Library</h1>
            </a>
            <el-sub-menu index="1">
              <template #title>
                <el-icon><Compass /></el-icon> <span>文件管理</span>
              </template>
                <el-menu-item index="/doc/up/"><span>上传</span></el-menu-item>
                <el-menu-item index="/doc/down/"><span>下载</span></el-menu-item> 
              <el-sub-menu index="1-4">
                <template #title>其他</template>
                <el-menu-item index="/doc/callme/"><span>联系我</span></el-menu-item>
              </el-sub-menu>
            </el-sub-menu>


            <el-sub-menu index="2">
              <template #title>
                <el-icon><icon-menu /></el-icon><span>权限管理</span>
              </template>
              <el-menu-item-group>
                <template #title>Group 1</template>
                <el-menu-item index="/auth/none"><span>暂无</span></el-menu-item>
                <el-menu-item index="/auth/other"><span>其他</span></el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="Group 2">
                <el-menu-item index="/user"><span>order1</span></el-menu-item>
              </el-menu-item-group>
              <el-sub-menu index="2-4">
                <template #title>无</template>
                <el-menu-item index="file"><span>oder2</span></el-menu-item>
              </el-sub-menu>
            </el-sub-menu>

            <el-sub-menu index="3">
              <template #title>
                <el-icon><setting /></el-icon><span>Navigator Three</span>
              </template>
              <el-menu-item-group>
                <template #title>Group 1</template>
                <el-menu-item index="/o/1"><span>1</span></el-menu-item>
                <el-menu-item index="/o/2"><span>2</span></el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="Group 2">
                <el-menu-item index="/o/3"><span>3</span></el-menu-item>
              </el-menu-item-group>
              <el-sub-menu index="3-4">
                <template #title>Option 4</template>
                <el-menu-item index="/o/4"><span>4</span></el-menu-item>
              </el-sub-menu>
            </el-sub-menu>

          </el-menu>

        </el-scrollbar>
      </el-aside>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import {
  Menu as IconMenu,
  Setting,
  Compass
} from "@element-plus/icons-vue";
import { is_collapse } from "./IsCollapse.vue";
</script>

<style lang="scss" scoped>
.el-aside {
  background-color: #FFFAF0;
  height: 100vh;
  width:auto;
}
.el-menu {
  background-color: #FFFAF0;
  border-right: none;
  width: 200px;
  &.el-menu--collapse {
    width: 60px;
    & h1 {
      display: none;
    }
  }
}
.logo {
  display: flex;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: black;
  height: 60px;
  img {
    width: 32px;
    height: 32px;
  }
}
</style>
