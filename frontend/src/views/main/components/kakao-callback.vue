<template>
  <div>
    callback 페이지 입니다.
  </div>
</template>

<style>
</style>

<script>
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'kakao-callback',
  setup() {
      const store = new useStore()
      const router = new useRouter()

      // URL에서 code를 뽑아오는 코드
      const code = store.getters['root/getCurrentPath'].query.code;

      // code를 이용하여 AccessToken 받아온 후,
      store.dispatch('root/requestAccessToken', code)
      .then(function(result){
        console.log(result)
        const accessToken = result.data.user.accessToken
        const refreshToken = result.data.user.refreshToken

        // accessToken을 통해 userInfo 받아오기 + store에 저장
        store.dispatch('root/requestUserInfo', { accessToken : accessToken, refreshToken : refreshToken })
         .then(function(result){
            console.log(result)
            store.commit('root/setAccessToken', result.data.user.Token.accessToken)
            store.commit('root/setRefreshToken', result.data.user.Token.refreshToken)
            store.commit('root/setLoginUserInfo', result.data.user.userInfo)

            console.log(store.getters['root/getLoginUserInfo'])
            alert("로그인 되었습니다.")

            router.push({name : 'Main'}) // Main으로 redirect
         }).catch(function(err){
           alert("로그인에 실패하였습니다.")
         });
      }).catch(function(err){
        alert("로그인에 실패하였습니다.")
      });

      router.push({name : 'Main'}) // Main으로 redirect
    }
}
</script>
