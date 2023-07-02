// 所有和用户相关的，向后端请求的接口，全部封装在这里
import request from "./request";
import { useUserStore } from "~/store/user";

// 定义表单提交类型
type LoginInfo = {
    username: string
    password: string
    code?: string // ? 表示可有可无
}

// 定义返回类型
type LoginResult = {
    username: string
    msg: string
    access_token: string
    refresh_token: string
    profile: string
}

// 导出函数，供其他方，使用
export const login = (loginInfo: LoginInfo) => {
    return request<LoginResult>({
        method: 'post',
        url: '/api/account/login',
        data: loginInfo, // axios 自动将其转化为 json 

    })
}

type InfoResult = {
    username: string
    profile: string
    slogan: string
    id: number
    msg: string
    email: string
}
// 获取用户信息的api
export const getInfo = () => {
    const store = useUserStore()
    return request<InfoResult>({
        method: 'get',
        url: '/api/account/info',
        headers: {
            Authorization: "Bearer " + store.getAccess()
      }
    })
}

type Rtoken = {
    msg: string,
    access_token: string
    refresh_token: string,
}

// 定义一个变量，避免重复刷新token
let promiseRtoken: Promise<any>
let is_refresh = false
// 刷新 token
export const refreshToken = () => {
    if(is_refresh) {
        // 已经请求过了，可以不用请求了
        return promiseRtoken
    }
    is_refresh = true
    const store = useUserStore()
    promiseRtoken =  request({
        method: 'post',
        url: '/api/refresh',
        data: {
            refresh_token: store.getRefresh()
        },
    }).finally(() => {
        is_refresh = false
    })
    
    return promiseRtoken
}

