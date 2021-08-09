<template>
  <el-row :gutter="20">
    <el-col :span="12" v-for="(o, idx) in result" :key="idx">
      <div class="grid-content bg-beige">
        <div class="tag">
          <el-tag
              color="#D7AFA4"
              effect="dark"
              size="medium"
              style="border:none; border-radius: 30px; font-size:10pt;">{{o.result}}</el-tag>
        </div>
        <div class="apply-content"><p>분류 : 입양</p><p>글제목: 우리강쥐를 입양하세요!</p></div>
        <div class="icon">
          <font-awesome-icon
                        icon="file"
                        aria-hidden="true"
                        style="color: rgb(78, 78, 78); font-size: 20px; cursor: pointer; margin-right:20px"
                        class="scale-up-5">
          </font-awesome-icon>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<style scoped>
  /* .el-row{
    display: inline-block;
  } */
  .el-col {
    border-radius: 4px;
  }
  .bg-beige {
    background: #F9F0E7;
    height : 90px;
  }
  .grid-content {
    display: flex;
    align-items: center;
    flex-direction: row;
    padding: 10px 0 10px 20px;
    border-radius: 4px;
    min-height: 36px;
    margin-bottom: 20px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  } 
  .tag{
    vertical-align: middle; 
    margin-right: 20px;
  }
  .apply-content{
    text-align:left;
    vertical-align: middle; 
    margin-right: 20px;
  }
  .apply-content p{
    text-align: left;
    margin: 5px 0 5px 0;
  }
  .icon{
    text-align: right;
    margin-left:20px;
  }
</style>

<script>
import $axios from 'axios'
import { onBeforeMount, onMounted, reactive, computed } from "vue";
import { useStore } from 'vuex'
import { useRouter } from 'vue-router';

export default {
  name: 'user-apply-result-item',
  props:{
      result :{
          type: String
      }

  },
  setup () {
    const store = new useStore()
    const router = new useRouter()
    const userid = store.getters["root/getLoginUserInfo"].userId;

    const state = reactive({
        boardList: [],
        board : computed(()=>{
          console.log(store.getters['root/getBoardDetail'])
          return store.getters['root/getBoardDetail']

        })
      })
 

    // $axios
    //     .get("/board/" + result.data. + "/" + userid)
    //     .then(function(result) {
    //       console.log(result);

    //       const boardDetail = {
    //         boardId: result.data.board.id,
    //         boardType: result.data.board.type,
    //         thumbnailUrl: result.data.board.thumbnailUrl,
    //         title: result.data.board.title,
    //         address: result.data.dogInformation.address,
    //         mbti: result.data.dogInformation.mbti,
    //         colorType: result.data.dogInformation.colorType,
    //         gender: result.data.dogInformation.gender,
    //         hairType: result.data.dogInformation.hairType,
    //         neutralization: result.data.dogInformation.neutralization,
    //         writer: result.data.writer,
    //         weight: result.data.dogInformation.weight,
    //         ageType: result.data.dogInformation.age,
    //         regDate: result.data.board.regDate,
    //         fileList: result.data.boardImageList,
    //         isOwner: result.data.owner,
    //         description: result.data.dogInformation.description,
    //         dogName: result.data.dogInformation.dogName
    //       };

    //       store.commit("root/setBoardDetail", boardDetail);

    //       router.push({ name: "AdoptDetail" });
    //     })
    //     .catch(function(err) {
    //       console.log(err);
    //     });

  }
}
</script>

