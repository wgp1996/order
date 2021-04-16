<template>
    <el-dialog :close-on-click-modal="false" :title="title"
    :visible.sync="visible" width="1100px">
    <el-row :gutter="20">
     <el-col :span="24" :xs="24">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="订单号" prop="djNumber">
        <el-input style="width:150px"
          v-model="queryParams.djNumber"
          placeholder="请输入订单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户名称" prop="khName">
        <el-input style="width:150px"
          v-model="queryParams.khName"
          placeholder="请输入客户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单日期" prop="djTime">
       <el-date-picker
            style="width: 100%"
            v-model="queryParams.djTime"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd"
            type="date"
            placeholder="单据日期"
             @keyup.enter.native="handleQuery"
          >
           </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button icon="el-icon-check" type="warning" size="mini" @click="selectDataMore">批量选择</el-button>
      </el-form-item>
    </el-form>
  
    
    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单号" align="center" prop="djNumber" width="140"/> 
      <el-table-column label="客户" align="center" prop="khName"/> 
      <el-table-column label="订单日期" align="center" prop="djTime" />
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <el-table-column label="规格" align="center" prop="goodsGg" />
      <el-table-column label="单位" align="center" prop="goodsDw" />
      <el-table-column label="数量" align="center" prop="goodsNum" />
      <el-table-column label="单价" align="center" prop="goodsPrice" />
      <el-table-column label="金额" align="center" prop="goodsMoney" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-check"
            @click="selectOrderData(scope.row)"
          >选择</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
       </el-col>
    </el-row>
     </el-dialog>
</template>

<script>
import { orderList } from "@/api/system/cgddInfo";
export default {
  name: "Stall",
  data() {
    return {
      orderSelectList:[],
       orderList:[],
       goodsTypeOptions:[],
       visible: false,
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
      // 市场摊位信息表格数据
      stallList: [],
      // 弹出层标题
      title: "选择订单",
      // 是否显示弹出层
      open: false,
      // 所属非类市场
      markDatas:[],
      //摊位状态
      perationOptions:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stallName: undefined,
        stallCode: undefined,
        markCode: undefined,
        regionArea: undefined,
        stallStatus: undefined,
        stallStartTime: undefined,
        stallEndTime: undefined,
        stallMoney: undefined,
        stallLeaseholder: undefined,
        stallNote: undefined,
        createUser: undefined,
      },
      // 表单参数
      form: {},
    };
  },
  created() {
      // this.getList();
  },
  methods: {
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.getList();
    },
    selectOrderData(row){
          this.$emit('selectData',row)
    },
    /** 查询市场摊位信息列表 */
    getList() {
      this.loading = true;
      orderList(this.queryParams).then(response => {
        this.orderList = response.rows;
        console.log( this.orderList )
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        stallName: undefined,
        stallCode: undefined,
        markCode: undefined,
        regionArea: undefined,
        stallStatus: undefined,
        stallStartTime: undefined,
        stallEndTime: undefined,
        stallMoney: undefined,
        stallLeaseholder: undefined,
        stallNote: undefined,
        createUser: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        remark: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.orderSelectList=selection;
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
     selectDataMore(){
          this.$emit('selectDataMore',this.orderSelectList);
    },
  }
};
</script>