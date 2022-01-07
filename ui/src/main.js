import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
//全局样式表
import './assets/css/global.css'

import axios from "axios";
axios.defaults.baseURL='http://localhost:8081/'
axios.interceptors.request.use(config => {
  const token = window.sessionStorage.getItem('token');
  if (token) config.headers.Authorization = token;
  return config;
});
Vue.prototype.$http=axios

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

