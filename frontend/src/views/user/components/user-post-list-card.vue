<template>
  <div>
    <el-row>
    <el-col :span="6" v-for="(o, idx) in card" :key="idx"> <!-- :offset="index > 0 ? 2 : 0" -->
      <el-card :body-style="{ padding: '10px' }" style="margin: 10px !important;" shadow="hover">
        <img :src="require('@/assets/images/logo2.png')" class="image" />
        <div style="padding: 14px;">
          <!-- 입양/임보 구분 -->
          <el-tag class="type" style="mini">{{o.type.name}}</el-tag>
          <div class="cardtitle"><p>{{o.title}}</p></div>
          <div class="bottom">
            <div class="detail"><el-button type="text" class="button" @click="readDetail(o.id)" >글 보러가기</el-button></div>
          </div>
        </div>
      </el-card>
    </el-col>
    </el-row>
  </div>


</template>

<style scoped>
  .type{
    float : left;
  }
  .bottom {
    margin-top: 13px;
    line-height: 12px;
    display: right;
    justify-content: space-between;
    align-items: center;
  }

  /* .button {
    padding: 0;
    min-height: auto;
  } */

  .image {
    width: 100%;
    display: block;
  }
  .cardtitle{
    float : left;
    display: block;
    margin : auto;

  }
  .detail{
    float : right;
    display: block;
  }


</style>

<script>
import $axios from 'axios'
import { onBeforeMount, onMounted, reactive, computed } from "vue";
import { useStore } from 'vuex'
import { useRouter } from 'vue-router';

export default {
  name: 'post-list-card',
  props:{
      card :{
          type: String
      }
 
  },
  setup () {
    const store = new useStore()
    const router = new useRouter()
    const readDetail = function(id) {
      const userid = store.getters["root/getLoginUserInfo"].userId;

      $axios
        .get("/board/" + id + "/" + userid)
        .then(function(result) {
          console.log(result);

          const boardDetail = {
            boardId: result.data.board.id,
            boardType: result.data.board.type,
            thumbnailUrl: result.data.board.thumbnailUrl,
            title: result.data.board.title,
            address: result.data.dogInformation.address,
            mbti: result.data.dogInformation.mbti,
            colorType: result.data.dogInformation.colorType,
            gender: result.data.dogInformation.gender,
            hairType: result.data.dogInformation.hairType,
            neutralization: result.data.dogInformation.neutralization,
            writer: result.data.writer,
            weight: result.data.dogInformation.weight,
            ageType: result.data.dogInformation.age,
            regDate: result.data.board.regDate,
            fileList: result.data.boardImageList,
            isOwner: result.data.owner,
            description: result.data.dogInformation.description,
            dogName: result.data.dogInformation.dogName
          };

          store.commit("root/setBoardDetail", boardDetail);

          router.push({ name: "AdoptDetail" });
        })
        .catch(function(err) {
          console.log(err);
        });
    };
    return {readDetail}

  }
}
</script>