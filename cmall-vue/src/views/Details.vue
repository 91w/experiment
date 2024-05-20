<!--
 * @Descripttion: 商品详情
 * @Author: congz
 * @Date: 2020-06-04 11:22:40
 * @LastEditors: congz
 * @LastEditTime: 2020-10-28 12:05:31
-->

<template>
  <div id="details" v-if="productDetails">
    <!-- 头部 -->
    <div class="page-header">
      <div class="title">
        <p>{{ productDetails.name }}</p>
        <div class="list"> 
          <!-- <div class="select">
            <el-button type="text" class="list-select" @click="goParam">参数</el-button>
          </div> -->
        </div>
      </div>
    </div>
    <!-- 头部END -->

    <!-- 主要内容 -->
    <div class="details-main">
      <!-- 左侧商品轮播图 -->
      <div class="details-block">
        <el-carousel height="560px" v-if="productPictures.length > 1">
          <el-carousel-item v-for="item in productPictures" :key="item.id">
            <img style="height: 560px;" :src="'data:image/jpeg;base64,' + productPictures[item.id].img" />
          </el-carousel-item>
        </el-carousel>
        <div v-if="productPictures.length == 1">
          <img style="height: 560px;" :src="productPictures[0].img_path" />
        </div>
      </div>
      <!-- 左侧商品轮播图END -->

      <!-- 右侧内容区 -->
      <div class="details-content">
        <h1 class="name">{{ productDetails.commname }}</h1>
        <!-- <li class="view">
          <i class="el-icon-view"></i>
          {{ productDetails.view }}
        </li> -->
        <p class="intro">{{ productDetails.commdesc }}</p>
        <p class="store">{{productDetails.common}}</p>
        <div class="price">
          <span>{{ productDetails.thinkmoney }}元</span>
          <span
            v-show="productDetails.thinkmoney != productDetails.orimoney"
            class="del"
          >{{ productDetails.orimoney }}元</span>
        </div>
        <div class="pro-list">
          <span class="pro-name">{{ productDetails.commname}}</span>
          <span class="pro-price">
            <span>{{ productDetails.thinkmoney }}元</span>
            <span
              v-show="productDetails.orimoney != productDetails.thinkmoney"
              class="pro-del"
            >{{ productDetails.orimoney }}元</span>
          </span>
          <p class="price-sum">总计 : {{ productDetails.thinkmoney }}元</p>
        </div>
        <!-- 内容区底部按钮 -->
        <div class="button">
          <el-button class="shop-cart" :disabled="dis" @click="addShoppingCart">加入购物车</el-button>
          <el-button class="like" @click="addFavorite">收藏</el-button>
        </div>
        <!-- 内容区底部按钮END -->
        <div class="pro-policy">
          <ul>
            <li>
              <i class="el-icon-circle-check"></i>
              七天无理由退货
            </li>
            <li>
              <i class="el-icon-circle-check"></i>
              急速退换货
            </li>
            <li>
              <i class="el-icon-circle-check"></i>
              顺丰发货
            </li>
            <li>
              <i class="el-icon-circle-check"></i>
              7天价格保护
            </li>
          </ul>
        </div>
      </div>
      <!-- 右侧内容区END -->
    </div>

    <!-- 主要内容END -->
    <div class="product-select" id="product-select">
      <el-form :model="form" status-icon  ref="form">
        <el-form-item label="留下你的评价">
          <el-input v-model="form.content" type="textarea" />
        </el-form-item>
      </el-form>
      <a href="javascript:;" class="btn-gradient blue block" @click="comment('form')">评论</a>
    </div>  
      <div  class="comment" v-for="item in comments" :key="item.id" :data="item" style="width: 100%">
          <img style="width: 30px;
          height: 30px;
          border-radius: 50%; 
          object-fit: cover; " class="user_img" :src="'data:image/jpeg;base64,' + item.userimage">
          <div class="meta">
            <strong>{{ item.username}}</strong> - <span>{{item.createtime}}</span>
          </div>
        <div class="content">
            {{item.content}}
        </div>
      </div>


  </div>
  <div class="not-found" v-else>查询不到该商品</div>
</template>
<script>
import { mapActions } from 'vuex'
import * as productsAPI from '@/api/products/'
import * as commentAPI from '@/api/comment/'
import * as favoritesAPI from '@/api/favorites/'
import * as cartsAPI from '@/api/carts/'
export default {
  data() {
    return {
      dis: false, // 控制“加入购物车按钮是否可用”
      productID: 0, // 商品id
      productDetails: '', // 商品详细信息
      productPictures: '', // 商品图片
      select: 0,
      comments: [],
      form: {
        id: this.$route.query.productID,
        content: '',
        // user_id: this.$store.state.user.id
        user_id: JSON.parse(localStorage.getItem("user")).id
      }
    }
  },
  // 通过路由获取商品id
  activated() {
    if (this.$route.query.productID != undefined) {
      this.productID = this.$route.query.productID
    }
  },
  watch: {
    // 监听商品id的变化，请求后端获取商品数据
    productID: function() {
      this.load()
      //this.getDetailsPicture(val);
    }
  },
  methods: {
    ...mapActions(['unshiftShoppingCart', 'addShoppingCartNum']),
    // 获取商品详细信息
    load() {
      productsAPI.showProduct(this.productID).then(res => {
        
        this.productDetails = res.data
      })
      productsAPI.showPictures(this.productID).then(res => {
        
        this.productPictures = res
        
      })
      commentAPI.showComment(this.productID).then(res => {
        
        this.comments = res.data
      })
    },
    goInfo() {
      this.showInfoImgs()
      document.getElementById('product-select').scrollIntoView()
    },
    goParam() {
      this.showParamImgs()
      document.getElementById('product-select').scrollIntoView()
    },

    comment(formName) {
          commentAPI
            .addComment(this.form)
            .then(res => {
              
              if (res.msg == "成功") {
                
              } else {
                this.notifyError('评论失败', res.msg)
              }
            })
            .catch(error => {
              this.notifyError('评论失败', error)
            })
    },
    // 加入购物车
    addShoppingCart() {
      // 判断是否登录,没有登录则显示登录组件
      if (!this.$store.getters.getUser) {
        this.$router.push({ name: 'Login' })
        return
      }
      var form = {
        user_id: JSON.parse(localStorage.getItem("user")).id,
        product_id: Number(this.productID)
      }
      cartsAPI
        .postCart(form)
        .then(res => {
          console.log(res)
          if(res.status === '成功') {
              //新加入购物车成功
              this.unshiftShoppingCart(res.data)
              this.notifySucceed('添加购物车成功')
          } else if(res.status === '失败') {
              this.notifyErro(res.msg)
          }
        })
        .catch(err => {
          this.notifyError('商品已经在购物车了，看看其他的吧')
        })
    },
    addFavorite() {
      // 判断是否登录,没有登录则显示登录组件
      if (!this.$store.getters.getUser) {
        this.$router.push({ name: 'Login' })
        return
      }
      var form = {
        user_id: JSON.parse(localStorage.getItem('user')).id,
        product_id: Number(this.productID)
      }
      favoritesAPI
        .postFavorite(form)
        .then(res => {
          if (res.msg === '成功') {
            this.notifySucceed('添加收藏夹成功')
          } 
           else {
            this.notifyError('添加收藏夹失败', res.msg)
          }
        })
        .catch(err => {
          this.notifyError('添加收藏夹失败', err)
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
/* 头部CSS */
#details .page-header {
  height: 64px;
  margin-top: -20px;
  z-index: 4;
  background: #ffffff;
  border-bottom: 1px solid #e0e0e0;
  -webkit-box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.07);
  box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.07);
}
#details .page-header .title {
  width: 1225px;
  height: 64px;
  line-height: 64px;
  font-size: 18px;
  font-weight: 400;
  color: #212121;
  margin: 0 auto;
}
#details .page-header .title p {
  float: left;
}
#details .page-header .title .list {
  height: 64px;
  float: right;
  display: flex;
  align-items: center;
}
#details .page-header .title .list .select {
  float: left;
  margin-left: 20px;
}

#details .page-header .title .list .select .list-select {
  font-size: 14px;
  color: #616161;
}
#details .page-header .title .list .select .list-select:hover {
  font-size: 14px;
  color: #ff6700;
}
#details .page-header .title .list .cut {
  font-size: 17px;
  color: #c9c7c7;
  margin-left: 20px;
}
/* 头部CSS END */

/* 主要内容CSS */
#details .details-main {
  width: 1225px;
  height: 560px;
  padding-top: 30px;
  margin: 0 auto;
  padding-bottom: 30px;
  border-bottom: 1px solid #e0e0e0;
}
#details .details-main .details-block {
  float: left;
  width: 560px;
  height: 560px;
}
#details .el-carousel .el-carousel__indicator .el-carousel__button {
  background-color: rgba(163, 163, 163, 0.8);
}
#details .details-main .details-content {
  float: left;
  margin-left: 25px;
  width: 640px;
}

#details .details-main .details-content .name {
  line-height: 30px;
  font-size: 24px;
  font-weight: normal;
  color: #212121;
  display: inline;
}

#details .details-main .details-content .view {
  color: #b0b0b0;
  float: right;
  padding-top: 5px;
  font-size: 18px;
}

#details .details-main .details-content .intro {
  color: #b0b0b0;
  padding-top: 10px;
}
#details .details-main .details-content .store {
  color: #ff6700;
  padding-top: 10px;
}
#details .details-main .details-content .price {
  display: block;
  font-size: 18px;
  color: #ff6700;
  border-bottom: 1px solid #e0e0e0;
  padding: 25px 0 25px;
}
#details .details-main .details-content .price .del {
  font-size: 14px;
  margin-left: 10px;
  color: #b0b0b0;
  text-decoration: line-through;
}
#details .details-main .details-content .pro-list {
  background: #f9f9f9;
  padding: 30px 60px;
  margin: 50px 0 50px;
}
#details .details-main .details-content .pro-list span {
  line-height: 30px;
  color: #616161;
}
#details .details-main .details-content .pro-list .pro-price {
  float: right;
}
#details .details-main .details-content .pro-list .pro-price .pro-del {
  margin-left: 10px;
  text-decoration: line-through;
}
#details .details-main .details-content .pro-list .price-sum {
  color: #ff6700;
  font-size: 24px;
  padding-top: 20px;
}
#details .details-main .details-content .button {
  height: 55px;
  margin: 10px 0 20px 0;
}
#details .details-main .details-content .button .el-button {
  float: left;
  height: 55px;
  font-size: 16px;
  color: #fff;
  border: none;
  text-align: center;
}
#details .details-main .details-content .button .shop-cart {
  width: 340px;
  background-color: #ff6700;
}
#details .details-main .details-content .button .shop-cart:hover {
  background-color: #f25807;
}

#details .details-main .details-content .button .like {
  width: 260px;
  margin-left: 40px;
  background-color: #b0b0b0;
}
#details .details-main .details-content .button .like:hover {
  background-color: #757575;
}
#details .details-main .details-content .pro-policy li {
  float: left;
  margin-right: 20px;
  color: #b0b0b0;
}
/* 主要内容CSS END */

/*商品概述&参数*/
#details .product-select {
  width: 300px;
  margin: 0 auto;
  height: 80px;
  display: flex;
  align-items: center;
}
#details .product-select .notSelect {
  font-size: 19px;
  color: #757575;
}
#details .product-select .isSelect {
  font-size: 19px;
  color: #ff6700;
}
#details .product-select .notSelect:hover {
  color: #ff6700;
}
#details .product-select .cut {
  font-size: 25px;
  color: #c9c7c7;
  margin-left: 45px;
  margin-right: 45px;
}
#details .product-img img {
  width: 1225px;
  display: block;
  margin: 0 auto;
}
/*商品概述&参数END*/

.comment {
  border-bottom: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
}
.comment.user_image {
  width: 30px;
  height: 30px;
  border-radius: 50%;
}
.comment.meta {
  font-size: 0.85em;
  color: #666;
}
.comment .content {
  margin-top: 5px;
}

/*v-else*/
.not-found {
  height: 500px;
  text-align: center;
}
/*v-else END*/
</style>
