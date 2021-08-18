<template>
  <div class="main-body main-padding">
    <bread-crumb></bread-crumb>
    <el-card class="box-card" shadow="none" style="border:none;">
      <div style="margin-top:40px; margin-left:60px;">
        <el-row>
          <el-button
            class="mb-2"
            effect="dark"
            size="small"
            style="margin-left:10px; background:linear-gradient( to right, #D7AFA4, #E9CDA4, #B4D9A7, #87CEDC ); font-size:16px; padding:7px; border:none; font-weight:600;  color:#FFFFFF; float:left;"
          >
            {{ state.MbtiDetail.name }}
          </el-button>
          <el-popover placement="right" width="200" trigger="hover">
            <div class="content">
              <h4 style="font-weight:700;">강아지 MBTI 해석하기</h4>
              <hr />

              <table class="table table-borderless">
                <thead>
                  <tr style="text-align:center;">
                    <th scope="col">
                      <h6 style="font-weight:700; color:#606266;">
                        활동성향
                      </h6>
                    </th>
                    <th scope="col">
                      <h6 style="font-weight:700; color:#606266;">
                        순종성향
                      </h6>
                    </th>

                    <th scope="col">
                      <h6 style="font-weight:700; color:#606266;">
                        관계성향
                      </h6>
                    </th>
                    <th scope="col">
                      <h6 style="font-weight:700; color:#606266;">
                        적응성향
                      </h6>
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>
                      <el-col>
                        <el-tag
                          class="m-3"
                          color="#D7AFA4"
                          effect="dark"
                          size="large"
                          :style="{ border: '3px solid #D7AFA4' }"
                          >E</el-tag
                        >에너지 <br />
                        Energetic</el-col
                      >
                    </td>
                    <td>
                      <el-col>
                        <el-tag
                          class="m-3"
                          color="#E9CDA4"
                          effect="dark"
                          size="large"
                          :style="{ border: '3px solid #E9CDA4' }"
                          >S</el-tag
                        >충성심강한 <br />
                        Supportive</el-col
                      >
                    </td>

                    <td>
                      <el-col>
                        <el-tag
                          class="m-3"
                          color="#B4D9A7"
                          effect="dark"
                          size="large"
                          :style="{ border: '3px solid #B4D9A7' }"
                          >F</el-tag
                        >관계지향 <br />
                        Friendly</el-col
                      >
                    </td>
                    <td>
                      <el-col>
                        <el-tag
                          class="m-3"
                          color="#87CEDC"
                          effect="dark"
                          size="large"
                          :style="{ border: '3px solid #87CEDC' }"
                          >P</el-tag
                        >신중한 <br />Prudent</el-col
                      >
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <el-col>
                        <el-tag
                          class="m-3"
                          color="#FFFFFF"
                          style="border: 3px solid #D7AFA4; color: #606266;"
                          effect="dark"
                          size="large"
                          >I</el-tag
                        >침착한 <br />Introversion</el-col
                      >
                    </td>
                    <td>
                      <el-col>
                        <el-tag
                          class="m-3"
                          color="#FFFFFF"
                          style="border: 3px solid #E9CDA4; color: #606266;"
                          effect="dark"
                          size="large"
                          >N</el-tag
                        >영리한 <br />
                        Naughty</el-col
                      >
                    </td>

                    <td>
                      <el-col>
                        <el-tag
                          class="m-3"
                          color="#FFFFFF"
                          style="border: 3px solid #B4D9A7; color: #606266;"
                          effect="dark"
                          size="large"
                          >T</el-tag
                        >독립지향 <br />
                        independenT</el-col
                      >
                    </td>
                    <td>
                      <el-col>
                        <el-tag
                          class="m-3"
                          color="#FFFFFF"
                          style="border: 3px solid #87CEDC; color: #606266;"
                          effect="dark"
                          size="large"
                          >J</el-tag
                        >친화적인 <br />
                        Jolly</el-col
                      >
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
        </el-row>

        <div class="col-md-9">
          <span :style="{ 'font-size': '50pt', 'font-weight': '700' }">
            {{ state.MbtiDetail.title }}
          </span>
        </div>
      </div>
      <el-row class="vertical-center" :gutter="20">
        <el-col :span="10" style="margin-left:50px;">
          <img
            class="dog-thumbnail fit-image"
            :src="state.MbtiDetail.image_url"
          />
        </el-col>
        <el-col :span="14">
          <div class="dog-info-box">
            <div class="box">
              <h6
                class="mb-2 "
                style="font-size:16px; line-height: 3rem; white-space:pre-wrap;"
              >
                {{ state.MbtiDetail.desc }}
              </h6>
            </div>
          </div>
        </el-col>
      </el-row>

      <!-- 유사 공고 -->
      <!-- 조건 걸기 -->

      <div class="dog-image-box">
        <h5
          class="pt-3 pb-3"
          style="font-weight:600; padding-left:20px; margin-right:20px; background:linear-gradient( to bottom,#f3e8dc, #f5edea );"
        >
          저랑 잘 맞으실 것 같아요😘🐶
        </h5>
        <!-- 유사공고 -->
        <div v-if="state.MbtiDetail.matchedBoardList != null">
          <el-scrollbar>
            <div class="flex-content">
              <p
                class="item"
                v-for="(card, idx) in state.MbtiDetail.matchedBoardList"
                :key="idx"
                style="width:360px; margin: 5px; display:inline-block"
              >
                <AdoptCard
                  :card="card"
                  @click="readDetail(card.boardId.id)"
                  style="margin:10px; width:360px;"
                />
              </p>
            </div>
          </el-scrollbar>
        </div>
        <div v-else>
          <el-empty
            description="같은 MPTI를 가진 아이가 없네요😢"
            image="https://d2ud6j7vlf3xy9.cloudfront.net/img/KakaoTalk_20210816_223416590.png"
            image-size="300"
          >
          </el-empty>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import { computed, reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
// import 'mosha-vue-toastify/dist/style.css'
import AdoptCard from "@/views/adopt/components/adopt-card.vue";
import BreadCrumb from "@/views/adopt/components/bread-crumb.vue";
import { onMounted } from "vue";

export default {
  name: "MbtiDetail",
  components: {
    AdoptCard,
    BreadCrumb
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();
    console.log(router);

    const state = reactive({
      MbtiDetail: computed(() => {
        return store.getters["root/getMbti"];
      }),
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"].userId;
      })
    });

    const readDetail = function(id) {
      var checkId = state.userId;
      if (checkId === undefined || checkId === null || checkId == "") {
        checkId = "none";
      }

      store
        .dispatch("root/requestBoardDetail", {
          boardId: id,
          userId: checkId
        })
        .then(function(result) {
          const boardDetail = {
            boardId: result.data.dogInformation.boardId.id,
            boardType: result.data.dogInformation.boardId.type,
            thumbnailUrl: result.data.dogInformation.boardId.thumbnailUrl,
            title: result.data.dogInformation.boardId.title,
            address: result.data.dogInformation.address,
            mbti: result.data.dogInformation.mbti,
            colorType: result.data.dogInformation.colorType,
            gender: result.data.dogInformation.gender,
            dogType: result.data.dogInformation.dogType,
            neutralization: result.data.dogInformation.neutralization,
            writer: result.data.writer,
            weight: result.data.dogInformation.weight,
            ageType: result.data.dogInformation.age,
            regDate: result.data.dogInformation.boardId.regDate,
            fileList: result.data.boardImageList,
            isOwner: result.data.owner,
            gugun: result.data.dogInformation.gugun,
            sido: result.data.dogInformation.gugun.sidoCode,
            description: result.data.dogInformation.description,
            dogName: result.data.dogInformation.dogName,
            isBookmarked: result.data.bookmarked
          };

          store.commit("root/setBoardDetail", boardDetail);
          router.push({ name: "AdoptDetail" });
        })
        .catch(function(err) {
          console.log(err);
        });
    };

    onMounted(() => {
      // console.log("breadcrumb");
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "MPTI",
        path: "/mbti",
        subTitle: state.MbtiDetail.name
      });
      window.scrollTo(0, 0);
    });

    return { state, readDetail };
  }
};
</script>

<style scoped>
.main-body {
  width: 80%;
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

.fit-image {
  object-fit: contain;
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

::-webkit-scrollbar {
  width: 10px;
}

::-webkit-scrollbar-thumb {
  background: linear-gradient(to bottom, #f3e8dc, #f5edea);
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}

::-webkit-scrollbar-track {
  background-color: rgb(192, 186, 178);
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}

.flex-content {
  white-space: nowrap;
  overflow-x: auto;
  margin: 0;
  max-width: 99%;
}
</style>
