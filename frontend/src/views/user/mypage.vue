<template>
  <div class="main-body main-padding">
    <side-navi></side-navi>
    <router-view></router-view>
  </div>
</template>

<style>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%, 위 여백 50px) */
.main-body{
  width: 100%;
  min-height: calc(100vh - 140px);
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
}
.main-padding{
  padding-top: 50px;
}
</style>

<script>
import UserProfile from './components/user-profile.vue'
import SideNavi from './components/user-side-navi.vue'
import UserPost from './components/user-post-list.vue'
import Bookmark from './components/user-bookmark.vue'
import Applicant from './components/user-applicant-list.vue'
import ApplyResult from './components/user-apply-result-list.vue'

export default {
  name: 'mypage',
  components: {
    SideNavi,
    UserProfile,
    UserPost,
    Bookmark,
    Applicant,
    ApplyResult
  },
  setup() {

  }
}
</script>
