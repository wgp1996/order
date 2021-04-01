<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      label-width="80px"
    >
      <el-form-item label="订单号" prop="djNumber">
        <el-input
          v-model="queryParams.djNumber"
          placeholder="请输入订单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户名称" prop="personName">
        <el-input
          v-model="queryParams.personName"
          placeholder="请输入客户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
       <el-form-item label="开票状态" prop="fpNumber">
      <el-select v-model="queryParams.fpNumber" clearable  placeholder="开票状态">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
         </el-form-item>
     <el-form-item label="开始日期" prop="beginTime">
        <el-date-picker
          style="width: 100%"
          v-model="queryParams.beginTime"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd"
          type="date"
          placeholder="开始日期"
          @keyup.enter.native="handleQuery"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束日期" prop="endTime">
        <el-date-picker
          style="width: 100%"
          v-model="queryParams.endTime"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd"
          type="date"
          placeholder="结束日期"
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
          v-hasPermi="['system:cgrkdSingle:add']"
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
          v-hasPermi="['system:cgrkdSingle:edit']"
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
          v-hasPermi="['system:cgrkdSingle:remove']"
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
          v-hasPermi="['system:cgrkdSingle:effect']"
          >确认订单</el-button
        >
      </el-col>
        <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="handleCancel"
          v-hasPermi="['system:cgrkdSingle:cancel']"
          >取消确认</el-button
        >
      </el-col>
        <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-s-promotion"
          size="mini"
          :disabled="single"
          @click="handleUpdateFp"
          v-hasPermi="['system:cgrkdSingle:edit']"
          >确认开票</el-button
        >
      </el-col>
    </el-row>

    <el-table
      v-loading="loading"
      :data="leaseList"
       :summary-method="getSummariesMain"
         show-summary
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
            <el-table-column label="商品单位" align="center" prop="goodsDw" />
            <el-table-column label="料号" align="center" prop="goodsCodeImg" />
            <el-table-column label="数量" align="center" prop="goodsNum" />
            <el-table-column label="单价" align="center" prop="goodsPrice" />
            <el-table-column label="金额" align="center" prop="goodsMoney" />
            <!-- <el-table-column label="备注" align="center" prop="remark" /> -->
          </el-table>
        </template>
      </el-table-column>
      <el-table-column label="订单状态" align="center" prop="djStatusName" />
      <el-table-column label="开票状态" align="center" prop="fpStatus" />
      <el-table-column label="订单号" align="center" prop="djNumber" />
      <el-table-column label="订单日期" align="center" prop="djTime" />
      <el-table-column label="客户名称" align="center" prop="personName" />
      <!-- <el-table-column label="结算方式" align="center" prop="payTypeName" /> -->
      <!-- <el-table-column label="制单人" align="center" prop="createBy" /> -->
      <el-table-column label="订单总额" align="center" prop="sumMoney" />
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
            @click="selectUpdateFp(scope.row)"
            v-hasPermi="['system:cgrkdSingle:edit']"
            >开票信息</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:cgrkdSingle:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page-sizes="[10, 30, 50,100,300,500]"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
   <el-dialog :title="title" :visible.sync="openFp" width="600px">
          <el-form ref="formFp" :model="formFp" :rules="rulesFp" label-width="120px">
            <el-form-item label="发票号" prop="fpNumber">
              <el-input
                v-model="formFp.fpNumber"
                placeholder="发票号"
              />
            </el-form-item>
            <el-form-item label="发票代码" prop="fpNum">
              <el-input
                v-model="formFp.fpNum"
                placeholder="发票代码"
              />
            </el-form-item>
            <el-form-item label="开户账号" prop="fpAccount">
              <el-input
                v-model="formFp.fpAccount"
                placeholder="开户账号"
              />
            </el-form-item>
            <el-form-item label="开户行" prop="fpBank">
              <el-input
                v-model="formFp.fpBank"
                placeholder="开户行"
              />
            </el-form-item>
            <el-form-item label="税号" prop="fpRate">
              <el-input
                v-model="formFp.fpRate"
                placeholder="税号"
              />
            </el-form-item>
            <el-form-item label="开票日期" prop="fpTime">
              <el-date-picker
                style="width: 100%"
                v-model="formFp.fpTime"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd"
                type="date"
                placeholder="开票日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormFp">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 添加或修改二级市场信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="基础信息" name="first">
          <el-form ref="form" :model="form" :rules="rules" label-width="120px">
            <el-form-item label="客户" prop="personCode">
              <el-select
                v-model="form.personCode"
                placeholder="请选择客户"
                filterable
                style="width: 100%"
                @change="selectPerson"
              >
                <el-option
                  v-for="item in personList"
                  :key="item.khCode"
                  :label="item.khName"
                  :value="item.khCode"
                >
                  <span style="float: left; width: 50%">{{
                    item.khCode
                  }}</span>
                  <span style="float: left; width: 50%">{{
                    item.khName
                  }}</span>
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="订单号" prop="djNumber">
              <el-input
                v-model="form.djNumber"
                placeholder="请输入订单号"
              />
            </el-form-item>
            <el-form-item label="备注信息" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注信息" />
            </el-form-item>
            <el-form-item label="订单日期" prop="djTime" class="changeBlue">
              <el-date-picker
                style="width: 100%"
                v-model="form.djTime"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd"
                type="date"
                placeholder="订单日期"
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
                @click="goodsSelect"
                >选择商品</el-button
              >
            </el-col>
          </el-row>

          <el-table
            :data="tableData"
             :summary-method="getSummariesChild"
             show-summary
            class="tb-edit"
            style="width: 100%"
            highlight-current-row
            @row-click="handleCurrentChange"
            :header-cell-class-name="starAdd"
          >
            <el-table-column prop="goodsName" label="商品名称" width="130"   fixed="left">
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
            <el-table-column label="规格" width="130">
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
                  placeholder="单位"
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

            <el-table-column label="数量" width="100">
              <template scope="scope">
                <el-input
                  size="small"
                  v-model="scope.row.goodsNum"
                  placeholder="数量"
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
            <el-table-column label="单价" width="100">
              <template scope="scope">
                <el-input
                  size="small"
                  v-model="scope.row.goodsPrice"
                  placeholder="单价"
                  @change="handleEdit(scope.$index, scope.row)"
                ></el-input>
                <span>{{ scope.row.goodsPrice }}</span>
              </template>
            </el-table-column>
            <el-table-column label="金额" width="100">
              <template scope="scope">
                <el-input
                  :disabled="true"
                  size="small"
                  v-model="scope.row.goodsMoney"
                  placeholder="金额"
                ></el-input>
                <span>{{ scope.row.goodsMoney }}</span>
              </template>
            </el-table-column>
            <el-table-column label="备注" width="120">
              <template scope="scope">
                <el-input
                  size="small"
                  v-model="scope.row.remark"
                  placeholder="备注"
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
        <!-- <el-tab-pane label="附件信息" name="three">
          <el-row :gutter="15" class="mb8">
            <el-col :span="1.5">
              <el-upload
                class="upload-demo"
                :limit="1"
                drag
                :file-list="fileList"
                :action="upload.url"
                :headers="upload.headers"
                :on-success="handleFileSuccess"
                :on-remove="handleRemove"
                :on-preview="clickFile"
              >
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">
                  将文件拖到此处，或
                  <em>点击上传</em>
                </div>
              </el-upload>
            </el-col>
          </el-row>
        </el-tab-pane> -->
      </el-tabs>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <goods-select
      v-if="selectGoodsDialog"
      ref="selectGoods"
      @selectData="selectData"
      @selectDataMore="selectDataMore"
    ></goods-select>
  </div>
</template>

<script>
import {
  listCgrkdSingle,
  getCgrkdSingle,
  getCgrkdSingleChild,
  delCgrkdSingle,
  delCgrkdSingleChild,
  addCgrkdSingle,
  updateCgrkdSingle,
  updateCgrkdSingleFp,
  updateCgrkdSingleStatus,
  updateCgrkdSingleCanceltatus,
  exportCgrkdSingle,
  getOwnerList,
  
} from "@/api/system/cgrkdSingle";

import goodsSelect from "./goodsSelect";
import { getKhList } from "@/api/system/kh";
import { getToken } from "@/utils/auth";
import { getInfo } from "@/api/login";
import {getFpInfoByKh} from "@/api/system/fpInfo";
export default {
  name: "Lease",
  components: {
    goodsSelect,
  },
  data() {
    return {
      sumMoney:0,
      //用户信息
      user: {
        ownerCode: "",
        ownerName: "",
        ownerNameJc: "",
      },
      options:[{
          value: '0',
          label: '未开票'
        }, {
          value: '1',
          label: '已开票'
        },],
      //仓库列表
      storeList: [],
      fileList: [],
      sumNum: 0,
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/common/upload",
      },
      // 仓库信息默认
      placeholderone: "",

      //客户
      personList: [],
      // 遮罩层
      loading: false,
      selectGoodsDialog: false,
      //选取主市场
      markDatas: [],
      // 选中数组
      ids: [],
      khCodes:[],
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
      openFp:false,
      perationOptions: [],
      dwOptions: [],
      operateOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fpNumber:"",
        personCode: undefined,
        djNumber: undefined,
        beginTime: undefined,
        endTime: undefined,
      },
      // 表单参数
      form: {},
      formFp:{},
      // 默认现结
      cash: undefined,
      // 仓库默认
      place: undefined,
      // 结算方式名称默认
      payTypes: undefined,
      // 仓库名称
      storeNames: undefined,
      activeName: "first",
      // 表单校验
      rules: {
         djNumber: [
            { required: true, message: "订单号不能为空", trigger: "blur" },
          ],
        personCode: [
          { required: true, message: "客户不能为空", trigger: "blur" },
        ],
        // storeCode: [
        //   { required: true, message: "仓库信息不能为空", trigger: "blur" },
        // ],
      },
      rulesFp:{
          fpNumber: [
            { required: true, message: "发票号不能为空", trigger: "blur" },
          ],
          fpTime: [
            { required: true, message: "开票日期不能为空", trigger: "blur" },
          ],
      }
    };
  },
  created() {
     let djNumber=this.$route.query.djNumber;
     if(djNumber!=""&&djNumber!=null&&djNumber!=undefined){
        this.queryParams.djNumber=djNumber;
     }
    this.getList();
    this.getDicts("pay_type").then((response) => {
      this.perationOptions = response.data;
      this.cash = response.data[1].dictValue;
      this.payTypes = response.data[1].dictLabel;
      console.log(this.perationOptions);
    });
    let queryParams = { khType: 0 };
    getKhList(queryParams).then((response) => {
      this.personList = response.rows;
    });
    getInfo().then((response) => {
      this.user.ownerCode = response.user.userName;
      this.user.ownerName = response.user.nickName;
      this.user.ownerNameJc = response.user.nickName;
      this.form.djTime = this.getTime();
    });
  },
  methods: {
    // getSummaries(param) {
    //   const { columns, data } = param;
    //   const sums = [];
    //   columns.forEach((column, index) => {
    //     if (index === 4) {
    //       sums[index] = "金额总数:";
    //       return;
    //     }
    //     if (index === 5) {
    //       sums[index] = this.sumNum;
    //       return;
    //     }
    //   });

    //   return sums;
    // },
     getSummarie(param) {
      return this.getSummaries(param,['goodsDw'])
    },
    getSummaries (param, arr = []) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 2) {
          sums[index] = '合计:';
          return;
        };
        let bor = true;
        //判断是否存在不需要合计的列
        if (arr.length > 0 && (arr.find(item => item == column.property) != undefined)) {
          bor = false
        };
        const values = data.map(item => Number(item[column.property]));
        if (!values.every(value => isNaN(value)) && bor) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr);
            if (!isNaN(value)) {
              return (Number(prev) + Number(curr)).toFixed(2);
            } else {
              return Number(prev);
            }
          }, 0);
        } else {
          sums[index] = '';
        }
      });
      return sums;
    },
    getSummariesMain (param, arr = []) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 6) {
          sums[index] = '合计:';
          return;
        };
        let bor = true;
        //判断是否存在不需要合计的列
        if (arr.length > 0 && (arr.find(item => item == column.property) != undefined)) {
          bor = false
        };
        const values = data.map(item => Number(item[column.property]));
        if (!values.every(value => isNaN(value)) && bor) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr);
            if (!isNaN(value)) {
              return (Number(prev) + Number(curr)).toFixed(2);
            } else {
              return Number(prev);
            }
          }, 0);
        } else {
          sums[index] = '';
        }
      });
      return sums;
    },
     getSummariesChild (param, arr = []) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 4) {
          sums[index] = '金额合计:';
          return;
        };
      });
      sums[5]=this.sumMoney;
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
    //是否含税
    changeRate(data) {
      //不含税时置空税率跟恢复含税金额
      if (data == 0) {
        for (let i = 0; i < this.tableData.length; i++) {
          this.tableData[i].goodsRate = "0";
          this.tableData[i].goodsPriceRate = this.tableData[i].goodsPrice;
          this.tableData[i].goodsMoneyRate = this.tableData[i].goodsMoney;
        }
      }
    },
    //追加子表必填样式
    starAdd(obj) {
      if (obj.columnIndex === 0 || obj.columnIndex === 3) {
        return "star";
      }
    },
    clickFile(file) {
      if (file.url != "") {
        window.location.href = file.url;
      }
    },
    handleFileSuccess(res, file, fileList) {
      // 上传成功
      console.log(res.url);
      this.form.fileName = res.url;
    },
    handleRemove(file, fileList) {
      this.form.fileName = "";
    },
    //选择客户
    selectOwner(data) {},
    //选择客户
    selectPerson(data) {
      //根据编码找产地
      for (let i = 0; i < this.personList.length; i++) {
        if (this.personList[i].khCode == data) {
          this.form.personName = this.personList[i].khName;
          break;
        }
      }
    },
    //选择仓库
    selectStore(data) {
      //根据摊位编码查找摊位名称
      for (let i = 0; i < this.storeList.length; i++) {
        if (this.storeList[i].ckCode == data) {
          this.form.storeName = this.storeList[i].ckName;

          break;
        }
      }
    },
    //选择结算方式
    selectCell(data) {
      //根据摊位编码查找摊位名称
      for (let i = 0; i < this.perationOptions.length; i++) {
        if (this.perationOptions[i].dictValue == data) {
          this.form.payTypeName = this.perationOptions[i].dictLabel;
          // alert(this.form.payTypeName)
          break;
        }
      }
    },
    handleClick(tab, event) {
      // if(tab.name=="second"){
      //     if(this.radio=="1"){
      //       this.show=true
      //     }else{
      //      this.show= false
      //     }
      // }
      //  console.log(tab.name);
    },
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
        this.getSumMoney();
    },
    getSumMoney() {
      //计算总金额
      let sumMoney = 0;
      for (let i = 0; i < this.tableData.length; i++) {
        if (this.tableData[i].goodsMoney != "") {
          sumMoney += parseFloat(this.tableData[i].goodsMoney);
        }
      }
      this.sumMoney=sumMoney.toFixed(2);
    },
    handleChildDelete(index, row) {
      if (row.id != "" && row.id != undefined && row.id != null) {
        if(this.form.status==0){
            delCgrkdSingleChild(row.id);
            this.tableData.splice(index, 1);
        }
      } else {
        this.tableData.splice(index, 1);
      }
      this.getSumMoney();
      console.log(index, row);
    },
    /** 操作 */
    goodsSelect() {
      this.selectGoodsDialog = true;
      this.$nextTick(() => {
        //this.$refs.selectGoods.getList(this.form.personCode);
        this.$refs.selectGoods.visible = true;
      });
    },
    selectData(row) {
      console.log(row);
      this.$nextTick(() => {
        //检查是否存在重复数据
        // for (let i = 0; i < this.tableData.length; i++) {
        //   if (row.goodsCode == this.tableData[i].goodsCode) {
        //     this.msgError("信息重复!");
        //     return;
        //   }
        // }
        let goodsInfo = new Object();
        goodsInfo.goodsCode = row.goodsCode;
        goodsInfo.goodsCodeImg = row.goodsCodeImg;
        goodsInfo.goodsName = row.goodsName;
        goodsInfo.goodsDw = row.goodsDw;
        goodsInfo.goodsGg = row.goodsGg;
        goodsInfo.personCode = "";
        goodsInfo.personName = "";
        goodsInfo.goodsNum = "";
        goodsInfo.goodsPrice = "";
        goodsInfo.goodsMoney = "";
        goodsInfo.remark = "";
        this.tableData.push(goodsInfo);
        this.$refs.selectGoods.visible = false;
      });
    },
    //批量选择数据
    selectDataMore(rows) {
      this.$nextTick(() => {
        //检查是否存在重复数据
        // for (let i = 0; i < this.tableData.length; i++) {
        //   if (row.goodsCode == this.tableData[i].goodsCode) {
        //     this.msgError("信息重复!");
        //     return;
        //   }
        // }
        for (let i = 0; i < rows.length; i++) {
          let row = rows[i];
          let goodsInfo = new Object();
          goodsInfo.goodsCode = row.goodsCode;
          goodsInfo.goodsCodeImg = row.goodsCodeImg;
          goodsInfo.goodsName = row.goodsName;
          goodsInfo.goodsDw = row.goodsDw;
          goodsInfo.goodsGg = row.goodsGg;
          goodsInfo.personCode = "";
          goodsInfo.personName = "";
          goodsInfo.goodsNum = "";
          goodsInfo.goodsPrice = "";
          goodsInfo.goodsMoney = "";
          //goodsInfo.goodsWeight = "";
          goodsInfo.remark = "";
          this.tableData.push(goodsInfo);
        }
        this.$refs.selectGoods.visible = false;
      });
    },
    /** 查询二级市场信息列表 */
    getList() {
      this.sumNum = 0;
      this.loading = true;
      listCgrkdSingle(this.queryParams).then((response) => {
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
      this.openFp = false;
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
      this.formFp = {
        id: undefined,
        fpNumber: undefined,
        fpBank: undefined,
        fpRate: undefined,
        fpNum: undefined,
        fpAccount: undefined,
        fpTime: undefined,
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
      this.khCodes = selection.map((item) => item.personCode);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getCgrkdSingle(id).then((response) => {
        this.form = response.data;
        if (response.data.fileName != "") {
          this.fileList = [];
          let info = new Object();
          info.name = response.data.fileName;
          info.url = response.data.fileName;
          this.fileList.push(info);
        }
        getCgrkdSingleChild(this.form.djNumber).then((response) => {
          //this.form.rows = response.data;
          this.tableData = response.rows;
          this.getSumMoney();
        });
        this.open = true;
        this.title = "修改订单";
      });
    },
     /** 确认开票操作 */
    handleUpdateFp(row) {
      this.reset();
      const id = row.id || this.ids;
      const khCode = row.personCode || this.khCodes;
      this.formFp.id=id[0];
      getFpInfoByKh(khCode).then((response) => {
        if (response.data != null) {
           this.formFp.fpNum=response.data.fpNum;
           this.formFp.fpAccount=response.data.fpAccount;
           this.formFp.fpBank=response.data.fpBank;
           this.formFp.fpRate=response.data.fpRate;
        }
      });
      this.openFp = true;
      this.title = "确认开票";
    },
    /** 开票按钮 */
    submitFormFp: function () {
        this.$refs["formFp"].validate((valid) => {
          if (valid) {
              updateCgrkdSingleFp(this.formFp).then((response) => {
                if (response.code === 200) {
                  this.msgSuccess("开票成功");
                  this.openFp = false;
                  this.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
          }
        });
    },
    selectUpdateFp(row) {
      this.reset();
      getCgrkdSingle(row.id).then((response) => {
         this.formFp = response.data;
      });
      this.openFp = true;
      this.title = "查看开票信息";
    },
    /** 提交按钮 */
    submitForm: function () {
      if (this.tableData.length > 0) {
        //检查子表信息
        for (let i = 0; i < this.tableData.length; i++) {
          if (
            this.tableData[i].goodsCode == "" ||
            //this.tableData[i].goodsPrice == "" ||
            //this.tableData[i].goodsMoney == "" ||
            //this.tableData[i].goodsRate == "" ||
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
              updateCgrkdSingle(this.form).then((response) => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                  this.$refs.selectGoods.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            } else {
              addCgrkdSingle(this.form).then((response) => {
                if (response.code === 200) {
                  this.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                  this.$refs.selectGoods.getList();
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
      this.$confirm('是否确认删除?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delCgrkdSingle(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function () {});
    },
    /** 确认按钮操作 */
    handleEffect(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认已确认?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return updateCgrkdSingleStatus(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("操作成功");
        })
        .catch(function () {});
    },
     /** 取消确认按钮操作 */
    handleCancel(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认取消该单据?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return updateCgrkdSingleCanceltatus(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("操作成功");
        })
        .catch(function () {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有租赁单据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportCgrkdSingle(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
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