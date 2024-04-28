/*
 * @Descripttion:用户登录状态模块
 * @Author: congz
 * @Date: 2020-06-04 11:22:40
 * @LastEditors: congz
 * @LastEditTime: 2020-08-06 10:18:08
 */

export default {
  state: {
    user: '' // 登录的用户
  },
  getters: {
    getUser(state) {
      console.log("current user:" + state.user)
      return state.user
    }
  },
  mutations: {
    setUser(state, data) {
      state.user = data
    }
  },
  actions: {
    setUser({ commit }, data) {
      commit('setUser', data)
    }
  }
}
/*
current user:{
  "id":1,
  "username":"test",
  "password":" ",
  "mobilephone":"13835107109",
  "email":"abc@abc.com",
  "uimage":"https://cdn.acwing.com/media/user/profile/photo/173183_lg_52f4c2c9ee.jpg",
  "sex":"女",
  "address":"北京",
  "userstatus":1,
  "createtime":"2024-04-27 00:09:55",
  "updatetime":"2024-04-27 08:43:36",
  "sign":"这个人很懒什么也没有留下。。。"}
*/
