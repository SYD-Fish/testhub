<template>
  <div class="schedule-container">
    <!--    bread crumb-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>课程管理</el-breadcrumb-item>
      <el-breadcrumb-item>课程列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!--    card view-->
    <el-card>
      <el-row :gutter="30">
        <el-col :span="8">
          <el-input placeholder="请输入课程名称">
            <el-button slot="append" icon="el-icon-search" ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="showAddDialog" >添加课程</el-button>
        </el-col>
      </el-row>

      <el-table
          ref="filterTable"
          :data="courseList"
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
            width="120">
        </el-table-column>
        <el-table-column
            prop="phone"
            label="电话"
            width="120">
        </el-table-column>
        <el-table-column
            prop="email"
            label="邮箱"
            width="180">
        </el-table-column>
        <el-table-column
            prop="profession"
            label="专业"
            width="120">
        </el-table-column>
        <el-table-column
            prop="stage"
            label="年级"
            width="120">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="创建日期"
            :formatter="dateFormat"
            width="180">
        </el-table-column>
        <el-table-column
            label="状态"
            width="120"
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
        <el-table-column
            label="角色"
            width="120"
            filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag
                :type="scope.row.roleId === '1' ? 'primary' : (scope.row.roleId === 2 ? 'success' : 'warn')"
                disable-transitions>
              {{ scope.row.roleId == 1 ? '学生' : (scope.row.roleId === 2 ? '教师' : '管理员') }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.row)">删除
            </el-button>
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
    <!--    add user-->
    <el-dialog
        title="添加课程"
        :visible.sync="addDialogVisible"
    >
      <el-form ref="singleUserRef" :model="singleCourseForm" :rules="singleCourseRule" label-width="70px" class="add_user">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="singleCourseForm.username"></el-input>
        </el-form-item>

      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="cancleAdd">取 消</el-button>
    <el-button type="primary" @click="confirmAdd">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import moment from "moment";

export default {
  name: "schedule",
  data() {
    return {
      courseList: [],
      queryInfo: {
        pageIndex: 1,
        pageSize: 10,
        content: ''
      },
      total: 0,
      addDialogVisible: false,
      singleCourseForm: {},
      singleCourseRule: {}

    }
  },
  methods: {
    handleSizeChange(pageSize) {
      this.queryInfo.pageSize = pageSize;
      this.getUserList();

    },
    handleCurrentChange(pageIndex) {
      this.queryInfo.pageIndex = pageIndex;
      this.getUserList();

    },
    showAddDialog() {
      this.addDialogVisible = true;
    },
    cancleAdd() {
      this.addDialogVisible = false;
    },
    confirmAdd() {
      this.addDialogVisible = false;
    },
    dateFormat (row, column) {
      var date = row[column.property];
      if(date == undefined){return ''}
      return moment(date).format("YYYY-MM-DD HH:mm:ss");
    },

  }
}
</script>

<style lang="less" scoped>
.schedule-container{
  margin-left: 15px;
  margin-top: 15px;
  margin-right: 15px;
  width: 100%;
}

</style>