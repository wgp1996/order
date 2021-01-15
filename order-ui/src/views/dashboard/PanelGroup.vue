<template>
  <el-row :gutter="40" class="panel-group">
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData(1)" :style="{'background': (check==true ? 'beige':'#fff')}">
        <div class="card-panel-icon-wrapper icon-message">
          <svg-icon icon-class="message" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            采购提醒
          </div>
          <count-to :start-val="0" :end-val="cgCount" :duration="3000" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData(2)" :style="{'background': (check1==true ? 'beige':'#fff')}">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="message" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            入库提醒
          </div>
          <count-to :start-val="0" :end-val="rkCount" :duration="3200" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData(3)" :style="{'background': (check2==true ? 'beige':'#fff')}">
        <div class="card-panel-icon-wrapper icon-shopping">
          <svg-icon icon-class="message" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            交货提醒
          </div>
          <count-to :start-val="0" :end-val="jhCount" :duration="3600" class="card-panel-num" />
        </div>
      </div>
    </el-col>
     <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData(4)" :style="{'background': (check3==true ? 'beige':'#fff')}">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="message" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            供应商开票提醒
          </div>
          <count-to :start-val="0" :end-val="gkpCount" :duration="3200" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData(5)" :style="{'background': (check4==true ? 'beige':'#fff')}">
        <div class="card-panel-icon-wrapper icon-shopping">
          <svg-icon icon-class="message" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            货主方开票提醒
          </div>
          <count-to :start-val="0" :end-val="hkpCount" :duration="3600" class="card-panel-num" />
        </div>
      </div>
    </el-col>
        <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData(6)" :style="{'background': (check5==true ? 'beige':'#fff')}">
        <div class="card-panel-icon-wrapper icon-people">
          <svg-icon icon-class="peoples" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
              订单总数
          </div>
          <count-to :start-val="0" :end-val="orderCount" :duration="2600" class="card-panel-num" />
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import CountTo from 'vue-count-to'
import {
  index
} from "@/api/system/cgddInfo";
export default {
  data(){
      return {
          check:false,
          check1:false,
          check2:false,
          check3:false,
          check4:false,
          check5:false,
          orderCount:0,
          cgCount:0,
          rkCount:0,
          jhCount:0,
          hkpCount:0,
          gkpCount:0
      }
  },
     // background: beige;
  components: {
    CountTo
  },
    created() {
    index().then((response) => {
      this.orderCount = response.data.orderCount;
      this.cgCount = response.data.cgCount;
      this.rkCount = response.data.rkCount;
      this.jhCount = response.data.jhCount;
       this.hkpCount = response.data.hkpCount;
        this.gkpCount = response.data.gkpCount;
    });
  },
  methods: {
    handleSetLineChartData(type) {
      if(type==1){
        this.check=true;
        this.check1=false;
        this.check2=false;
        this.check3=false;
        this.check4=false;
        this.check5=false;
      }
      if(type==2){
        this.check=false;
        this.check1=true;
        this.check2=false;
        this.check3=false;
        this.check4=false;
        this.check5=false;
      }
      if(type==3){
        this.check=false;
        this.check1=false;
        this.check2=true;
        this.check3=false;
        this.check4=false;
        this.check5=false;
      }
      
      if(type==4){
        //this.$router.replace('/order/cgManager/cgOrder')
        //this.$router.push({path: '/order/cgManager/cgOrder'});
        this.check=false;
        this.check1=false;
        this.check2=false;
        this.check3=true;
        this.check4=false;
        this.check5=false;
      }
       if(type==5){
        this.check=false;
        this.check1=false;
        this.check2=false;
        this.check3=false;
        this.check4=true;
        this.check5=false; 
      }
       if(type==6){
        this.check=false;
        this.check1=false;
        this.check2=false;
        this.check3=false;
        this.check4=false;
        this.check5=true; 
      }
      this.$emit('handleSetLineChartData', type)
    }
  }
}
</script>

<style lang="scss" scoped>
.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #34bfa3
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
