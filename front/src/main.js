import Vue from 'vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import router from "./router.js"
import store from "./store.js"
import App from "./App.vue"
import '@babel/polyfill'
import * as axios from "axios"

axios.defaults.withCredentials = true;

Vue.use(Vuetify);

export default new Vue({
    el: '#app',
    router,
    store,
    render: a => a(App),
})
