import axios from 'axios';

// 创建 Axios 实例
const request = axios.create({
    baseURL: 'http://localhost:8080', // 基础 URL
    timeout: 10000, // 请求超时时间
});

// 配置请求拦截器
request.interceptors.request.use(
    config => {
        // 从本地存储获取 token
        const token = localStorage.getItem('token');
        if (token) {
            config.headers['token'] = `${token}`; // 设置请求头
        }
        return config;
    },
    error => {
        // 请求错误处理
        return Promise.reject(error);
    }
)
/*request.interceptors.response.use(
    response=>{
        let res=response.data
        /!*const data=JSON.parse(res)
        if(data.errCode===401)//拦截没登陆的状况
        {

        }*!/
}*/
//)
export default request;
