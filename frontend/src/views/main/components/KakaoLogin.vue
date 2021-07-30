<template>
test
</template>


<style>
</style>


<script>
import axios from 'axios' // 서버로부터 받은 kakao url에 요청보내기 위한 axios
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'login-dialog',
  setup() {
      const store = new useStore()
      const router = new useRouter()
    }
}
</script>