import axios, { Axios, AxiosRequestConfig, AxiosRequestHeaders } from 'axios';
import { error } from 'console';
import { useUserStore } from '~/store/user';
import { refreshToken } from './user';
import router from '../router/index';


const request = axios.create({
    // baseURL: import.meta.env.VITE_API_URL,
})


// 响应拦截器
request.interceptors.response.use((response) => response, async (error) => {
    if(error.response.status === 403) {
        // 在refresh 有效的情况下
        const { data } = await refreshToken()
        if(data.msg === 'success') {
            
            // 刷新成功
            // 保存新的token
            const store = useUserStore()
            store.saveAToken(data.access_token)
            store.saveRToken(data.refresh_token)
            // 重新请求
            // 更新请求配置中的 Authorization 头部信息
            error.config.headers['Authorization'] = `Bearer ${data.access_token}`;
            return request(error.config)
        } else {
            // 失败，重新登录(refresh 有问题)
                
            router.push({name: 'login'})
            return
        }
    }
    return Promise.reject(error)
})


export default request