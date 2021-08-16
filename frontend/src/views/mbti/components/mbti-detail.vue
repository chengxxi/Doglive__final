<template>
  <div class="main-body main-padding">
    <el-card class="box-card" shadow="none" style="border:none;">

      <div style="margin-top:40px; margin-left:60px;"></div>

      <el-row class="vertical-center" :gutter="20">
        <el-col :span="12" style="margin-left:50px;">
          <img
            class="dog-thumbnail"
            :src="state.MbtiDetail.image_url"
          />
        </el-col>
        <el-col :span="12">
          <div class="dog-info-box" style="margin-right:50px;">
            <el-row>
              <el-tag
                class="mb-3  scale-up-2"
                color="#D7AFA4"
                effect="dark"
                size="large"
                style="border:none; border-radius: 30px; font-size:14pt; float:left;"
                >{{ state.MbtiDetail.name }}</el-tag
              >
            </el-row>

            <div class="vertical-center row">
              <div class="col-md-9">
                <span :style="{ 'font-size': '30pt', 'font-weight': '700' }">{{
                  state.MbtiDetail.name
                }}</span>
              </div>
              <div class="col-md-3 ms-auto">
                <div
                  class="align-self-center vertical-center"
                  style="text-align: center;"
                >

                </div>
              </div>
            </div>

            <el-divider class="mt-4 mb-4" />

            <el-descriptions class="margin-top mt-3" :column="1" :size="size">
              <el-descriptions-item label="MBTI">
                <el-tag
                  class="mb-2"
                  effect="dark"
                  style="height:30px; background:linear-gradient( to right, #D7AFA4, #E9CDA4, #B4D9A7, #87CEDC ); border:none;font-weight:700; color: #606266; "
                  >{{ state.mbtiList.id }}</el-tag
                >

                <el-popover placement="bottom" width="200" trigger="hover">
                  <div class="content">
                    <h3 style="font-weight:700;">강아지 MBTI 해석하기</h3>
                    <hr />

                    <table class="table table-borderless">
                      <thead>
                        <tr style="text-align:center;">
                          <th scope="col">
                            <h5 style="font-weight:700; color:#606266;">
                              활동성향
                            </h5>
                          </th>
                          <th scope="col">
                            <h5 style="font-weight:700; color:#606266;">
                              순종성향
                            </h5>
                          </th>

                          <th scope="col">
                            <h5 style="font-weight:700; color:#606266;">
                              관계성향
                            </h5>
                          </th>
                          <th scope="col">
                            <h5 style="font-weight:700; color:#606266;">
                              적응성향
                            </h5>
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>
                            <el-tag
                              class="m-3"
                              color="#D7AFA4"
                              effect="dark"
                              size="large"
                              :style="{ border: '3px solid #D7AFA4' }"
                              >E</el-tag
                            >에너지 (E) Energetic
                          </td>
                          <td>
                            <el-tag
                              class="m-3"
                              color="#E9CDA4"
                              effect="dark"
                              size="large"
                              :style="{ border: '3px solid #E9CDA4' }"
                              >S</el-tag
                            >충성심 강한 (S) Supportive
                          </td>

                          <td>
                            <el-tag
                              class="m-3"
                              color="#B4D9A7"
                              effect="dark"
                              size="large"
                              :style="{ border: '3px solid #B4D9A7' }"
                              >F</el-tag
                            >관계지향 (F) Friendly
                          </td>
                          <td>
                            <el-tag
                              class="m-3"
                              color="#87CEDC"
                              effect="dark"
                              size="large"
                              :style="{ border: '3px solid #87CEDC' }"
                              >P</el-tag
                            >신중한 (P) Prudent
                          </td>
                        </tr>
                        <tr>
                          <td>
                            <el-tag
                              class="m-3"
                              color="#FFFFFF"
                              style="border: 3px solid #D7AFA4; color: #606266;"
                              effect="dark"
                              size="large"
                              >I</el-tag
                            >침착한 (I) Introversion
                          </td>
                          <td>
                            <el-tag
                              class="m-3"
                              color="#FFFFFF"
                              style="border: 3px solid #E9CDA4; color: #606266;"
                              effect="dark"
                              size="large"
                              >N</el-tag
                            >영리한 (N) Naughty
                          </td>

                          <td>
                            <el-tag
                              class="m-3"
                              color="#FFFFFF"
                              style="border: 3px solid #B4D9A7; color: #606266;"
                              effect="dark"
                              size="large"
                              >T</el-tag
                            >독립지향 (T) independenT
                          </td>
                          <td>
                            <el-tag
                              class="m-3"
                              color="#FFFFFF"
                              style="border: 3px solid #87CEDC; color: #606266;"
                              effect="dark"
                              size="large"
                              >J</el-tag
                            >친화적인 (J) Jolly
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>

                  <template #reference>
                    <i
                      class="el-icon-question scale-up-2"
                      style="margin-left : 10px; cursor: pointer;"
                    />
                  </template>
                </el-popover>
              </el-descriptions-item>
            </el-descriptions>

            <el-divider class="mb-3" />

          </div>
        </el-col>
      </el-row>
      <!-- <div class="box">
        <h4 class="mb-2">
          <b>{{ state.title }}</b>
        </h4>
      </div> -->
      <el-divider />

      <!-- <div class="dog-image-box">
        <el-carousel :interval="4000" type="card" height="500px">
          <el-carousel-item v-for="item in 6" :key="item">
            <img
              style="  width: 100%;
              height: auto;"
              :src="require('@/assets/images/mbti_infj.png')"
            />
          </el-carousel-item>
        </el-carousel>
      </div> -->
    </el-card>
  </div>
</template>



<script>
import { computed, reactive, } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'


export default {
  name: 'MbtiDetail',

  setup() {
    const store = new useStore();
    const router = new useRouter();
    console.log(router)

    const state = reactive({
      MbtiDetail: computed(() => {
        console.log(store.getters['root/getMbti']);
        return store.getters['root/getMbti'];
      })
    });

    return { state, };
  }
};
</script>


<style scoped>
.main-body {
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
}
.main-padding {
  padding-top: 50px;
  padding-bottom: 50px;
}

h3 {
  margin-block-start: 0px;
}

:deep(.el-descriptions) {
  font-size: 13pt;
  font-weight: 500;
}
:deep(.el-descriptions__title) {
  font-size: 30pt;
  font-weight: 700;
}

.dog-thumbnail {
  width: 90%;
  height: auto;
  margin-right: 20px;
  margin: 10px;
}

.dog-info-box {
  width: 100%;
  margin-right: 30px;
  margin: 10px;
  padding-right: 20px;
}

:deep(.el-descriptions__label) {
  display: inline-block;
  width: 150px;
  font-weight: 500;
}

:deep(.el-descriptions__content) {
  display: inline-block;
  font-weight: 500;
}
:deep(.el-button) {
  font-size: 15pt;
  font-family: NEXONLv1Gothic;
  font-weight: 700;
  color: #f9f0e7;
}

.dog-image-box {
  margin: 30px;
  padding: 10px;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}


.el-col {
  display: flex;

  align-items: center;
}

.box-card {
  margin-top: 20px;
}

.box {
  margin-top: 30px;
  margin-bottom: 40px;
  margin-left: 50px;
  margin-right: 50px;
  padding: 40px;
  background-color: #f9f0e7;
}

.content {
  margin-top: 20px;
  margin-bottom: 20px;
  padding: 20px;
  background-color: #ffffff;
}

.el-card__body {
  display: flex;
  align-items: center;
  margin-right: auto;
}
</style>
