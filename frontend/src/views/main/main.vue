<template>
<el-container class="main-wrapper">
    <main-header :height="`70px`"/>
    <el-container class="main-container">
      <router-view></router-view>
    </el-container>
    <main-footer :height="`70px`"/>
  </el-container>
</template>

<style>
  @import "https://unpkg.com/element-plus/lib/theme-chalk/index.css";
  @import './main.css';
  @import '../../common/css/common.css';
  @import '../../common/css/element-plus.css';
</style>

<script>
import MainHeader from './components/main-header'
import MainFooter from './components/main-footer'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'main',
  components: {
    MainHeader,
    MainFooter,
  },
  setup(){
    const store = useStore()
    const router = useRouter()

    router.afterEach((to, from, next) => {
      store.commit('root/setCurrentPath', to)
    })
  }
}
</script>
