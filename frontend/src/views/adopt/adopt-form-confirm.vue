<template>
  <div class="main-body main-padding">
    <div style="width:100%; ">
      <bread-crumb></bread-crumb>
      <el-card
        class="box-card"
        style="width:100%; height:auto;  border:none;"
        shadow="none"
      >
        <div style="margin-left:30px; margin:right:30px;">
          <el-form
            style=" margin:100px;"
            label-position="left"
            :model="ruleForm"
            :rules="rules"
            ref="ruleForm"
            label-width="120px"
          >
            <div
              style="display: flex;
  justify-content: center; "
            >
              <h3
                class="mt-1 mb-3"
                style="font-weight:600; background-color:#fbe997"
              >
                입양은 "평생, 끝까지" 책임지는 일입니다.
              </h3>
            </div>
            <div
              style="display: flex;
  justify-content: center;"
            >
              <h6>반려동물은 물건이 아닌 생명이고 가족입니다.</h6>
            </div>
            <div
              style=" margin-bottom:100px; display: flex;
  justify-content: center;"
            >
              <h6>
                아이들이 두 번 상처받지 않도록 신중하게 고민하고 책임감을
                가져주세요.
              </h6>
            </div>

            <h4 class="mt-1 mb-3" style="font-weight:600">
              🚥 입양 절차 및 유의사항
            </h4>
            <div class="box" style="margin-bottom:60px;">
              <h5 style="font-weight:600">1. 입양 신청서 작성</h5>
              <div style="margin-left:20px; margin-bottom:40px;">
                <h6>
                  입양을 원하시는 분은 [입양 신청] 버튼을 누르시고, 입양신청서를
                  작성해 주세요.
                </h6>
                <h6>
                  🚫
                  <b>작성 내용이 사실과 다를 경우 입양이 취소될 수 있습니다.</b>
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

            <h4 class="mt-1 mb-3" style="font-weight:600">
              🚥 입양 자격 확인
            </h4>
            <div class="box" style=" margin-bottom:60px;">
              <h5 style="font-weight:600;">
                다음의 경우에는 입양이 어렵습니다.
              </h5>
              <div style="margin-left:20px; margin-top:20px;">
                <h6>
                  <font-awesome-icon :icon="['fas', 'check']" />
                  가정으로의 입양이 아닌 경우 ( 예 : 공장, 회사, 식당 등 )
                </h6>
                <h6>
                  <font-awesome-icon :icon="['fas', 'check']" />
                  미성년자의 입양 신청
                </h6>
                <h6>
                  <font-awesome-icon :icon="['fas', 'check']" />
                  가족 구성원 전체의 동의를 얻지 않은 경우
                </h6>
                <h6>
                  <font-awesome-icon :icon="['fas', 'check']" />
                  가족 구성원 중 동물에 대한 알레르기로 치료를 받고 있거나
                  동물에게 위해가 될 수 있는 정신 질환을 가진 경우
                </h6>
                <h6>
                  <font-awesome-icon :icon="['fas', 'check']" />
                  미취학 아동의 자녀가 3명 이상인 경우
                </h6>
                <h6>
                  <font-awesome-icon :icon="['fas', 'check']" />
                  70세 이상 혼자 거주하고 있는 경우
                </h6>
              </div>
            </div>
            <el-row
              class="mt-5"
              style=" display: flex;
  justify-content: center;"
            >
              <el-checkbox v-model="isAgree"
                >위 입양 절차에 동의하며 유의사항을 확인했습니다.</el-checkbox
              >
            </el-row>
            <el-row
              class="mt-5"
              style=" display: flex;
  justify-content: center;"
            >
              <el-button
                type="primary"
                @click="goAdoptForm()"
                :disabled="!isAgree"
                >입양 신청</el-button
              >
            </el-row>
          </el-form>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import BreadCrumb from "./components/bread-crumb.vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { computed, reactive, onMounted, ref, defineComponent } from "vue";

export default defineComponent({
  name: "AdoptFormConfirm",
  components: {
    BreadCrumb
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();

    const goAdoptForm = function() {
      router.push({ name: "AdoptForm" });
    };

    onMounted(() => {
      console.log("breadcrumb");
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "입양/임보",
        path: "/adopt",
        subTitle: "입양/임보 신청서 작성"
      });
      window.scrollTo(0, 0);
    });

    const isAgree = ref(false);

    return { isAgree, goAdoptForm };
  }
});
</script>

<style scoped>
:deep(.el-button) {
  font-weight: 600;
}
:deep(.el-button:hover) {
  color: #f9f0e7;
  border-color: #755744;
  background-color: #755744;
}

:deep(.el-radio-button__inner:hover) {
  color: #d7aea4;
}

:deep(.el-button--primary) {
  color: #755744;
  border-color: #f9f0e7;
  background-color: #f9f0e7;
}

:deep(.el-checkbox__input.is-checked + .el-checkbox__label) {
  background-color: #fbe997;
  color: #202020;
  font-weight: 600;
}

:deep(.el-checkbox__input.is-checked
    .el-checkbox__inner, .el-checkbox__input.is-indeterminate
    .el-checkbox__inner) {
  background-color: #755744;
  border-color: #755744;
}

.box {
  margin-top: 30px;
  margin-bottom: 40px;
  padding: 40px;
  background-color: #faf4ef;
}
</style>
