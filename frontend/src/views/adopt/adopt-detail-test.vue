<template>
  <div class="main-body main-padding">
    <el-page-header @back="goBack" content="입양 공고 목록">
    </el-page-header>
    <bread-crumb></bread-crumb>
    <div>
      <el-button :plain="true" @click="readData">9번 보드 읽기</el-button>
    </div>
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

</style>



<script>
import $axios from 'axios'
import BreadCrumb from './components/bread-crumb.vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { onMounted } from 'vue';

export default {
  name : 'AdoptDetailTest',
  components: {
    BreadCrumb
  },
  setup(){
    const store = new useStore()
    const router = new useRouter()

    const readData = function() {

      const userid = store.getters['root/getLoginUserInfo'].userId

      $axios.get('/api/v1/board/9/'+userid)
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
          isOwner : result.data.isOwner,
          description : result.data.board.description
        }

        store.commit('root/setBoardDetail', boardDetail)

        router.push({name : 'AdoptDetail'})

      }).catch(function(err){
        console.log(err)
      });
    }


    onMounted(() => {
      console.log('breadcrumb')
      store.commit('root/setBreadcrumbInfo', {
        isHome : false,
        title: 'Adopt',
        subTitle: '입양 공고 목록'
      })
    })

    return { readData }

  }
}
</script>
