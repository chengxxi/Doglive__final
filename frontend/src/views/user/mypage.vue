<template>
  <div class="main-body main-padding">
    <side-navi></side-navi>
    <user-profile></user-profile>
  </div>
</template>

<style>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%, 위 여백 100px) */
.main-body{
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
}
.main-padding{
  padding-top: 100px;
}
</style>

<script>
import UserProfile from './components/user-profile.vue'
import SideNavi from './components/user-side-navi.vue'

export default {
  name: 'mypage',
  components: {
    SideNavi,
    UserProfile,
  },
  setup() {

  }
}
</script>
