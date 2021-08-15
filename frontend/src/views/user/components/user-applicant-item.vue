<template>
  <el-row :gutter="20">
    <el-col :span="12" v-for="(o, idx) in applicant" :key="idx">
      <el-row :gutter="20" class="grid-content bg-beige">
        <el-col :span="4">
          <div class="image">
            <el-avatar s4hape="circle" :size="70" :src="require('@/assets/images/profile-image.jpg')" :style="{'border' : 'solid 1px rgb(212, 212, 212)'}"/>
          </div>
        </el-col>
        <el-col :span="13">
          <div class="apply-content"><p>이름 : {{o.applicantId.name}}</p><p>{{o.applicantId.phoneNumber}}</p></div>
        </el-col>
        <el-col :span="3">
          <div class="icon">
          <font-awesome-icon
                        icon="file"
                        aria-hidden="true"
                        style="color: rgb(78, 78, 78); font-size: 20px; cursor: pointer; margin-right:20px"
                        class="scale-up-5">
          </font-awesome-icon>
          <font-awesome-icon
                        icon="comments"
                        aria-hidden="true"
                        style="color: rgb(78, 78, 78); font-size: 20px; cursor: pointer; margin-top: 10px;"
                        class="scale-up-5">
          </font-awesome-icon>
        </div>
        </el-col>
        <el-col :span="3">
          <div class="button" style="text-align:center;">
            <el-tag
                color="#D7AFA4"
                effect="dark"
                size="medium"
                style="border:none; border-radius: 30px; font-size:7pt; display:block; margin-bottom: 5px"
                @click="clickAccept(o.id)">승인</el-tag>
            <el-tag
                color="#BDBDBD"
                effect="dark"
                size="medium"
                style="border:none; border-radius: 30px; font-size:7pt; display:block;"
                @click="clickReject(o.id)">거절</el-tag>
          </div>
        </el-col>
      </el-row>
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
    margin-right:2%;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  } 
  .image{
    display: flex;
    align-items: center;
    float : left;
    vertical-align: middle; 
    margin-right: 20px;
  }
  .apply-content{
    vertical-align: middle; 
    margin-right: 20px;
  }
  .apply-content p{
    text-align: left;
    margin: 5px 0 5px 0;
  }
  .button{
    text-align: right;
    vertical-align: middle; 

  }
  .icon{
    text-align: right;
    margin-right: 20px;
    margin-left:30px;

  }
</style>

<script>
import { onBeforeMount, onMounted, reactive, computed } from "vue";
import { useStore } from 'vuex'
import { useRouter } from 'vue-router';
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: 'user-applicant-item',
  props:{
      applicant :{
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

    const clickAccept = function(id){
      store.dispatch('root/changeResult', {id : id, status : { result : '승인'}})
      .then(function(result){
        createToast("신청 결과가 등록되었습니다 💨💨", {
                hideProgressBar: "true",
                timeout: 4500,
                showIcon: "true",
                toastBackgroundColor: "#7eaa72",
                position: "bottom-right",
                transition: "bounce",
                type: "success"
            });
        router.push({name : 'mypage-applicant-list'})
      }).catch(function(err){
        console.log(err)
      });
    }

    const clickReject = function(id){
      store.dispatch('root/changeResult', {id : id,  status : { result : '거절'} })
      .then(function(result){
        createToast("신청 결과가 등록되었습니다 💨💨", {
                hideProgressBar: "true",
                timeout: 4500,
                showIcon: "true",
                toastBackgroundColor: "#7eaa72",
                position: "bottom-right",
                transition: "bounce",
                type: "success"
            });
        router.push({name : 'mypage-applicant-list'})
      }).catch(function(err){
        console.log(err)
      });
    }

    return {state, clickAccept, clickReject}

  }
}
</script>
