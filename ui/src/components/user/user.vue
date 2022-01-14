<template>
  <div class="user-container">
<!--    bread crumb-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
<!--    card view-->
    <el-card>
      <el-row>
        <el-col :span="8">
          <el-input placeholder="请输入内容">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col></el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "user"
}
</script>

<style lang="less" scoped>
.user-container{
  margin-left: 10px;
  margin-top: 10px;
}

</style>