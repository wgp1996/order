<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="120px">
      <el-form-item label="商品名称" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入商品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商名称" prop="khName">
        <el-input
          v-model="queryParams.khName"
          placeholder="请输入供应商名称"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:goodsPrice:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:goodsPrice:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:goodsPrice:remove']"
        >删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:goodsPrice:export']"
        >导出</el-button>
      </el-col> -->
    </el-row>

    <el-table v-loading="loading" :data="goodsPriceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <!-- <el-table-column label="商品编码" align="center" prop="goodsCode" /> -->
      <el-table-column label="供应商名称" align="center" prop="khName" />
      <!-- <el-table-column label="客户编码" align="center" prop="khCode" /> -->
      <el-table-column label="商品价格" align="center" prop="goodsPrice" />
      <el-table-column label="规格" align="center" prop="goodsGg" />
      <el-table-column label="单位" align="center" prop="goodsDw" />
      <el-table-column label="日期" align="center" prop="createTime" />
       <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:goodsPrice:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:goodsPrice:remove']"
          >删除</el-button>
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

    <!-- 添加或修改商品价格管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="商品名称" prop="goodsCode">
          <el-select
                v-model="form.goodsCode"
                placeholder="请选择商品"
                filterable
                style="width: 100%"
                @change="selectGoods"
              >
                <el-option
                  v-for="item in goodsList"
                  :key="item.goodsCode"
                  :label="item.goodsName"
                  :value="item.goodsCode"
                >
                </el-option>
           </el-select>
        </el-form-item>
        <el-form-item label="选择供应商" prop="khCode">
           <el-select
                v-model="form.khCode"
                placeholder="请选择供应商"
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
        <el-form-item label="商品价格" prop="goodsPrice">
          <el-input v-model="form.goodsPrice" placeholder="请输入商品价格" />
        </el-form-item>
        <el-form-item label="规格" prop="goodsGg">
          <el-input v-model="form.goodsGg" placeholder="请输入规格" :disabled="true"/>
        </el-form-item>
        <el-form-item label="单位" prop="goodsDw">
          <el-input v-model="form.goodsDw" placeholder="请输入单位" :disabled="true"/>
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
import { listGoodsPrice, getGoodsPrice, delGoodsPrice, addGoodsPrice, updateGoodsPrice, exportGoodsPrice } from "@/api/system/goodsPrice";
import { getKhList }  from "@/api/system/kh";
import { goodsListAll }  from "@/api/system/goods";
export default {
  name: "GoodsPrice",
  data() {
    return {
      goodsList:[],
      khList:[],
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
      // 商品价格管理表格数据
      goodsPriceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsName: undefined,
        goodsCode: undefined,
        khName: undefined,
        khCode: undefined,
        goodsPrice: undefined,
        goodsGg: undefined,
        goodsDw: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        goodsCode: [
          { required: true, message: "商品不能为空", trigger: "blur" }
        ],
        khCode: [
          { required: true, message: "供应商不能为空", trigger: "blur" }
        ],
        goodsPrice: [
          { required: true, message: "商品价格不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    let queryParams={khType:1};
    getKhList(queryParams).then((response) => {
        this.khList = response.rows;
    });
    let goodsQueryParams={};
    goodsListAll(goodsQueryParams).then((response) => {
        this.goodsList = response.rows;
    });
  },
  methods: {
      //选择供应商
    selectKh(data) {
      //根据编码找名称
      for (let i = 0; i < this.khList.length; i++) {
        if (this.khList[i].khCode == data) {
          this.form.khName = this.khList[i].khName;
          break;
        }
      }
    },
     //选择客户
    selectGoods(data) {
      //根据编码找名称
      for (let i = 0; i < this.goodsList.length; i++) {
        if (this.goodsList[i].goodsCode == data) {
          this.form.goodsName = this.goodsList[i].goodsName;
          this.form.goodsDw = this.goodsList[i].goodsDw;
          this.form.goodsGg = this.goodsList[i].goodsGg;
          break;
        }
      }
    },
    /** 查询商品价格管理列表 */
    getList() {
      this.loading = true;
      listGoodsPrice(this.queryParams).then(response => {
        this.goodsPriceList = response.rows;
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
        goodsName: undefined,
        goodsCode: undefined,
        khName: undefined,
        khCode: undefined,
        goodsPrice: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        remark: undefined,
        goodsGg: undefined,
        goodsDw: undefined
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品价格管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getGoodsPrice(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品价格管理";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateGoodsPrice(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addGoodsPrice(this.form).then(response => {
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
      this.$confirm('是否确认删除商品价格管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delGoodsPrice(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有商品价格管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportGoodsPrice(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>