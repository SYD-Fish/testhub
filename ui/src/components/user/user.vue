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
          <el-button type="primary" @click="addUser">添加用户</el-button>
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
<!--    add user-->
    <el-dialog
        title="添加用户"
        :visible.sync="addDialogVisible"
    >
      <el-form ref="singleUserRef" :model="singleUserForm" :rules="singleUserRule" label-width="70px" class="add_user">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="singleUserForm.username"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="singleUserForm.phone"></el-input>
        </el-form-item>

        <el-form-item label="邮箱" prop="email">
          <label slot="label">邮&emsp;箱</label>
          <el-input v-model="singleUserForm.email"></el-input>
        </el-form-item>
        <el-form-item label="专业" prop="profession">
          <label slot="label">专&emsp;业</label>
          <el-input v-model="singleUserForm.profession"></el-input>
        </el-form-item>
        <el-form-item label="年级" prop="stage">
          <label slot="label">年&emsp;级</label>
          <el-select v-model="singleUserForm.stage" placeholder="请选择年级">
            <el-option
                v-for="item in stageOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
<!--          <el-input v-model="singleUserForm.stage"></el-input>-->
        </el-form-item>
        <el-form-item label="角色" prop="stage">
          <label slot="label">角&emsp;色</label>
          <el-select v-model="singleUserForm.roleId" placeholder="请选择角色">
            <el-option
                v-for="item in roleOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="cancleAddUser">取 消</el-button>
    <el-button type="primary" @click="confirmAddUser">确 定</el-button>
  </span>
    </el-dialog>
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
      addDialogVisible: false,
      singleUserForm: {
        stage: ''
      },
      singleUserRule: {
        username: [
          {required: true, message: "请输入用户名", trigger: 'blur'},
          { min: 3, max: 20, message: '长度在 3 到 20个字符', trigger: 'blur' }
        ],
        roleId: [
          {required: true, message: "选择角色", trigger: 'blur'}
        ]
      },
      stageOptions: [
        {value: "大一", label: "大一"},
        {value: "大二", label: "大二"},
        {value: "大三", label: "大三"},
        {value: "大四", label: "大四"},
      ],
      roleOptions: [
        {value: 1, label: "学生"},
        {value: 2, label: "教师"},
        {value: 3, label: "管理员"}
      ]
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

    },
    addUser() {
      this.addDialogVisible = true;
    },
    confirmAddUser() {
      this.$refs.singleUserRef.validate(valid => {
        if (!valid) return;
        this.$http.post('/user/create', this.singleUserForm)
            .then(res => {
              this.addDialogVisible = false;
              if (res.data.code == 0) {
                this.$message.success("添加用户成功");
                this.getUserList();
              } else {
                this.$message.error("添加用户失败");
              }
            })
            .catch(e => {
              this.addDialogVisible = false;
              this.$message.error("添加用户失败, msg:" + e)
            })
      })
    },
    cancleAddUser() {
      this.$refs.singleUserRef.resetFields();
      this.addDialogVisible = false
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
.add_user{
  box-sizing: border-box;
  padding: 0 40px;
}

</style>