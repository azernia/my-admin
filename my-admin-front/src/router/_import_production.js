// 生产环境组件导入
module.exports = file => () => import('@/views' + file)
