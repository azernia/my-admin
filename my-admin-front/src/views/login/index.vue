<template>
  <div class="login-container">
    <el-form ref="loginForm" :rules="rules" :model="loginForm" class="login-form">
      <el-form-item>
        <div class="form-title">
          系统登录
        </div>
      </el-form-item>
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" placeholder="请输入用户名" prefix-icon="el-icon-user" />
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="loginForm.password" placeholder="请输入密码" type="password" prefix-icon="el-icon-lock" />
      </el-form-item>
      <el-form-item prop="code">
        <el-row :gutter="20">
          <el-col :span="16" :offset="0">
            <el-input v-model="loginForm.code" placeholder="请输入验证码" @keyup.enter.native="handleLogin" />
          </el-col>
          <el-col :span="8" :offset="0">
            <el-input />
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-button :loading="loading" class="login-btn" type="primary" @click="handleLogin">登录</el-button>
          </el-col>
          <el-col :span="12">
            <el-button class="login-btn" type="default">清空</el-button>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      loading: false,
      loginForm: {
        username: '',
        password: '',
        code: ''
      },
      rules: {
        username: [
          {
            required: true,
            trigger: 'blur',
            message: '用户名不能为空'
          }
        ],
        password: [
          {
            required: true,
            trigger: 'blur',
            message: '密码不能为空'
          }
        ],
        code: [
          {
            required: true,
            trigger: 'blur',
            message: '验证码不能为空'
          }
        ]
      }
    }
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm).then(() => {
            this.$router.push({ path: this.redirect || '/' })
            this.loading = false
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>

<style lang="scss">
.login-container {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  .form-title {
    font-size: 24px;
    font-weight: 600;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .login-form {
    height: 350px;
    width: 450px;
    box-shadow: 0 0 25px #cac6c6;
    padding: 20px 30px;
  }
  .login-btn {
    width: 100%;
  }
}
</style>
