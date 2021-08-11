<template>
  <div class="main-body main-padding">
    <el-card class="box-card " shadow="none" style="border:none;">
      <div>
        <bread-crumb></bread-crumb>
        <el-collapse class="mt-4 mb-4">
          <el-collapse-item title="입양 절차 안내" name="1">
            <div style="text-align:center;">
              <h4 class="mt-4 mb-3" style="font-weight:600">
                🚥 입양 절차 및 유의사항
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
          </el-collapse-item>
        </el-collapse>

        <span class="filter-wrapper">
          <el-button @click="showFilter = !showFilter" circle>
            <i class="el-icon-s-operation"></i>
          </el-button>
          <el-row v-show="showFilter" class="select-wrapper" :gutter="20">
            <!-- Filter Options -->
            <el-select
              v-model="value_type"
              clearable
              placeholder="공고타입"
              style="width:25%;"
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
              v-model="value_gender"
              clearable
              placeholder="성별"
              style="width:25%;"
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
              v-model="value_size"
              clearable
              placeholder="크기"
              style="width:25%;"
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
              v-model="value_age"
              clearable
              placeholder="연령"
              style="width:25%;"
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
            <el-col :span="18">
              <el-form-item style="width:100%">
                <el-input placeholder="이름" v-model="value_search"></el-input>
              </el-form-item>
            </el-col>
            <el-button @click="searchButon(value_search)">
              검색
            </el-button>
          </el-row>
        </span>
        <el-row>
          <el-button
            type="outline-primary"
            @click="goRegister"
            style="float:right; margin-right:10px; background-coldr:#f9f0e7; height:50px;"
            >글 작성하기
          </el-button>
        </el-row>
        <AdoptList />
      </div>
      <el-row>
        <el-col :span="24" style="margin-top:20px;">
          <Pagination
            :listRowCount="listRowCount"
            :pageLinkCount="pageLinkCount"
            :currentPageIndex="currentPageIndex"
            :houseListcount="houseListCount"
            @call-parent="movePage"
        /></el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import AdoptFilter from "./components/adopt-filter.vue";
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
      //목록 정보
      houseList: [],
      houseListCount: 0,
      limit: 10,
      offset: 0,

      //페이지네이션
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      // Color Option
      options_type: [
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

      value_type: "",
      value_gender: "",
      value_size: "",
      value_age: "",
      value_search: "",

      showFilter: true
    };
  },
  method: {
    btnSearchClick() {
      this.currentPageIndex = 1;
      this.offset = 0;
      this.searchList();
    },
    searchList() {
      console.log("search!");
    },
    // pagination
    movePage(pageIndex) {
      this.offset = (pageIndex - 1) * this.listRowCount;
      this.currentPageIndex = pageIndex;

      this.searchList();
    }
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      boardList: [],
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"].userId;
      })
    });

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

    const searchButton = function(data) {
      console.log(data);
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

    onMounted(() => {
      console.log("breadcrumb");
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "입양/임보",
        path: "/adopt",
        subTitle: "입양 공고 목록"
      });
    });

    return {
      state,
      searchButton,
      goRegister,
      goMBTI
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
  margin-left: 20px;
  color: #606266;
  font-size: 15px;
  font-weight: 600;
}

:deep(.el-button) {
  color: #755744;
  border-color: #f9f0e7;
  background-color: #f9f0e7;

  font-weight: 600;
}

:deep(.el-button--primary) {
  color: #f9f0e7;
  border-color: #755744;
  background-color: #755744;
}

:deep(.el-button:hover) {
  color: #f9f0e7;
  border-color: #755744;
  background-color: #755744;
}
</style>
