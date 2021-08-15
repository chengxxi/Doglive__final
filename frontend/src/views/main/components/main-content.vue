<template>
  <div></div>
  <div class="main-body main-padding">
    <div style="width:100%; ">
      <div class="row align-items-center">
        <div
          class="col-md-12 col-lg-12 py-6 text-sm-start text-center"
          style="margin-top:13%; margin-bottom:13%;"
        >
          <h1 class="fw-bold display-4 fs-4 fs-lg-6 fs-xxl-7 text-gradient">
            <span style="color:#D7AFA4;">당신</span>과
            <span style="color:#E9CDA4;">강아지</span>의 행복한 삶을 위해
          </h1>
          <h1 style="font-weight:700; font-size:80pt;">
            독립<span style="font-size:30pt; "> Dog Live</span>
          </h1>
          <p class="mb-5 fs-0">
            독립은 강아지들의 행복하고 안정적인 삶을 위해 노력합니다.
            <br class="d-none d-lg-block" />

            새로운 만남을 기다리는 아이들이 지금 여기, 어쩌고 저쩌고 어쩌고
            저쩌고
            <br class="d-none d-lg-block" />
            유기견들의 평생 가족이 되어 주세요. 어쩌고 저쩌고
          </p>
          <a class="btn hover-top btn-glow btn-klean" href="#"
            >독립 더 알아보기</a
          >
        </div>
      </div>

      <div id="main" style="margin-top:50px;">
        <div
          class="row align-items-center circle-blend circle-blend-right circle-warning"
        >
          <div class="col-lg-7 col-xxl-5 mx-auto text-center mb-6">
            <h5 class="fw-bold fs-3 fs-lg-5 lh-sm mb-3">신규 공고</h5>
            <p class="mb-5">
              새롭게 올라온 공고들을 확인해보세요 새롭게 올라온 공고들을
              <br />
              새롭게 올라온 공고들을 확인해보세요
            </p>
          </div>

          <el-row>
            <!-- <AdoptCard /> -->
            <span v-for="(card, idx) in state.adoptList" :key="idx">
              <el-col :span="6">
                <AdoptCard
                  :card="card"
                  @click="readDetail(card.boardId.id)"
                  style="margin:10px; "
                />
              </el-col>
            </span>
            <el-col :span="6">
              <MainCard />
            </el-col>
          </el-row>
          <el-row>
            <!-- <AdoptCard /> -->
            <span v-for="(card, idx) in state.findList" :key="idx">
              <el-col :span="6">
                <FindCard
                  :card="card"
                  @click="readDetail(card.boardId.id)"
                  style="margin:10px; "
                />
              </el-col>
            </span>
          </el-row>
          <div class="col-auto mx-auto my-3">
            <button
              class="scale-up-2 btn btn-klean-outline order-0 fs-0"
              type="submit"
            >
              <span class="text-gradient fw-bold" @click="goAdopt"
                >입양/임보 더 보러가기</span
              >
            </button>
            <button
              class="scale-up-2 btn btn-klean-outline order-0 fs-0"
              type="submit"
            >
              <span class="text-gradient fw-bold" @click="goFind"
                >실종/보호 더 보러가기</span
              >
            </button>
          </div>
        </div>
        <div class="py-5" id="blog" style="margin-top:120px;">
          <div class="container container-md-fluid">
            <!--/.bg-holder-->

            <div class="row">
              <div class="col-lg-7 col-xxl-5 mx-auto text-center mb-6">
                <h5 class="fw-bold fs-3 fs-lg-5 lh-sm mb-3">독립신문</h5>
                <p class="mb-5">
                  독립신문은 독립의 어쩌고 저쩌고 커뮤니티입니다. <br />
                  오늘의 신간 소식을 살펴보세요
                </p>
              </div>
            </div>
            <div
              class="row flex-center h-100 circle-blend circle-blend-left circle-primary"
            >
              <div class="col-12">
                <div
                  class="row align-items-center circle-blend circle-blend-right circle-warning"
                >
                  <div class="col-md-6 col-lg-4 mb-4 undefined">
                    <div class="card card-bg p-3 h-100">
                      <div class="card-body">
                        <h6 class="text-gradient fs--1 fw-bold">입양일기</h6>
                        <hr class="text-100" />

                        <div class="d-flex align-items-center">
                          <img
                            class="me-3 me-md-3"
                            style="width:100%; height:220px;"
                            src="https://doglive.s3.ap-northeast-2.amazonaws.com/static/20210415120439-puppy-1207816_960_720.webp"
                            alt="blog"
                          />
                        </div>
                        <div class="my-3 fs--1">
                          <p class="mb-0 fw-bold">Jessica Parker</p>
                          <p class="fw-normal mb-0">
                            May, 2020 .<span>5 mins read</span>
                          </p>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div
                    class="col-md-6 col-lg-4 mb-4 circle-blend circle-blend-left circle-pink"
                  >
                    <div class="card card-bg p-3 h-100">
                      <div class="card-body">
                        <h6 class="text-gradient fs--1 fw-bold">임보일기</h6>
                        <hr class="text-100" />

                        <div class="d-flex align-items-center">
                          <img
                            class="me-3 me-md-3"
                            style="width:100%; height:220px;"
                            src="https://doglive.s3.ap-northeast-2.amazonaws.com/img/logo2.png"
                            alt="blog"
                          />
                        </div>
                        <div class="my-3 fs--1">
                          <p class="mb-0 fw-bold">Jason Bourne</p>
                          <p class="fw-normal mb-0">
                            May, 2020 .<span>10 mins read</span>
                          </p>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6 col-lg-4 mb-4 undefined">
                    <div class="card card-bg p-3 h-100">
                      <div class="card-body">
                        <h6 class="text-gradient fs--1 fw-bold">나눔</h6>
                        <hr class="text-100" />

                        <div class="d-flex align-items-center">
                          <img
                            class="me-3 me-md-3"
                            style="width:100%; height:220px;"
                            src="https://doglive.s3.ap-northeast-2.amazonaws.com/static/20210415120439-dog-4390885_960_720.webp"
                            alt="blog"
                          />
                        </div>
                        <div class="my-3 fs--1">
                          <p class="mb-0 fw-bold">Wanda Maximoff</p>
                          <p class="fw-normal mb-0">
                            May, 2020 .<span>7 mins read</span>
                          </p>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-auto mx-auto my-3">
                    <button
                      class="scale-up-2  btn btn-klean-outline order-0 fs-0"
                      type="submit"
                    >
                      <span class="text-gradient fw-bold" @click="goCommunity"
                        >독립신문 더 보러가기</span
                      >
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- end of .container-->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FindCard from "@/views/find/components/find-card.vue";
import MainCard from "@/views/main/components/main-card.vue";
import AdoptCard from "@/views/adopt/components/adopt-card.vue";
import { computed, reactive, onMounted } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default {
  name: "MainContent",
  components: {
    FindCard,
    AdoptCard,
    MainCard
  },

  setup() {
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      adoptList: {},
      findList: {},
      communityList: {},
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"].userId;
      })
    });

    //최신 공고 읽어오기

    store
      .dispatch("root/readRecentBoard")
      .then(function(result) {
        console.log("readRecentData" + result.data.adoptList);
        state.adoptList = result.data.adoptList;
        state.findList = result.data.findList;
      })
      .catch(function(err) {
        console.log(err);
      });

    const readDetail = function(id) {
      console.log("read");

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
          console.log(result);

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

    const goCommunity = function() {
      router.push({ name: "Community" });
    };

    const goFind = function() {
      router.push({ name: "Find" });
    };

    const goAdopt = function() {
      router.push({ name: "Adopt" });
    };

    onMounted(() => {
      // readRecentBoard();
      window.scrollTo(0, 0);
    });

    return { state, readDetail, goCommunity, goAdopt, goFind };
  }
};
</script>

<style scoped>
.main-body {
  width: 100%;
  margin-left: 15%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 15%;
}
.main-padding {
  padding-top: 50px;
  padding-bottom: 50px;
}

:deep(.el-button--primary) {
  color: #755744;
  border-color: #f9f0e7;
  background-color: #f9f0e7;
}

:deep(.el-button) {
  font-weight: 600;
}
:deep(.el-button:hover) {
  color: #f9f0e7;
  border-color: #755744;
  background-color: #755744;
}

.margin-box {
  margin-top: 150px;
  margin-bottom: 150px;
}
</style>
