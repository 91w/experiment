<template>
    <NaveMenu>
        <div class="box-pie" style="height: 400px" ref="chart">
        </div>
    </NaveMenu>

</template>
<script>
import NaveMenu from '../components/NaveMenu'
export default {

    components: {
        NaveMenu
    },
    data(){
    return {
      chart:null,
      // 配置可视化图形
      option:  {
        title: {
          text: 'Referer of a Website',
          subtext: 'Fake Data',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 1048, name: 'Search Engine' },
              { value: 735, name: 'Direct' },
              { value: 580, name: 'Email' },
              { value: 484, name: 'Union Ads' },
              { value: 300, name: 'Video Ads' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
    }
  },
  mounted() {
    this.getPage();
  },
  methods:{
    getPage() {
      // 引用chart并初始化
      this.chart = this.$echarts.init(this.$refs.chart);
      // 使用刚指定的配置项和数据显示图表。
      this.chart.setOption(this.option);
    }
  }
}

</script>