<template>
  <div>
    <!-- 添加菜单 -->
    <div class="op-container">
      <el-button type="primary" @click="addFormOpen = true">添加菜单</el-button>
      <el-dialog title="添加菜单" :visible.sync="addFormOpen">
        <el-form v-loading="loading" :model="menuForm" label-width="100px">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="父级菜单" required>
                <el-input v-model="menuForm.parentId" placeholder="请选择父级菜单" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="菜单名称" required>
                <el-input v-model="menuForm.name" placeholder="请输入菜单名称" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="菜单路由" required>
                <el-input v-model="menuForm.path" placeholder="请输入菜单路由" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="菜单组件" required>
                <el-input v-model="menuForm.component" placeholder="请输入菜单组件" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="组件名称" required>
                <el-input v-model="menuForm.componentName" placeholder="请输入组件名称" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="菜单图标" required>
                <el-popover
                  placement="bottom"
                  trigger="click"
                >
                  <Icons />
                  <el-input slot="reference" v-model="menuForm.icon" placeholder="请输入菜单名称" readonly />
                </el-popover>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="是否隐藏">
                <el-switch
                  v-model="menuForm.hidden"
                  style="display: block"
                  active-color="#13ce66"
                  inactive-color="#ff4949"
                  active-text="是"
                  inactive-text="否"
                  class="switch-item"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="一直显示">
                <el-switch
                  v-model="menuForm.alwaysShow"
                  style="display: block"
                  active-color="#13ce66"
                  inactive-color="#ff4949"
                  active-text="是"
                  inactive-text="否"
                  class="switch-item"
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-form-item label="权限标识" required>
                <el-input v-model="menuForm.authority" placeholder="请输入权限标识" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addFormOpen = false">取 消</el-button>
          <el-button type="primary" @click="addFormOpen = false">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <!-- 菜单树形表格 -->
    <el-table
      :data="tableData"
      style="width: 100%"
      row-key="id"
      border
      :tree-props="{children: 'children'}"
    >
      <el-table-column
        prop="id"
        label="ID"
        width="180"
      />
      <el-table-column
        prop="name"
        label="菜单名称"
        width="180"
      />
      <el-table-column
        prop="path"
        label="菜单路径"
        width="180"
      />
      <el-table-column
        prop="component"
        label="菜单组件"
      />
      <el-table-column
        prop="componentName"
        label="组件名称"
      />
      <el-table-column
        prop="icon"
        label="菜单图标"
      />
      <el-table-column
        prop="hidden"
        label="是否隐藏"
      />
      <el-table-column
        prop="alwaysShow"
        label="一直显示"
      />
      <el-table-column
        prop="authority"
        label="权限标识"
      />
    </el-table>
  </div>
</template>

<script>
import { getMenus } from '@/api/menu'
import Icons from '@/components/icons'

export default {
  name: 'Menu',
  components: {
    Icons
  },
  data() {
    return {
      addFormOpen: false,
      loading: false,
      menuForm: {
        // 菜单名称
        name: '',
        // 菜单路径
        path: '',
        // 菜单组件
        component: '',
        // 组件名称
        componentName: '',
        // 菜单图标,
        icon: '',
        // 是否隐藏
        hidden: false,
        // 权限标识
        authority: '',
        // 是否一直显示（根目录）
        alwaysShow: true,
        // 父ID
        parentId: ''
      },
      tableData: []
    }
  },
  mounted() {
    this.initMenuTree()
  },
  methods: {
    async initMenuTree() {
      try {
        this.loading = true
        const resp = await getMenus()
        if (resp) {
          this.tableData = resp.data
          this.loading = false
        }
        console.log(resp, 'resp')
      } catch (e) {
        console.error(e)
      } finally {
        this.loading = false
      }
    },
    showIconSelector() {

    }
  }
}
</script>

<style lang="scss" scoped>
.op-container {
  margin-left: 10px;
  padding: 10px 0;
}
.switch-item {
  padding-top: 7px;
}
</style>
