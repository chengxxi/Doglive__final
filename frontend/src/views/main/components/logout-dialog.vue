<template>
</template>
<style scoped>
</style>

<script>
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'logout-dialog',
  setup() {
    const store = new useStore()
    const router = new useRouter()

    // store 초기화
    store.commit('root/setAccessToken', null)
    store.commit('root/setRefreshToken', null)
    store.commit('root/requestKakaoLogout', {})

    store.dispatch('root/requestKakaoLogout')
    .then(function(result){
      alert("로그아웃 되었습니다.")
    })
    .catch(function(err){
      alert("로그아웃에 실패하였습니다.")
    })

    router.push({name : 'Main'})

    return { }
  }
}
</script>
