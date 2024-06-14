import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// 引入ant-design-vue组件和css样式
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/reset.css'
// 引入ant-design-vue图标
import * as Icons from '@ant-design/icons-vue'

// createApp(App).use(Antd).use(store).use(router).mount('#app')
const app = createApp(App)
app.use(Antd).use(store).use(router).mount('#app')

// 全局使用icons
const icons = Icons
for (const i in icons) {
    app.component(i, icons[i])
}