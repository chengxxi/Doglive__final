<template>
  <el-row :gutter="20">
    <el-col :span="12" v-for="(o, idx) in result" :key="idx">
      <el-row :gutter="20" class=" grid-content bg-beige">
        <el-col :span="4">
          <div class="tag">
            <el-tag
              v-if="o.result == '승인'"
              color="#B4D9A7"
              effect="dark"
              size="large"
              style="border:none; font-size:16px; border-radius: 30px; font-weight:700;"
              >{{ o.result }}</el-tag
            >
            <el-tag
              v-if="o.result == '거절'"
              color="#A5A4A4"
              effect="dark"
              size="large"
              style="border:none; font-size:16px; border-radius: 30px; font-weight:700;  "
              >{{ o.result }}</el-tag
            >
            <el-tag
              v-if="o.result == '대기'"
              color="#87CEDC"
              effect="dark"
              size="large"
              style="border:none; font-size:16px; border-radius: 30px; font-weight:700; "
              >{{ o.result }}</el-tag
            >
          </div>
        </el-col>

        <el-col :span="9">
          <div class="apply-content">
            {{ o.boardType }} /
            {{
              o.boardTitle.length < 15
                ? o.boardTitle
                : o.boardTitle.substr(0, 14) + "..."
            }}

            <h3 style="font-weight:800;  ">
              {{
                o.dogName.length < 6 ? o.dogName : o.dogName.substr(0, 5) + ".."
              }}
            </h3>
          </div>
        </el-col>
        <el-col :span="3"> </el-col>
        <el-col :span="3">
          <div class="icon">
            <font-awesome-icon
              icon="file"
              aria-hidden="true"
              @click="readAdoptForm(o.id)"
              style="color: rgb(78, 78, 78); font-size: 20px; cursor: pointer; margin-left:350%;"
              class="scale-up-3"
            >
            </font-awesome-icon>
          </div>
        </el-col>
      </el-row>
    </el-col>
  </el-row>
</template>

<style scoped>
/* .el-row{
    display: inline-block;
  } */
.el-col {
  border-radius: 4px;
}
.bg-beige {
  background: linear-gradient(to top, #f3ede7, #f5e9e4);
  height: 90px;
}
.grid-content {
  display: flex;
  align-items: center;
  flex-direction: row;
  padding: 10px 0 10px 20px;
  border-radius: 15px;
  min-height: 36px;
  margin-bottom: 20px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.tag {
  vertical-align: middle;
  margin-right: 20px;
}
.apply-content {
  text-align: left;
  vertical-align: middle;
  margin-right: 20px;
}
.apply-content p {
  text-align: left;
  margin: 5px 0 5px 0;
}
.icon {
  text-align: right;
  margin-left: 20px;
}
</style>

<script>
import $axios from "axios";
import { onBeforeMount, onMounted, reactive, computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default {
  name: "user-apply-result-item",
  props: {
    result: {
      type: String
    }
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();
    const userid = store.getters["root/getLoginUserInfo"].userId;

    const state = reactive({
      boardList: [],
      board: computed(() => {
        console.log(store.getters["root/getBoardDetail"]);
        return store.getters["root/getBoardDetail"];
      })
    });

    const readAdoptForm = function(id) {
      store.commit("root/setAdoptFormId", id);
      router.push({ name: "AdoptFormReview" });
    };
    return { state, readAdoptForm };
  }
};
</script>
