<template>
  <div class="dashboard-editor-container">
    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <el-row style="background: #fff; padding: 16px 16px 0; margin-bottom: 32px">
       <el-table
          v-loading="loading"
          v-if="orderShow"
          :data="orderList"
          @row-click="returnOrder"
          @selection-change="handleSelectionChange"
        >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="单据状态" align="center" prop="djStatusName" />
      <el-table-column label="订单号" align="center" prop="djNumber" />
      <el-table-column label="开票状态" align="center" prop="fpStatus" />
      <el-table-column label="订单日期" align="center" prop="djTime" />
      <el-table-column label="客户名称" align="center" prop="personName" />
      <el-table-column label="订单总额" align="center" prop="sumMoney" />
    </el-table>
    <pagination
         v-if="orderShow"
        v-show="totalOrder > 0"
        :total="totalOrder"
        :page.sync="queryParamsOrder.pageNum"
        :limit.sync="queryParamsOrder.pageSize"
        @pagination="getListOrder"
      />

      <el-table
        v-if="cgddShow"
        v-loading="loading"
        :data="cgddList"
        @selection-change="handleSelectionChange"
        @row-click="returnCgdd"
      >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="单据状态" align="center" prop="djStatusName" />
      <el-table-column label="开票状态" align="center" prop="fpStatusName" />
      <el-table-column label="入库状态" align="center" prop="rkStatusName" />
      <el-table-column label="交货状态" align="center" prop="shStatusName" />
      <el-table-column label="订单号" align="center" prop="orderNumber" />
      <el-table-column label="采购日期" align="center" prop="cgTime" />
      <el-table-column label="入库日期" align="center" prop="rkTime" />
      <el-table-column label="交货日期" align="center" prop="jhTime" />
      <el-table-column label="采购金额" align="center" prop="goodsMoney" />
      </el-table>
      <pagination
        v-if="cgddShow"
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    <el-form :model="queryParamsAll" ref="queryForm" :inline="true" label-width="120px"   v-if="cgddAllShow">
      <el-form-item label="订单号" prop="orderNumber">
        <el-input
          v-model="queryParamsAll.orderNumber"
          placeholder="请输入商品编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户名称" prop="personName">
        <el-input
          v-model="queryParamsAll.personName"
          placeholder="请输入客户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
        v-if="cgddAllShow"
        v-loading="loading"
        :data="cgddAllList"
        @selection-change="handleSelectionChange"
        @row-click="returnCgdd"
      >
      <el-table-column type="selection" width="30" align="center" hidden/>
      <el-table-column label="客户名称" align="center" prop="personName" />
      <el-table-column label="订单日期" align="center" prop="orderDjTime" />
      <el-table-column label="订单开票状态" align="center" prop="orderFpStatusName" />
      <el-table-column label="采购开票状态" align="center" prop="fpStatusName" />
      <el-table-column label="入库状态" align="center" prop="rkStatusName" />
      <el-table-column label="交货状态" align="center" prop="shStatusName" />
      <el-table-column label="订单号" align="center" prop="orderNumber" />
      <!-- <el-table-column label="对账单号" align="center" prop="orderNumber" /> -->
      <el-table-column label="采购日期" align="center" prop="cgTime" />
      <el-table-column label="入库日期" align="center" prop="rkTime" />
      <el-table-column label="交货日期" align="center" prop="jhTime" />
      <el-table-column label="采购金额" align="center" prop="goodsMoney" />
      <!-- <el-table-column label="发票号码" align="center" prop="fpNumber" /> -->
      </el-table>
      <pagination
        v-if="cgddAllShow"
        v-show="totalAll > 0"
        :total="totalAll"
        :page.sync="queryParamsAll.pageNum"
        :limit.sync="queryParamsAll.pageSize"
        @pagination="getAllList"
      />

    </el-row>
  </div>
</template>

<script>
import PanelGroup from "./dashboard/PanelGroup";
import { indexList } from "@/api/system/cgddInfo";
import { allList } from "@/api/system/cgddInfoChild";
import { listIndex } from "@/api/system/cgrkdSingle";
let socket = null;
export default {
  name: "Index",
  components: {
    PanelGroup,
  },
  data() {
    return {
      
      orderShow:true,
      cgddShow:false,
      cgddAllShow:false,
      userName: "",
      type: 1,
      khList: [],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      totalAll: 0,
      totalOrder: 0,
      // 采购列表
      cgddList: [],
      //订单列表
      orderList:[],
      //汇总列表
      cgddAllList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fpNumber: undefined,
        fpAccount: undefined,
        fpBank: undefined,
        fpRate: undefined,
        fpNum: undefined,
        khName: undefined,
        khCode: undefined,
      },
      queryParamsOrder: {
        pageNum: 1,
        pageSize: 10,
        fpNumber: undefined,
        fpAccount: undefined,
        fpBank: undefined,
        fpRate: undefined,
        fpNum: undefined,
        khName: undefined,
        khCode: undefined,
      },
       queryParamsAll: {
        pageNum: 1,
        pageSize: 10,
        fpNumber: undefined,
        fpAccount: undefined,
        fpBank: undefined,
        fpRate: undefined,
        orderNumber:undefined,
        personName:undefined,
        fpNum: undefined,
        khName: undefined,
        khCode: undefined,
      },
      // 表单参数
      form: {},
    };
  },
  created() {
    this.getListOrder();
    let username = localStorage.getItem("user");
    if (username != "" && username != null && username != undefined) {
      this.userName = username;
     // this.initWebSocket();
    }
  },
  methods: {
     /** 搜索按钮操作 */
    handleQuery() {
      this.queryParamsAll.pageNum = 1;
      this.getAllList();
    },
     /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    returnOrder(row, event, column){
      this.$router.push({ path: '/orderManager/order' ,query: {djNumber:row.djNumber}})
    },
    returnCgdd(row, event, column){
      this.$router.push({ path: '/cgManager/cgOrder' ,query: {djNumber:row.orderNumber}})
    },
    handleSelectionChange() {},
    handleSetLineChartData(data) {
      if(data==1){
          this.type = 1;
          this.getListOrder();
          this.orderShow=true;
          this.cgddShow=false;
          this.cgddAllShow=false;
      }
      if(data==2){
        this.type = 1;
        this.getList();
        this.orderShow=false;
        this.cgddShow=true;
        this.cgddAllShow=false;
      }
      if(data==3){
        this.type = 2;
        this.getList();
        this.orderShow=false;
        this.cgddShow=true;
        this.cgddAllShow=false;
      }
      if(data==4){
        this.type = 3;
        this.getList();
        this.orderShow=false;
        this.cgddShow=true;
        this.cgddAllShow=false;
      }
      if(data==5){
          this.type = 2;
          this.getListOrder();
          this.orderShow=true;
          this.cgddShow=false;
          this.cgddAllShow=false;
      }
      if(data==6){
          //this.type = 2;
          this.getAllList();
          this.orderShow=false;
          this.cgddShow=false;
          this.cgddAllShow=true;
      }
      // if (data == 4) {
      //   location.href = "/order/cgManager/cgOrder";
      // } else {
      //   //this.$router.push({path: '/order/cgManager/cgOrder'});
      //   this.type = data;
      //   this.getList();
      // }
    },
    /** 查询列表信息 */
    getList() {
      this.loading = true;
      indexList(this.type,this.queryParams).then((response) => {
        this.cgddList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getAllList() {
      this.loading = true;
      allList(this.queryParamsAll).then((response) => {
        this.cgddAllList = response.rows;
        this.totalAll = response.total;
        this.loading = false;
      });
    },
    getListOrder() {
      this.loading = true;
      listIndex(this.type,this.queryParamsOrder).then((response) => {
        this.orderList= response.rows;
        this.totalOrder = response.total;
        this.loading = false;
      });
    },
    initWebSocket() {
      this.websock = null;
      //判断当前浏览器是否支持WebSocket
      if ("WebSocket" in window) {
        //初始化weosocket(必须)
        this.websock = new WebSocket(
          "ws://192.168.1.107:80/websocket/" + this.userName
        ); //新建一个webstock对象
        this.websock.onopen = this.websocketonopen;
        this.websock.onmessage = this.websocketonmessage;
        this.websock.onerror = this.websocketonerror;
        this.websock.onclose = this.websocketclose;
      } else {
        alert("当前浏览器 Not support websocket");
      }
    },
    websocketonopen() {
      console.log("---连接建立成功---");
    },
    websocketonerror() {
      //连接建立失败重连
      this.initWebSocket();
    },
    websocketonmessage(e) {
      //数据接收
      this.redata = e.data;
      console.log("-----redata-----");
      console.log(this.redata);
      this.msgError(this.redata);
    },
    websocketsend(Data) {
      //数据发送
      this.websock.send(Data);
    },
    websocketclose(e) {
      //关闭
      console.log("断开连接", e);
    },
  },
};
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
