import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  beforeCreate() {
    this.$store.dispatch("actCheckLogin");
  },
  render: h => h(App)
}).$mount('#app')
