<template>
  <div>
    <!-- 添加角色 -->
    <div class="op-container">
      <el-button type="primary" @click="addFormOpen = true">添加角色</el-button>
    </div>
    <div class="data-container">
      <el-table
        v-loading="loading"
        :data="roleList"
        row-key="id"
        border
        class="table-container"
      >
        <el-table-column
          label="序号"
          type="index"
          align="center"
        />
        <el-table-column
          prop="name"
          label="英文名称"
          align="center"
        />
        <el-table-column
          prop="nameZh"
          label="中文名称"
          align="center"
        />
        <el-table-column
          prop="enable"
          label="是否可用"
          align="center"
        >
          <template v-slot="scope">
            <el-tag :type="scope.row.enable ? 'success' : 'warning'">
              {{ scope.row.enable ? '是' : '否' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="创建时间"
          align="center"
        />
        <el-table-column
          prop="updateTime"
          label="修改时间"
          align="center"
        />
        <el-table-column
          label="操作"
          align="center"
          width="360"
        >
          <template v-slot="scope">
            <el-button type="warning" size="small" @click="showEditForm(scope.row)">编辑</el-button>
            <el-button type="warning" size="small" @click="showEditForm(scope.row)">分配菜单</el-button>
            <el-button type="warning" size="small" @click="showEditForm(scope.row)">分配权限</el-button>
            <el-button type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        layout="total, prev, pager, next, jumper"
        :hide-on-single-page="false"
        :total="pageParam.count"
        class="page-container"
        @current-change="currentChangeHandler"
      />
    </div>
  </div>
</template>

<script>
import { pageList } from '@/api/role'

export default {
  name: 'Role',
  data() {
    return {
      addFormOpen: false,
      loading: false,
      roleList: [],
      pageParam: {
        page: 1,
        limit: 10,
        count: 0
      }
    }
  },
  mounted() {
    this.initTable()
  },
  methods: {
    async initTable() {
      try {
        this.loading = true
        const resp = await pageList(this.pageParam)
        if (resp) {
          this.roleList = resp.data
          this.pageParam.count = resp.count
        }
      } catch (e) {
        console.error(e)
      } finally {
        this.loading = false
      }
    },
    currentChangeHandler(currentPage) {
      this.pageParam.page = currentPage
      this.initTable()
    },
    showEditForm(data) {
    }
  }
}
</script>

<style scoped lang="scss">
::v-deep .data-container {
  .table-container {
    height: calc(100vh - 170px);
    width: 100%;
  }

  .page-container {
    margin-top: 10px;
    display: flex;
    justify-content: flex-end;
  }
}
</style>
