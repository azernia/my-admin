<template>
  <div>
    <div class="op-container">
      <el-button type="warning" @click="syncPermission">同步权限</el-button>
    </div>
    <div>
      <el-table
        v-loading="loading"
        :data="permissionList"
        row-key="id"
        border
        style="width: 100%; height: calc(100vh - 110px)"
      >
        <el-table-column
          label="序号"
          type="index"
          align="center"
        />
        <el-table-column
          prop="moduleName"
          label="模块名"
          align="center"
        />
        <el-table-column
          prop="funcName"
          label="功能名称"
          align="center"
        />
        <el-table-column
          prop="authority"
          label="权限标识"
          align="center"
        />
        <el-table-column
          prop="createTime"
          label="创建时间"
          align="center"
        />
      </el-table>
    </div>
  </div>
</template>

<script>
import { getPermissions, getSwaggerData, syncPermission } from '@/api/permission'

export default {
  name: 'Permission',
  data() {
    return {
      loading: false,
      permissionList: [],
      authorities: []
    }
  },
  mounted() {
    this.initTable()
  },
  methods: {
    async initTable() {
      try {
        this.loading = true
        const resp = await getPermissions()
        if (resp) {
          this.permissionList = resp.data
        }
      } catch (e) {
        console.error(e)
      } finally {
        this.loading = false
      }
    },
    async syncPermission() {
      try {
        await this.getInterfaceInfo()
        await syncPermission({ permissions: JSON.stringify(this.authorities) })
      } catch (e) {
        console.error(e)
      } finally {
        await this.initTable()
      }
    },
    async getInterfaceInfo() {
      try {
        const resp = await getSwaggerData()
        const paths = Object.keys(resp.paths)
        paths.forEach(path => {
          const apiObj = resp.paths[path]
          const reqs = Object.keys(apiObj)
          const methodObj = apiObj[reqs]
          const permission = {
            moduleName: methodObj.tags[0],
            funcName: methodObj.summary,
            authority: reqs[0] + ':' + path
          }
          this.authorities.push(permission)
        })
      } catch (e) {
        console.error(e)
      }
    }
  }
}
</script>
