<template>
  <div class="course-container">
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
          <el-input placeholder="请输入课程名称" v-model="queryInfo.content">
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
            prop="courseName"
            label="课程名称"
            width="180">
        </el-table-column>
        <el-table-column
            prop="classRoom"
            label="教室名称"
            width="180">
        </el-table-column>
        <el-table-column
            prop="count"
            label="课程节数"
            width="180">
        </el-table-column>
        <el-table-column
            prop="teacherName"
            label="授课老师"
            width="180">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="创建日期"
            :formatter="dateFormat"
            width="180">
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
      <el-form ref="singleCourseRef" :model="singleCourseForm" :rules="singleCourseRule" label-width="80px" class="add_user">
        <el-form-item label="课程名称" prop="courseName">
          <el-input v-model="singleCourseForm.courseName"></el-input>
        </el-form-item>
        <el-form-item label="教室名称" prop="classRoom">
          <el-input v-model="singleCourseForm.classRoom"></el-input>
        </el-form-item>
        <el-form-item label="课程节数" prop="count">
          <el-input v-model.number="singleCourseForm.count"></el-input>
        </el-form-item>
        <el-form-item label="授课老师" prop="teacherId">
          <el-select v-model="singleCourseForm.teacherId" placeholder="请选择授课老师">
            <el-option
                v-for="item in teachers"
                :key="item.userId"
                :label="item.userName"
                :value="item.userId">
            </el-option>
          </el-select>
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
  name: "course",
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
      singleCourseRule: {
        courseName: [
          {required: true, message: "请输入课程名称", trigger: 'blur'}
        ],
        classRoom: [
          {required: true, message: "请输入教室名称", trigger: 'blur'}
        ],
        count: [
          {required: true, message: "请输入课程节数", trigger: 'blur'}
        ],
        teacherId: [
          {required: true, message: "请选择授课老师", trigger: 'blur'}
        ]
      },
      teachers: [],

    }
  },
  created() {
    this.getCourseList()
    this.getTeachers()
  },
  methods: {
    getCourseList() {
      this.$http.post("/course/list", this.queryInfo)
      .then(res => {
        if (res.data.code == 0) {
          this.courseList = res.data.data.data;
          this.total = res.data.data.total;
        }
      })
    },
    handleSizeChange(pageSize) {
      this.queryInfo.pageSize = pageSize;
      this.getCourseList();

    },
    handleCurrentChange(pageIndex) {
      this.queryInfo.pageIndex = pageIndex;
      this.getCourseList();

    },
    handleDelete(row) {
      const ids = [row.id];
      this.$http.delete("/course/delete", {data: ids})
      .then(res => {
        if (res.data.code == 0) {
          this.$message.success("删除课程"+ row.courseName +"成功");
          this.getCourseList()
        } else {
          this.$message.error("删除课程"+ row.courseName + "失败")
        }
      })
    },
    showAddDialog() {
      this.addDialogVisible = true;
    },
    cancleAdd() {
      this.addDialogVisible = false;
    },
    confirmAdd() {
      this.$refs.singleCourseRef.validate(valid => {
        if (!valid) return;
        let index = this.teachers.findIndex((t) =>{
          return t.userId == this.singleCourseForm.teacherId;
        });
        this.singleCourseForm.teacherName = this.teachers[index].userName;
        this.$http.post("/course/create", this.singleCourseForm)
            .then(res => {
              this.addDialogVisible = false;
              if (res.data.code == 0) {
                this.$message.success("添加课程:" + this.singleCourseForm.courseName + "成功")
                this.singleCourseForm = {}
                this.getCourseList()
              } else {
                this.$message.error("添加课程:" + this.singleCourseForm.courseName + "失败")
              }
            })
            .catch(e => {
              this.addDialogVisible = false;
              this.$message.error("添加课程:" + this.singleCourseForm.courseName + "失败" + e)
            })
      });
    },
    dateFormat (row, column) {
      var date = row[column.property];
      if(date == undefined){return ''}
      return moment(date).format("YYYY-MM-DD HH:mm:ss");
    },
    getTeachers() {
      const userQuery = {
        pageIndex: 1,
        pageSize: 100,
        roleId: 2
      }
      this.$http.post("/user/list", userQuery)
      .then(res => {
        if (res.data.code == 0) {
          this.teachers = res.data.data.data
        }
      })
    }

  }
}
</script>

<style lang="less" scoped>
.course-container{
  margin-left: 15px;
  margin-top: 15px;
  margin-right: 15px;
  width: 100%;
}

</style>