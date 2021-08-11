<template>
  <div class="main-body main-padding">
    <el-card class="box-card " style="width:100%;" shadow="none">
      <bread-crumb></bread-crumb>
      <div style="margin-top:2%; margin-left:1%;">
        <h1 class="title">Community</h1>
        <p>강아지와 당신의 이야기</p>
      </div>
      <el-row class="myboard" v-for="(item, index) in state.myBoardList" :key="index">
        <div class="button-group">
            <el-button-group>
              <el-button type="info" plain icon="el-icon-edit" size="mini"></el-button>
              <el-button type="info" plain icon="el-icon-delete" size="mini"></el-button>
            </el-button-group>
        </div>
        <div class="image">
          <img :src="require('@/assets/images/mbti_isfp.png')" />
        </div>
        <div class="tag">
          <el-tag
                v-if="item.category == '입양일기'"
                class="mb-3"
                color="#D7AFA4"
                effect="dark"
                size="large"
                style="border:none; border-radius: 30px; font-size:14pt;"
                >{{ item.category }}</el-tag
              >
              <el-tag
                v-if="item.category == '임보일기'"
                class="mb-3"
                color="#E9CDA4"
                effect="dark"
                size="large"
                style="border:none; border-radius: 30px; font-size:14pt;"
                >{{ item.category }}</el-tag
              >
              <el-tag
                v-if="item.category == '자유게시판'"
                class="mb-3"
                color="#A06565"
                effect="dark"
                size="large"
                style="border:none; border-radius: 30px; font-size:14pt;"
                >{{ item.category }}</el-tag
              >
              <el-tag
                v-if="item.category == '나눔'"
                class="mb-3"
                color="#C9FD30"
                effect="dark"
                size="large"
                style="border:none; border-radius: 30px; font-size:14pt;"
                >{{ item.category }}</el-tag
              >
        </div>
        <div class="title">
          <span style="font-weight: bold;">
            <!-- <font-awesome-icon
              icon="heart"
              aria-hidden="true"
              style="color: rgb(237, 0, 109); font-size: 3vmin; cursor: pointer; margin-top: 10px;"
              class="scale-up-5"
            >
          </font-awesome-icon> -->
          {{item.userId}}</span>
          <span class="boardtitle">{{item.title}}</span>
        </div>
        <div class="content">
          <p>{{item.description}}</p>
        </div>
      </el-row>
    </el-card>
    
  </div>
</template>

<style scoped>
.main-body {
  width: 90%;
  margin-left: 25%; 
  margin-right: 25%;
  margin-bottom : 50px
}
.main-padding {
  padding-top: 50px;
}

.title {
  font-size: 2.5rem;
  font-weight: normal;
}
.el-button{
  margin-right:1%;
}
.myboard{
  border:1px solid lightgray;
  margin: 1%;
  margin-bottom: 5%;
  margin-top:2%;
}
.board{
  border:1px solid lightgray;
  margin: 1%;
  margin-bottom: 5%;
  margin-top:2%;
}
.button-group{
  float:right;
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
.tag{
  margin : 15px 0 55px 0;
}
.el-tag{
  margin-bottom: 2%;
}
.title{
  text-align: left;
}
.title >span{
  display: inline-block;
  font-size: 3vmin;
  margin-bottom : 2%;
}
.boardtitle{
  margin-left:2%;
  background-color: lightgoldenrodyellow;
}
.content{
  margin: 0 5% 5% 5%;
  vertical-align: middle; 
}

.content >p{
  font-size: 2.4vmin;
  vertical-align: middle; 
  margin-bottom: 0px;
}

.el-tag{
  float:left;
  margin-left: 5%;
  margin-right : 10%;
}
</style>

<script>
import $axios from "axios";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { onBeforeMount, onMounted, reactive, computed } from "vue";
import BreadCrumb from "./components/bread-crumb.vue";

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
        state.myBoardList = result.data.communityList;
      }).catch(function(err){
        console.log(err)
    });

    onMounted(() => {
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "Community",
        subTitle: "내 피드 확인하기"
      });
    });
    return { state };
  }
}
</script>
