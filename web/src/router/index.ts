import { createRouter, createWebHistory } from 'vue-router'
import ApplayoutVue from '~/components/layouts/Applayout.vue';
//import store from "../store/index"; // 读入vue存储的信息(一般在内存中)
import NotFound from "../views/NotFound.vue";
import IndexView from "../views/index.vue";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: ApplayoutVue,
      children: [
        {
          path: '',
          component: IndexView,
          meta: {
            // 额外信息
            requestAuth: true, // 需要授权才能访问
          }
      
        },
        {
          path: "/404/",
          name: "notfound",
          component: NotFound,
          meta: {
            // 额外信息
            requestAuth: false, // 需要授权才能访问
          }
        },
      ]
    },

    {
      path: "/:catchAll(.*)",
      redirect: "/404/"
    }
  ]
})

    // meta: {
    //   // 额外信息
    //   requestAuth: true, // 需要授权才能访问
    // }
  
  // {
  //   path: "/pk/",
  //   name: "pkIndex",
  //   component: pkIndexView,
  //   meta: {
  //     // 额外信息
  //     requestAuth: true, // 需要授权才能访问
  //   }
  // },


  // {
  //   path: "/userBotIndex/",
  //   name: "userBotIndex",
  //   component: userBotIndexView,
  //   meta: {
  //     // 额外信息
  //     requestAuth: true, // 需要授权才能访问
  //   }
  // },
  // {
  //   path: "/user/account/login/",
  //   name: "userLogin",
  //   component: userLoginView,
  //   meta: {
  //     // 额外信息
  //     requestAuth: false, // 需要授权才能访问
  //   }
  // },
  // {
  //   path: "/user/account/register/",
  //   name: "userRegister",
  //   component: userRegisterView,
  //   meta: {
  //     // 额外信息
  //     requestAuth: false, // 需要授权才能访问
  //   }
  // },





  
  // {
  //   path: "/userList",  //访问的网址
  //   name: "userList", //路径的名字，router，根据名字跳转
  //   component: UserListView
  // },






// router.beforeEach((to, from, next) => {
//     // 执行router规则前，先执行的函数
//   // to 表示跳转到哪个页面，from 从哪个页面跳转，next：页面执不执行下一步操作

//   if(to.meta.requestAuth && !store.state.user.isLogin) {
//    // 需要授权 并且 不是登录状态

//    next({name: "userLogin"});  //下一步，重定向
//   } else {
//    next(); //默认跳转
//   }
// })

export default router