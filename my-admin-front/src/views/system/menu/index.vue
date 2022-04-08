<template>
  <div>
    <!-- 添加菜单 -->
    <div class="op-container">
      <el-button type="primary" @click="addFormOpen = true">添加菜单</el-button>
      <el-dialog title="添加菜单" :visible.sync="addFormOpen" @close="resetMenuForm">
        <el-form ref="addForm" :model="menuForm" :rules="rules" label-width="100px">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="父级菜单" prop="parentId">
                <el-popover
                  ref="popoverRef"
                  placement="bottom"
                  width="400"
                  trigger="click"
                >
                  <el-tree
                    ref="menuTree"
                    node-key="id"
                    :check-strictly="true"
                    :data="tableData"
                    :props="treeProps"
                    :show-checkbox="true"
                    @check-change="setParentMenu"
                  />
                  <el-input slot="reference" v-model="menuForm.parentName" placeholder="请选择父级菜单" readonly />
                </el-popover>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="菜单名称" prop="name">
                <el-input v-model="menuForm.name" placeholder="请输入菜单名称" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="菜单路由" prop="path">
                <el-input v-model="menuForm.path" placeholder="请输入菜单路由" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="菜单组件" prop="component">
                <el-input v-model="menuForm.component" placeholder="请输入菜单组件" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="组件名称" prop="componentName">
                <el-input v-model="menuForm.componentName" placeholder="请输入组件名称" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="菜单图标">
                <el-input v-model="menuForm.icon" prop="icon" placeholder="请输入菜单名称" readonly @click.native="iconDrawer = true" />
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
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="权限标识">
                <el-input v-model="menuForm.authority" placeholder="请输入权限标识" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="排序值" prop="sort" required>
                <el-input v-model.number="menuForm.sort" placeholder="请输入排序值" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addFormOpen = false">取 消</el-button>
          <el-button type="primary" @click="add">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <!-- 菜单树形表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%"
      row-key="id"
      border
      :tree-props="{children: 'children'}"
      :default-expand-all="true"
    >
      <el-table-column
        prop="name"
        label="菜单名称"
      />
      <el-table-column
        prop="path"
        label="菜单路径"
        align="center"
      />
      <el-table-column
        prop="component"
        label="菜单组件"
        align="center"
      />
      <el-table-column
        prop="componentName"
        label="组件名称"
        align="center"
      />
      <el-table-column
        prop="icon"
        label="菜单图标"
        align="center"
      >
        <template v-slot="scope">
          <svg-icon v-if="scope.row.icon && scope.row.icon.substr(0, 2) !== 'el'" :icon-class="scope.row.icon" />
          <i v-else-if="scope.row.icon" :class="scope.row.icon" />
        </template>
      </el-table-column>
      <el-table-column
        prop="hidden"
        label="是否隐藏"
        align="center"
      >
        <template v-slot="scope">
          <el-tag :type="scope.row.hidden ? 'warning' : 'success'">
            {{ scope.row.hidden ? '隐藏' : '显示' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="alwaysShow"
        label="一直显示"
        align="center"
      >
        <template v-slot="scope">
          <el-tag :type="scope.row.alwaysShow ? 'success' : 'warning'">
            {{ scope.row.alwaysShow ? '是' : '否' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="authority"
        label="权限标识"
        align="center"
      />
      <el-table-column
        prop="sort"
        label="排序号"
        align="center"
      />
      <el-table-column
        label="操作"
        align="center"
        width="180"
      >
        <template v-slot="scope">
          <el-button type="warning" size="small" @click="showEditForm(scope.row)">编辑</el-button>
          <el-button type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 菜单图标 -->
    <div>
      <el-drawer
        ref="iconDrawer"
        :visible.sync="iconDrawer"
        direction="rtl"
        :show-close="false"
        :with-header="false"
      >
        <el-tabs type="border-card" class="icon-tabs">
          <el-tab-pane label="自定义图标" style="overflow: auto">
            <div class="grid">
              <div v-for="item of svgIcons" :key="item" @click="setMenuIcon(item, true)">
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
              <div v-for="item of elementIcons" :key="item" @click="setMenuIcon(item, false)">
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
    <!-- 修改菜单 -->
    <div>
      <el-dialog title="修改菜单" :visible.sync="editFormOpen" @close="resetMenuForm">
        <el-form ref="editForm" :model="menuForm" :rules="rules" label-width="100px">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="父级菜单" prop="parentId">
                <el-popover
                  ref="popoverRef"
                  placement="bottom"
                  width="400"
                  trigger="click"
                  :disabled="menuForm.parentId === -1"
                >
                  <el-tree
                    ref="menuTree"
                    node-key="id"
                    :check-strictly="true"
                    :data="tableData"
                    :props="treeProps"
                    :show-checkbox="true"
                    :default-expanded-keys="[checkedKey]"
                    :default-checked-keys="[checkedKey]"
                    @check-change="setParentMenu"
                    @show="initTreePopover"
                  />
                  <el-input
                    slot="reference"
                    v-model="menuForm.parentName"
                    :placeholder="menuForm.parentId === -1 ? '顶级目录(不可修改)' : '请选择父级菜单'"
                    readonly
                    :disabled="menuForm.parentId === -1"
                  />
                </el-popover>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="菜单名称" prop="name">
                <el-input v-model="menuForm.name" placeholder="请输入菜单名称" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="菜单路由" prop="path">
                <el-input v-model="menuForm.path" placeholder="请输入菜单路由" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="菜单组件" prop="component">
                <el-input v-model="menuForm.component" placeholder="请输入菜单组件" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="组件名称" prop="componentName">
                <el-input v-model="menuForm.componentName" placeholder="请输入组件名称" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="菜单图标">
                <el-input v-model="menuForm.icon" prop="icon" placeholder="请输入菜单名称" readonly @click.native="iconDrawer = true" />
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
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="权限标识">
                <el-input v-model="menuForm.authority" placeholder="请输入权限标识" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="排序值" prop="sort" required>
                <el-input v-model.number="menuForm.sort" placeholder="请输入排序值" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="editFormOpen = false">取 消</el-button>
          <el-button type="primary" @click="edit">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import { getMenus, add, edit } from '@/api/menu'
// 菜单图标
import svgIcons from '@/assets/icons/svg-icons'
import elementIcons from '@/assets/icons/element-icons'

export default {
  name: 'Menu',
  data() {
    return {
      addFormOpen: false,
      editFormOpen: false,
      loading: false,
      iconDrawer: false,
      checkedKey: '',
      svgIcons,
      elementIcons,
      treeProps: {
        children: 'children',
        label: 'name'
      },
      rules: {
        parentId: [{ required: true, message: '父级ID不能为空', trigger: 'blur' }],
        name: [{ required: true, message: '菜单名称不能为空', trigger: 'blur' }],
        path: [{ required: true, message: '菜单路径不能为空', trigger: 'blur' }],
        component: [{ required: true, message: '菜单组件不能为空', trigger: 'blur' }],
        componentName: [{ required: true, message: '组件名称不能为空', trigger: 'blur' }],
        sort: [
          { required: true, message: '排序值不能为空', trigger: 'blur' },
          { type: 'number', message: '排序值必须为数字' }
        ]
      },
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
        // 排序值
        sort: '',
        // 是否一直显示（根目录）
        alwaysShow: true,
        // 父ID
        parentId: '',
        // 父级菜单名称
        parentName: ''
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
        }
      } catch (e) {
        console.error(e)
      } finally {
        this.loading = false
      }
    },
    // 重置表单
    resetMenuForm() {
      this.menuForm = {
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
        // 排序值
        sort: '',
        // 是否一直显示（根目录）
        alwaysShow: true,
        // 父ID
        parentId: '',
        // 父级菜单名称
        parentName: ''
      }
      this.checkedKey = ''
      if (this.$refs.addForm) {
        this.$refs.addForm.clearValidate()
      }
      if (this.$refs.editForm) {
        this.$refs.editForm.clearValidate()
      }
    },
    // 设置父级菜单
    setParentMenu(data, isChecked) {
      if (isChecked) {
        // 设置单选
        this.$refs.menuTree.setCheckedNodes([])
        this.$refs.menuTree.setCheckedNodes([data])
        this.menuForm.parentId = data.id
        this.menuForm.parentName = data.name
        // 手动关闭
        // this.$refs.popoverRef.doClose()
      }
    },
    // 初始化弹出框默认选中
    initTreePopover() {
      if (this.menuForm.parentId) {
        this.checkedKey = this.menuForm.parentId
      }
    },
    // 基础操作
    add() {
      add(this.menuForm).then(resp => {
        if (resp.code === 200) {
          this.initMenuTree()
        }
      }).catch(error => {
        console.error(error)
      }).finally(() => {
        this.addFormOpen = false
      })
    },
    edit() {
      edit(this.menuForm).then(resp => {
        if (resp.code === 200) {
          this.initMenuTree()
        }
      }).catch(error => {
        console.error(error)
      }).finally(() => {
        this.editFormOpen = false
      })
    },
    showEditForm(data) {
      Object.assign(this.menuForm, data)
      this.editFormOpen = true
    },
    delete() {
    },
    // 菜单图标相关方法
    generateIconCode(symbol) {
      return `<svg-icon icon-class="${symbol}" />`
    },
    generateElementIconCode(symbol) {
      return `<i class="el-icon-${symbol}" />`
    },
    setMenuIcon(symbol, flag) {
      if (flag) {
        this.menuForm.icon = symbol
      } else {
        this.menuForm.icon = `el-icon-${symbol}`
      }
      this.$refs.iconDrawer.closeDrawer()
    }
  }
}
</script>

<style lang="scss" scoped>
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
