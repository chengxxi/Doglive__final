<template>
    <div class="mypage-body">
      <div class="hide-on-small">
        <div class="menu-title">
          <h5>상담 신청 결과</h5>
        </div>
        <div class="mypage-content">
          <apply-result></apply-result>
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
  padding-top: 40px;
  text-align: center;
  margin: 0 auto;
}
</style>

<script>
import ApplyResult from './user-apply-result-item.vue';
export default {
  name: 'user-apply-result-list',
  components :{
    ApplyResult,
  },
  setup () {

  }
}
</script>
