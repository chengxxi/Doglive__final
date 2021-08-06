<template>
  <div class="main-body main-padding">
     <el-card class="box-card " style="width:100%; border:none;" shadow="hover" >
       <bread-crumb></bread-crumb>

    <div style="margin-top:50px">
        <h1 class="title">입양하기 게시물</h1>
    <p>입양을 기다리는 아이들</p> <!-- 상세 문구 수정 필요 -->
    </div>
<div>

  <el-button :plain="true" @click="readData9" round>테스트용 : 9번 보드 읽기</el-button>
      <el-button :plain="true" @click="readData" round>테스트용 : 13번 보드 읽기</el-button>

       <el-button type="outline-primary" @click="goRegister" round>입양 공고 작성하기</el-button>


        <el-button type="outline-primary" @click="dialogVisible = true" round>나와 맞는 강아지 찾기</el-button>
        <!-- MBTI 기능 연계가 완료되면 사라질 코드 -->
        <el-dialog
          title="Notice"
          v-model="dialogVisible"
          width="30%"
        >
          <span>준비 중인 기능입니다.</span>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible = false">Close</el-button>
            </span>
          </template>
        </el-dialog>
        <!-- 임시 코드 (끝) -->
<AdoptFilter/>
    </div>

    <div>

    </div>
    <el-space wrap>
      <!-- 입양 공고 Filter -->




      <!-- 입양 공고 작성하기 버튼 -->
      <div class="newAdoptbtn">

        <!-- 공고 작성 기능 연계가 완료되면 사라질 코드 -->
        <el-dialog
          title="Notice"
          v-model="dialogVisible2"
          width="30%"
        >
          <span>준비 중인 기능입니다.</span>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible2 = false">Close</el-button>
            </span>
          </template>
        </el-dialog>
        <!-- 임시 코드 (끝) -->
      </div>

    </el-space>





    <AdoptList/>

     </el-card>

  </div>
</template>

<style>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%, 위 여백 50px) */
.main-body{
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
}
.main-padding{
  padding-top: 50px;
}


.title {
  font-size: 2.5rem;
  font-weight: normal;
}
</style>

<script>
import AdoptFilter from './components/adopt-filter.vue'
import AdoptList from './components/adopt-list.vue'
import BreadCrumb from './components/bread-crumb.vue'
import { defineComponent, ref } from 'vue';
import $axios from 'axios'

import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { onMounted } from 'vue';


export default {
  name: 'adoptlist',
  components: {
    AdoptFilter,
    AdoptList,
    BreadCrumb
  },

  setup() {
    const dialogVisible = ref(false);
    const dialogVisible2 = ref(false);

    const store = new useStore()
    const router = new useRouter()

    const readData = function() {

      const userid = store.getters['root/getLoginUserInfo'].userId

      $axios.get('/board/13/'+userid)
      .then(function(result){
        console.log(result)

        const boardDetail = {
          boardId : result.data.board.id,
          boardType : result.data.board.type,
          thumbnailUrl : result.data.board.thumbnailUrl,
          title : result.data.board.title,
          address : result.data.dogInformation.address,
          mbti : result.data.dogInformation.mbti,
          colorType : result.data.dogInformation.colorType,
          gender : result.data.dogInformation.gender,
          hairType : result.data.dogInformation.hairType,
          neutralization : result.data.dogInformation.neutralization,
          writer : result.data.writer,
          weight : result.data.dogInformation.weight,
          ageType : result.data.dogInformation.ageType,
          regDate : result.data.board.regDate,
          fileList : result.data.boardImageList,
          isOwner : result.data.owner,
          description : result.data.dogInformation.description,
          dogName : result.data.dogInformation.dogName
        }

        store.commit('root/setBoardDetail', boardDetail)

        router.push({name : 'AdoptDetail'})

      }).catch(function(err){

        console.log(err)
      });
    }

    const readData9 = function() {

      const userid = store.getters['root/getLoginUserInfo'].userId

      $axios.get('/board/9/'+userid)
      .then(function(result){
        console.log(result)

        const boardDetail = {
          boardId : result.data.board.id,
          boardType : result.data.board.type,
          thumbnailUrl : result.data.board.thumbnailUrl,
          title : result.data.board.title,
          address : result.data.dogInformation.address,
          mbti : result.data.dogInformation.mbti,
          colorType : result.data.dogInformation.colorType,
          gender : result.data.dogInformation.gender,
          hairType : result.data.dogInformation.hairType,
          neutralization : result.data.dogInformation.neutralization,
          writer : result.data.writer,
          weight : result.data.dogInformation.weight,
          ageType : result.data.dogInformation.ageType,
          regDate : result.data.board.regDate,
          fileList : result.data.boardImageList,
          isOwner : result.data.owner,
          description : result.data.dogInformation.description,
          dogName : result.data.dogInformation.dogName
        }

        store.commit('root/setBoardDetail', boardDetail)

        router.push({name : 'AdoptDetail'})

      }).catch(function(err){

        console.log(err)
      });
    }

    const goRegister = function(){
      router.push({name : 'AdoptRegister'})
    }


    onMounted(() => {
      console.log('breadcrumb')
      store.commit('root/setBreadcrumbInfo', {
        isHome : false,
        title: 'Adopt',
        subTitle: '입양 공고 목록'
      })
    })

    return {
      dialogVisible,
      dialogVisible2,readData, goRegister, readData9
    }
  },

}
</script>
