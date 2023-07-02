import { createRouter, createWebHistory } from 'vue-router'
import ApplayoutVue from '~/components/layouts/Applayout.vue';
//import store from "../store/index"; // 读入vue存储的信息(一般在内存中)
import NotFound from "../views/NotFound.vue";
import IndexView from "../views/index.vue";
import LoginView from "../views/account/login.vue";
import RegisterView from "../views/account/register.vue";
import { useUserStore } from '~/store/user';
import UserInfoView from "../views/account/UserInfo.vue";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/login/",
      name: "login",
      component: LoginView,
      meta: {
        requireAuth: false,
      }
    },
    {
      path: "/register/",
      name: "register",
      component: RegisterView,
      meta: {
        requireAuth: false,
      }
    }, 
    {
      // 父路由，加入子路由不用加
      path: '/',
      name: "",
      component: ApplayoutVue,
      meta: {
        requireAuth: true
      },
      children: [
        {
          path: '',
          name: "home",
          component: IndexView,
          meta: {
            // 额外信息
            requireAuth: true, // 需要授权才能访问
          }
        },
        {
          path: '/account/info',
          name: 'info',
          component: UserInfoView,
          meta: {
            requireAuth: true
          }
        },


      ]
    },
    {
      path: "/404/",
      name: "notfound",
      component: NotFound,
      meta: {
        // 额外信息
        requireAuth: false, // 需要授权才能访问
      }
    },

    {
      path: "/:catchAll(.*)",
      redirect: "/404/"
    }
  ]
})



router.beforeEach((to, from, next) => {
  // 执行router规则前，先执行的函数

  // to 表示跳转到哪个页面，from 从哪个页面跳转，next：页面执不执行下一步操作

  if(to.matched.some((r) => r.meta.requireAuth)) {
   // 需要授权 并且 不是登录状态
   const store = useUserStore()
    if(store.getAccess() === "" ) {
      next({name: "login"}); 
      return
    } //下一步，重定向
  } 
  next(); //默认跳转
  

})


export default router