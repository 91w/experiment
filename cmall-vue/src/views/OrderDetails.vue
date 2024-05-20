<!--
 * @Descripttion: 
 * @Author: congz
 * @Date: 2020-07-22 13:14:57
 * @LastEditors: congz
 * @LastEditTime: 2020-08-13 10:34:49
--> 
<template>
  <div class="order-details" id="order-details" name="order-details">
    <div class="order-details-layout" v-if="order">
      <el-row :gutter="10">
        <div>
          <CenterMenu></CenterMenu>
        </div>
        <el-col :span="20">
          <div class="order-details-content">
            <div class="extra"></div>
            <div class="order-details-title">
              <p>订单详情</p>
            </div>
            <div class="order-operate">
              <div class="order-num">订单号：{{order[0].orderid}}</div>
              <div class="order-button" v-if="order[0].type==1">
                <el-button class="cancel" type="info" size="small" style="width:120px" plain>取消订单</el-button>
                <router-link :to="{ path: '/payment', query: {orderNum:order[0].orderid}}">
                  <el-button class="pay" size="small" style="width:120px">立即付款</el-button>
                </router-link>
              </div>
            </div>
            <div class="order-step-info" v-if="order[0].type==1">等待付款</div>
            <div class="order-success-info" v-else>已付款</div>
            <div class="order-step">
              <el-steps
                :space="200"
                :active="order.type==1?1:5"
                finish-status="success"
                align-center
              >
                <el-step title="下单" :description="order[0].createtime| dateFormat"></el-step>
                <el-step title="付款" v-if="order[0].type==1"></el-step>
                <el-step title="付款" :description="order[0].createtime| dateFormat" v-else></el-step>
                <el-step title="配货"></el-step>
                <el-step title="出库"></el-step>
                <el-step title="交易成功"></el-step>
              </el-steps>
            </div>
            <div class="extra"></div>
            <div class="extra"></div>

            <div class="order-list-product" v-for="item in order" :key="item.commid">
              <div class="pro-img">
                <router-link :to="{ path: '/goods/details', query: {productID:item.commid} }">
                  <img :src="'data:image/jpeg;base64,' + item.mainimg"/>
                </router-link>
              </div>
              <div class="pro-info">
                <span style="margin-bottom:7px">
                  <router-link
                    class="info-href"
                    :to="{ path: '/goods/details', query: {productID:item.commid} }"
                  >{{item.commname}}</router-link>
                </span>
              </div>
              <div class="pro-price">
                <span>
                  {{item.thinkmoney}}元&nbsp;
                </span>
              </div>
            </div>
            <div class="order-address-title">收货信息</div>
            <div class="order-address">
              <div class="order-address-head">
                <p>姓名：</p>
                <p>联系电话：</p>
                <p>收货地址：</p>
              </div>
              <div class="order-address-data">
                <p>{{address.name}}</p>
                <p>{{address.phone}}</p>
                <p>{{address.address}}</p>
              </div>
            </div>
            <div class="order-address-title">支付方式</div>
            <div class="order-address">
              <div class="order-address-head">
                <p>支付方式：</p>
              </div>
              <div class="order-address-data">
                <p>在线支付</p>
              </div>
            </div>
            <div class="order-address-title">发票信息</div>
            <div class="order-address">
              <div class="order-address-head">
                <p>发票类型：</p>
                <p>发票内容：</p>
                <p>发票抬头：</p>
              </div>
              <div class="order-address-data">
                <p>电子普通发票</p>
                <p>购买商品明细</p>
                <p>个人</p>
              </div>
            </div>
            <!-- 结算列表 -->
            <div class="section-count">
              <div class="money-box">
                <ul>
                  <li>
                    <span class="title">商品件数：</span>
                    <span class="value">{{order.length}}件</span>
                  </li>
                  <li>
                    <span class="title">商品总价：</span>
                    <span class="value">{{total_price}}元</span>
                  </li>
                  <li>
                    <span class="title">活动优惠：</span>
                    <span class="value">-0元</span>
                  </li>
                  <li>
                    <span class="title">优惠券抵扣：</span>
                    <span class="value">-0元</span>
                  </li>
                  <li>
                    <span class="title">运费：</span>
                    <span class="value">0元</span>
                  </li>
                  <li class="total">
                    <span class="title">应付总额：</span>
                    <span class="value">
                      <span class="total-price">{{total_price}}</span>元
                    </span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="not-found" v-else>查询不到该订单</div>
  </div>
</template>
<script>
import CenterMenu from '../components/CenterMenu'
import * as ordersAPI from '@/api/orders'
export default {
  name: 'OrderDetails',
  data() {
    return {
      orderNum: '', // 订单num,
      order: '',
      address: '',
      total_price: '',
      user: JSON.parse(localStorage.getItem('user'))
    }
  },
  activated() {
    if (this.$route.query.orderNum != undefined) {
      this.orderNum = this.$route.query.orderNum
    }
  },
  watch: {
    // 监听商品id的变化，请求后端获取商品数据
    orderNum: function() {
      this.load()
    }
  },
  methods: {
    load() {
      ordersAPI
        .showOrder(this.orderNum, this.user.id)
        .then(res => {
          if (res.msg === '成功') {
            this.order = res.data
            this.address = res.address
            this.orderNum = res.orderid
            this.total_price = res.total_price
          }  else {
            this.notifyError('获取订单失败', res.msg)
          }
        })
        .catch(err => {
          this.notifyError('获取订单失败', err)
        })
    }
  },
  components: {
    CenterMenu
  }
}
</script>
<style scoped>
.order-details-layout {
  max-width: 1225px;
  margin: 0 auto;
}
.order-details-content {
  background-color: #ffffff;
  margin-bottom: 30px;
}
.order-details-title {
  height: 100px;
  display: flex;
  align-items: center;
}
.order-details-title p {
  font-size: 30px;
  color: #757575;
  margin-left: 50px;
}
.extra {
  height: 10px;
}
/*订单号按钮区域*/
.order-details-content .order-operate {
  width: 920px;
  margin: 0 auto;
  display: flex;
  border-bottom: 1px solid #e0e0e0;
}
.order-details-content .order-operate .order-num {
  width: 500px;
  font-size: 18px;
  margin-bottom: 17px;
  color: #242424;
}
.order-details-content .order-operate .order-button {
  margin-left: 170px;
}
.order-details-content .order-operate .order-button .pay {
  margin-left: 10px;
  background-color: #ff6700;
  color: #ffffff;
}
/*订单号按钮区域END*/
/*进度条区域*/
.order-details-content .order-step-info {
  width: 920px;
  margin: 0 auto;
  color: #ff6700;
  font-size: 18px;
  margin-top: 20px;
}
.order-details-content .order-success-info {
  width: 920px;
  margin: 0 auto;
  color: #00a724;
  font-size: 18px;
  margin-top: 20px;
}
.order-details-content .order-step {
  width: 920px;
  margin: 0 auto;
  margin-top: 40px;
}
/*进度条区域END*/
/*订单商品区域*/
.order-details-content .order-list-product {
  width: 920px;
  height: 110px;
  background-color: #ffffff;
  margin: 0 auto;
  display: flex;
  align-items: center;
  border-bottom: 1px solid #e0e0e0;
}
.order-details-content .order-list-product .pro-img {
  margin-right: 20px;
}
.order-details-content .order-list-product .pro-img img {
  height: 80px;
  width: 80px;
}
.order-details-content .order-list-product .pro-info {
  font-size: 15px;
  color: #333333;
}
.order-details-content .order-list-product .pro-info .info-href {
  font-size: 15px;
  color: #333333;
}
.order-details-content .order-list-product .pro-info .info-href:hover {
  color: #ff6700;
}
.order-details-content .order-list-product .pro-price {
  margin-left: 30px;
  font-size: 15px;
  color: #333333;
}

/*订单商品区域END*/
/*订单收货信息区域*/
.order-details-content .order-address-title {
  width: 920px;
  margin: 0 auto;
  color: #242424;
  font-size: 18px;
  margin-top: 20px;
}
.order-details-content .order-address {
  width: 920px;
  height: 120px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  border-bottom: 1px solid #e0e0e0;
}
.order-details-content .order-address .order-address-head {
  font-size: 15px;
  line-height: 25px;
  font-weight: bold;
  color: #757575;
}
.order-details-content .order-address .order-address-data {
  font-size: 15px;
  line-height: 25px;
  color: #757575;
}
/*订单收货信息区域END*/

/* 结算列表CSS */
.order-details-content .section-count {
  margin: 0 48px;
  padding: 20px 0;
  overflow: hidden;
}
.order-details-content .section-count .money-box {
  float: right;
  text-align: right;
}
.order-details-content .section-count .money-box .title {
  float: left;
  width: 126px;
  height: 30px;
  display: block;
  line-height: 30px;
  color: #757575;
}
.order-details-content .section-count .money-box .value {
  float: left;
  min-width: 105px;
  height: 30px;
  display: block;
  line-height: 30px;
  color: #ff6700;
}
.order-details-content .section-count .money-box .total .title {
  padding-top: 15px;
}
.order-details-content .section-count .money-box .total .value {
  padding-top: 10px;
}
.order-details-content .section-count .money-box .total-price {
  font-size: 30px;
}
/* 结算列表CSS END */

/*v-else*/
.order-details .not-found {
  height: 500px;
  text-align: center;
}
/*v-else END*/
</style>