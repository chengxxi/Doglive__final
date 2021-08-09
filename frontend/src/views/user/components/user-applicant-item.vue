<template>
  <el-row :gutter="20">
    <el-col :span="12" v-for="(o, idx) in applicant" :key="idx">
      <div class="grid-content bg-beige">
        <div class="image">
          <el-avatar shape="circle" :size="70" :src="require('@/assets/images/profile-image.jpg')" :style="{'border' : 'solid 1px rgb(212, 212, 212)'}"/>
        </div>
        <div class="apply-content"><p>이름 : {{o.name}}</p><p>{{o.phoneNumber}}</p></div>
        
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
        <div class="button">
          <el-tag
              color="#D7AFA4"
              effect="dark"
              size="medium"
              style="border:none; border-radius: 30px; font-size:7pt; display:block; margin-bottom: 5px">승인</el-tag>
          <el-tag
              color="#BDBDBD"
              effect="dark"
              size="medium"
              style="border:none; border-radius: 30px; font-size:7pt; display:block;">거절</el-tag>
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
import $axios from 'axios'
import { onBeforeMount, onMounted, reactive, computed } from "vue";
import { useStore } from 'vuex'
import { useRouter } from 'vue-router';
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

  }
}
</script>
