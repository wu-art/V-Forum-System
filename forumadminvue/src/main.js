// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import axios from 'axios';
/*Vue中加入axios原型*/
Vue.prototype.$http = axios;
axios.defaults.baseURL = 'http://localhost:8088/ForumServer';
axios.defaults.withCredentials=true;

import ElementUi from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUi)

import moment from 'moment';

// 定义全局时间戳过滤器
 Vue.filter('formatDate', function(value) {
   return moment(Number(value)).format('YYYY-MM-DD HH:mm:ss')
 })

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
