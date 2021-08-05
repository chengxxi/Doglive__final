<template>
  <div>

  <!-- 임시 카드 및 리스트 삭제 후 detail Component 병합 -->
    <!-- multiple template 때문에 div로 묶었으나 el-card만 들어갈 예정 -->
    <!-- 개별 입양 공고 card -->
    <!-- <el-row>
    <el-col :span="6" v-for="(o, idx) in 100" :key="o">
      <el-card :body-style="{ padding: '10px' }" style="margin: 10px !important;" shadow="hover">
        <img :src="require('@/assets/images/logo2.png')" class="image" />
        <div style="padding: 14px;">
          <span><el-tag style="mini; color: #D7AEA4;">입양</el-tag></span>
          <div class="bottom">
            <p>공고 제목</p>
            <el-button type="text" class="button">Details</el-button>
          </div>
        </div>
      </el-card>
    </el-col>
    </el-row> -->


  <!-- Adopt Detail -->
  <div class="main-body main-padding">
    <bread-crumb></bread-crumb>

    <el-card class="box-card "  shadow="hover">
      <el-row class="vertical-center" :gutter="20" style="margin-top:35px;" >

        <el-col :span="12" style="margin-left:50px;">
          <img class="dog-thumbnail" :src="require('@/assets/images/mbti_isfp.png')"/>
        </el-col>

        <el-col :span="12">
          <div class="dog-info-box" style="margin-right:50px;">
            <el-tag class="mb-1" color="#D7AEA4" effect="dark" size="large" style="border:none; border-radius: 30px; font-size:14pt;">{{state.board.boardType.name}}</el-tag>
            <div class="vertical-center row">
              <div class="col-md-9">
                <span :style="{'font-size':'30pt', 'font-weight':'700', }">{{state.board.title}}</span>
              </div>

              <div class="col-md-3 ms-auto">
                <div class="align-self-center vertical-center" style="text-align: center;">
                  <font-awesome-icon
                    :icon="[ state.board.isbookmarked  ? 'fas' : 'far', 'star']"
                    @click="clickBookmark()"
                    aria-hidden="true"
                    style="color: rgb(255, 226, 95); font-size: 25px; cursor: pointer;"
                    class=" scale-up-5"
                  >
                  </font-awesome-icon>

                  <img @click="kakaoShare" style="margin-left:15px" src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_small.png" width="40"/>
                </div>
              </div>
            </div>

          <el-divider class="mt-4 mb-4"/>

            <el-descriptions class="margin-top mt-3" :column="1" :size="size">
              <el-descriptions-item label="성별/중성화여부">{{state.board.gender.name}} / {{state.board.neutralization ? '중성화 O' : '중성화 X'  }} </el-descriptions-item>
              <el-descriptions-item label="연령" >{{state.board.ageType.name}}</el-descriptions-item>
              <el-descriptions-item label="무게" >{{state.board.weight.name}}</el-descriptions-item>
              <el-descriptions-item label="견종" >{{state.board.hairType.name}}</el-descriptions-item>
              <el-descriptions-item label="현재위치">{{state.board.address}}</el-descriptions-item>
              <el-descriptions-item label="MBTI" >

              <el-tag color="#E9CDA4" effect="dark" style="font-weight:700; color: #606266;" size="small" :style="{'border' : 'none'}">{{state.board.mbti}}</el-tag>
                <el-popover
                  placement="bottom"
                  width="200"
                  trigger="hover"
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
                    <tbody>
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


          </div>

          <el-divider class="mt-4 mb-5"/>

          <div >
            <div v-if="!state.board.isOwner">
              <el-button :style="{'width':'100%'}">상담 신청</el-button>
            </div>
            <div v-if="state.board.isOwner">
              <el-button :style="{'width':'100%'}">공고 수정</el-button>
            </div>
          </div>


        </div>
      </el-col>
    </el-row>

  <div class="box">
    {{state.board.description}}
  </div>

  <el-divider/>


  <div class="dog-image-box">
    <el-carousel :interval="4000" type="card" height="300px">
      <el-carousel-item v-for="item in 6" :key="item">
        <img src="{{item}}"/>
      </el-carousel-item>
    </el-carousel>
  </div>

  </el-card>

  </div>










    <!-- <el-pagination
      :page-size="20"
      :pager-count="11"
      layout="prev, pager, next"
      :total="1000">
    </el-pagination> -->
  </div>


</template>

<style scoped>
.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  display: block;
}



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
  width: 90%;
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
  margin-bottom : 40px;
  margin-left:50px;
  margin-right:50px;
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
export default {
  name: 'adopt-card',
  setup () {

  }
}
</script>
