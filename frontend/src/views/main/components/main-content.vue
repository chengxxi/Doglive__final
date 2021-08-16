<template>
  <div></div>

  <div class="main-body  main-padding" id="top">
    <div class="py-0">
      <!--/.bg-holder-->

      <div class="container position-relative">
        <div class="row align-items-center">
          <div class="col-md-5 col-lg-6 order-md-1 pt-8">
            <img
              class="img-fluid"
              src="https://d2ud6j7vlf3xy9.cloudfront.net/img/%EC%9B%B0%EC%8B%9C%EC%BD%94%EA%B8%B0_%EB%A9%94%EC%9D%B8.png"
            />
          </div>
          <div class="col-md-7 col-lg-6 text-center text-md-start pt-5 pt-md-9">
            <div class="fw-bold display-4 fs-4 text-gradient mb-2">
              <span style="color:#D7AFA4;">당신</span>과
              <span style="color:#E9CDA4;">강아지</span>의 행복한 삶을 위해
            </div>
            <h1 style="font-weight:700; font-size:90pt;" class="mb-0">
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

            <el-button
              type="primary"
              class="btn btn-lg btn-primary rounded-pill hover-top"
              @click="goAdopt"
              >새로운 가족 만나러가기</el-button
            >
          </div>
        </div>
      </div>
    </div>
    <div class="py-3 mt-3"></div>
    <div class="py-6 py-lg-8" id="about">
      <div class="container">
        <div class="row g-xl-0 align-items-center">
          <div class="col-md-6">
            <img
              class="img-fluid mb-5 mb-md-0"
              src="https://d2ud6j7vlf3xy9.cloudfront.net/img/mbti_isfp.png"
              width="480"
              alt=""
            />
          </div>
          <div class="col-md-6 text-center text-md-start">
            <h2 class="fw-bold lh-base">
              유기견들의 <br /><span style="color:#D7AFA4;">따뜻한 새 가족</span
              >이 되어주세요
            </h2>
            <hr
              class="text-dark mx-auto mx-md-0"
              style="height:2px;width:50px"
            />
            <p class="pt-3">
              농림축산검역본부의 통계 자료에 따르면, 작년 한 해에만 13만 마리의
              동물이 유기 및 유실되었으며, 그 중 강아지는 약 10만 마리에
              달한다고 합니다.<br />
              안타깝게 실종되는 경우도 있지만, 주인에게 유기된 후 인도되거나
              입양되지 못하고 자연사하거나 안락사 되는 경우가 많습니다. 게다가
              새로운 주인에게 입양되는 비율이 불과 30%도 채 되지 않는다고
              합니다.<br />
              <br />독립은 유기견들이 행복한 가정을 다시 되찾을 수 있도록
              <b>실시간 화상 상담 및 채팅을 통한 입양 서비스</b> 를 제공합니다.
              <br /><br />유기견 입양 플랫폼 독립의<b> 비대면 소통 창구</b>를
              통해 <b>유기견 입양 문화가 활성화</b>되고, 더 많은 유기견들이
              따뜻한 집으로 돌아갈 수 있기를 기대합니다.<br />
            </p>
            <div class="py-3">
              <el-button
                type="primary"
                class="btn btn-lg btn-primary rounded-pill hover-top"
                @click="goAdopt"
                >입양/임보 공고 보러가기</el-button
              >
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="py-2 py-lg-4">
      <div class="container">
        <div class="row g-xl-0 align-items-center">
          <div class="col-md-6 order-md-1 text-md-end">
            <img
              class="img-fluid mb-5 mb-md-0"
              src="https://d2ud6j7vlf3xy9.cloudfront.net/img/mbti_infj.png"
              width="480"
              alt=""
            />
          </div>
          <div class="col-md-6 text-center text-md-start order-md-0">
            <h2 class="fw-bold lh-base">
              <span style="color:#E9CDA4;">가족의 품</span>으로 <br />돌아갈 수
              있도록
            </h2>
            <hr
              class="text-dark mx-auto mx-md-0"
              style="height:2px;width:50px"
            />
            <p class="pt-3">
              농림축산식품부의 전국 유기동물 현황 자료에 따르면, 유기동물이
              새로운 가족에게 입양되는 경우는 27%, 원래 가족을 찾는 경우는 14%에
              불과하다고 합니다.
              <br /><br />

              독립은 가족을 잃어버린 이들을 위해
              <b>실종/보호 공고 매칭 서비스</b>를 제공합니다.
              <br /><br />독립만의 매칭 알고리즘으로 실종, 보호 공고들을 분석해
              유사도 높은 공고를 상호 추천합니다. 또한 실시간 채팅 서비스를 통해
              실시간으로 목격 제보 및 상담을 할 수 있도록 지원합니다.<br />독립을
              통해 아이들이 하루빨리 가족의 품으로 돌아갈 수 있기를 바랍니다.
            </p>
            <div class="py-3">
              <el-button
                type="primary"
                class="btn btn-lg btn-primary rounded-pill hover-top"
                @click="goFind"
                >실종/보호 공고 보러가기
              </el-button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="py-6 py-lg-8" id="about">
      <div class="container">
        <div class="row g-xl-0 align-items-center">
          <div class="col-md-6">
            <img
              class="img-fluid mb-5 mb-md-0"
              src="https://d2ud6j7vlf3xy9.cloudfront.net/img/mbti_isfp.png"
              width="480"
              alt=""
            />
          </div>
          <div class="col-md-6 text-center text-md-start">
            <h2 class="fw-bold lh-base">
              나와 맞는 <br /><span style="color:#D7AFA4;">강아지</span>는
              누구일까?
            </h2>
            <hr
              class="text-dark mx-auto mx-md-0"
              style="height:2px;width:50px"
            />
            <p class="pt-3">
              asfsadfsafsdfs sfsfsfsad sfsfsfsdfsd asfsadfsadfasdf asfasdfsdf s
              asfsad sfsdfsd asfsadfsadfasdf asfasdsfsdfsd asfsadfsadfasdf
              asfasdsfsdfsd asfsadfsadfasdf asfasdsfsdfsd asfsadfsadfasdf
              asfasd<br />
              DBTI(*가제) 서비스를 제공합니다.
            </p>
            <div class="py-3">
              <el-button
                type="primary"
                class="btn btn-lg btn-primary rounded-pill hover-top"
                @click="goAdopt"
                >DBTI 하러가기</el-button
              >
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="py-lg-8" id="testimonial">
      <div class="container">
        <div
          class="carousel slide"
          id="carouselExampleDark"
          data-bs-ride="carousel"
        >
          <div class="carousel-inner">
            <div class="carousel-item active" data-bs-interval="5000">
              <div class="row h-100">
                <div class="col-12">
                  <div class="card  bg-primary-gradient">
                    <div class="card-body p-4 p-md-4 p-lg-7">
                      <h2 class="fw-bold text-center">
                        ✨ 당신을 기다리고 있어요 ✨
                      </h2>
                      <hr
                        class="text-dark mx-auto "
                        style="height:2px;width:50px"
                      />

                      <div
                        class="d-md-flex align-items-md-center mt-5 text-center text-md-start"
                      >
                        <AdoptCard
                          v-for="(card, idx) in state.adoptList"
                          :key="idx"
                          :card="card"
                          @click="readDetail(card.boardId.id)"
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="carousel-item" data-bs-interval="2000">
              <div class="row h-100">
                <div class="col-12">
                  <div class="card bg-primary-gradient">
                    <div class="card-body p-4 p-md-4 p-lg-7">
                      <h2 class="fw-bold text-center text-dark">
                        🐕‍🦺 집에 돌아가고 싶어요 🐕
                      </h2>
                      <hr class="mx-auto" style="height:2px;width:50px" />
                      <div
                        class="d-md-flex align-items-md-center mt-5 text-center text-md-start"
                      >
                        <FindCard
                          v-for="(card, idx) in state.findList"
                          :key="idx"
                          :card="card"
                          @click="readDetail(card.boardId.id)"
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row mt-4 flex-center">
            <div class="col-4 col-sm-5 text-end position-relative z-index-2">
              <a
                class="carousel-control-prev carousel-icon z-index-2"
                href="#carouselExampleDark"
                role="button"
                data-bs-slide="prev"
                ><span
                  class="carousel-control-prev-icon"
                  aria-hidden="true"
                ></span
                ><span class="visually-hidden">Previous</span></a
              >
            </div>
            <div class="col-auto position-relative z-index-2">
              <ol class="carousel-indicators">
                <li
                  class="active"
                  data-bs-target="#carouselExampleDark"
                  data-bs-slide-to="0"
                ></li>
                <li
                  data-bs-target="#carouselExampleDark"
                  data-bs-slide-to="1"
                ></li>
              </ol>
            </div>
            <div class="col-4 col-sm-5 position-relative z-index-2">
              <a
                class="carousel-control-next carousel-icon z-index-2"
                href="#carouselExampleDark"
                role="button"
                data-bs-slide="next"
                ><span
                  class="carousel-control-next-icon"
                  aria-hidden="true"
                ></span
                ><span class="visually-hidden">Next</span></a
              >
            </div>
          </div>
        </div>
      </div>
    </div>
    <div>
      <div class="container-lg py-6 py-lg-8">
        <div class="row flex-center">
          <div class="col-auto text-center">
            <h2 class="fw-bold">독립신문</h2>
            <hr class="mx-auto text-dark" style="height:2px;width:50px" />
            <p class="mb-3">
              독립신문은 행복한 유기동물 입양 경험을 공유하는 독립만의
              커뮤니티입니다. <br />
              자신의 유기동물 입양 경험을 나누면 또 다른 입양자가 나타날 수
              있습니다.<br />
              행복한 입양 후기를 통한 나비효과를 기대합니다. <br />
            </p>
          </div>
        </div>
        <div class="row h-100 justify-content-center pt-3">
          <div class="col-12 col-sm-9 col-md-4 mt-4">
            <div class="card h-100 hover-top rounded-3 shadow">
              <div class="card-body">
                <h6 class="text-gradient fs--1 fw-bold">입양일기</h6>
                <hr class="text-100" />

                <div class="d-flex align-items-center">
                  <img
                    class="me-3 me-md-3"
                    style="width:100%; height:300px;"
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
          <div class="col-12 col-sm-9 col-md-4 mt-4">
            <div class="card h-100 hover-top rounded-3 shadow">
              <div class="card-body">
                <h6 class="text-gradient fs--1 fw-bold">입양일기</h6>
                <hr class="text-100" />

                <div class="d-flex align-items-center">
                  <img
                    class="me-3 me-md-3"
                    style="width:100%; height:300px;"
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
          <div class="col-12 col-sm-9 col-md-4 mt-4">
            <div class="card h-100 hover-top rounded-3 shadow">
              <div class="card-body">
                <h6 class="text-gradient fs--1 fw-bold">입양일기</h6>
                <hr class="text-100" />

                <div class="d-flex align-items-center">
                  <img
                    class="me-3 me-md-3"
                    style="width:100%; height:300px;"
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
          <div class="text-center pt-4 z-index-2">
            <el-button
              type="primary"
              @clcik="goCommunity"
              class="btn btn-lg btn-primary rounded-pill hover-top"
              >더보기</el-button
            >
          </div>
        </div>
      </div>

      <div class="container-lg py-6 py-lg-8">
        <div class="row flex-center">
          <div class="col-auto text-center">
            <h2 class="fw-bold">팀 소개</h2>
            <hr class="mx-auto text-dark" style="height:2px;width:50px" />
            <p class="mb-3">
              당신과 강아지의 행복한 삶을 위해, <br />"독립" 서비스를 만들고
              있는 <b>犬과You</b> 팀을 소개합니다.
            </p>
          </div>
        </div>
        <div class="row h-100 justify-content-center pt-3">
          <div class="col-12 col-sm-9 col-md-4 mt-4">
            <div class="card h-100 hover-top rounded-3 shadow">
              <div class="card-body">
                <h6 class="text-gradient fs--1 fw-bold">입양일기</h6>
                <hr class="text-100" />

                <div class="d-flex align-items-center">
                  <img
                    class="me-3 me-md-3"
                    style="width:100%; height:300px;"
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
          <div class="col-12 col-sm-9 col-md-4 mt-4">
            <div class="card h-100 hover-top rounded-3 shadow">
              <div class="card-body">
                <h6 class="text-gradient fs--1 fw-bold">입양일기</h6>
                <hr class="text-100" />

                <div class="d-flex align-items-center">
                  <img
                    class="me-3 me-md-3"
                    style="width:100%; height:300px;"
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
          <div class="col-12 col-sm-9 col-md-4 mt-4">
            <div class="card h-100 hover-top rounded-3 shadow">
              <div class="card-body">
                <h6 class="text-gradient fs--1 fw-bold">입양일기</h6>
                <hr class="text-100" />

                <div class="d-flex align-items-center">
                  <img
                    class="me-3 me-md-3"
                    style="width:100%; height:300px;"
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
          <div class="text-center pt-4 z-index-2">
            <el-button
              type="primary"
              @clcik="goCommunity"
              class="btn btn-lg btn-primary rounded-pill hover-top"
              >더보기</el-button
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FindCard from "@/views/main/components/main-find-card.vue";
import AdoptCard from "@/views/main/components/main-adopt-card.vue";
import { computed, reactive, onMounted } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default {
  name: "MainContent",
  components: {
    FindCard,
    AdoptCard
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
@import "../../../common/css/main-content.css";

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

:deep(.main-container) {
  margin-top: 0px !important;
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

* {
  color: rgb(51, 44, 44);
}
</style>
