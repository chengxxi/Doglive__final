<template>
  <div class="main-body main-padding">
     <el-card class="box-card " style="width:100%" shadow="hover">

          <bread-crumb></bread-crumb>

    <div style="margin-top:100px">
      <el-button :plain="true" @click="readData">13번 보드 읽기</el-button>
    </div>

    <div>
      <el-button :plain="true" @click="goRegister">글 작성하기</el-button>
    </div>
     </el-card>
  </div>
</template>


<style  scoped>
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

    return { readData, goRegister }

  }
}
</script>
