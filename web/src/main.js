import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// 引入ant-design-vue组件和css样式
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/reset.css'
// 引入ant-design-vue图标
import * as Icons from '@ant-design/icons-vue'

import axios from 'axios'

// createApp(App).use(Antd).use(store).use(router).mount('#app')
const app = createApp(App)
app.use(Antd).use(store).use(router).mount('#app')

// 全局使用icons
const icons = Icons
for (const i in icons) {
    app.component(i, icons[i])
}

// axios拦截器
axios.interceptors.request.use(function (config) {
    console.log('请求参数：', config);
    return config
}, function (error) {
    return Promise.reject(error);
})
axios.interceptors.response.use(function (response) {
    console.log('返回结果：', response);
    return response;
}, function (error) {
    console.log('返回错误', error);
    return Promise.reject(error);
})