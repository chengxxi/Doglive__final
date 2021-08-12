<template>
  <div class="main-body main-padding">
    <div style="border:none; width:100%;">
      <div>
        <bread-crumb></bread-crumb>

        <el-row style="margin-right:5px; margin-top:20px; margin-bottom:15px;">
          <el-button
            class="scale-up-2"
            type="primary"
            @click="goRegister"
            style="float:right; margin-top:20px;  margin-right:5px; height:40px;"
            >글 작성하기<i
              class="el-icon-circle-plus "
              style="margin-left : 10px; cursor: pointer;"
            />
          </el-button>
        </el-row>

        <span class="filter-wrapper">
          <!-- <el-button @click="showFilter = !showFilter" circle>
            <i class="el-icon-s-operation"></i>
          </el-button> -->

          <el-row
            style="background-color:#f9f4f0; margin-bottom:30px; margin-left:10px; margin-right:10px; padding-left:15px; padding-right:15px; padding-top:20px;"
          >
            <el-col :span="20">
              <el-row
                v-show="showFilter"
                style="height:50%; "
                class="select-wrapper"
                :gutter="20"
              >
                <!-- Filter Options -->
                <el-select
                  label="공고타입"
                  v-model="state.boardTypeCode"
                  clearable
                  placeholder="공고타입"
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
                  placeholder="색상"
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
                class="scale-up-2"
                style=" width:100%;  height:100%; float:right;  margin-right:5px;"
              >
                초기화<i
                  class="el-icon-refresh "
                  style="margin-left : 10px; cursor: pointer;"
                />
              </el-button>
              <el-button
                @click="searchData"
                type="primary"
                class="scale-up-2"
                style=" width:100%;   height:100%; float:right; margin-right:5px;  margin-top:20px;  "
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
          style="float:left; height:30px; margin-left:20px; padding-left:0px; padding-right:0px;  padding-bottom:0px;"
          >최신순
        </el-button>
        <el-button
          type="info"
          style="float:left; font-weight:500; height:30px;width:5px;padding-left:0px; padding-right:0px;padding-bottom:0px;"
          >|
        </el-button>
        <el-button
          type="info"
          class="scale-up-2"
          @click="oldestSort"
          style="float:left;height:30px; padding-left:0px; padding-right:0px;padding-bottom:0px; "
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
        <FindList :boardList="state.boardList" />
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
import FindList from "./components/find-list.vue";
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
    FindList,
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
          value: 3,
          label: "실종"
        },
        {
          value: 4,
          label: "보호"
        }
      ],
      // Gender Option
      options_gender: [
        {
          value: "",
          label: "색상"
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
          label: "지역"
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
          label: "품종"
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
      sort: "",

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
      if (state.userId === null) {
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
          isAdopt: false,
          searchWord: state.searchWord,
          age: state.ageCode,
          gender: state.genderCode,
          boardType: state.boardTypeCode,
          weight: state.weightCode
        })
        .then(function(result) {
          console.log("success search!");
          console.log(result);
          state.boardList = result.data.boardList.content;
          state.boardListCount = result.data.boardList.totalElements;
          store.commit("root/setBoardList", result.data.boardList.content);
          store.commit(
            "root/setBoardTotalListItemCnt",
            result.data.totalElements
          );
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
      state.sort = "";

      readData();
    };

    //검색 버튼 클릭 => 페이지 초기화
    const searchData = function() {
      state.currentPageIndex = 1;
      state.offset = 0;
      state.sort = "";
      readData();
    };

    const goMBTI = function() {
      // if 사용자가 아직 테스트 전이면
      createToast("아직 테스트 결과가 없어요.", {
        hideProgressBar: "true",
        timeout: 4500,
        showIcon: "true",
        toastBackgroundColor: "#c49d83",
        position: "bottom-right",
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
      console.log(state.offset, state.currentPageIndex);
      readData();
    };

    //날짜 최신순 정렬(default)
    const newestSort = function() {
      state.sort = "";
      readData();
    };

    //날짜 오래된 순 정렬
    const oldestSort = function() {
      state.sort = "boardId.regDate,desc";
      readData();
    };

    //강아지 품종 데이터 읽어오기
    const readDogTypeList = function() {
      store.dispatch("root/requestDogTypeList").then(function(result) {
        console.log(result);
        state.dogTypeList = result.data.dogTypeList;
      });
    };

    onMounted(() => {
      console.log("breadcrumb");
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "실종/보호",
        path: "/find",
        subTitle: "실종 공고 목록"
      });
      readData();
      readDogTypeList();
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
      oldestSort,
      readDogTypeList
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
  border-color: #d7afa49c;
  background-color: #d7afa49c;
}

:deep(.el-button--primary:focus) {
  color: #755744;
  border-color: #d7afa49c;
  background-color: #d7afa49c;
}

:deep(.el-button--warning) {
  color: #755744;
  border-color: #f9f0e7;
  background-color: #f9f0e700;
}

:deep(.el-button--warning:hover) {
  color: #755744;
  border-color: #d7afa49c;
  background-color: #d7afa49c;
}

:deep(.el-button--warning:focus) {
  color: #755744;
  border-color: #d7afa49c;
  background-color: #d7afa49c;
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
