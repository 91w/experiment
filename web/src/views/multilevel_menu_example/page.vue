<route lang="yaml">
meta:
  title: 7-11文件
</route>

<script>
import api from '@/api'

api.post('user/test/', {
  id: 1,
}).then((res) => {
  // 后续业务代码
  // eslint-disable-next-line no-console
  console.log(res)
}).catch((error) => {
  // eslint-disable-next-line no-console
  console.log(error)
})
</script>

<template>
  <div>
    <page-main>
      多级导航1
    </page-main>
  </div>
</template>
