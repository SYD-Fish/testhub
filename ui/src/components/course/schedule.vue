<template>
  <div class="schedule-container">
    <!--    bread crumb-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>课程管理</el-breadcrumb-item>
      <el-breadcrumb-item>排课</el-breadcrumb-item>
    </el-breadcrumb>
    <!--    card view-->
    <el-card>
      <el-row :gutter="30">
        <el-col :span="8">
          <el-input placeholder="请输入搜索内容" v-model="queryInfo.content">
            <el-button slot="append" icon="el-icon-search" ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="warning" @click="showAddDialog" >添加</el-button>
        </el-col>
      </el-row>

      <el-table
          ref="filterTable"
          :data="scheduleList"
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
            prop="timePeriod"
            label="上课时间"
            width="180"
            filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag
                type="success"
                disable-transitions>
              {{ scope.row.timePeriod | courseTimeFilter }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
            prop="startDate"
            label="开始日期"
            :formatter="formatDate"
            width="240">
        </el-table-column>
        <el-table-column
            prop="endDate"
            label="结束日期"
            :formatter="formatDate"
            width="240">
        </el-table-column>
<!--        <el-table-column-->
<!--            prop="createTime"-->
<!--            label="创建日期"-->
<!--            :formatter="formatTime"-->
<!--            width="180">-->
<!--        </el-table-column>-->
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
      <el-form ref="singleScheduleRef" :model="singleScheduleForm" :rules="singleScheduleRule" label-width="100px" class="add_user">
        <el-form-item label="课程名称" prop="courseId">
          <el-select v-model="singleScheduleForm.courseId" placeholder="请选择课程">
            <el-option
                v-for="item in scheduleCourseList"
                :key="item.id"
                :label="item.courseName"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="上课时间" prop="timePeriod">
          <el-select v-model="singleScheduleForm.timePeriod" placeholder="请选择上课时间">
            <el-option
                v-for="item in courseTimeList"
                :key="item.key"
                :label="item.value"
                :value="item.key">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="开始日期" prop="startDate">
          <el-date-picker
              v-model="singleScheduleForm.startDate"
              type="date"
              placeholder="选择开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束日期" prop="endDate">
          <el-date-picker
              v-model="singleScheduleForm.endDate"
              type="date"
              placeholder="选择结束日期">
          </el-date-picker>
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
      scheduleList: [],
      queryInfo: {
        pageIndex: 1,
        pageSize: 10,
        content: ''
      },
      total: 0,
      addDialogVisible: false,
      courseTimeList: [
        {'key': 1, 'value': '8:00 ~ 9:45'},
        {'key': 2, 'value': '10:00 ~ 11:45'},
        {'key': 3, 'value': '13:00 ~ 14:45'},
        {'key': 4, 'value': '15:00 ~ 16:45'},
      ],
      singleScheduleForm: {},
      singleScheduleRule: {
        courseId: [
          {required: true, message: "请选择课程名称", trigger: 'blur'}
        ],
        timePeriod: [
          {required: true, message: "请选择上课时间", trigger: 'blur'}
        ],
        startDate: [
          {required: true, message: "请选择开始日期", trigger: 'blur'}
        ],
      },
      scheduleCourseList: []

    }
  },
  created() {
    this.getScheduleList();
    this.getScheduleCourseList()
  },
  filters: {
    courseTimeFilter(timePeriod) {
      switch (timePeriod) {
        case 1:
          return '8:00 ~ 9:45'
        case 2:
          return '10:00 ~ 11:45'
        case 3:
          return '13:00 ~ 14:45'
        case 4:
          return '15:00 ~ 16:45'
      }
    }
  },
  methods: {
    getScheduleList() {

      this.$http.post("/schedule/list", this.queryInfo)
      .then(res => {
        if (res.data.code == 0) {
          this.scheduleList = res.data.data.data;
          this.total = res.data.data.total;
        }
      })
    },
    getScheduleCourseList() {
      let query = {
        pageIndex: 1,
        pageSize: 100
      }
      this.$http.post("/schedule/courses", query)
          .then(res => {
            if (res.data.code == 0) {
              this.scheduleCourseList = res.data.data;
            }
          })
    },
    handleSizeChange(pageSize) {
      this.queryInfo.pageSize = pageSize;
      this.getScheduleList();

    },
    handleCurrentChange(pageIndex) {
      this.queryInfo.pageIndex = pageIndex;
      this.getScheduleList();

    },
    handleDelete(row) {
      const ids = [row.id];
      this.$http.delete("/schedule/delete", {data: ids})
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
      this.$refs.singleScheduleRef.validate(valid => {
        if (!valid) return;
        let index = this.scheduleCourseList.findIndex((t) =>{
          return t.id == this.singleScheduleForm.courseId;
        });
        this.singleScheduleForm.courseName = this.scheduleCourseList[index].courseName;
        this.$http.post("/schedule/create", this.singleScheduleForm)
            .then(res => {
              this.addDialogVisible = false;
              if (res.data.code == 0) {
                this.$message.success("添加课程:" + this.singleScheduleForm.courseName + "成功")
                this.singleScheduleForm = {};
                this.getScheduleList()
              } else {
                this.$message.error("添加课程:" + this.singleScheduleForm.courseName + "失败")
              }
            })
            .catch(e => {
              this.addDialogVisible = false;
              this.$message.error("添加课程:" + this.singleScheduleForm.courseName + "失败" + e)
            })
      });
    },
    formatTime (row, column) {
      var date = row[column.property];
      if(date == undefined){return ''}
      return moment(date).format("YYYY-MM-DD HH:mm:ss");
    },
    formatDate(row, column) {
      var date = row[column.property];
      if(date == undefined){return ''}
      return moment(date).format("YYYY-MM-DD");
    }

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