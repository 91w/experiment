<!--
 * @Descripttion: 首页组件
 * @Author: congz
 * @Date: 2020-06-04 11:22:40
 * @LastEditors: congz
 * @LastEditTime: 2020-08-12 21:20:08
--> 

<template>
  <div class="home" id="home" name="home">
    <!-- 轮播图 -->
    <div class="block">
      <el-carousel height="460px" indicator-position="outside">
        <el-carousel-item v-for="item in carousels" :key="item.commid">
          <div style="display: flex; height: 100%;">
            <!-- 文本区域 -->
            <div style="flex: 1; background-color: #FFF; padding: 20px; display: flex; flex-direction: column; justify-content: center;">
              <h2>{{ item.title }}</h2>
              <router-link :to="{ path: '/goods/details', query: {productID:item.commid} }">
                <el-button type="primary" style="margin-top: 40px;">购买</el-button>
              </router-link>
            </div>
            <!-- 图片区域 -->
            <div style="flex: 2;">
              <img :src="`/api/carousels/${item.image}`" style="height: 100%; width: 100%; object-fit: contain;">
              <p>{{item.image}}</p>
            </div>
          </div>
        </el-carousel-item>
      </el-carousel>
    </div>
    <!-- 轮播图END -->

    <div class="main-box">
      <div class="main">
        <!-- 手机商品展示区域 -->
        <div class="phone">
          <div class="box-hd">
            <div class="title">苹果</div>
          </div>
          <div class="box-bd">
            <div class="promo-list">
              <router-link :to="{ path: '/goods/details', query: {productID:1} }">
                <img :src="'https://cdn.acwing.com/media/user/profile/photo/173183_lg_52f4c2c9ee.jpg'" />
              </router-link>
              <!-- 一个推荐的手机 -->
            </div>
            <!-- 下面是列举的几个热门手机 -->
            <div class="list">
              <MyList :list="phonesList" :isMore="true"></MyList>
            </div>
          </div>
        </div>
        <!-- 手机商品展示区域END -->

        <!-- 家电商品展示区域 -->
        <div class="appliance" id="promo-menu">
          <div class="box-hd">
            <div class="title">安卓</div>
            <div class="more" id="more">
              <MyMenu :val="2" @fromChild="getChildMsg">
                <span slot="1">热门</span>
                <span slot="2">随便看看</span>
              </MyMenu>
            </div>
          </div>
          <div class="box-bd">
            <div class="promo-list">
              <ul>
                <router-link :to="{ path: '/goods/details', query: {productID:21} }">
                  <li>
                    <img
                      :src="'https://cdn.acwing.com/media/user/profile/photo/173183_lg_52f4c2c9ee.jpg'"
                    />
                  </li>
                </router-link>
                <router-link :to="{ path: '/goods/details', query: {productID:22} }">
                  <li>
                    <img
                      :src="'https://cdn.acwing.com/media/user/profile/photo/173183_lg_52f4c2c9ee.jpg'"
                    />
                  </li>
                </router-link>
              </ul>
            </div>
            <div class="list">
              <MyList :list="applianceList" :isMore="true"></MyList>
            </div>
          </div>
        </div>
        <!-- 家电商品展示区域END -->

        <!-- 配件商品展示区域 -->
        <div class="accessory" id="promo-menu">
          <div class="box-hd">
            <div class="title">平板</div>
            <div class="more" id="more">
              <MyMenu :val="3" @fromChild="getChildMsg2">
                <span slot="1">热门</span>
                <span slot="2">Ipad</span>
                <span slot="3">安卓</span>
              </MyMenu>
            </div>
          </div>
          <div class="box-bd">
            <div class="promo-list">
              <ul>
                <router-link :to="{ path: '/goods/details', query: {productID:30} }">
                  <li>
                    <img
                      :src="'https://cdn.acwing.com/media/user/profile/photo/173183_lg_52f4c2c9ee.jpg'"
                      alt
                    />
                  </li>
                </router-link>
                <router-link :to="{ path: '/goods/details', query: {productID:32} }">
                  <li>
                    <img
                      :src="'https://cdn.acwing.com/media/user/profile/photo/173183_lg_52f4c2c9ee.jpg'"
                      alt
                    />
                  </li>
                </router-link>
              </ul>
            </div>
            <div class="list">
              <MyList :list="accessoryList" :isMore="true"></MyList>
            </div>
          </div>
        </div>
        <!-- 配件商品展示区域END -->
      </div>
    </div>
  </div>
</template>
<script>
import * as carouselsAPI from '@/api/carousels/'
import * as productsAPI from '@/api/products/'
import * as rankingAPI from '@/api/ranking/'

export default {
  data() {
    return {
      carousels: [], // 轮播图数据
      iphonesList: '', // 苹果手机商品列表
      // anPhonesList: '', // 安卓手机列表
      applianceList: '', // 安卓手机商品列表
      applianceHotList: '', // 安卓手机热门商品列表

      accessoryList: '', // 平板商品列表
      accessoryHotList: '', // 热门平板商品列表
      phoneShellsList: '', // 安卓平板商品列表
      chargersList: '', // ipad商品列表
      applianceActive: 1, // 安卓当前选中的商品分类
      accessoryActive: 1, // 平板当前选中的商品分类
      start: 0,
      limit: 7
    }
  },
  watch: {
    // 家电当前选中的商品分类，响应不同的商品数据
    applianceActive: function(val) {
      // 页面初始化的时候把applianceHotList(热门家电商品列表)直接赋值给applianceList(家电商品列表)
      // 所以在切换商品列表时判断applianceHotList是否为空,为空则是第一次切换,把applianceList赋值给applianceHotList
      if (this.applianceHotList == '') {
        this.applianceHotList = this.applianceList
      }
      if (val == 1) {
        // 1为热门商品
        this.applianceList = this.applianceHotList
        return
      }
      if (val == 2) {
        // 2为电视商品
        this.applianceList = this.televisionsList
        return
      }
    },
    accessoryActive: function(val) {
      // 页面初始化的时候把accessoryHotList(热门配件商品列表)直接赋值给accessoryList(配件商品列表)
      // 所以在切换商品列表时判断accessoryHotList是否为空,为空则是第一次切换,把accessoryList赋值给accessoryHotList
      if (this.accessoryHotList == '') {
        this.accessoryHotList = this.accessoryList
      }
      if (val == 1) {
        // 1为热门商品
        this.accessoryList = this.accessoryHotList
        return
      }
      if (val == 2) {
        // 2为保护套商品
        this.accessoryList = this.phoneShellsList
        return
      }
      if (val == 3) {
        //3 为充电器商品
        this.accessoryList = this.chargersList
        return
      }
    }
  },
  activated() {
    // 获取首页数据
    this.load()
  },
  methods: {
    load() {
      //获取热门家电
      rankingAPI
        .listPadRanking()
        .then(res => {
          if (res.status === 200) {
            this.applianceHotList = res.data
            this.applianceList = res.data
          } else {
            this.notifyError('获取热门平板失败')
          }
        })
        .catch(err => {
          this.notifyError('获取热门平板失败', err)
        })
      //获取热门配件
      rankingAPI
        .listAndroidRanking()
        .then(res => {
          if (res.status === 200) {
            this.accessoryHotList = res.data
            this.accessoryList = res.data
          } else {
            this.notifyError('获取热门安卓手机失败')
          }
        })
        .catch(err => {
          this.notifyError('获取热门安卓手机失败', err)
        })
      //获取手机列表
      this.getProduct(1, 'phonesList')
      //获取电视列表
      this.getProduct(2, 'televisionsList')
      //获取保护套列表
      this.getProduct(5, 'phoneShellsList')
      //获取充电器列表
      this.getProduct(7, 'chargersList')
      
      carouselsAPI
        .listCarousels()
        .then(res => {
          console.log(res)
          if (res.msg == '成功') {
            console.log("1:",res.data)
            let itemsArray = Object.values(res.data)
            this.carousels = itemsArray
            console.log("2:",this.carousels)
          } else {
            this.notifyError('获取轮播图失败', res.msg)
          }
        })
        .catch(err => {
          this.notifyError('获取轮播图失败', err)
        })
    },
    // 获取家电模块子组件传过来的数据
    getChildMsg(val) {
      this.applianceActive = val
    },
    // 获取配件模块子组件传过来的数据
    getChildMsg2(val) {
      this.accessoryActive = val
    },
    // 获取各类商品数据方法封装
    getProduct(categoryID, val) {
      productsAPI
        .listProducts(categoryID, this.start, this.limit)
        .then(res => {
          if (res.status === 200) {
            this[val] = res.data.items
          } else {
            this.notifyError('获取失败', res.msg)
          }
        })
        .catch(err => {
          this.notifyError('获取失败', err)
        })
    }
  },
  beforeRouteEnter(to, from, next) {
    // 添加背景色
    document
      .querySelector('body')
      .setAttribute('style', 'background-color:#ffffff')
    next()
  },
  beforeRouteLeave(to, from, next) {
    // 去除背景色
    document
      .querySelector('body')
      .setAttribute('style', 'background-color:#f5f5f5')
    next()
  }
}
</script>
<style scoped>
@import '../assets/css/index.css';
</style>