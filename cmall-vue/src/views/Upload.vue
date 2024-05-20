<template>
    <el-container>
        <el-header>
            <el-steps
        style="max-width: 1000px"
        :space="350"
        :active="action"
    >
        <el-step title="手机型号" >
            <div @click="jumpToStep(0)" class="step-clickable-area"></div>
        </el-step>
        <el-step title="使用情况" >
            <div @click="jumpToStep(1)" class="step-clickable-area"></div>
        </el-step>
        <el-step title="存储版本">
            <div @click="jumpToStep(2)" class="step-clickable-area"/>
        </el-step>
        <el-step title="销售渠道" >
            <div @click="jumpToStep(3)" class="step-clickable-area"/>
        </el-step>
        <el-step title="官保时长" >
            <div @click="jumpToStep(4)" class="step-clickable-area"/>
        </el-step>
        <el-step title="电池状况" >
            <div @click="jumpToStep(5)" class="step-clickable-area"/>
        </el-step>
        <el-step title="屏幕外观" >
            <div @click="jumpToStep(6)" class="step-clickable-area"/>
        </el-step>
        <el-step title="边框背板" >
            <div @click="jumpToStep(7)" class="step-clickable-area"/>
        </el-step>
        <el-step title="包装配件" >
            <div @click="jumpToStep(8)" class="step-clickable-area"/>
        </el-step>
        <el-step title="其他" >
            <div @click="jumpToStep(9)" class="step-clickable-area"/>
        </el-step>
        <el-step title="最后一步" >
            <div @click="jumpToStep(10)" class="step-clickable-area"/>
        </el-step>

        
        <el-button style="margin-top: 12px" @click="last">上一步</el-button>
        <el-button style="margin-top: 12px" @click="next">下一步</el-button>
    </el-steps>
        </el-header>
        <el-main>
            <el-form :model="form" label-width="auto">
                <div v-show="action === 0">
                    <el-form-item label="手机型号">
                        <el-input v-model="form.type"/>
                    </el-form-item>
                </div>
                <div v-show="action === 1">
                    <el-form-item label="使用情况">
                        <el-radio-group v-model="form.used">
                          <el-radio label="1" value="normal">正常使用</el-radio>
                          <el-radio label="2" value="unnormal">无法正常使用</el-radio>
                          <el-radio label="3" value="new">全新未拆封</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </div>
                <div v-show="action === 2">
                    <el-form-item label="存储版本">
                        <el-input v-model="form.storage" placeholder="内存+容量格式"/>
                    </el-form-item>
                </div>
                <div v-show="action === 3">
                    <el-form-item label="销售渠道">
                        <el-radio-group v-model="form.channel">
                          <el-radio label="1" value="china">大陆</el-radio>
                          <el-radio label="2" value="hk">港澳台</el-radio>
                          <el-radio lable="3" value="foreign">海外</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </div>
                <div v-show="action === 4">
                    <el-form-item label="官方保修">
                        <el-radio-group v-model="form.official">
                          <el-radio label="1" value="in">在保</el-radio>
                          <el-radio label="2" value="out">过保</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </div>
                <div v-show="action === 5">
                    <el-form-item label="电池情况">
                        <el-radio-group v-model="form.battery">
                          <el-radio label="1" value="healthy">健康</el-radio>
                          <el-radio label="2" value="unhealthy">不健康(低于90%)</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </div>
                <div v-show="action === 6">
                    <el-form-item label="屏幕情况">
                        <el-radio-group v-model="form.screen">
                          <el-radio label="1" value="perfect">完美无划痕</el-radio>
                          <el-radio label="2" value="good">细微划痕</el-radio>
                          <el-radio label="3" value="bad">屏幕碎裂</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </div>
                <div v-show="action === 7">
                    <el-form-item label="边框背板">
                        <el-radio-group v-model="form.frame">
                          <el-radio label="1" value="perfect">完美无划痕</el-radio>
                          <el-radio label="2" value="good">细微划痕</el-radio>
                          <el-radio label="3" value="bad">裂痕</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </div>
                <div v-show="action === 8">
                    <el-form-item label="包装配件">
                        <el-radio-group v-model="form.accessories">
                          <el-radio label="1" value="perfect">配件包装齐全</el-radio>
                          <el-radio label="2" value="bad">配件/包装不齐全</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </div>
                <div v-show="action === 9">
                    <el-form-item label="其他">
                        <el-checkbox-group v-model="form.other">
                          <el-checkbox label="1" value="call" name="type">
                            通话功能不正常
                          </el-checkbox>
                          <el-checkbox label="2" value="changeBattery" name="type">
                            更换电池
                          </el-checkbox>
                          <el-checkbox label="3" value="wifi" name="type">
                            WIFI和蓝牙功能无法使用
                          </el-checkbox>
                          <el-checkbox label="4" value="camera" name="type">
                            摄像头问题
                          </el-checkbox>
                          <el-checkbox label="5" value="mainboard" name="type">
                            主板维修
                          </el-checkbox>
                        </el-checkbox-group>
                      </el-form-item>
                </div>
                <div v-show="action === 10">
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit('form')">提交</el-button>
                    </el-form-item>
                </div>
              </el-form>
        </el-main>
      </el-container>
    

</template>
<script>
import * as uploadAPI from '@/api/upload/'
export default {
  data() {
    return { 
      action: 0,
      form: {
        type: '',
        used: null,
        storage: '',
        channel: null,
        official: null,
        battery: null,
        screen: null,
        frame: null,
        accessories: null,
        other: [],
      }
    };
  },
  methods: {
    onSubmit(formName) {
      uploadAPI
      .UploadForm(this.form)
      .then(res => {
        if(res.msg === '成功') {
            this.notifySucceed('提交成功')
            this.$router.replace({
                  name: 'Home'
                })
        } else {
            this.notifyError('提交失败')
        }
      })
    },
  jumpToStep(stepIndex) {
    this.action = stepIndex;
  },
  next() {
    this.action++;
    if (this.action > 10) {
      this.action = 0;
    }
  },
  last() {
    this.action--;
    if (this.action < 0) {
      this.action = 10;
    }
  },
}
}
</script>
<style scoped>
.el-step {
    cursor: pointer;
}
</style>
  