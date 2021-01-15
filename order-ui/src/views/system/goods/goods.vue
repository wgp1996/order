<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="商品编码" prop="goodsCode">
        <el-input
          v-model="queryParams.goodsCode"
          placeholder="请输入商品编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名称" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入商品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="品牌" prop="goodsAddress">
        <el-input
          v-model="queryParams.goodsAddress"
          placeholder="请输入商品品牌"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规格" prop="goodsGg">
        <el-input
          v-model="queryParams.goodsGg"
          placeholder="请输入商品规格"
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
          v-hasPermi="['system:goods:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:goods:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:goods:remove']"
        >删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:goods:export']"
        >导出</el-button>
      </el-col> -->
    </el-row>

    <el-table v-loading="loading" :data="goodsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品编码" align="center" prop="goodsCode" />
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <el-table-column label="商品分类" align="center" prop="goodsTypeName" />
      <el-table-column label="品牌" align="center" prop="goodsAddress" />
      <el-table-column label="商品规格" align="center" prop="goodsGg" />
      <el-table-column label="商品单位" align="center" prop="goodsDw" />
      <!-- <el-table-column label="备注" align="center" prop="remark" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:goods:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:goods:remove']"
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

    <!-- 添加或修改商品建档对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品分类" prop="goodsTypePid">
          <treeselect v-model="form.goodsType" :defaultExpandLevel="3" :options="goodsTypeOptions"   :normalizer="normalizer" placeholder="请选择商品分类父级" />
        </el-form-item>
        <el-form-item label="品牌" prop="goodsAddress">
          <el-input v-model="form.goodsAddress" placeholder="请输入品牌" />
        </el-form-item>
        <el-form-item label="商品规格" prop="goodsGg">
          <el-input v-model="form.goodsGg" placeholder="请输入商品规格" />
        </el-form-item>
        <el-form-item label="商品单位" prop="goodsDw">
           <el-select v-model="form.goodsDw" placeholder="请输入商品单位" style="width:100%">
                <el-option
                  v-for="dict in perationOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
        </el-form-item>
         <!-- <el-form-item label="产地" prop="goodsDw">
          <el-input v-model="form.goodsAddress" placeholder="请输入产地" />
        </el-form-item>
         <el-form-item label="图片信息">
              <el-upload
                class="avatar-uploader"
                  :action="upload.url"
                  :headers="upload.headers"
                :show-file-list="false"
                :on-success="handleImageSuccess">
                <img width="100%" v-if="form.goodsImg" :src="form.goodsImg" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
          </el-form-item> -->
          <el-form-item label="备注" prop="goodsDw">
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
import { listGoods, getGoods, delGoods, addGoods, updateGoods, exportGoods } from "@/api/system/goods";
import { listGoodsType } from "@/api/system/goodsType";
import { getToken } from "@/utils/auth";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
export default {
  name: "Goods",
  components: { Treeselect },
  data() {
    return {
      goodsTypeOptions:[],
      perationOptions:[],
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
        url: process.env.VUE_APP_BASE_API + "/common/upload"
      },
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
      // 商品建档表格数据
      goodsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsCode: undefined,
        goodsName: undefined,
        goodsGg: undefined,
        goodsDw: undefined,
        goodsAddress: undefined,
        type: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
          goodsName: [
          { required: true, message: "商品名称不能为空", trigger: "blur" }
        ],
         goodsAddress: [
          { required: true, message: "品牌不能为空", trigger: "blur" }
        ],
         goodsGg: [
          { required: true, message: "规格不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
      this.getDicts("sys_dw").then((response) => {
       console.log(response)
      this.perationOptions = response.data;

    });
  },
  methods: {
     handleImageSuccess(res, file, fileList) {
      this.form.goodsImg = res.url;
      // 上传成功
      console.log(res.url);
    },
    /** 查询商品建档列表 */
    getList() {
      this.loading = true;
      listGoods(this.queryParams).then(response => {
        this.goodsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
     /** 转换商品分类数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.goodsTypeId,
        label: node.goodsTypeName,
        children: node.children
      };
    },
	/** 查询部门下拉树结构 */
    getTreeselect() {
      listGoodsType().then(response => {
        this.goodsTypeOptions = [];
        const data = { goodsTypeId: 0, goodsTypeName: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "goodsTypeId", "goodsTypePid");
        this.goodsTypeOptions.push(data);
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
        goodsCode: undefined,
        goodsName: undefined,
        goodsAddress: undefined,
        goodsGg: undefined,
        goodsDw: undefined,
        goodsImg: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        remark: undefined,
        type: undefined
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
      this.getTreeselect();
      this.open = true;
      this.title = "添加商品建档";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      const id = row.id || this.ids
      getGoods(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品建档";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateGoods(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addGoods(this.form).then(response => {
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
      this.$confirm('是否确认删除商品建档编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delGoods(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有商品建档数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportGoods(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  .el-upload-dragger{
    height: auto !important;
  }
</style>