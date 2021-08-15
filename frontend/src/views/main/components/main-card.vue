<template>
  <el-card
    shadow="hover"
    style="background:linear-gradient( to bottom,#f3e8dc, #f5edea ); border-radius:6px; min-width:120px; height:360px;"
    class="scale-up-2"
  >
    <el-row style="margin-top:15px; margin-right:15px; margin-left:15px;">
    </el-row>
    <el-row
      style="margin-top:5px; display:flex; display: flex;
  justify-content: center;"
    >
      <img style="width:80%" src="" />
    </el-row>

    <el-row
      style="margin-top:10px; margin-right:15px; margin-left:15px; vertical-align: middle; "
    >
      <h3 style="font-weight:800; float:right;" class="mb-0 "></h3>
      <!-- <p style="font-size:13px; font-weight:600; margin-bottom:0px;">
        {{ card.boardId.title }}
      </p> -->
      <p style="font-size:13px; font-weight:600; margin-bottom:0px;"></p>

      <p
        style="font-size:11px; margin-top:0px; font-weight:600; color:#727272;"
      ></p>
    </el-row>
  </el-card>
</template>

<style scoped>
.image {
  width: 90%;
  height: 250px;
}

* {
  color: #606266;
  cursor: pointer;
}

:deep(.el-card__body) {
  padding: 0px;
}

.scale-up-2:hover {
  transform: scale(1.03);
}

.scale-up-hover-2:hover .scale {
  transform: scale(1.03);
}
</style>

<script>
export default {
  name: "FindCard"
};
</script>
