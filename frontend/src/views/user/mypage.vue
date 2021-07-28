<template>
  <div class="main-body main-margin">
    <side-navi></side-navi>
    <user-profile></user-profile>
  </div>
</template>

<style>
.main-body{
  width: 100%;
  height: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
}
.main-margin{
  margin-top: 100px;
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
