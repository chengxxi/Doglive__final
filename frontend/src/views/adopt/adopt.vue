<template>
  <div class="main-body main-padding">
    <div style="border:none; width:100%;">
      <div>
        <bread-crumb></bread-crumb>

        <el-row style="margin-right:5px; margin-top:20px; margin-bottom:15px;">
          <el-button
            type="primary"
            @click="goRegister"
            style="float:right; margin-top:20px; font-weight:600;  margin-right:5px; height:40px;"
            >글 작성하기<i
              class="el-icon-circle-plus "
              style="margin-left : 10px; font-size:15px; cursor: pointer;"
            />
          </el-button>

          <el-button
            type="info"
            class="scale-up-2"
            data-bs-toggle="collapse"
            href="#notice"
            style="float:left; font-size:20px;margin-top:20px; height:40px;"
          >
            <i class="el-icon-info " style=" cursor: pointer;"
          /></el-button>
          <el-button
            type="info"
            @click="showFilter = !showFilter"
            style="float:right; margin-top:20px; font-weight:600; margin-right:15px; height:40px;"
          >
            {{ showFilter ? " 필터 OFF " : " 필터 ON " }}
            <i class="el-icon-s-operation"></i>
          </el-button>
        </el-row>

        <div class="collapse" id="notice">
          <div class="card card-body">
            <div style="text-align:center;">
              <h4 class="mt-5 mb-3" style="font-weight:600;">
                🚥 입양 절차 및 유의사항 🚥
              </h4>

              <div class="box" style="margin-bottom:60px;">
                <h5 style="font-weight:600">1. 입양 신청서 작성</h5>
                <div style="margin-left:20px; margin-bottom:40px;">
                  <h6>
                    입양을 원하시는 분은 [입양 신청] 버튼을 누르시고,
                    입양신청서를 작성해 주세요.
                  </h6>
                  <h6>
                    🚫
                    <b
                      >작성 내용이 사실과 다를 경우 입양이 취소될 수
                      있습니다.</b
                    >
                  </h6>
                </div>

                <h5 style="font-weight:600">2. 채팅 및 화상상담</h5>
                <div style="margin-left:20px; margin-bottom:40px;">
                  <h6>
                    채팅 및 화상상담 임보자가 신청서 검토 및 승인 후 개별 면담이
                    시작됩니다.
                  </h6>
                  <h6>
                    입양 신청자는 채팅 및 화상회의를 통해 개별 면담을 진행하게
                    됩니다.
                  </h6>
                  <h6>
                    이 상담 결과로 최종 입양이 결정됩니다.
                  </h6>
                </div>

                <h5 style="font-weight:600">3. 입양동의서 작성 및 입양 확정</h5>
                <div style="margin-left:20px;">
                  <h6>
                    입양이 결정되면 대면 만남을 통해 입양 동의서 작성 후 모든
                    절차가 마무리 됩니다.
                  </h6>
                  <h6>
                    🚫
                    <b
                      >단, 방문시 사실과 다르거나 입양자격이 부족하다고 판단되면
                      입양이 취소될 수 있습니다.</b
                    >
                  </h6>
                </div>
              </div>
            </div>
          </div>
        </div>
        <span class="filter-wrapper">
          <!-- <el-button @click="showFilter = !showFilter" circle>
            <i class="el-icon-s-operation"></i>
          </el-button> -->

          <el-row
            v-show="showFilter"
            style="background:linear-gradient( to top,  #f3ede7, #f5e9e4 ); margin-bottom:10px; margin-left:10px; margin-right:10px; padding-left:15px; padding-right:15px; padding-top:20px;"
          >
            <el-col :span="20">
              <el-row style="height:50%; " class="select-wrapper" :gutter="20">
                <!-- Filter Options -->
                <el-select
                  v-model="state.boardTypeCode"
                  clearable
                  placeholder="분류"
                  style="width:25%; padding-left:15px;padding-right:30px;"
                >
                  <el-option
                    v-for="type in options_type"
                    :key="type.value"
                    :label="type.label"
                    :value="type.value"
                  >
                  </el-option>
                </el-select>
                <el-select
                  v-model="state.genderCode"
                  clearable
                  placeholder="성별"
                  style="width:25%; padding-right:30px;"
                >
                  <el-option
                    v-for="gender in options_gender"
                    :key="gender.value"
                    :label="gender.label"
                    :value="gender.value"
                  >
                  </el-option>
                </el-select>

                <el-select
                  v-model="state.weightCode"
                  clearable
                  placeholder="크기"
                  style="width:25%;padding-right:30px;"
                >
                  <el-option
                    v-for="size in options_size"
                    :key="size.value"
                    :label="size.label"
                    :value="size.value"
                  >
                  </el-option>
                </el-select>

                <el-select
                  v-model="state.ageCode"
                  clearable
                  placeholder="연령"
                  style="width:25%;padding-right:30px;"
                >
                  <el-option
                    v-for="age in options_age"
                    :key="age.value"
                    :label="age.label"
                    :value="age.value"
                  >
                  </el-option>
                </el-select>
              </el-row>
              <el-row>
                <el-form-item
                  style="width:100%; height:50%; padding-left:5px; padding-right:20px; margin-top:20px;"
                >
                  <el-input
                    placeholder="이름 또는 제목으로 검색 가능합니다"
                    v-on:keyup.enter="readData"
                    v-model="state.searchWord"
                  ></el-input>
                </el-form-item>
              </el-row>
            </el-col>
            <el-col :span="4">
              <el-button
                @click="resetData"
                type="warning"
                style=" width:100%; font-weight:600; border:none; height:100%; float:right;  margin-right:5px;"
              >
                초기화<i
                  class="el-icon-refresh "
                  style="margin-left : 10px; cursor: pointer;"
                />
              </el-button>
              <el-button
                @click="searchData"
                type="primary"
                style=" width:100%; font-weight:600;   height:100%; float:right; margin-right:5px;  margin-top:20px;  "
              >
                검색<i
                  class="el-icon-search "
                  style="margin-left : 10px; cursor: pointer;"
                />
              </el-button>
            </el-col>
          </el-row>
        </span>
      </div>
      <el-row>
        <el-button
          type="info"
          class="scale-up-2"
          @click="newestSort"
          style="float:left;  color:#727272; height:30px; margin-left:20px; padding-left:0px; padding-right:0px;  padding-bottom:0px;"
          >최신순
        </el-button>
        <el-button
          type="info"
          style="float:left;  color:#727272; font-weight:500; height:30px;width:5px;padding-left:0px; padding-right:0px;padding-bottom:0px;"
          >|
        </el-button>
        <el-button
          type="info"
          class="scale-up-2"
          @click="oldestSort"
          style="float:left;height:30px;  color:#727272; padding-left:0px; padding-right:0px;padding-bottom:0px; "
          >오래된순
        </el-button>
      </el-row>
      <el-row v-if="state.boardListCount == 0">
        <el-empty
          style="margin-top:80px; margin-bottom:50px;"
          description="검색 결과가 없다개 💨"
          image=""
        ></el-empty>
      </el-row>
      <el-row v-if="state.boardListCount != 0">
        <AdoptList :boardList="state.boardList" />
      </el-row>
      <el-col :span="24" style="margin-top:20px;">
        <Pagination
          :listRowCount="state.listRowCount"
          :pageLinkCount="state.pageLinkCount"
          :currentPageIndex="state.currentPageIndex"
          :boardListCount="state.boardListCount"
          @call-parent="movePage"
        />
      </el-col>
    </div>
  </div>
</template>

<script>
import AdoptList from "./components/adopt-list.vue";
import Pagination from "./components/pagination.vue";
import BreadCrumb from "./components/bread-crumb.vue";
import { reactive, computed, onMounted } from "vue"; // ref

// import $axios from 'axios'

import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "adoptlist",
  components: {
    AdoptList,
    BreadCrumb,
    Pagination
  },
  data() {
    return {
      // Color Option
      options_type: [
        {
          value: "",
          label: "분류"
        },
        {
          value: 1,
          label: "입양"
        },
        {
          value: 2,
          label: "임보"
        }
      ],
      // Gender Option
      options_gender: [
        {
          value: "",
          label: "성별"
        },
        {
          value: 8,
          label: "여"
        },
        {
          value: 9,
          label: "남"
        }
      ],
      // Size Option
      options_size: [
        {
          value: "",
          label: "크기"
        },
        {
          value: 1,
          label: "소(8kg 미만)"
        },
        {
          value: 2,
          label: "중(8kg-18kg 미만)"
        },
        {
          value: 3,
          label: "대(18kg 이상)"
        }
      ],
      // Age Option
      options_age: [
        {
          value: "",
          label: "연령"
        },
        {
          value: 4,
          label: "Puppy(~ 6개월)"
        },
        {
          value: 5,
          label: "Junior(7개월 ~ 2살)"
        },
        {
          value: 6,
          label: "Adult(3살 ~ 8살)"
        },
        {
          value: 7,
          label: "Senior(9살 ~)"
        }
      ],

      showFilter: true
    };
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      //검색용
      dogTypeList: [],

      dialogDataVisible: false,
      boardList: [],
      boardListCount: 0,
      limit: 12,
      offset: 0,
      searchWord: "",
      age: [],
      weight: [],
      boardType: [],
      gender: [],
      ageCode: "",
      weightCode: "",
      boardTypeCode: "",
      genderCode: "",
      sort: "boardId.regDate,desc",

      //페이지네이션
      listRowCount: 12,
      pageLinkCount: 12,
      currentPageIndex: 1,

      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"].userId;
      })
    });

    //글 등록하기
    const goRegister = function() {
      if (
        state.userId === null ||
        state.userId == "" ||
        state.userId === undefined
      ) {
        createToast("로그인해야 이용 가능하개🐕‍🦺💨", {
          hideProgressBar: "true",
          timeout: 4500,
          showIcon: "true",
          toastBackgroundColor: "#c49d83",
          position: "bottom-left",
          transition: "bounce",
          type: "warning"
        });
        router.push({ name: "Login" });
      } else {
        router.push({ name: "AdoptRegister" });
      }
    };

    //목록 정보 가져오기
    const readData = function() {
      store
        .dispatch("root/requestBoardList", {
          page: state.currentPageIndex,
          sort: state.sort,
          size: state.limit,
          isAdopt: true,
          searchWord: state.searchWord,
          age: state.ageCode,
          gender: state.genderCode,
          boardType: state.boardTypeCode,
          weight: state.weightCode
        })
        .then(function(result) {
          state.boardList = result.data.boardList.content;
          state.boardListCount = result.data.boardList.totalElements;
        });
    };

    //필터 리셋
    const resetData = function() {
      state.boardList = [];
      state.boardListCount = 0;
      state.offset = 0;
      state.searchWord = "";
      state.ageCode = "";
      state.weightCode = "";
      state.boardTypeCode = "";
      state.genderCode = "";
      state.sort = "boardId.regDate,desc";

      readData();
    };

    //검색 버튼 클릭 => 페이지 초기화
    const searchData = function() {
      state.currentPageIndex = 1;
      state.offset = 0;
      state.sort = "boardId.regDate,desc";
      readData();
    };

    const goMBTI = function() {
      // if 사용자가 아직 테스트 전이면
      createToast("아직 테스트 결과가 없어요.", {
        hideProgressBar: "true",
        timeout: 4500,
        showIcon: "true",
        toastBackgroundColor: "#c49d83",
        position: "bottom-left",
        transition: "bounce",
        type: "warning"
      });
      router.push({ name: "MBTI" });

      // else 사용자 테스트 결과가 있으면 필터링
    };

    // const enterKey = function() {
    //   if (window.event.keyCode == 13) readData();
    // };

    // pagination
    const movePage = function(pageIndex) {
      state.offset = (pageIndex - 1) * state.listRowCount;
      state.currentPageIndex = pageIndex;
      readData();
    };

    //날짜 최신순 정렬(default)
    const newestSort = function() {
      state.sort = "boardId.regDate,desc";
      readData();
    };

    //날짜 오래된 순 정렬
    const oldestSort = function() {
      state.sort = "";
      readData();
    };

    onMounted(() => {
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "입양/임보",
        path: "/adopt",
        subTitle: "입양 공고 목록"
      });
      readData();
      window.scrollTo(0, 0);
    });

    return {
      state,
      movePage,
      goRegister,
      goMBTI,
      readData,
      resetData,
      searchData,
      newestSort,
      oldestSort
    };
  }
};
</script>

<style scoped>
.main-body {
  width: 100%;
  margin-left: 10%;
  margin-right: 10%;
}
.main-padding {
  padding-top: 50px;
  padding-bottom: 50px;
}

:deep(.el-collapse-item__header) {
  border-radius: 30px;
  margin-left: 5px;
  margin-right: 5px;
  color: #f9f0e7;
  font-size: 15px;
  font-weight: 600;
  background-color: #755744a2;
}

:deep(.el-button) {
  /* color: #755744;
  border-color: #f9f0e7;
  background-color: #f9f0e7; */
}

:deep(.el-button:hover) {
  /* color: #755744;
  border-color: #f9f0e7;
  background-color: #f9f0e7; */

  font-weight: 600;
}

:deep(.el-button--primary) {
  color: #f9f0e7;
  border-color: #755744;
  background-color: #755744;
}

:deep(.el-button--primary:hover) {
  color: #755744;
  border-color: #75574488;
  background-color: #75574488;
}

:deep(.el-button--primary:focus) {
  color: #755744;
  border-color: #75574488;
  background-color: #75574488;
}

:deep(.el-button--warning) {
  color: #755744;
  border-color: #f9f0e7;
  background-color: #f9f0e700;
}

:deep(.el-button--warning:hover) {
  color: #755744;
  border-color: #75574488;
  background-color: #75574488;
}

:deep(.el-button--warning:focus) {
  color: #755744;
  border-color: #75574488;
  background-color: #75574488;
}

:deep(.el-select-dropdown__item.selected) {
  color: #755744;
}
:deep(.el-select .el-input.is-focus .el-input__inner) {
  border-color: #755744;
}

:deep(.el-button--info) {
  color: #755744;
  border-color: #fff;
  background-color: #fff;
}

:deep(.el-button--info:hover) {
  font-weight: 600;
}

:deep(.el-button--info:focus) {
  font-weight: 600;
}

:deep(.el-select .el-input__inner) {
  font-weight: 600;
  color: #755744;
}

li.el-select-dropdown__item.selected {
  color: #755744;
  font-weight: 700;
}

/* :deep(.el-collapse-item__arrow) {
  display: none;
} */
</style>
