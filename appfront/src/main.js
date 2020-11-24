import Vue from 'vue'
import App from './App.vue'
import router from "./router"

import VueBetterCalendar from 'vue-better-calendar'


// 后加的pengffe------------------------------------
import ElementUI from 'element-ui'
import VueResource from 'vue-resource'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false

Vue.use(VueBetterCalendar)
Vue.use(ElementUI)
Vue.use(VueResource)

import Axios from 'axios'
Vue.prototype.$axios = Axios
//-------------------------------------------------

new Vue({
  render: h => h(App),
  router
}).$mount('#app')
