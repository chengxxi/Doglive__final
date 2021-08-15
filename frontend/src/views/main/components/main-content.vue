<template>
  <div class="main-body main-padding">
    <div style="width:100%; ">
      <div class="community">
        <h3 class="pt-4 pb-4" style="font-weight:600; ">
          독립신문의 이야기들을 살펴보세요!
        </h3>
        <div
          style=" display: flex;
  justify-content: center; "
        >
          <el-row>
            <el-col :span="6">
              <div
                style="width:250px; height:250px; margin:20px; display:inline-block; background:linear-gradient( to bottom,#f3e8dc, #f5edea );"
              ></div>
            </el-col>
            <el-col :span="6"
              ><div
                style="width:250px; height:250px; margin:20px; display:inline-block; background:linear-gradient( to bottom,#f3e8dc, #f5edea );"
              ></div>
            </el-col>
            <el-col :span="6"
              ><div
                style="width:250px; height:250px; margin:20px; display:inline-block; background:linear-gradient( to bottom,#f3e8dc, #f5edea );"
              ></div>
            </el-col>
            <el-col :span="6">
              <div
                style="width:250px; height:250px; margin:20px; display:inline-block; background:linear-gradient( to bottom,#f3e8dc, #f5edea );"
              ></div
            ></el-col>
          </el-row>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.main-body {
  width: 100%;
  margin-left: 15%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 15%;
}
.main-padding {
  padding-top: 50px;
  padding-bottom: 50px;
}
</style>

<script>
export default {
  name: "main-content",
  data() {
    return {
      imgArray: []
    };
  },

  setup() {}
};
</script>
