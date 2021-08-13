<template>
  <div>
    <div style="text-align:left;">
        <el-button type="outline-primary" round @click="goRegister"
        >글 작성하기</el-button>
        
      </div>

      
    <el-row class="board" v-for="(item, index) in state.boardList" :key="index">
      
      <div class="button-group" v-if="item.userId==state.userId">
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
              style="border:none; border-radius: 30px; font-size:2.3vmin;"
              >{{ item.category }}</el-tag
            >
            <el-tag
              v-if="item.category == '임보일기'"
            
              color="#E9CDA4"
              effect="dark"
              size="large"
              style="border:none; border-radius: 30px; font-size:2.3vmin;"
              >{{ item.category }}</el-tag
            >
            <el-tag
              v-if="item.category == '자유게시판'"
             
              color="#A06565"
              effect="dark"
              size="large"
              style="border:none; border-radius: 30px; font-size:2.3vmin;"
              >{{ item.category }}</el-tag
            >
            <el-tag
              v-if="item.category == '나눔'"
             
              color="#C9FD30"
              effect="dark"
              size="large"
              style="border:none; border-radius: 30px; font-size:2.3vmin;"
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
</template>

<style scoped>
.board{
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
import { onBeforeMount, onMounted, reactive,computed } from "vue";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css"; 

export default {
  name: "community-board-list",
  components:{
    createToast,
  },

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
        console.log(result)
        state.boardList = result.data;
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

    const goRegister = function() {
      if (state.userId === null) {
        createToast("로그인을 진행해주세요 💨💨", {
            hideProgressBar: "true",
            timeout: 4500,
            showIcon: "true",
            toastBackgroundColor: "#c49d83",
            position: "bottom-right",
            transition: "bounce",
            type: "success"
          });
          router.push({ name: "Login" });
      } else {
        router.push({ name: "community-board-register" });
      }
    }

    
    onMounted(() => {
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "Community",
        subTitle: "게시글 구경하기"
      });
    });

   

    return { state, deleteCommunity, goRegister, updateCommunity};
  }
}
</script>