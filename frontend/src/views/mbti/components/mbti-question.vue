<template>
  <div class="block">
    <span class="demonstration">Color for different levels</span>
    <el-rate
      v-model="value2"
      :colors="colors">
    </el-rate>
  </div>

</template>

<style scoped>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%, 위 여백 50px) */
.main-body {
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
}
.main-padding {
  padding-top: 50px;
}

.title {
  font-size: 2.5rem;
  font-weight: normal;
}
</style>

<script>
import { ref } from 'vue'

export default {
  name: 'MbtiQuestion',
  setup() {
    return {
      value1: ref(null),
      value2: ref(null),
      colors: ref(['#99A9BF', '#F7BA2A', '#FF9900']), // same as { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
    };
  },
}
</script>


