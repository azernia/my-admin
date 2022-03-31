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
              <el-form-item label="菜单图标">
                <el-input v-model="menuForm.icon" placeholder="请输入菜单名称" readonly @click.native="iconDrawer = true" />
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
        prop="name"
        label="菜单名称"
        width="180"
      />
      <el-table-column
        prop="path"
        label="菜单路径"
        align="center"
        width="180"
      />
      <el-table-column
        prop="component"
        label="菜单组件"
        align="center"
        width="180"
      />
      <el-table-column
        prop="componentName"
        label="组件名称"
        align="center"
        width="120"
      />
      <el-table-column
        prop="icon"
        label="菜单图标"
        align="center"
        width="50"
      >
        <template slot-scope="scope">
          <svg-icon v-if="scope.row.icon && scope.row.icon.substr(0, 2) !== 'el'" :icon-class="scope.row.icon" />
          <i v-else-if="scope.row.icon" :class="scope.row.icon" />
        </template>
      </el-table-column>
      <el-table-column
        prop="hidden"
        label="是否隐藏"
        align="center"
        width="80"
      />
      <el-table-column
        prop="alwaysShow"
        label="一直显示"
        align="center"
        width="80"
      />
      <el-table-column
        prop="authority"
        label="权限标识"
        align="center"
      />
    </el-table>
    <div>
      <el-drawer
        :visible.sync="iconDrawer"
        direction="rtl"
        :show-close="false"
        :with-header="false"
      >
        <el-tabs type="border-card" class="icon-tabs">
          <el-tab-pane label="自定义图标" style="overflow: auto">
            <div class="grid">
              <div v-for="item of svgIcons" :key="item" @click="handleClipboard(generateIconCode(item),$event)">
                <el-tooltip placement="top">
                  <div slot="content">
                    {{ generateIconCode(item) }}
                  </div>
                  <div class="icon-item">
                    <svg-icon :icon-class="item" class-name="disabled" />
                    <span>{{ item }}</span>
                  </div>
                </el-tooltip>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="Element-UI 图标">
            <div class="grid">
              <div v-for="item of elementIcons" :key="item" @click="handleClipboard(generateElementIconCode(item),$event)">
                <el-tooltip placement="top">
                  <div slot="content">
                    {{ generateElementIconCode(item) }}
                  </div>
                  <div class="icon-item">
                    <i :class="'el-icon-' + item" />
                    <span>{{ item }}</span>
                  </div>
                </el-tooltip>
              </div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-drawer>
    </div>
  </div>
</template>

<script>
import { getMenus } from '@/api/menu'
// 菜单图标
import clipboard from '@/utils/clipboard'
import svgIcons from '@/assets/icons/svg-icons'
import elementIcons from '@/assets/icons/element-icons'

export default {
  name: 'Menu',
  data() {
    return {
      addFormOpen: false,
      loading: false,
      iconDrawer: false,
      svgIcons,
      elementIcons,
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
    generateIconCode(symbol) {
      return `<svg-icon icon-class="${symbol}" />`
    },
    generateElementIconCode(symbol) {
      return `<i class="el-icon-${symbol}" />`
    },
    handleClipboard(text, event) {
      clipboard(text, event)
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

::v-deep .el-tabs__content {
  height: calc(100vh - 39px);
  overflow-y: auto;
}

::v-deep .el-tabs__content::-webkit-scrollbar {
  display: none;
}

//::v-deep .el-table th > .cell {
//  text-align: center;
//}

//::v-deep .el-table .cell {
//  text-align: center;
//}

.icon-tabs {
  .grid {
    position: relative;
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(100px, 1fr));
  }

  .icon-item {
    margin: 10px;
    height: 60px;
    text-align: center;
    width: 80px;
    float: left;
    font-size: 16px;
    color: #24292e;
    cursor: pointer;
  }

  span {
    display: block;
    font-size: 12px;
    margin-top: 10px;
  }

  .disabled {
    pointer-events: none;
  }
}

</style>
