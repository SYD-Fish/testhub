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
      <el-row :gutter="30">
        <el-col :span="8">
          <el-input placeholder="请输入用户名" v-model="queryInfo.username">
            <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary">添加用户</el-button>
        </el-col>
      </el-row>

      <el-table
          ref="filterTable"
          :data="userList"
          border
          stripe
          style="width: 100%;">
        <el-table-column
            label="序号"
            type="index">
        </el-table-column>
        <el-table-column
            prop="userName"
            label="姓名"
            width="180">
        </el-table-column>
        <el-table-column
            prop="phone"
            label="电话"
            width="180">
        </el-table-column>
        <el-table-column
            prop="email"
            label="邮箱"
            width="180">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="创建日期"
            :formatter="dateFormat"
            width="180">
        </el-table-column>
        <el-table-column
            label="状态"
            width="180"
            filter-placement="bottom-end">
          <template slot-scope="scope">
<!--            <el-tag-->
<!--                :type="scope.row.enable === '1' ? 'primary' : 'success'"-->
<!--                disable-transitions>-->
<!--              {{scope.row.enable == 1 ? '有效' : '无效'}}-->
<!--            </el-tag>-->
            <el-switch
                v-model="scope.row.enable"
                :active-value="1"
                :inactive-value="0"
                @change="userStatusChange(scope.row)"
             >
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
<!--      分页-->
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryInfo.pageIndex"
          :page-sizes="[5, 10, 40, 100]"
          :page-size="queryInfo.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
import moment from 'moment'
export default {
  name: "user",
  data() {
    return {
      queryInfo: {
        username: "",
        pageIndex: 1,
        pageSize: 10,
      },
      userList: [],
      total: 0,
    }
  },
  created() {
    this.getUserList()
  },
  methods: {
    getUserList() {
      this.$http.post("/user/list", this.queryInfo)
      .then(res => {
        if (res.data.code == 0) {
          this.userList = res.data.data.data;
          this.total = res.data.data.total;
        }
      })
    },
    dateFormat (row, column) {
      var date = row[column.property];
      if(date == undefined){return ''}
      return moment(date).format("YYYY-MM-DD HH:mm:ss");
    },
    handleDelete(row) {
      const userIds = [];
      userIds.concat(row.userId)
      this.$http.delete("/user/delete", userIds)
      .then(res => {
        if (res.data.code == 0) {
          this.$message.success("删除用户"+ row.userName +"成功");
        } else {
          this.$message.error("删除用户"+ row.userName + "失败")
        }
      })
    },
    userStatusChange(row) {
      console.log(row);
    },
    handleSizeChange(pageSize) {
      this.queryInfo.pageSize = pageSize;
      this.getUserList();

    },
    handleCurrentChange(pageIndex) {
      this.queryInfo.pageIndex = pageIndex;
      this.getUserList();

    }
  }
}
</script>

<style lang="less" scoped>
.user-container{
  margin-left: 15px;
  margin-top: 15px;
  margin-right: 15px;
  width: 100%;
}

</style>