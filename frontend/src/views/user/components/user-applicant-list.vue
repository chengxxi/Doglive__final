<template>
    <div class="mypage-body">
      <div class="hide-on-small">
        <div class="menu-title">
          <h5>신청자 목록 보기</h5>
        </div>
        <div class="mypage-content">
          <applicant-item :applicant="state.Applicant"></applicant-item>
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
import { computed, reactive, onMounted } from 'vue';
import { useStore } from 'vuex'
import { useRouter } from 'vue-router';
import ApplicantItem from './user-applicant-item.vue';

export default {
  name: 'user-applicant-list',
  components:{
    ApplicantItem,
  },

  setup () {
    const store = new useStore()
    const router = new useRouter()
    const state = reactive({
      Applicant : [],
    })
    const userId = store.getters['root/getLoginUserInfo'].userId;

    store.dispatch('root/requestApplicant', userId)
      .then(function(result){
        console.log(result)
        state.Applicant = result.data.counselingHistoryList;
      }).catch(function(err){
        console.log(err)
      });

    return { state }
  }
}
</script>
