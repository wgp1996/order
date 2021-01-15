<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      label-width="120px"
    >
      <el-form-item label="发票代码" prop="fpNum">
        <el-input
          v-model="queryParams.fpNum"
          placeholder="请输入发票代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="往来单位名称" prop="khName">
        <el-input
          v-model="queryParams.khName"
          placeholder="请输入往来单位名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:fpInfo:add']"
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
          v-hasPermi="['system:fpInfo:edit']"
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
          v-hasPermi="['system:fpInfo:remove']"
          >删除</el-button
        >
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:fpInfo:export']"
          >导出</el-button
        >
      </el-col> -->
    </el-row>

    <el-table
      v-loading="loading"
      :data="fpInfoList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="往来单位名称" align="center" prop="khName" />
      <!-- <el-table-column label="发票号码" align="center" prop="fpNumber" /> -->
      <el-table-column label="发票代码" align="center" prop="fpNum" />
      <el-table-column label="开户账号" align="center" prop="fpAccount" />
      <el-table-column label="开户行" align="center" prop="fpBank" />
      <el-table-column label="税号" align="center" prop="fpRate" />
      <el-table-column label="备注" align="center" prop="remark" />
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
            v-hasPermi="['system:fpInfo:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:fpInfo:remove']"
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

    <!-- 添加或修改发票建档对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="选择往来单位" prop="khCode">
          <el-select
            v-model="form.khCode"
            placeholder="请选择往来单位"
            filterable
            style="width: 100%"
            @change="selectKh"
          >
            <el-option
              v-for="item in khList"
              :key="item.khCode"
              :label="item.khName"
              :value="item.khCode"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="发票号码" prop="fpNumber">
          <el-input v-model="form.fpNumber" placeholder="请输入发票号码" />
        </el-form-item> -->
        <el-form-item label="发票代码" prop="fpNum">
          <el-input v-model="form.fpNum" placeholder="请输入发票代码" />
        </el-form-item>
        <el-form-item label="税号" prop="fpRate">
          <el-input v-model="form.fpRate" placeholder="请输入税号" />
        </el-form-item>

        <el-form-item label="开户账号" prop="fpAccount">
          <el-input v-model="form.fpAccount" placeholder="请输入开户账号" />
        </el-form-item>
        <el-form-item label="开户行" prop="fpBank">
          <el-input v-model="form.fpBank" placeholder="请输入开户行" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listFpInfo,
  getFpInfo,
  delFpInfo,
  addFpInfo,
  updateFpInfo,
  exportFpInfo,
} from "@/api/system/fpInfo";
import { getKhList } from "@/api/system/kh";
export default {
  name: "FpInfo",
  data() {
    return {
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
      // 发票建档表格数据
      fpInfoList: [],
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
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // fpNumber: [
        //   { required: true, message: "发票号码不能为空", trigger: "blur" },
        // ],
        khCode: [
          { required: true, message: "往来单位不能为空", trigger: "blur" },
        ],
        fpNum: [
          { required: true, message: "发票代码不能为空", trigger: "blur" },
        ],
        fpRate: [{ required: true, message: "税号不能为空", trigger: "blur" }],
        // fpAccount: [
        //   { required: true, message: "开户账号不能为空", trigger: "blur" }
        // ],
        // fpBank: [
        //   { required: true, message: "开户行不能为空", trigger: "blur" }
        // ],
      },
    };
  },
  created() {
    this.getList();
    let queryParams = {};
    getKhList(queryParams).then((response) => {
      this.khList = response.rows;
    });
  },
  methods: {
    //选择往来单位
    selectKh(data) {
      //根据编码找名称
      for (let i = 0; i < this.khList.length; i++) {
        if (this.khList[i].khCode == data) {
          this.form.khName = this.khList[i].khName;
          break;
        }
      }
    },
    /** 查询发票建档列表 */
    getList() {
      this.loading = true;
      listFpInfo(this.queryParams).then((response) => {
        this.fpInfoList = response.rows;
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
        fpNumber: undefined,
        fpAccount: undefined,
        fpBank: undefined,
        fpRate: undefined,
        fpNum: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        remark: undefined,
        khName: undefined,
        khCode: undefined,
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
      this.ids = selection.map((item) => item.id);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加发票建档";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getFpInfo(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改发票建档";
      });
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != undefined) {
            updateFpInfo(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addFpInfo(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm(
        '是否确认删除发票建档编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delFpInfo(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function () {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有发票建档数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportFpInfo(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>