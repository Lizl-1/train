<template>
  <a-row>
    <a-col :span="8"></a-col>
    <a-col :span="8" class="md">
      <a-form
          :model="formState"
          name="basic"
          :label-col="{ span: 8 }"
          :wrapper-col="{ span: 16 }"
          autocomplete="off"
          @finish="onFinish"
          @finishFailed="onFinishFailed"
      >
        <a-form-item
            label="手机号"
            name="mobile"
            :rules="[{ required: true, message: '请输入您的手机号!' }]"
        >
          <a-input v-model:value="formState.username" />
        </a-form-item>

        <a-form-item
            label="图片验证码"
            name="ICode"
            :rules="[{ required: true, message: '请输入您的图片验证码!' }]"
        >
          <a-input v-model:value="formState.ICode" />
        </a-form-item>

        <a-form-item
            label="短信验证码"
            name="MCode"
            :rules="[{ required: true, message: '请输入您的短信验证码!' }]"
        >
          <a-input-group compact>
            <a-input v-model:value="formState.MCode" />
            <a-button type="primary" style="display: block;float: right;margin-bottom: 20px">发送短信</a-button>
          </a-input-group>
        </a-form-item>

        <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
          <a-button type="primary" html-type="submit">登录</a-button>
        </a-form-item>
      </a-form>
    </a-col>
    <a-col :span="8"></a-col>
  </a-row>

</template>
<style>
  .md {
    padding-top: 200px;
  }
</style>
<script>
import { defineComponent, reactive } from 'vue';
export default defineComponent({
  setup() {
    const formState = reactive({
      mobile: '',
      ICode: '',
      MCode: '',
      remember: true,
    });
    const onFinish = values => {
      console.log('Success:', values);
    };
    const onFinishFailed = errorInfo => {
      console.log('Failed:', errorInfo);
    };
    return {
      formState,
      onFinish,
      onFinishFailed,
    };
  },
});
</script>