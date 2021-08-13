<template>
  <div class="main-body main-padding">
    <el-card class="box-card " style="width:100%;" shadow="none">
      <bread-crumb></bread-crumb>
      <div style="margin-top:2%; margin-left:1%; margin-bottom:3%">
        <h1 style="font-size: 2.5rem; font-weight: normal;">Community</h1>
        <p>강아지와 당신의 이야기</p>
      </div>


      <div>
        <el-row class="myboard" v-for="(item, index) in state.myBoardList" :key="index">
          <div class="button-group">
              <el-button-group>
                <el-button type="info" plain icon="el-icon-edit" size="mini" @click="updateCommunity(item.id)"></el-button>
                <el-button type="info" plain icon="el-icon-delete" size="mini" @click="deleteCommunity(item.id)"></el-button>
              </el-button-group>
          </div>
          <div class="title">
            {{item.title}}
          </div>
          <div class="image">
            <img :src="require('@/assets/images/mbti_isfp.png')" />
          </div>
          <div class="tag">
            <el-tag
                  v-if="item.category == '입양일기'"
                  color="#D7AFA4"
                  effect="dark"
                  size="large"
                  style="border:none; border-radius: 30px; font-size:14pt;"
                  >{{ item.category }}</el-tag
                >
                <el-tag
                  v-if="item.category == '임보일기'"
                  color="#E9CDA4"
                  effect="dark"
                  size="large"
                  style="border:none; border-radius: 30px; font-size:14pt;"
                  >{{ item.category }}</el-tag
                >
                <el-tag
                  v-if="item.category == '자유게시판'"
                  color="#A06565"
                  effect="dark"
                  size="large"
                  style="border:none; border-radius: 30px; font-size:14pt;"
                  >{{ item.category }}</el-tag
                >
                <el-tag
                  v-if="item.category == '나눔'"
                  color="#C9FD30"
                  effect="dark"
                  size="large"
                  style="border:none; border-radius: 30px; font-size:14pt;"
                  >{{ item.category }}</el-tag
                >
          </div>
          <div class="content">
            <span style="font-weight: bold; font-size: 2.3vmin;">
              <!-- <font-awesome-icon
                icon="heart"
                aria-hidden="true"
                style="color: rgb(237, 0, 109); font-size: 3vmin; cursor: pointer; margin-top: 10px;"
                class="scale-up-5"
              >
            </font-awesome-icon> -->
            {{item.name}}</span>
            <span class="boardcontent">{{item.description}}</span>
          </div>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<style scoped>
.main-body {
  width: 100%;
  margin-left: 10%; 
  margin-right: 10%;
  margin-bottom : 50px;
}
.main-padding {
  padding-top: 50px;
}

.myboard{
  border:1px solid lightgray;
  margin-right:25%;
  margin-left: 25%;
  margin-bottom: 5%;
  margin-top:2%;
}
.title{
  font-size: 2vmin;
  text-align:right;
  margin-top:1%;
  margin-right:1%;
  color: lightgray;
}
.button-group{
  text-align:right;
  margin-top:1%;
  margin-right:1%;
}
.image{
  margin-top: 2%;
  text-align: center;
}
.image img {
  width: 70%;
}
.el-tag{
  margin-bottom: 2%;
}

.title >span{
  display: inline-block;
  margin-bottom : 2%;
}
.boardcontent{
  font-size: 2vmin;
  margin-left:2%;
}
.content{
  margin: 2% 5% 5% 5%;
  vertical-align: middle; 
  text-align: left;
}

.content >p{
  font-size: 2.4vmin;
  vertical-align: middle; 
  margin-bottom: 0px;
}
.tag{
  margin-left:5%;
}
.tag >.el-tag{
  margin-bottom: 0%;
}




</style>

<script>
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { onBeforeMount, onMounted, reactive, computed } from "vue";
import BreadCrumb from "./components/bread-crumb.vue";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "MyCommunity",
  components:{
      BreadCrumb,
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();
    const state = reactive({
      myBoardList:[],
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"].userId;
      }),

    })
   
    const userId = store.getters["root/getLoginUserInfo"].userId;

    store.dispatch('root/requestMyCommunity', userId)
      .then(function(result){
        console.log(result)
        state.myBoardList = result.data.communityList;
      }).catch(function(err){
        console.log(err)
    });

    const updateCommunity = function(id){
      store.dispatch("root/requestCommunityDetail", id)
      .then(function(result){
        console.log(result)
        const CommunityDetail = {
          communityId : result.data.community.id,
          title : result.data.community.title,
          category : result.data.community.category,
          description : result.data.community.description,
        }
        store.commit("root/setCommunityBoard", CommunityDetail);
        router.push({ name : "community-board-update"})
      }).catch(function(err) {
          console.log(err);
        });
      
    }

    const deleteCommunity = function(id){
      store.dispatch("root/requestDeleteCommunity", id)
      .then(function(result){
        createToast("게시글이 삭제되었어요 💨💨", {
            hideProgressBar: "true",
            timeout: 4500,
            showIcon: "true",
            toastBackgroundColor: "#7eaa72",
            position: "bottom-right",
            transition: "bounce",
            type: "success"
          });
          router.go(router.currentRoute)
      })
      .catch(function(err) {
          createToast("게시글 삭제에 실패했어요 😱💦", {
            hideProgressBar: "true",
            timeout: 4500,
            showIcon: "true",
            toastBackgroundColor: "#c49d83",
            position: "bottom-right",
            transition: "bounce",
            type: "warning"
          });
          console.log(err);
        });
    }

    onMounted(() => {
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "Community",
        subTitle: "내 피드 확인하기"
      });
    });
    return { state ,deleteCommunity, updateCommunity };
  }
}
</script>
