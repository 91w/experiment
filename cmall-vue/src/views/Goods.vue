<!--
 * @Descripttion: 全部商品页面组件
 * @Author: congz
 * @Date: 2020-06-04 11:22:40
 * @LastEditors: congz
 * @LastEditTime: 2020-08-12 20:56:01
--> 

<template>
  <div class="goods" id="goods" name="goods">
    <!-- 面包屑 -->
    <div class="breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item to="/">首页</el-breadcrumb-item>
        <el-breadcrumb-item>全部商品</el-breadcrumb-item>
        <el-breadcrumb-item v-if="search">搜索</el-breadcrumb-item>
        <el-breadcrumb-item v-else>分类</el-breadcrumb-item>
        <el-breadcrumb-item v-if="search">{{search}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <!-- 面包屑END -->

    <!-- 分类标签 -->
    <div class="nav">
      <div class="product-nav">
        <div class="title">分类</div>
        <el-tabs v-model="activeName" type="card">
          <el-tab-pane
            v-for="(item, index) in categoryList"
            :key="index"
            :label="item"
            :name=item
          />
        </el-tabs>
      </div>
    </div>
    <!-- 分类标签END -->

    <!-- 主要内容区 -->
    <div class="main">
      <div class="list">
        <MyList :list="product" v-if="product.length>0"></MyList>
        <div v-else class="none-product">抱歉没有找到相关的商品，请看看其他的商品</div>
      </div>
      <!-- 分页 -->
      <div class="pagination">
        <el-pagination
          background
          @current-change="handleCurrentChange"
          :page-size="pageSize"
          layout="total, prev, pager, next, jumper"
          :total="total/15"
        ></el-pagination>
      </div>
      <!-- 分页END -->
    </div>
    <!-- 主要内容区END -->
  </div>
</template>
<script>
import * as productAPI from '@/api/products'
import * as categoryAPI from '@/api/categories'
import axios from 'axios'

export default {
  data() {
    return {
      categoryList: '', //分类列表，用于显示
      category: '', // 分类

      product: '', // 商品列表

      productList: '',

      total: 0, // 商品总量
      pageSize: 15, // 每页显示的商品数量

      currentPage: 1, //当前页码

      activeName: '-1', // 分类列表当前没有选中的分类名称
      search: '', // 搜索条件
      start: 0,
      limit: 15
    }
  },
  created() {
    // 获取分类列表
    this.getCategory()
  },
  activated() {
    this.activeName = '-1' // 初始化分类列表没有选择

    this.total = 0 // 初始化商品总量为0
    this.currentPage = 1 //初始化当前页码为1
    // 如果路由没有传递参数，默认为显示全部商品
    if (Object.keys(this.$route.query).length == 0) {
      this.category = '全部'
      this.activeName = '全部'
      return
    }
    // 如果路由传递了category，则显示对应的分类商品
    if (this.$route.query.category != undefined) {
      this.category = this.$route.query.category
      return
    }
    // 如果路由传递了search，则为搜索，显示对应的分类商品
    if (this.$route.query.search != undefined) {
      this.search = this.$route.query.search
    }
  },
  watch: {
    // 监听点击了哪个分类标签，通过修改分类id，响应相应的商品
    activeName: function(val) {
      if (val === '' || val == undefined || val == null) {
        this.category = '全部'
      }
      if (val != '') {
        this.category = val
      }
      // 初始化商品总量和当前页码
      this.total = 0
      this.currentPage = 1
      // 更新地址栏链接，方便刷新页面可以回到原来的页面
      this.$router.push({
        path: '/goods',
        query: { category: this.category }
      })
    },
    // 监听搜索条件，响应相应的商品
    search: function(val) {
      if (val != '') {
        this.getProductBySearch(val)
      }
    },
    // 监听分类id，响应相应的商品
    category: function() {
      this.getData()
      this.search = ''
    },
    // 监听路由变化，更新路由传递了搜索条件
    $route: function(val) {
      if (val.path == '/goods') {
        if (val.query.search != undefined) {
          this.activeName = '-1'
          this.currentPage = 1
          this.total = 0
          this.search = val.query.search
        }
      }
    }
  },
  methods: {
    // 返回顶部
    backtop() {
      const timer = setInterval(function() {
        const top =
          document.documentElement.scrollTop || document.body.scrollTop
        const speed = Math.floor(-top / 5)
        document.documentElement.scrollTop = document.body.scrollTop =
          top + speed

        if (top === 0) {
          clearInterval(timer)
        }
      }, 20)
    },
    handleCurrentChange(val) {
      this.start = this.limit * (val - 1) // val 页面
      this.getData()
    },
    // 向后端请求分类列表数据
    getCategory() {
      categoryAPI
        .listCategories()
        .then(res => {
            const val = '全部'
            const cate = res.data
            cate.unshift(val)
            this.categoryList = res.data
        })
        .catch(err => {
          this.notifyError('获取分类失败', err)
        })
    },
    // 向后端请求全部商品或分类商品数据
    getData() {
      // 如果分类列表为空则请求全部商品数据，否则请求分类商品数据
      if (this.category === null || this.category === '' || this.category === undefined) {
        productAPI
          .listProducts('全部', this.start, this.limit)
          .then(res => {
            if (res.msg === '成功') {
              this.product = res.data
              this.total = res.num
            } else {
              this.notifyError('获取商品失败', res.msg)
            }
          })
          .catch(err => {
            this.notifyError('获取商品失败', err)
          })
      } else {
        let type = this.category
        productAPI
          .listProducts(type, this.start, this.limit)
          .then(res => {
            if (res.msg === '成功') {
              this.product = res.data
              this.total = res.num
            } else {
              this.notifyError('获取分类商品失败', res.msg)
            }
          })
          .catch(err => {
            this.notifyError('获取分类商品失败', err)
          })
      }
    },
    // 通过搜索条件向后端请求商品数据
    getProductBySearch() {
      var form = {
        search: this.search
      }
      productAPI
        .searchProducts(form)
        .then(res => {
            this.product = res.data
        })
        .catch(err => {
          this.notifyError('搜索失败2', err)
        })
    }
  }
}
</script>

<style scoped>
.goods {
  background-color: #f2f2f2;
}
/* 面包屑CSS */
.el-tabs--card .el-tabs__header {
  border-bottom: none;
}
.goods .breadcrumb {
  height: 50px;
  background-color: #f2f2f2;
}
.goods .breadcrumb .el-breadcrumb {
  width: 1225px;
  line-height: 30px;
  font-size: 16px;
  margin: 0 auto;
}
/* 面包屑CSS END */

/* 分类标签CSS */
.goods .nav {
  background-color: #f2f2f2;
}
.goods .nav .product-nav {
  width: 1225px;
  height: 40px;
  line-height: 40px;
  margin: 0 auto;
}
.nav .product-nav .title {
  width: 50px;
  font-size: 16px;
  font-weight: 700;
  float: left;
}
/* 分类标签CSS END */

/* 主要内容区CSS */
.goods .main {
  margin: 0 auto;
  max-width: 1225px;
}
.goods .main .list {
  min-height: 650px;
  padding-top: 14.5px;
  margin-left: -13.7px;
  overflow: auto;
}
.goods .main .pagination {
  height: 50px;
  text-align: center;
}
.goods .main .none-product {
  color: #333;
  margin-left: 13.7px;
}
/* 主要内容区CSS END */
</style>