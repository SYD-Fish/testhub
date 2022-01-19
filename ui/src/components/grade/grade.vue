<template>
  <div class="grade-container">
    <!--    bread crumb-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>成绩管理</el-breadcrumb-item>
      <el-breadcrumb-item>成绩列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!--    card view-->
    <el-card>
      <el-row :gutter="30">
        <el-col :span="4">
          <el-select v-model="queryInfo.courseId"
                     default-first-option
                     @change="getGradeList"
                     placeholder="请选择课程名称">
            <el-option
                v-for="item in courseList"
                :key="item.id"
                :label="item.courseName"
                :value="item.id">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="8">
          <el-input placeholder="请输入搜索内容">
            <el-button slot="append" icon="el-icon-search" @click="getGradeList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" @click="showAddDialog" >打分</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="success" @click="exportGrades" >导出</el-button>
        </el-col>
      </el-row>

      <el-table
          ref="filterTable"
          :data="gradeList"
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
            prop="courseName"
            label="课程名称"
            width="180">
        </el-table-column>
        <el-table-column
            prop="grade"
            label="成绩"
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
        title="添加成绩"
        :visible.sync="addDialogVisible"
    >
<!--      course selector start -->
<!--      <el-select v-model="studentQuery.courseId" placeholder="请选择课程名称">-->
<!--        <el-option-->
<!--            v-for="item in courseList"-->
<!--            :key="item.id"-->
<!--            :label="item.courseName"-->
<!--            :value="item.id">-->
<!--        </el-option>-->
<!--      </el-select>-->
      <!--      course selector end -->
      <el-table :data="studentList">
        <el-table-column label="序号" type="index"></el-table-column>
        <el-table-column property="userName" label="姓名" width="120"></el-table-column>
        <el-table-column label="课程编号" width="150">
          <template>
            <el-tag>{{queryInfo.courseId}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="成绩" width="150">
          <template slot-scope="scope">
            <el-input v-model="scope.row.grade"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
                size="mini"
                type="danger"
                @click="confirmAdd(scope.row)">确认
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
    <el-button @click="cancleAdd">关闭</el-button>
<!--    <el-button type="primary" @click="confirmAdd">关闭</el-button>-->
  </span>
    </el-dialog>
  </div>
</template>

<script>
import moment from "moment";

export default {
  name: "grade",
  data() {
    return {
      gradeList: [],
      queryInfo: {
        pageIndex: 1,
        pageSize: 10,
        content: '',
        courseId: ''
      },
      total: 0,
      courseList: {},
      addDialogVisible: false,
      studentQuery: {
        pageIndex: 1,
        pageSize: 10
      },
      studentList: []

    }
  },
  created() {
    this.getGradeList();
    this.getCourseList();
  },
  filters: {
    // courseFilter(courseId) {
      // if (this.courseList.length > 0) {
      //   let index = this.courseList.findIndex((c) => {
      //     return c.id == courseId
      //   })
      //   return this.courseList[index].courseName;
      // } else {
      //   return ''
      // }

    // }
  },
  methods: {
    exportGrades() {
      this.$http({
        url: '/grade/export?courseId='+this.queryInfo.courseId+'&userId=',
        method: 'get',
        responseType: 'blob'
      }).then(res=>{
        const blob = new Blob([res.data], { type: 'application/vnd.ms.xlsx;charset=utf-8' });
        var time =
            new Date().getHours() +
            "" +
            new Date().getMinutes() +
            "" +
            new Date().getSeconds()
        const fileName = "学生成绩_" + time +".xlsx";

        if ("download" in document.createElement("a")) {
          // 非IE下载
          const elink = document.createElement("a");
          elink.download = fileName;
          elink.style.display = "none";
          elink.href = URL.createObjectURL(blob);
          document.body.appendChild(elink);
          elink.click();
          URL.revokeObjectURL(elink.href); // 释放URL 对象
          document.body.removeChild(elink);
        } else {
          // IE10+下载
          navigator.msSaveBlob(blob, fileName);
        }
      }).catch(err=>{
        console.log(err)
      })

      this.$http.get("/grade/export?courseId="+this.queryInfo.courseId+"&userId=", {responseType: 'blob'})
      .then(fileData => {
        this.download(fileData)
      })
    },

    // download(fileData){
    //   const url = window.URL.createObjectURL(new Blob([fileData],{type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8'}))
    //   const link = document.createElement('a')
    //   link.href = url
    //   link.setAttribute('download', '学生成绩.xlsx') // 下载文件的名称及文件类型后缀
    //   document.body.appendChild(link)
    //   link.click()
    //   document.body.removeChild(link); // 下载完成移除元素
    //   window.URL.revokeObjectURL(url); // 释放掉blob对象
    // },
    getGradeList() {
      this.$http.post("/grade/list", this.queryInfo)
          .then(res => {
            if (res.data.code == 0) {
              this.gradeList = res.data.data.data;
              this.total = res.data.data.total;
            }
          })
    },
    getCourseList() {
      let param = {
        pageIndex: 1,
        pageSize: 100
      }
      this.$http.post("/course/list", param)
          .then(res => {
            if (res.data.code == 0) {
              this.courseList = res.data.data.data;
              // this.total = res.data.data.total;
              if (this.queryInfo.courseId == '') {
                this.queryInfo.courseId = this.courseList[0].id;
              }
            }
          })
    },
    getStudentList() {
      this.studentQuery.courseId = this.queryInfo.courseId;
      this.$http.post("/grade/students", this.studentQuery)
          .then(res => {
            if (res.data.code == 0) {
              this.studentList = res.data.data;
              // this.total = res.data.data.total;
            }
          })
    },
    handleSizeChange(pageSize) {
      this.queryInfo.pageSize = pageSize;
      this.getUserList();

    },
    handleDelete(row) {
      const ids = [row.id];
      this.$http.delete("/grade/delete", {data: ids})
          .then(res => {
            if (res.data.code == 0) {
              this.$message.success("删除学生"+ row.userName +"成绩成功");
              this.getGradeList()
            } else {
              this.$message.error("删除学生"+ row.userName + "成绩失败")
            }
          })
    },
    handleCurrentChange(pageIndex) {
      this.queryInfo.pageIndex = pageIndex;
      this.getGradeList();

    },
    showAddDialog() {
      this.getStudentList();
      this.addDialogVisible = true;
    },
    cancleAdd() {
      this.getGradeList();
      this.addDialogVisible = false;
    },
    confirmAdd(row) {
      let param = {
        userId: row.userId,
        userName: row.userName,
        grade: row.grade,
        courseId: '',
        courseName: ''
      }
      param.courseId = this.queryInfo.courseId;
      let index = this.courseList.findIndex((c) =>{
        return c.id == this.queryInfo.courseId;
      })
      param.courseName = this.courseList[index].courseName;
      this.$http.post("/grade/create", [param])
      .then(res => {
        if (res.data.code == 0) {
          let index = this.studentList.findIndex((s)=>{
            return s.userId = param.userId
          })
          this.studentList.splice(index, 1);
        } else {
          this.$message.error("添加成绩失败")
        }
      })
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
.grade-container{
  margin-left: 15px;
  margin-top: 15px;
  margin-right: 15px;
  width: 100%;
}
</style>