/*
 * @Descripttion:路由配置
 * @Author: congz
 * @Date: 2020-06-04 11:22:40
 * @LastEditors: congz
 * @LastEditTime: 2020-08-18 19:42:54
 */

import Vue from 'vue'
import Router from 'vue-router'
import store from "../store/index"; // 读入vue存储的信息(一般在内存中)

Vue.use(Router)
const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../views/Home.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue'),
    meta: {
      showMenu: false
    }
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue'),
    meta: {
      showMenu: false
    }
  },
  {
    path: '/admin/login',
    name: 'Alogin',
    component: () => import('../views/admin.vue'),
    meta: {
      showMenu: false
    }
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('../views/AdminHome.vue'),
    meta: {
      requireAuth: true,
      role: true,
      showMenu: false,
      showFooter: false
    }
  },
  {
    path: '/admin/user',
    name: 'UserManage',
    component: () => import('../views/UserManage.vue'),
    meta: {
      requireAuth: true,
      role: true,
      showMenu: false,
      showFooter: false
    }
  },
  {
    path: '/admin/profile',
    name: 'Show',
    component: () => import('../views/Profile.vue'),
    meta: {
      requireAuth: true,
      role: true,
      showMenu: false,
      showFooter: false
    }
  },
  {
    path: '/admin/goods',
    name: 'GoodsManage',
    component: () => import('../views/GoodsManage.vue'),
    meta: {
      requireAuth: true,
      role: true,
      showMenu: false,
      showFooter: false
    }
  },
  {
    path: '/error',
    name: 'Error',
    component: () => import('../components/Error.vue')
  },
  {
    path: '/goods',
    name: 'Goods',
    component: () => import('../views/Goods.vue'),
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue'),
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/goods/details',
    name: 'Details',
    component: () => import('../views/Details.vue'),
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/cart',
    name: 'Cart',
    component: () => import('../views/Cart.vue'),
    meta: {
      showMenu: false,
      requireAuth: true // 需要验证登录状态
    }
  },
  {
    path: '/favorite',
    name: 'Favorite',
    component: () => import('../views/Favorite.vue'),
    meta: {
      requireAuth: true // 需要验证登录状态
    }
  },
  {
    path: '/order',
    name: 'Order',
    component: () => import('../views/Order.vue'),
    meta: {
      requireAuth: true // 需要验证登录状态
    }
  },
  {
    path: '/confirmOrder',
    name: 'ConfirmOrder',
    component: () => import('../views/ConfirmOrder.vue'),
    meta: {
      showMenu: false,
      requireAuth: true // 需要验证登录状态
    }
  },
  {
    path: '/order/details',
    name: 'OrderDetails',
    component: () => import('../views/OrderDetails.vue'),
    meta: {
      requireAuth: true // 需要验证登录状态
    }
  },
  {
    path: '/service',
    name: 'Service',
    component: () => import('../views/Service.vue'),
    meta: {
      requireAuth: true // 需要验证登录状态
    }
  },
  {
    path: '/center',
    name: 'Center',
    component: () => import('../views/Center.vue'),
    meta: {
      requireAuth: true // 需要验证登录状态
    }
  },
  {
    path: '/user/details',
    name: 'UserDetails',
    component: () => import('../views/UserDetails.vue'),
    meta: {
      requireAuth: true // 需要验证登录状态
    }
  },
  {
    path: '/user/pass',
    name: 'UserPass',
    component: () => import('../views/UserPass.vue'),
    meta: {
      requireAuth: true // 需要验证登录状态
    }
  },
  {
    path: '/user/address',
    name: 'UserAddress',
    component: () => import('../views/UserAddress.vue'),
    meta: {
      requireAuth: true // 需要验证登录状态
    }
  },
  {
    path: '/upload',
    name: 'Upload',
    component: () => import('../views/Upload.vue'),
    meta: {
      requireAuth: true // 需要验证登录状态
    }
  },
  {
    path: `/vaild/email/:token`,
    name: 'VaildEmail',
    component: () => import('../views/VaildEmail.vue'),
    meta: {
      showMenu: false
    }
  },
  {
    path: '/payment',
    name: 'Payment',
    component: () => import('../views/Payment.vue'),
    meta: {
      requireAuth: true // 需要验证登录状态
    }
  },
  {
    path: '/callback/qq',
    name: 'CallbackQQ',
    component: () => import('../views/CallbackQQ.vue'),
    meta: {
      showMenu: false
    }
  }
]

const router = new Router({
  // base: '/dist',
  // mode: 'history',
  routes
})

/* 由于Vue-router在3.1之后把$router.push()方法改为了Promise。所以假如没有回调函数，错误信息就会交给全局的路由错误处理。
vue-router先报了一个Uncaught(in promise)的错误(因为push没加回调) ，然后再点击路由的时候才会触发NavigationDuplicated的错误(路由出现的错误，全局错误处理打印了出来)。*/
// 禁止全局路由错误处理打印
const originalPush = Router.prototype.push
Router.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

export default router
