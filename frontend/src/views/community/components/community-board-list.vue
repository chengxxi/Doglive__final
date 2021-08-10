<template>
  <div>
    <el-row class="board" v-for="(item, index) in state.boardList" :key="index">
      <div class="button-group" v-if="item.userId==state.userId">
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
              v-if="item.category != '입양일기'"
              class="mb-3"
              color="#E9CDA4"
              effect="dark"
              size="large"
              style="border:none; border-radius: 30px; font-size:14pt;"
              >{{ item.category }}</el-tag
            >
      </div>
      <div class="title">
        <span>
          <font-awesome-icon
            icon="heart"
            aria-hidden="true"
            style="color: rgb(237, 0, 109); font-size: 3vmin; cursor: pointer; margin-top: 10px;"
            class="scale-up-5"
          >
        </font-awesome-icon>
        <b>{{item.userId}}</b></span>
        <span class="boardtitle">{{item.title}}</span>
      </div>
      <div class="content">
        <p>{{item.description}}</p>
      </div>
    </el-row>
  </div>
</template>

<style scoped>
.board{
  border:1px solid lightgray;
  margin: 5%;
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
  font-size: 3vmin;
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

export default {
  name: "community-board-list",
  setup() {
    const store = new useStore();
    const router = new useRouter();
    const state = reactive({
      boardList:[],
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"].userId;
      }),
    });
   

    store.dispatch('root/requestCommunityBoardList')
      .then(function(result){
        console.log(result.data)
        state.boardList = result.data.communityList;
      }).catch(function(err){
        console.log(err)
    });

    

    onMounted(() => {
      // console.log('breadcrumb')
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "Adopt",
        subTitle: "입양 공고 목록"
      });
    });

   

    return { state };
  }
}
</script>
