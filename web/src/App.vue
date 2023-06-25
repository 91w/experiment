<script setup lang="ts">
import {RouterView}  from "vue-router";
import Applayout from "./components/layouts/Applayout.vue";
import request from "./api/request";

request({
  method: 'get',
  url: '/api/account/info',
  // data: {
  //   username: 'wgh',
  //   password: '123456',
  //   // confirm_password: '123456',
  // }
  headers: {
    Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3Z2giLCJleHAiOjE2ODc2MDY1NTQsImlhdCI6MTY4NzYwMjk1NH0.B0GZkHgHObrSzpQBGkU_DDjvE0CjOz_6QY1u8fkt-lA",
  }
}).then((res) => {
  console.log(res)
})




</script>


<template>
    

  <RouterView/> 
</template>

<style>
#app {
  text-align: center;
  color: var(--ep-text-color-primary);
}

.main-container {
  height: calc(100vh - var(--ep-menu-item-height) - 3px);
}
</style>
