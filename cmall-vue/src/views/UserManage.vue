<template>
    <NaveMenu>
        <el-table
        :data="tableData"
        border
        style="width: 100%">
        <el-table-column
        prop="id"
        label="编号"
        width="120">
        </el-table-column>
        <el-table-column
        prop="username"
        label="用户名"
        width="120">
        </el-table-column>
        <el-table-column
        prop="userstatus"
        label="状态"
        width="120">
        </el-table-column>
        <el-table-column
        prop="email"
        label="邮箱"
        width="120">
        </el-table-column>
        <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="accept(scope.$index, tableData)" type="text" size="small">一键下线</el-button>
          <el-button @click="reject(scope.$index, tableData)" type="text" size="small">删除用户</el-button>
        </template>
      </el-table-column>
    </el-table>
    </NaveMenu>

</template>
<script>
import NaveMenu from '../components/NaveMenu'
import * as adminAPI from '@/api/admin/'
 export default {
    data() {
      return {
        tableData: []
      }
    },
    methods: {
       deleteRow(index, rows) {
        adminAPI.deleteser(rows[index].id)
        .then(res => {
            this.tableData = res.data
        })
        .catch(err => {
          this.notifyError('获取数据失败', err)
        })
        rows.splice(index, 1);
      },
      accept(index, rows) {
        adminAPI.kickUser(rows[index].id)
        .then(res => {
            this.tableData = res.data
        })
        .catch(err => {
          this.notifyError('获取数据失败', err)
        })
        rows.splice(index, 1); 
      }
    },
    activated() {
        adminAPI.getUser()
        .then(res => {
            this.tableData = res.data
        })
        .catch(err => {
          this.notifyError('获取数据失败', err)
        })
    },
    components: {
        NaveMenu
  }
  }
</script>