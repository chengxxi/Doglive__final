<template>
  <div>
    <main class="content">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }"
          ><font-awesome-icon :icon="['fas', 'home']"
        /></el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: state.breadCrumbInfo.path }">{{
          state.breadCrumbInfo.title
        }}</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/' }">{{
          state.breadCrumbInfo.subTitle
        }}</el-breadcrumb-item>
      </el-breadcrumb>
    </main>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { computed, reactive } from "vue";

export default {
  name: "BreadCrumb",
  setup() {
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      breadCrumbInfo: computed(() => {
        return store.getters["root/getBreadCrumbInfo"];
      })
    });

    return { state };
  }
};
</script>

<style scoped>
:deep(.el-breadcrumb__inner) {
  color: #606266;
}
:deep(.el-breadcrumb__inner:hover) {
  color: #755744;
}
</style>
