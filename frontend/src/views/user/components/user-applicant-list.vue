<template>
    <div class="mypage-body">
      <div class="hide-on-small">
        <div class="menu-title">
          <h5>신청자 목록 보기</h5>
        </div>
        <div class="mypage-content">
          <applicant-item></applicant-item>
        </div>
      </div>
    </div>
</template>

<style scoped>
.mypage-body{
  float: left;
  width: calc(100% - 240px);
  height: 100%;
}
/* .mypage-body .menu-title{
  width: inherit;
  padding-top: 10px;
  padding-bottom: 10px;
  border-bottom: solid 1px rgb(212, 212, 212);
} */
.mypage-body .menu-title {
  text-align: left;
  padding-top: 10px;
  padding-bottom: 10px;
  border-bottom: solid 1px rgb(212, 212, 212);
}
.mypage-body .mypage-content {
  padding-top: 60px;
  text-align: center;
  margin: 0 auto;
}
</style>

<script>
import ApplicantItem from './user-bookmark-card.vue';

export default {
  name: 'user-apply-list',
  components:{
    ApplicantItem,
  },
  setup () {

  }
}
</script>
