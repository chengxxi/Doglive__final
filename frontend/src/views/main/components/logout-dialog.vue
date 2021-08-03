<template>
</template>
<style scoped>
</style>

<script>
import Cookies from 'universal-cookie';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  name: 'logout-dialog',
  setup() {
    const router = new useRouter()
    const store = new useStore()
    const cookies = new Cookies()

    // Request Header에 cookie를 담아서 전송 ({withCredentials: true} 설정을 통해 Cookie를 헤더에 담아서 전송 가능.)
    store.dispatch('root/requestKakaoLogout', {withCredentials: true})
    .then(function(result){
      alert("로그아웃 되었습니다.")
      // cookie 삭제
      cookies.remove('accessToken', { path : '/', sameSite : 'strict' })
      cookies.remove('refreshToken', { path : '/', sameSite : 'strict' })
      store.commit('root/setLoginUserInfo', {});
      router.push({name : 'Main'})
    })
    .catch(function(err){
      alert("로그아웃에 실패하였습니다.")
      router.push({name : 'Main'})
    })
  }
}
</script>
