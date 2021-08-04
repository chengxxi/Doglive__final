<template>
  <div class="main-body main-padding">
    <bread-crumb></bread-crumb>

    <p>{{state.board.title}}</p>


    <el-card class="box-card">
      <el-row :gutter="20">

          <el-col :span="8">
              <el-avatar shape="square" :size="300" :src="require('@/assets/images/mbti_isfp.png')" :style="{'border' : 'solid 1px rgb(212, 212, 212)'}"/>
          </el-col>
          <el-col :span="16">
              <el-descriptions class="margin-top" title="이름도 지어줘야겠다" :column="3" :size="size">
                <el-tag size="small">{{state.board.mbti}}</el-tag>
                <el-descriptions-item label="성별/중성화여부">{{state.board.gender.name}}/{{state.board.neutralization.name}}</el-descriptions-item>
                <el-descriptions-item label="추정나이" :span="2">{{state.board.ageType.name}}</el-descriptions-item>
                <el-descriptions-item label="임보주소">{{state.board.address}}</el-descriptions-item>
                <el-descriptions-item label="MBTI" >
                  <el-tag size="small">{{state.board.mbti}}</el-tag>
                </el-descriptions-item>
              </el-descriptions>
          </el-col>

      </el-row>
    </el-card>

    {{state.board.description}}
  </div>
</template>


<style scoped>
.main-body{
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
}
.main-padding{
  padding-top: 50px;
}


.el-col {

  display: flex;

  align-items: center;
}

.box-card {
  margin-top : 20px;
}

.el-page-header__content{
  font-size : 20px;
  font-weight: bold;
}

.el-card__body{
  display: flex;

  align-items: center;

}



</style>



<script>

import BreadCrumb from './components/bread-crumb.vue'
import { computed, reactive, onMounted } from 'vue';
import { useStore } from 'vuex'
import { useRouter } from 'vue-router';

export default {
  name : 'AdoptDetail',
  components: {
    BreadCrumb
  },
  setup(){
      const store = new useStore()
      const router = new useRouter()

      const state = reactive({
        board : computed(()=>{
          console.log(store.getters['root/getBoardDetail'])
          return store.getters['root/getBoardDetail']
        })
      })

      const goBack = function(){
        router.push({name : 'AdoptDetailTest'})
      }

      onMounted(() => {
        console.log('breadcrumb')
        store.commit('root/setBreadcrumbInfo', {
          isHome : false,
          title: '입양/임보',
          subTitle: '입양/임보 동물 정보'
        })
      })
      return { state, goBack }
  }
}
</script>
