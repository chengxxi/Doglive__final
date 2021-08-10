<template>
  <div class="block">
    <span class="demonstration">
      다른 강아지들과 어울리는 데 시간이 좀 걸려요.
    </span>

    <el-rate
      v-model="value"
      :colors="colors"
      class="rating"
    >
    <!-- :icon-classes="iconClasses"
          void-icon-class="icon-rate-face-off" -->

    </el-rate>
  </div>

</template>

<style scoped>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%, 위 여백 50px) */
.main-body {
  width: 100%;
  margin-left: 10%;
  margin-right: 10%;
}
.main-padding {
  padding-top: 50px;
}

.title {
  font-size: 2.5rem;
  font-weight: normal;
}

.block {
  margin-top: 30px;
  margin-bottom: 30px;
  margin-left: 50px;
  margin-right: 50px;
  padding: 40px;
}

.demonstration {
  font-size: 1rem;
}

.rating {
  font-size: 2rem;
}

</style>

<script>
import { ref } from 'vue'

export default {
  name: 'MbtiQuestion',
  setup() {
    return {
      value: ref(null),
      colors: ref(['#99A9BF', '#F7BA2A', '#FF9900']),
      // same as { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
      // iconClasses: ref(['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'])
      // same as { 2: 'icon-rate-face-1', 4: { value: 'icon-rate-face-2', excluded: true }, 5: 'icon-rate-face-3' }
    };
  },
}
</script>


