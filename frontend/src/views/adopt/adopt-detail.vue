<template>
  <div class="main-body main-padding">
    <bread-crumb></bread-crumb>

    <p>{{state.board.title}}</p>


    <el-card class="box-card" shadow="hover">
      <el-row :gutter="20">

          <el-col :span="12">
              <img class="dog-thumbnail" :style="{'max-width' : '100%'}" :src="require('@/assets/images/mbti_isfp.png')"/>
          </el-col>
          <el-col :span="12">
            <div class="dog-info-box" style="margin-right:50px;">
              <div class="vertical-center">
                <span :style="{'font-size':'30pt', 'font-weight':'700', }">김강쥐</span>
                <el-tag class="m-3" color="#D7AEA4" effect="dark" size="large" style="border:none; border-radius: 30px; font-size:14pt;">임보</el-tag>
              </div>
              <el-divider class="mt-4 mb-4"/>

            <el-descriptions class="margin-top mt-3" :column="1" :size="size">

                <el-descriptions-item label="성별/중성화여부">암컷 / 중성화 O

                </el-descriptions-item>
                <el-descriptions-item label="연령" >Puppy(~6개월)</el-descriptions-item>
                <el-descriptions-item label="무게" >소(8kg 미만)</el-descriptions-item>
                <el-descriptions-item label="견종" >단모</el-descriptions-item>
                <el-descriptions-item label="현재주소">경기도 의왕시</el-descriptions-item>
                <el-descriptions-item label="MBTI" >

                    <el-tag color="#E9CDA4" effect="dark" style="font-weight:700; color: #606266;" size="small" :style="{'border' : 'none'}">ISTP</el-tag>
                        <el-popover
                            placement="bottom"
                            width="200"
                            trigger="click"
                            >
                            <div class="content" >

                              <h3 style="font-weight:700;">강아지 MBTI 해석하기</h3>
                             <hr>

                              <table class="table table-borderless">
<thead>
    <tr style="text-align:center;">
      <th scope="col"><h5 style="font-weight:700; color:#606266;">활동성향</h5></th>
      <th scope="col"><h5 style="font-weight:700; color:#606266;">순종성향</h5></th>
      <th scope="col"><h5 style="font-weight:700; color:#606266;">적응성향</h5></th>
      <th scope="col"><h5 style="font-weight:700; color:#606266;">관계성향</h5></th>
    </tr>
  </thead>
  <tbody >
    <tr>
<td><el-tag class="m-3" color="#D7AFA4" effect="dark" size="large" :style="{'border' : 'none'}">E</el-tag>에너지 (E) Energetic
</td>
      <td><el-tag class="m-3" color="#E9CDA4" effect="dark" size="large" :style="{'border' : 'none'}">S</el-tag>충성심 강한 (S) Supportive
                                  </td>
      <td><el-tag class="m-3" color="#B4D9A7" effect="dark" size="large" :style="{'border' : 'none'}">P</el-tag>신중한 (P) Prudent
                                  </td>
      <td><el-tag class="m-3" color="#87CEDC" effect="dark" size="large" :style="{'border' : 'none'}">F</el-tag>관계지향  (F) Friendly
                                 </td>
    </tr>
    <tr>
<td><el-tag class="m-3" color="#FFFFFF" style="border: 3px solid #D7AFA4; color: #606266;" effect="dark" size="large" >I</el-tag>침착한 (I) Introversion
                                  </td>
      <td><el-tag class="m-3" color="#FFFFFF" style="border: 3px solid #E9CDA4; color: #606266;" effect="dark" size="large">N</el-tag>영리한 (N) Naughty
</td>
      <td><el-tag class="m-3" color="#FFFFFF" style="border: 3px solid #B4D9A7; color: #606266;" effect="dark" size="large">J</el-tag>친화적인 (J) Jolly
</td>
      <td><el-tag class="m-3" color="#FFFFFF" style="border: 3px solid #87CEDC; color: #606266;" effect="dark" size="large">T</el-tag>독립지향 (T) independenT
</td>
    </tr>
  </tbody>
</table>
                            </div>


                            <template #reference>
                              <i class="el-icon-question" style="margin-left : 10px;"/>
                            </template>
                        </el-popover>
                </el-descriptions-item>

              </el-descriptions>
<div class="mt-2">


<el-divider/>

  <el-tag color="#C4C4C4" class="m-3" effect="dark" style="font-weight:700; color: #606266;" size="small" :style="{'border' : 'none'}">활동적</el-tag>
<el-tag color="#C4C4C4" class="m-3" effect="dark" style="font-weight:700; color: #606266;" size="small" :style="{'border' : 'none'}">유순한</el-tag>
<el-tag color="#C4C4C4" class="m-3" effect="dark" style="font-weight:700; color: #606266;" size="small" :style="{'border' : 'none'}">독립적인</el-tag>
<el-tag color="#C4C4C4" class="m-3"  effect="dark" style="font-weight:700; color: #606266;" size="small" :style="{'border' : 'none'}">낯가리는</el-tag>


</div>
              <el-divider class="mt-4 mb-5"/>

          <div >
                <el-button :style="{'width':'100%'}">상담 신청</el-button>
              </div>



            </div>

          </el-col>


      </el-row>

      <el-divider/>


      <div class="dog-image-box">
          <el-carousel :interval="4000" type="card" height="300px">
            <el-carousel-item v-for="item in 6" :key="item">
              <img src="{{item}}"/>
            </el-carousel-item>
          </el-carousel>
      </div>

    </el-card>


  <div class="box">

      <h3>김강쥐를 소개합니다</h3>
      <div class="content">
               소개글입니다 소개글 어쩌고 🐶 <br>
       어ㅓ쩌꺼 조조저쩌쩌구 저쩌ㅉ꾸 저저ㅉ꾸 <br>
       어ㅓ쩌꺼 조조저쩌쩌구 저쩌ㅉ꾸 저저ㅉ꾸 <br>
       어ㅓ쩌꺼 조조저쩌쩌구 저쩌ㅉ꾸 저저ㅉ꾸 <br>
       어ㅓ쩌꺼 조조저쩌쩌구 저쩌ㅉ꾸 저저ㅉ꾸 <br>
       어ㅓ쩌꺼 조조저쩌쩌구 저쩌ㅉ꾸 저저ㅉ꾸 <br>
       어ㅓ쩌꺼 조조저쩌쩌구 저쩌ㅉ꾸 저저ㅉ꾸 <br>
       어ㅓ쩌꺼 조조저쩌쩌구 저쩌ㅉ꾸 저저ㅉ꾸 <br>
       어ㅓ쩌꺼 조조저쩌쩌구 저쩌ㅉ꾸 저저ㅉ꾸 <br>
       어ㅓ쩌꺼 조조저쩌쩌구 저쩌ㅉ꾸 저저ㅉ꾸 <br>
       어ㅓ쩌꺼 조조저쩌쩌구 저쩌ㅉ꾸 저저ㅉ꾸 <br>
       어ㅓ쩌꺼 조조저쩌쩌구 저쩌ㅉ꾸 저저ㅉ꾸 <br>
       어ㅓ쩌꺼 조조저쩌쩌구 저쩌ㅉ꾸 저저ㅉ꾸 <br>
       어ㅓ쩌꺼 조조저쩌쩌구 저쩌ㅉ꾸 저저ㅉ꾸 <br>
       어ㅓ쩌꺼 조조저쩌쩌구 저쩌ㅉ꾸 저저ㅉ꾸 <br>
      </div>


    </div>

  </div>
</template>


<style scoped>
.main-body{
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
}
.main-padding{
  padding-top: 50px;
  padding-bottom : 50px;
}

h3 {
    margin-block-start: 0px;

}

:deep(.el-descriptions__title) {
    font-size: 30pt;
    font-weight: 700;
}

.dog-thumbnail {
  width: 100%;
	height: auto;
    margin-right : 20px;
  margin : 10px;

}

.dog-info-box{
  width:100%;
  margin-right:30px;
  margin:10px;
  padding-right:20px;
}

:deep(.el-descriptions__label) {
  display: inline-block;
  width:150px;
   font-weight:500;
}


:deep(.el-descriptions__content){
  display: inline-block;
   font-weight:500;

}
:deep(.el-button){
  font-family: NEXONLv1Gothic;
  font-weight: 700;
  background : #755744;
  color : #F9F0E7;

}

.dog-image-box {
  margin:30px;
  padding:10px;
}

  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

.el-col {
  display: flex;

  align-items: center;
}

.box-card {
  margin-top : 20px;
}

.box {
  margin-top : 30px;
  margin-bottom : 30px;
  padding : 40px;
  background-color: #F9F0E7;
}

.content {
  margin-top : 20px;
  margin-bottom : 20px;
  padding : 20px;
  background-color: #FFFFFF;
}

.el-card__body{
  display: flex;
  align-items: center;
  margin-right : auto;
}



</style>



<script>

import BreadCrumb from './components/bread-crumb.vue'
import { computed, reactive, onMounted } from 'vue';
import { useStore } from 'vuex'
import { useRouter } from 'vue-router';

import Popper from "vue3-popper";


export default {
  name : 'AdoptDetail',
  components: {
    BreadCrumb,
    Popper
  },
  data() {
    return {
        isPopoverVisible: false,
        popoverOptions: {
          animation: "scale-fade",
          popoverReference: null,
          placement: "top",
          offset: "0,0"
        }
      }
  },
  setup(){
      const store = new useStore()
      const router = new useRouter()

      const state = reactive({
        board : computed(()=>{
          console.log(store.getters['root/getBoardDetail'])
          return store.getters['root/getBoardDetail']
        })
      })

      const goBack = function(){
        router.push({name : 'AdoptDetailTest'})
      }

      onMounted(() => {
        console.log('breadcrumb')
        store.commit('root/setBreadcrumbInfo', {
          isHome : false,
          title: '입양/임보',
          subTitle: '입양/임보 동물 정보'
        })


      })


      return { state, goBack }
  }
}
</script>
