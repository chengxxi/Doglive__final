<template>
  <div class="main-body main-padding">
    <bread-crumb></bread-crumb>
    <div class="box-card " style="width:100%; border:none; ">
      <div style="margin-top:50px; margin-left:50px; ">
        <!-- <h5 class="fw-bold">16 Pupsonality</h5> -->
        <h1
          class="fw-bold"
          style="font-size:60pt; margin-bottom:0px; padding-bottom:0px; "
        >
          MPTI
        </h1>
        <h4 class="fw-bold" style="margin-top:0px; padding-top:0px;">
          My Puppy Type Indicator
        </h4>
      </div>
      <MbtiResult />
    </div>
  </div>
</template>

<style>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%, 위 여백 50px) */

.main-body {
  width: 100%;
  margin-left: 10%;
  margin-right: 10%;
}
.main-padding {
  padding-top: 50px;
  padding-bottom: 70px;
}

.title {
  font-size: 2.5rem;
  font-weight: normal;
}
</style>

<script>
import MbtiResult from './mbti-result.vue'
import BreadCrumb from '@/views/adopt/components/bread-crumb.vue'
import { onMounted } from 'vue'
import { useStore } from 'vuex'


export default {
  name: 'mbti-info',
  components: {
    MbtiResult,
    BreadCrumb
  },
  setup() {
    const store = new useStore();

    onMounted(() => {
      // console.log('breadcrumb');
      store.commit('root/setBreadcrumbInfo', {
        isHome: false,
        title: 'MPTI',
        path: '/mbti',
        subTitle: 'MPTI 유형'
      });
      window.scrollTo(0, 0);
    });
  }
};
</script>
