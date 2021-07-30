<template>
</template>


<style>
</style>


<script>
import axios from 'axios' // 서버로부터 받은 kakao url에 요청보내기 위한 axios
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'kakao-callback',
  setup() {
      const store = new useStore()
      const router = new useRouter()

      console.log(router)
      /*
      URL에서 code를 뽑아오는 코드가 필요하다
      const code = ?

      $axios.post('http://localhost:8080/kakao/login', {code: code})
      .then(function(result){
        console.log(result.user)

        store.commit('setAccessToken', result.user.Token.accessToken)
        store.commit('setRefreshToken', result.user.Token.refreshToken)
        store.commit('setLoginUserInfo', result.user.userInfo)

        console.log(store.getters['root/getLoginUserInfo'])
      }).catch(function(err){
        alert("로그인에 실패하였습니다.")
      });
      */

      console.log(router)
    }
}
</script>
