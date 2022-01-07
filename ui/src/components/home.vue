<template>
  <el-container class="home_container">
    <el-header>
      <div>
        <span>选课网站系统</span>
      </div>
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>
    <el-container>
      <el-aside :width="isCollapsed ? '64px' : '200px'">
        <div @click="collapse" style="text-align: center; font-size: x-large">
          <i :class="isCollapsed ? 'el-icon-s-unfold' : 'el-icon-s-fold'"></i>
        </div>
        <el-menu
            background-color="#24282b"
            text-color="#fff"
            active-text-color="#ffd04b"
            unique-opened
            :collapse="isCollapsed"
            :collapse-transition="false"
            router
        >
          <el-submenu :index="item.index + ''" v-for="item in menuList" :key="item.index">
            <template slot="title">
              <i :class="item.icon"></i>
              <span>{{item.menuName}}</span>
            </template>
            <el-menu-item :index="child.path + ''" v-for="child in item.childs" :key="child.index">
              <i class="el-icon-menu"></i>
              <span slot="title">{{child.menuName}}</span>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
<!--        <el-main>Main</el-main>-->
        <router-view></router-view>
<!--          <el-footer>Footer</el-footer>-->
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      menuList: [],
      isCollapsed: false,
    }
  },
  created() {
    this.getMenuList()
  },
  methods: {
    logout() {
      this.$http.delete('/user/logout')
      .then(()=>{
        window.sessionStorage.clear();
      });
      this.$message.success("退出成功");
      this.$router.push('/login');
    },
    getMenuList() {
      this.$http.get('/menu/all')
      .then(res => {
        if (res.data.code != 0) {
          return this.$message.error(res.data.message);
        } else {
          this.menuList = res.data.data;
        }
      })
    },
    collapse() {
      this.isCollapsed = !this.isCollapsed;
    }
  }
}
</script>

<style lang="less" scoped>
.home_container{
  height: 100%;
}
.el-header{
  background-color: #031526;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: white;
  font-size: large;
}

.el-aside{
  background-color: #24282b;
}

.el-main{
  background-color: antiquewhite;
}

</style>