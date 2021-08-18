<template>
  <nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
      <li v-if="state.prev" class="page-item">
        <a
          class="page-link"
          href="#"
          aria-label="Previous"
          @click="paginationChanged(state.startPageIndex - 1)"
        >
          <span aria-hidden="true">
            <font-awesome-icon
              :icon="['fas', 'angle-double-left']"
            ></font-awesome-icon>
          </span>
        </a>
      </li>
      <li
        v-for="index in state.endPageIndex - state.startPageIndex + 1"
        :key="index"
        v-bind:class="{
          active: state.startPageIndex + index - 1 == currentPageIndex
        }"
        class="page-item"
      >
        <a
          @click="paginationChanged(state.startPageIndex + index - 1)"
          class="page-link"
          href="#"
          >{{ state.startPageIndex + index - 1 }}</a
        >
        <!-- href 는 그대로, 커서 모양 유지-->
      </li>
      <li v-if="state.next" class="page-item">
        <a
          class="page-link"
          href="#"
          aria-label="Next"
          @click="paginationChanged(state.endPageIndex + 1)"
        >
          <span aria-hidden="true">
            <font-awesome-icon
              :icon="['fas', 'angle-double-right']"
            ></font-awesome-icon>
          </span>
        </a>
      </li>
    </ul>
  </nav>
</template>

<script>
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { reactive, computed, onMounted } from "vue"; // ref

export default {
  name: "Pagination",
  props: [
    "listRowCount",
    "pageLinkCount",
    "currentPageIndex",
    "boardListCount"
  ],
  setup(props, { emit }) {
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      pageCount: computed(() => {
        return Math.ceil(props.boardListCount / props.listRowCount);
      }),

      startPageIndex: computed(() => {
        if (props.currentPageIndex % props.pageLinkCount == 0) {
          return (
            (props.currentPageIndex / props.pageLinkCount - 1) *
              props.pageLinkCount +
            1
          );
        } else {
          return (
            Math.floor(props.currentPageIndex / props.pageLinkCount) *
              props.pageLinkCount +
            1
          );
        }
      }),

      endPageIndex: computed(() => {
        let ret = 0;
        if (props.currentPageIndex % props.pageLinkCount == 0) {
          //10, 20...맨마지막
          ret =
            (props.currentPageIndex / props.pageLinkCount - 1) *
              props.pageLinkCount +
            props.pageLinkCount;
        } else {
          ret =
            Math.floor(props.currentPageIndex / props.pageLinkCount) *
              props.pageLinkCount +
            props.pageLinkCount;
        }
        // 위 오류나는 코드를 아래와 같이 비교해서 처리
        // console.log(
        //   "endPage",
        //   props.currentPageIndex,
        //   props.pageLinkCount,
        //   props.boardListCount,
        //   props.listRowCount
        // );
        // console.log(ret > state.pageCount ? state.pageCount : ret);
        return ret > state.pageCount ? state.pageCount : ret;
      }),
      prev: computed(() => {
        if (props.currentPageIndex <= props.pageLinkCount) {
          return false;
        } else {
          return true;
        }
      }),
      next: computed(() => {
        if (state.endPageIndex >= state.pageCount) {
          return false;
        } else {
          return true;
        }
      })
    });

    const paginationChanged = function(pageIndex) {
      emit("call-parent", pageIndex);
    };

    return { paginationChanged, state };
  }
};
</script>
<style scoped>
.page-item.active .page-link {
  color: #755744;
  border-color: #f9f0e7;
  background-color: #f9f0e7;
}

.page-item.hover .page-link {
  color: #755744;
  border-color: #f9f0e7;
  background-color: #f9f0e7;
}

.page-link {
  color: #755744;
}
</style>
