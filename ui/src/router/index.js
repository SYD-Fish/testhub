import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/components/login.vue'
import Home from '@/components/home.vue'
import User from '@/components/user/user.vue'
import Course from '@/components/course/course.vue'
import Schedule from '@/components/course/schedule.vue'
import Grade from '@/components/grade/grade.vue'

Vue.use(VueRouter)

const routes = [
  {path: '/', redirect: '/login'},
  {path: '/login', component: Login},
  {
    path: '/home',
    component: Home,
    redirect: '/users',
    children: [
      {path: '/users', component: User},
      {path: '/course', component: Course},
      {path: '/schedule', component: Schedule},
      {path: '/grade', component: Grade}
    ]
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login') return next();
  const token = window.sessionStorage.getItem('token');
  if (!token) return next('/login');
  next();
})

export default router
