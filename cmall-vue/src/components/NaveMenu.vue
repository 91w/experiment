<template>
    <el-container>
        <el-header>二手手机管理系统</el-header>
        <el-container>
          <el-aside width="300px">
            <el-row class="tac">
                  <el-menu
                    default-active="activeIndex"
                    class="el-menu-vertical-demo"
                    router>
                    <el-menu-item index="/admin/user">
                      <i class="el-icon-menu"></i>
                      <span slot="title" >用户管理</span>
                    </el-menu-item>
                    <el-menu-item index="/admin/goods">
                      <i class="el-icon-setting"></i>
                      <span slot="title"  >设备管理</span>
                    </el-menu-item>
                  </el-menu>
              </el-row>
          </el-aside>
          <el-main>
            <slot></slot>
          </el-main>
        </el-container>
      </el-container>
</template>
<script>
export default {
  name: 'NaveMenu',
  beforeUpdate() {
    this.activeIndex = this.$route.path
  },
  data() {
    return {
      activeIndex: ''
    }
  },
  methods: {}
}
</script>
<style scoped>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  

  
 .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
    width: 1000px;
  } 
  
  body > .el-container {
    margin-bottom: 40px;
  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
</style>
