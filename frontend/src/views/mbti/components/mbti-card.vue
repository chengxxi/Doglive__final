<template>
  <el-card shadow="hover" style="background-color:#f9f4f0;">
    <el-row style="margin-top:15px; margin-right:15px; margin-left:15px;">
      <el-tag
        class="mb-2"
        effect="dark"
        size="small"
        style="height:20px; background:linear-gradient( to right, #D7AFA4, #E9CDA4, #B4D9A7, #87CEDC ); border:none; font-weight:600;  color:#FFFFFF; float:left;"
        > MBTI
        <!-- {{ card.mbti }} -->
        </el-tag>
      <el-tag
        class="mb-2"
        color="#D7AFA4"
        effect="dark"
        size="small"
        style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF; float:right; "
        > Name
        <!-- {{ card.boardId.type.name }} -->
      </el-tag>
    </el-row>

    <el-row
      style="margin-top:5px; display:flex; display: flex; justify-content: center;"
    >
      <img src="https://placedog.net/500/500?random" class="image" />
    </el-row>

    <el-row
      style="margin-top:10px; margin-right:15px; margin-left:15px; vertical-align: middle; "
    >
      <h3 style="font-weight:800; float:right;" class="mb-0">
        dogName <!-- {{ card.dogName }} -->
      </h3>
      <p style="font-size:13px; font-weight:600; margin-bottom:0px;">
        Title
        <!-- {{
          card.boardId.title.length < 12
            ? card.boardId.title
            : card.boardId.title.substr(0, 10) + "..."
        }} -->
      </p>

      <!-- <p style="font-size:10px; margin-top:0px; color:#727272;">
        {{ card.gender.name }} / {{ card.age.name.split("(")[0] }} /
        {{ card.weight.name.split("(")[0] }}
      </p> -->
    </el-row>
  </el-card>
</template>

<style scoped>

</style>

<script>

export default {
  name: 'mbti-card',

}
</script>
