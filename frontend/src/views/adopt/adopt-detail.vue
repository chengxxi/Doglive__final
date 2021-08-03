<template>
  <div class="main-body main-padding">
    <el-page-header @back="goBack" content="{{state.board.title}}">
    </el-page-header>



    <el-card class="box-card">
      <el-row :gutter="20">

          <el-col :span="6">
              <el-avatar shape="square" :size="300" :src="require('@/assets/images/mbti_isfp.png')" :style="{'border' : 'solid 1px rgb(212, 212, 212)'}"/>
          </el-col>
          <el-col :span="18">
              <el-descriptions class="margin-top" title="Vertical list without border" :column="4" direction="vertical">
                <el-descriptions-item label="Username">kooriookami</el-descriptions-item>
                <el-descriptions-item label="Telephone">18100000000</el-descriptions-item>
                <el-descriptions-item label="Place" :span="2">Suzhou</el-descriptions-item>
                <el-descriptions-item label="Remarks">
                  <el-tag size="small">School</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="Address">No.1188, Wuzhong Avenue, Wuzhong District, Suzhou, Jiangsu Province</el-descriptions-item>
              </el-descriptions>
          </el-col>

      </el-row>
    </el-card>

  </div>
</template>


<style>
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
          title: 'Adopt',
          subTitle: '입양 공고 상세 페이지'
        })
      })


      return { state, goBack }
  }
}
</script>
