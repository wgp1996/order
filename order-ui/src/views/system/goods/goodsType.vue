<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="120px">
      <el-form-item label="商品分类名称" prop="goodsTypeName">
        <el-input
          v-model="queryParams.goodsTypeName"
          placeholder="请输入商品分类名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
        </el-form-item>
      <el-form-item>
        <el-button
          class="filter-item"
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
        >搜索</el-button>
        <el-button
          class="filter-item"
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:goodsType:add']"
        >新增</el-button>
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      :data="goodsTypeList"
      row-key="goodsTypeId"
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="商品分类名称" align="center" prop="goodsTypeName" />
      <el-table-column label="显示顺序" align="center" prop="orderNum" />
      <el-table-column label="创建日期" align="center" prop="createTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:goodsType:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:goodsType:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改商品分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="商品分类父级" prop="goodsTypePid">
          <treeselect v-model="form.goodsTypePid" :options="goodsTypeOptions" :normalizer="normalizer" placeholder="请选择商品分类父级" />
        </el-form-item>
        <el-form-item label="商品分类名称" prop="goodsTypeName">
          <el-input v-model="form.goodsTypeName" placeholder="请输入商品分类名称" />
        </el-form-item>
        <el-form-item label="显示顺序" prop="orderNum">
          <el-input v-model="form.orderNum" placeholder="请输入显示顺序" />
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
import { listGoodsType, getGoodsType, delGoodsType, addGoodsType, updateGoodsType, exportGoodsType } from "@/api/system/goodsType";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "GoodsType",
  components: { Treeselect },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 商品分类表格数据
      goodsTypeList: [],
      // 商品分类树选项
      goodsTypeOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        goodsTypePid: undefined,
        goodsTypeName: undefined,
        orderNum: undefined,
        type: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
          goodsTypePid: [
          { required: true, message: "父级分类不能为空", trigger: "blur" }
        ],
         goodsTypeName: [
          { required: true, message: "分类名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询商品分类列表 */
    getList() {
      this.loading = true;
      listGoodsType(this.queryParams).then(response => {
        this.goodsTypeList = this.handleTree(response.data, "goodsTypeId", "goodsTypePid");
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
        goodsTypeId: undefined,
        goodsTypePid: undefined,
        goodsTypeName: undefined,
        orderNum: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        type: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
	  this.getTreeselect();
      this.open = true;
      this.title = "添加商品分类";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
	  this.getTreeselect();
      if (row != undefined) {
        this.form.goodsTypePid = row.goodsTypeId;
      }
      getGoodsType(row.goodsTypeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品分类";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.goodsTypeId != undefined) {
            updateGoodsType(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addGoodsType(this.form).then(response => {
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
      this.$confirm('是否确认删除商品分类编号为"' + row.goodsTypeId + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delGoodsType(row.goodsTypeId);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    }
  }
};
</script>