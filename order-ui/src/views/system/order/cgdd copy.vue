<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      label-width="100px"
    >
      <el-form-item label="单据编号" prop="djNumber">
        <el-input
          v-model="queryParams.djNumber"
          placeholder="请输入单据编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始日期" prop="djTime">
        <el-date-picker
          style="width: 100%"
          v-model="queryParams.beginTime"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd"
          type="date"
          placeholder="单据日期"
          @keyup.enter.native="handleQuery"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束日期" prop="djTime">
        <el-date-picker
          style="width: 100%"
          v-model="queryParams.endTime"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd"
          type="date"
          placeholder="单据日期"
          @keyup.enter.native="handleQuery"
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:cgddInfo:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:cgddInfo:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:cgddInfo:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-s-promotion"
          size="mini"
          :disabled="multiple"
          @click="handleEffect"
          v-hasPermi="['system:cgddInfo:effect']"
          >生效</el-button
        >
      </el-col>
        <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="handleCancel"
          v-hasPermi="['system:cgddInfo:cancel']"
          >取消</el-button
        >
      </el-col>
    </el-row>

    <el-table
      v-loading="loading"
      :data="leaseList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-table
            style="padding: 0; margin: 0"
            :data="props.row.childrenList"
            show-summary
            :summary-method="getSummarie"
            id="special"
          >
            <el-table-column label="商品名称" align="center" prop="goodsName" />
            <el-table-column label="商品规格" align="center" prop="goodsGg" />
            <el-table-column label="供应商" align="center" prop="khName" />
            <el-table-column label="采购日期" align="center" prop="cgTime" />
            <el-table-column label="下单日期" align="center" prop="orderTime" />
            <el-table-column label="入库时间" align="center" prop="rkTime" />
            <el-table-column label="交货时间" align="center" prop="jhTime" />
             <el-table-column
              label="收货人"
              align="center"
              prop="shPersonName"
            />
            <el-table-column label="采购数量" align="center" prop="goodsNum" />
            <el-table-column
              label="采购价格"
              align="center"
              prop="goodsPrice"
            />
            <el-table-column label="金额" align="center" prop="goodsMoney" />
           
           
          </el-table>
        </template>
      </el-table-column>
      <el-table-column label="单据状态" align="center" prop="djStatusName" />
      <el-table-column label="采购订单号" align="center" prop="djNumber" />
      <el-table-column label="采购订单日期" align="center" prop="djTime" />
      <el-table-column label="制单人" align="center" prop="createBy" />
      <el-table-column label="制单日期" align="center" prop="createTime" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:cgddInfo:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:cgddInfo:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改二级市场信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="基础信息" name="first">
          <el-form ref="form" :model="form" :rules="rules" label-width="120px">
            <el-form-item label="备注信息" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注信息" />
            </el-form-item>
            <el-form-item label="单据编号" prop="djNumber" class="changeBlue">
              <el-input
                v-model="form.djNumber"
                :disabled="true"
                placeholder="后台自动生成"
              />
            </el-form-item>
            <el-form-item label="制单人" class="changeBlue">
              <el-input
                v-model="user.ownerNameJc"
                :disabled="true"
                placeholder="制单人"
              />
            </el-form-item>
            <el-form-item label="制单日期" prop="djTime" class="changeBlue">
              <el-date-picker
                style="width: 100%"
                v-model="form.djTime"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd"
                type="date"
                placeholder="制单日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="明细信息" name="second">
          <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
              <el-button
                type="primary"
                icon="el-icon-plus"
                size="mini"
                @click="orderSelect"
                >选择订单</el-button
              >
            </el-col>
          </el-row>

          <el-table
            :data="tableData"
            class="tb-edit"
            style="width: 100%"
            highlight-current-row
            @row-click="handleCurrentChange"
            :header-cell-class-name="starAdd"
          >
            <el-table-column prop="goodsName" label="商品名称" width="150" fixed="left">
              <template scope="scope">
                <el-input
                  :disabled="true"
                  size="small"
                  v-model="scope.row.goodsName"
                  placeholder="请输入内容"
                ></el-input>
                <span>{{ scope.row.goodsName }}</span>
              </template>
            </el-table-column>
            <el-table-column label="规格" width="120">
              <template scope="scope">
                <el-input
                  size="small"
                  :disabled="true"
                  v-model="scope.row.goodsGg"
                  placeholder="请输入规格"
                  @change="scope.$index, scope.row"
                ></el-input>
                <span>{{ scope.row.goodsGg }}</span>
              </template>
            </el-table-column>
            <el-table-column prop="goodsDw" label="单位" width="100">
              <template scope="scope">
                <el-select
                  :disabled="true"
                  v-model="scope.row.goodsDw"
                  placeholder="请输入单位信息"
                  style="width: 100%"
                >
                  <el-option
                    v-for="dict in dwOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
                <span>{{ scope.row.goodsDw }}</span>
              </template>
            </el-table-column>
            <el-table-column label="供应商" prop="khCode" width="150">
              <template scope="scope">
                <el-select
                  v-model="scope.row.khCode"
                  placeholder="请选择供应商"
                  filterable
                  style="width: 100%"
                  @change="selectKh($event, scope.$index, scope.row)"
                >
                  <el-option
                    v-for="item in khList"
                    :key="item.khCode"
                    :label="item.khName"
                    :value="item.khCode"
                  >
                    <span style="width: 100%">{{ item.khName }}</span>
                  </el-option>
                </el-select>
              </template>
            </el-table-column>
            <el-table-column label="采购数量" width="120">
              <template scope="scope">
                <el-input
                  size="small"
                  v-model="scope.row.goodsNum"
                  placeholder="请输入数量"
                  :onkeyup="
                    (scope.row.goodsNum = scope.row.goodsNum.replace(
                      /[^\d.]/g,
                      ''
                    ))
                  "
                  @change="handleEdit(scope.$index, scope.row)"
                ></el-input>
                <span>{{ scope.row.goodsNum }}</span>
              </template>
            </el-table-column>
            <el-table-column label="采购单价" width="120">
              <template scope="scope">
                <el-input
                 :disabled="true"
                  size="small"
                  v-model="scope.row.goodsPrice"
                  placeholder="请输入单价"
                  @change="handleEdit(scope.$index, scope.row)"
                ></el-input>
                <span>{{ scope.row.goodsPrice }}</span>
              </template>
            </el-table-column>
            <el-table-column label="采购金额" width="120">
              <template scope="scope">
                <el-input
                  :disabled="true"
                  size="small"
                  v-model="scope.row.goodsMoney"
                  placeholder="请输入金额"
                ></el-input>
                <span>{{ scope.row.goodsMoney }}</span>
              </template>
            </el-table-column>
            <el-table-column label="采购日期" width="150">
              <template scope="scope">
                <el-date-picker
                  v-model="scope.row.cgTime"
                  type="date"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  placeholder="采购日期"
                ></el-date-picker>
                <span>{{ scope.row.cgTime }}</span>
              </template>
            </el-table-column>
            <!-- <el-table-column label="下单日期" width="150">
              <template scope="scope">
                <el-date-picker
                  v-model="scope.row.orderTime"
                  type="date"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  placeholder="下单日期"
                ></el-date-picker>
                <span>{{ scope.row.orderTime }}</span>
              </template>
            </el-table-column> -->
            <el-table-column label="入库日期" width="150">
              <template scope="scope">
                <el-date-picker
                  v-model="scope.row.rkTime"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  type="date"
                  placeholder="入库日期"
                ></el-date-picker>
                <span>{{ scope.row.rkTime }}</span>
              </template>
            </el-table-column>
            <el-table-column label="交货日期" width="150">
              <template scope="scope">
                <el-date-picker
                  v-model="scope.row.jhTime"
                  type="date"
                  placeholder="交货日期"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                ></el-date-picker>
                <span>{{ scope.row.jhTime }}</span>
              </template>
            </el-table-column>
            <el-table-column label="收货人" width="150">
              <template scope="scope">
                <el-input
                  size="small"
                  v-model="scope.row.shPersonName"
                  placeholder="请输入收货人"
                ></el-input>
                <span>{{ scope.row.shPersonName }}</span>
              </template>
            </el-table-column>
            <el-table-column label="备注" width="120">
              <template scope="scope">
                <el-input
                  size="small"
                  v-model="scope.row.remark"
                  placeholder="请输入备注"
                ></el-input>
                <span>{{ scope.row.remark }}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template scope="scope">
                <el-button
                  size="small"
                  type="danger"
                  @click="handleChildDelete(scope.$index, scope.row)"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <order-select
      v-if="selectOrderDialog"
      ref="selectOrder"
      @selectData="selectData"
      @selectDataMore="selectDataMore"
    ></order-select>
  </div>
</template>

<script>
import {
  listCgddInfo,
  getCgddInfo,
  delCgddInfo,
  addCgddInfo,
  updateCgddInfo,
  delCgddInfoChild,
  exportCgddInfo,
  getCgddInfoChild,
  cancelCgddInfoStatus,
  updateCgddInfoStatus,
} from "@/api/system/cgddInfo";

import { selectGoodsPrice } from "@/api/system/goodsPrice";
import { getKhList } from "@/api/system/kh";
import orderSelect from "./orderSelect";
import { getToken } from "@/utils/auth";
import { getInfo } from "@/api/login";
export default {
  name: "Lease",
  components: {
    orderSelect,
  },
  data() {
    return {
      khList: [],
      //用户信息
      user: {
        ownerCode: "",
        ownerName: "",
        ownerNameJc: "",
      },
      placeholderone: "",
      //客户
      personList: [],
      // 遮罩层
      loading: false,
      selectOrderDialog: false,
      //选取主市场
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      //子表数据
      tableData: [],

      // 主表信息表格数据
      leaseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      perationOptions: [],
      dwOptions: [],
      operateOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        markCode: undefined,
        djNumber: undefined,
        djTime: undefined,
        beginTime: undefined,
        endTime: undefined,
      },
      // 表单参数
      form: {},
      activeName: "first",
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
    let queryParams = { khType: 1 };
    getKhList(queryParams).then((response) => {
      this.khList = response.rows;
    });
    getInfo().then((response) => {
      this.user.ownerCode = response.user.userName;
      this.user.ownerName = response.user.nickName;
      this.user.ownerNameJc = response.user.nickName;
      this.form.djTime = this.getTime();
    });
  },
  methods: {
    //选择客户
    selectKh(data, index, row) {
      //根据编码找产地
      for (let i = 0; i < this.khList.length; i++) {
        if (this.khList[i].khCode == data) {
          row.khName = this.khList[i].khName;
          break;
        }
      }

      selectGoodsPrice(row.goodsCode, data).then((response) => {
        if (response.data != null) {
          row.goodsPrice = response.data.goodsPrice;
          if (row.goodsNum != "" && row.goodsNum != null) {
            row.goodsMoney = (
              parseFloat(row.goodsNum) * parseFloat(row.goodsPrice)
            ).toFixed(2);
          }
        }
      });
    },
    getSummarie(param) {
      return this.getSummaries(param, ["goodsDw"]);
    },
    getSummaries(param, arr = []) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 7) {
          sums[index] = "合计:";
          return;
        }
        let bor = true;
        //判断是否存在不需要合计的列
        if (
          arr.length > 0 &&
          arr.find((item) => item == column.property) != undefined
        ) {
          bor = false;
        }
        const values = data.map((item) => Number(item[column.property]));
        if (!values.every((value) => isNaN(value)) && bor) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr);
            if (!isNaN(value)) {
              return Number(prev) + Number(curr);
            } else {
              return Number(prev);
            }
          }, 0);
        } else {
          sums[index] = "";
        }
      });
      return sums;
    },
    editTime(i) {
      if (i < 10) {
        i = "0" + i;
      }
      return i;
    },
    getTime() {
      var date = new Date();
      var year = date.getFullYear(); //得到当前年份
      var month = this.editTime(date.getMonth() + 1); //得到当前月份
      var day = this.editTime(date.getDate()); //得到当前几号
      var hour = this.editTime(date.getHours()); //得到当前小时
      var min = this.editTime(date.getMinutes()); //得到当前分钟
      var seconds = this.editTime(date.getSeconds()); //得到当前秒
      var weeks = date.getDay();
      var week;
      switch (weeks) {
        case 0:
          week = "星期日";
          break;
        case 1:
          week = "星期一";
          break;
        case 2:
          week = "星期二";
          break;
        case 3:
          week = "星期三";
          break;
        case 4:
          week = "星期四";
          break;
        case 5:
          week = "星期五";
          break;
        case 6:
          week = "星期六";
          break;
      }
      return year + "-" + month + "-" + day;
    },
    //获取后三天日期
    getThreeTime() {
      var date = new Date();
      var year = date.getFullYear(); //得到当前年份
      var month = this.editTime(date.getMonth() + 1); //得到当前月份
      var day = this.editTime(date.getDate())+3; //得到当前几号
      var hour = this.editTime(date.getHours()); //得到当前小时
      var min = this.editTime(date.getMinutes()); //得到当前分钟
      var seconds = this.editTime(date.getSeconds()); //得到当前秒
      var weeks = date.getDay();
      var week;
      switch (weeks) {
        case 0:
          week = "星期日";
          break;
        case 1:
          week = "星期一";
          break;
        case 2:
          week = "星期二";
          break;
        case 3:
          week = "星期三";
          break;
        case 4:
          week = "星期四";
          break;
        case 5:
          week = "星期五";
          break;
        case 6:
          week = "星期六";
          break;
      }
      return year + "-" + month + "-" + day;
    },
    //追加子表必填样式
    starAdd(obj) {
      if (
        obj.columnIndex === 0 ||
        obj.columnIndex === 3 ||
        obj.columnIndex === 4 ||
        obj.columnIndex === 5 ||
        obj.columnIndex === 6 ||
        obj.columnIndex === 7 ||
        obj.columnIndex === 8 ||
        obj.columnIndex === 9 ||
        obj.columnIndex === 10
      ) {
        return "star";
      }
    },
    handleClick(tab, event) {},
    handleCurrentChange(row, event, column) {
      console.log(row, event, column, event.currentTarget);
    },
    handleEdit(index, row) {
      if (
        row.goodsPrice != "" &&
        row.goodsPrice != null &&
        row.goodsPrice != undefined
      ) {
        row.goodsPriceRate = row.goodsPrice;
      }
      if (
        row.goodsNum != "" &&
        row.goodsNum != null &&
        row.goodsNum != undefined &&
        row.goodsPrice != "" &&
        row.goodsPrice != null &&
        row.goodsPrice != undefined
      ) {
        row.goodsMoney = (
          parseFloat(row.goodsNum) * parseFloat(row.goodsPrice)
        ).toFixed(2);
        row.goodsMoneyRate = row.goodsMoney;
      }
    },
    getSumMoney(index, row) {
      //计算总金额
      let sumMoney = 0;
      for (let i = 0; i < this.tableData.length; i++) {
        if (this.tableData[i].goodsNum != "") {
          sumMoney += parseFloat(this.tableData[i].goodsNum);
        }
      }
      this.form.storeName = sumMoney.toString();
      console.log(this.form);
    },
    handleChildDelete(index, row) {
      if (row.id != "" && row.id != undefined && row.id != null) {
        if(this.form.status==0){
          delCgddInfoChild(row.id);
          this.tableData.splice(index, 1);
        }
      } else {
        this.tableData.splice(index, 1);
      }
      console.log(index, row);
    },
    /** 操作 */
    orderSelect() {
      this.selectOrderDialog = true;
      this.$nextTick(() => {
        this.$refs.selectOrder.getList();
        this.$refs.selectOrder.visible = true;
      });
    },
    selectData(row) {
      console.log(row);
      this.$nextTick(() => {
        //检查是否存在重复数据
        for (let i = 0; i < this.tableData.length; i++) {
          if (row.id == this.tableData[i].orderId) {
            //this.msgError("信息重复!");
            return;
          }
        }

        let goodsInfo = new Object();
        goodsInfo.goodsCode = row.goodsCode;
        goodsInfo.goodsName = row.goodsName;
        goodsInfo.orderNumber = row.djNumber;
        goodsInfo.orderId = row.id;
        goodsInfo.goodsDw = row.goodsDw;
        goodsInfo.orderTime = this.getTime();
        goodsInfo.cgTime = this.getTime();
        goodsInfo.goodsGg = row.goodsGg;
        goodsInfo.goodsNum = row.goodsNum;
        goodsInfo.goodsPrice = row.goodsPrice;
        goodsInfo.goodsMoney = row.goodsMoney;
        goodsInfo.khCode = "";
        goodsInfo.khName = "";
        goodsInfo.rkTime = this.getThreeTime();
        goodsInfo.jhTime = "";
        goodsInfo.shPersonName = "";
        goodsInfo.remark = "";
        this.tableData.push(goodsInfo);
        this.$refs.selectOrder.visible = false;
      });
    },
    //批量选择数据
    selectDataMore(rows) {
      this.$nextTick(() => {
        for (let i = 0; i < rows.length; i++) {
          let row = rows[i];
          //检查是否存在重复数据
          for (let j = 0; j < this.tableData.length; j++) {
            if (row.id == this.tableData[j].orderId) {
              //this.msgError("信息重复!");
              return;
            }
          }
          let goodsInfo = new Object();
          goodsInfo.goodsCode = row.goodsCode;
          goodsInfo.goodsName = row.goodsName;
          goodsInfo.orderNumber = row.djNumber;
          goodsInfo.orderId = row.id;
          goodsInfo.goodsDw = row.goodsDw;
          goodsInfo.orderTime = this.getTime();
          goodsInfo.cgTime = this.getTime();
          goodsInfo.goodsGg = row.goodsGg;
          goodsInfo.goodsNum = row.goodsNum;
          goodsInfo.goodsPrice = row.goodsPrice;
          goodsInfo.goodsMoney = row.goodsMoney;
          goodsInfo.khCode = "";
          goodsInfo.khName = "";
          goodsInfo.rkTime =  this.getThreeTime();
          goodsInfo.jhTime = "";
          goodsInfo.shPersonName = "";
          goodsInfo.remark = "";
          this.tableData.push(goodsInfo);
        }
        this.$refs.selectOrder.visible = false;
      });
    },
    /** 查询二级市场信息列表 */
    getList() {
      this.sumNum = 0;
      this.loading = true;
      listCgddInfo(this.queryParams).then((response) => {
        this.leaseList = response.rows;
        let sum = 0;
        this.sumNum = sum.toFixed(2);
        console.log(this.leaseList);
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
        djNumber: undefined,
        djTime: this.getTime(),
        storeCode: undefined,
        storeName: undefined,
        personName: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        remark: undefined,
        fileName: undefined,
        personCode: undefined,
        rows: "",
        isRate: 0,
        payType: undefined,
        payTypeName: undefined,
      };
      this.form.payType = this.cash;
      this.form.storeCode = this.place;
      this.form.payTypeName = this.payTypes;
      (this.form.storeName = this.storeNames), this.resetForm("form");
      this.tableData = [];
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
      this.ids = selection.map((item) => item.id);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加采购订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getCgddInfo(id).then((response) => {
        this.form = response.data;
        getCgddInfoChild(this.form.djNumber).then((response) => {
          //this.form.rows = response.data;
          this.tableData = response.rows;
        });
        this.open = true;
        this.title = "修改采购订单";
      });
    },
    /** 取消生效按钮操作 */
    handleCancel(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认取消该单据?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return cancelCgddInfoStatus(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("操作成功");
        })
        .catch(function () {});
    },
    /** 提交按钮 */
    submitForm: function () {
      if (this.tableData.length > 0) {
        //检查子表信息
        for (let i = 0; i < this.tableData.length; i++) {
          if (
            this.tableData[i].goodsCode == "" ||
            this.tableData[i].khCode == "" ||
            this.tableData[i].goodsPrice == "" ||
            this.tableData[i].goodsMoney == "" ||
            this.tableData[i].rkTime == "" ||
            //this.tableData[i].orderTime == "" ||
            this.tableData[i].jhTime == "" ||
            this.tableData[i].shPersonName == "" ||
            this.tableData[i].goodsNum == ""
          ) {
            this.msgError("检查明细信息必填项!");
            return;
          }
        }
        this.form.rows = JSON.stringify(this.tableData);
        this.$refs["form"].validate((valid) => {
          if (valid) {
            if (this.form.id != undefined) {
              updateCgddInfo(this.form).then((response) => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                  this.$refs.selectOrder.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            } else {
              addCgddInfo(this.form).then((response) => {
                if (response.code === 200) {
                  this.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                  this.$refs.selectOrder.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            }
          }
        });
      } else {
        this.msgError("请填写子表信息!");
      }
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除该单据?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delCgddInfo(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function () {});
    },
    /** 生效按钮操作 */
    handleEffect(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认该单据已生效?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return updateCgddInfoStatus(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("操作成功");
        })
        .catch(function () {});
    },
  },
};
</script>
<style>
.cell .el-select + span {
  display: none;
}
.cell .el-input + span {
  display: none;
}
.el-table__expanded-cell {
  padding: 0 !important;
  margin: 0 !important;
}
.tb-edit .el-input {
  display: block !important;
}
.tb-edit .current-row .el-input {
  display: block;
}
.tb-edit .current-row .el-input + span {
  display: none;
}
.tb-edit .current-row .el-select {
  display: block;
}
.tb-edit .current-row .el-select + span {
  display: none;
}
table th.star div::after {
  content: "*";
  color: red;
}
.el-input.is-disabled .el-input__inner {
  color: #606266;
}
.changeBlue .el-form-item__label {
  color: #1890ff;
}
</style>