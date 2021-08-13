<template>
  <el-row :gutter="20">
    <el-col :span="12" v-for="(o, idx) in result" :key="idx">
      <el-row :gutter="20" class="grid-content bg-beige" >
        <el-col :span="3">
          <div class="tag">
          <el-tag
              v-if="o.result=='승인'"
              color="#99CC00"
              effect="dark"
              size="medium"
              style="border:none; border-radius: 30px; font-size:10pt;">{{o.result}}</el-tag>
              <el-tag
              v-if="o.result=='거절'"
              color="#CC0033"
              effect="dark"
              size="medium"
              style="border:none; border-radius: 30px; font-size:10pt;">{{o.result}}</el-tag>
              <el-tag
              v-if="o.result=='대기'"
              color="#FFFF33"
              effect="dark"
              size="medium"
              style="border:none; border-radius: 30px; font-size:10pt;">{{o.result}}</el-tag>
        </div>
        </el-col>
        <el-col :span="10">
          <div class="apply-content"><p>분류 : {{o.boardType}}</p><p>강아지 이름 : {{o.dogName}}</p></div>
        </el-col>
        <el-col :span="3">
        </el-col>
        <el-col :span="3">
          <div class="icon">
          <font-awesome-icon
                        icon="file"
                        aria-hidden="true"
                        style="color: rgb(78, 78, 78); font-size: 20px; cursor: pointer; margin-left:350%;"
                        class="scale-up-5">
          </font-awesome-icon>
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

  }
}
</script>

