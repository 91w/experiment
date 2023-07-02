<script setup lang="ts">
import BaseSide from './BaseSide.vue';
import BaseHeader from './BaseHeader.vue';
import {RouterView}  from "vue-router";
</script>


<template>
  <!--主界面-->
    <div class="common-layout">
      <el-container>
        <BaseSide></BaseSide>
        <el-container class="header-and-main">
          <BaseHeader></BaseHeader>
          <el-main>
            <el-scrollbar>
                <RouterView/>
            </el-scrollbar> 
        </el-main>
        </el-container>
      </el-container>
    </div>
  </template>

<style lang="scss" scoped>
.header-and-main {
    flex-direction: column;
    height: 100vh;
}
</style>
  
  