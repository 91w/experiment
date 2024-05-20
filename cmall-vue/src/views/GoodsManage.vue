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
        prop="type"
        label="类型"
        width="120">
        </el-table-column>
        <el-table-column
        prop="used"
        label="使用情况"
        width="120">
        </el-table-column>
        <el-table-column
        prop="storage"
        label="存储"
        width="120">
        </el-table-column>
        <el-table-column
        prop="channel"
        label="渠道"
        width="120">
        </el-table-column>
        <el-table-column
        prop="official"
        label="官保"
        width="180">
        </el-table-column>
        <el-table-column
        prop="battery"
        label="电池"
        width="120">
        </el-table-column>
        <el-table-column
        prop="screen"
        label="屏幕"
        width="120">
        </el-table-column>
        <el-table-column
        prop="frame"
        label="边框"
        width="120">
        </el-table-column>
        <el-table-column
        prop="accessories"
        label="配件"
        width="120">
        </el-table-column>
        <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="accept(scope.$index, tableData)" type="text" size="small">允许回收</el-button>
          <el-button @click="reject(scope.$index, tableData)" type="text" size="small">拒绝回收</el-button>
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
        adminAPI.deleteGood(rows[index].id)
        .then(res => {
            this.tableData = res.data
        })
        .catch(err => {
          this.notifyError('获取数据失败', err)
        })
        rows.splice(index, 1);
      },
      accept(index, rows) {
        adminAPI.acceptGood(rows[index].id)
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
        adminAPI.getGoods()
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