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
        <!-- 苹果手机商品展示区域 -->
        <div class="phone">
          <div class="box-hd">
            <div class="title">苹果</div>
          </div>
          <div class="box-bd">
            <div class="promo-list">
            <ul>
              <router-link :to="{ path: '/goods/details', query: {productID:33} }">
                <li>
                  <img :src="'data:image/jpeg;base64,' + showList[0] "/>
                </li>
              </router-link>
              <router-link :to="{ path: '/goods/details', query: {productID:38} }">
                <li>
                  <img :src="'data:image/jpeg;base64,' + showList[1] "/>
                </li>
              </router-link>
            </ul>
            </div>
            <!-- 下面是列举的几个热门手机 -->
            <div class="list">
              <MyList :list="iphonesList" :isMore="true"></MyList>
            </div>
          </div>
        </div>
        <!-- 苹果手机商品展示区域END -->

        <!-- 安卓商品展示区域 -->
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
                <router-link :to="{ path: '/goods/details', query: {productID:40} }">
                  <li>
                    <img :src="'data:image/jpeg;base64,' + showList[2] "/>
                  </li>
                </router-link>
                <router-link :to="{ path: '/goods/details', query: {productID:41} }">
                  <li>
                    <img :src="'data:image/jpeg;base64,' + showList[3] "/>
                  </li>
                </router-link>
              </ul>
            </div>
            <div class="list">
              <MyList :list="androidList" :isMore="true"></MyList>
            </div>
          </div>
        </div>
        <!-- 安卓手机展示区域END -->

        <!-- 平板展示区域 -->
        <div class="accessory" id="promo-menu">
          <div class="box-hd">
            <div class="title">平板</div>
            <div class="more" id="more">
              <MyMenu :val="3" @fromChild="getChildMsg2">
                <span slot="1">热门</span>
                <span slot="2">安卓</span>
                <span slot="3">Ipad</span>
              </MyMenu>
            </div>
          </div>
          <div class="box-bd">
            <div class="promo-list">
              <ul>
                <router-link :to="{ path: '/goods/details', query: {productID:44} }">
                  <li>
                    <img :src="'data:image/jpeg;base64,' + showList[4] "/>
                  </li>
                </router-link>
                <router-link :to="{ path: '/goods/details', query: {productID:45} }">
                  <li>
                    <img :src="'data:image/jpeg;base64,' + showList[5] "/>
                  </li>
                </router-link>
              </ul>
            </div>
            <div class="list">
              <MyList :list="padList" :isMore="true"></MyList>
            </div>
          </div>
        </div>
        <!-- 平板展示区域END -->
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

      androidList: '', // 安卓展示列表
      androidHotList: '', // 安卓手机热门商品列表
      androidPhoneList: '', // 安卓手机商品列表

      padList: '', // 平板商品展示列表
      padHotList: '', // 热门平板商品列表
      padAndroidList: '', // 安卓平板商品列表
      ipadList: '', // ipad商品列表

      androidActive: 1, // 安卓当前选中的商品分类
      padActive: 1, // 平板当前选中的商品分类
      start: 0,
      limit: 7,
      showList: ''
    }
  },
  watch: {
    // 家电当前选中的商品分类，响应不同的商品数据
    androidActive: function(val) {
      // 页面初始化的时候把androidHotList(热门安卓商品列表)直接赋值给androidList(安卓商品列表)
      // 所以在切换商品列表时判断applianceHotList是否为空,为空则是第一次切换,把applianceList赋值给applianceHotList

      if (this.androidHotList == '') {
        this.androidHotList = this.androidList
      }
      if (val == 1) {
        // 1为热门商品
        this.androidList = this.androidHotList
        return
      }
      if (val == 2) {
        // 2为安卓商品
        this.androidList = this.androidPhoneList  
        return
      }
    },
    padActive: function(val) {

      // 页面初始化的时候把accessoryHotList(热门配件商品列表)直接赋值给accessoryList(配件商品列表)
      // 所以在切换商品列表时判断accessoryHotList是否为空,为空则是第一次切换,把accessoryList赋值给accessoryHotList
      if (this.padHotList == '') {
        this.padHotList = this.padList
      }
      if (val == 1) {
        // 1为热门商品
        this.padList = this.padHotList
        return
      }
      if (val == 2) {
        // 2为安卓平板商品
        this.padList = this.padAndroidList
        return
      }
      if (val == 3) {
        //3 为ipad商品
        this.padList = this.ipadList
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
      productsAPI
        .showImageList()
        .then(res => {
            this.showList = res.data
        })
        .catch(err => {
          this.notifyError('获取热门平板失败', err)
        })
      //获取热门平板
      rankingAPI
        .listPadRanking()
        .then(res => {
          if (res.msg === '成功') {
            this.padHotList = res.data
            this.padList = res.data
          } else {
            this.notifyError('获取热门平板失败')
          }
        })
        .catch(err => {
          this.notifyError('获取热门平板失败', err)
        })
      //获取热门安卓
      rankingAPI
        .listAndroidRanking()
        .then(res => {
          if (res.msg === '成功') {
            this.androidHotList = res.data
            this.androidList = res.data
          } else {
            this.notifyError('获取热门安卓手机失败')
          }
        })
        .catch(err => {
          this.notifyError('获取热门安卓手机失败', err)
        })
      //获取苹果手机列表
      this.getProduct('苹果', 'iphonesList')
      //获取android手机
      this.getProduct('安卓', 'androidPhoneList')
      //获取安卓平板列表
      this.getProduct('安卓平板', 'padAndroidList')
      //获取ipad列表
      this.getProduct('苹果平板', 'ipadList')
      
      carouselsAPI
        .listCarousels()
        .then(res => {
          if (res.msg == '成功') {
            let itemsArray = Object.values(res.data)
            this.carousels = itemsArray
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
      this.androidActive = val
    },
    // 获取配件模块子组件传过来的数据
    getChildMsg2(val) {
      this.padActive = val
    },
    // 获取各类商品数据方法封装
    getProduct(category, val) {
      productsAPI
        .listProducts(category, this.start, this.limit)
        .then(res => { 
          if (res.msg === '成功') {
            // this[val] = res.data.items
            this[val] = res.data
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