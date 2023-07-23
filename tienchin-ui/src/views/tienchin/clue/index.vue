<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="线索名称" prop="name">
        <el-input
            v-model="queryParams.name"
            placeholder="请输入线索名称"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="线索类型" prop="type">
        <el-select
            v-model="queryParams.channelId"
            placeholder="线索类型"
            clearable
        >
          <el-option
              v-for="ct in course_type"
              :key="ct.value"
              :label="ct.label"
              :value="ct.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="适用人群" prop="applyTo">
        <el-select v-model="queryParams.status" placeholder="适用人群" clearable>
          <el-option
              v-for="cat in course_apply_to"
              :key="cat.value"
              :label="cat.label"
              :value="cat.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="最低价格" prop="minPrice">
        <el-input-number placeholder="最低价格" @keyup.enter="handleQuery" clearable v-model="queryParams.minPrice"
                         :precision="2" :step="100" :min="0"/>
      </el-form-item>
      <el-form-item label="最高价格" prop="maxPrice">
        <el-input-number placeholder="最高价格" @keyup.enter="handleQuery" clearable v-model="queryParams.maxPrice"
                         :precision="2" :step="100" :min="0"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="Plus"
            @click="handleAdd"
            v-hasPermi="['tienchin:course:create']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['tienchin:course:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['tienchin:course:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['tienchin:course:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="线索编号" align="center" :show-overflow-tooltip="true" width="80" prop="courseId"/>
      <el-table-column label="线索类型" align="center" width="120px">
        <template #default="scope">
          <dict-tag :options="course_type" :value="scope.row.type"></dict-tag>
        </template>
      </el-table-column>
      <el-table-column label="线索名称" align="center" :show-overflow-tooltip="true" width="120" prop="name"/>
      <el-table-column label="线索价格" align="center" :show-overflow-tooltip="true" width="180" prop="price"/>
      <el-table-column label="线索适用人群" align="center" width="150">
        <template #default="scope">
          <dict-tag :options="course_apply_to" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="线索简介" align="center" :show-overflow-tooltip="true" width="180" prop="info"/>
      <!--            <el-table-column label="线索备注" align="center" :show-overflow-tooltip="true" width="180" prop="remark"/>-->
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
              type="text"
              icon="Edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['tienchin:course:edit']"
          >修改
          </el-button>
          <el-button
              type="text"
              icon="Delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['tienchin:course:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total > 0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 添加或修改活动对话框 -->
    <el-dialog :title="title" v-model="open" width="700px" append-to-body>
      <el-form ref="clueRef" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="手机号码" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号码"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="客户姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入客户姓名"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="渠道来源" prop="channelId">
              <el-select
                  v-model="form.channelId"
                  placeholder="渠道来源"
                  clearable
                  @change="channelChange"
                  style="width: 240px"
              >
                <el-option
                    v-for="cl in channelList"
                    :key="cl.channelId"
                    :label="cl.channelName"
                    :value="cl.channelId"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="活动信息" prop="activityId">
              <el-select
                  v-model="form.activityId"
                  placeholder="活动信息"
                  clearable
                  style="width: 240px"
              >
                <el-option
                    v-for="al in activityList"
                    :key="al.activityId"
                    :label="al.name"
                    :value="al.activityId"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="客户性别" prop="gender">
              <el-radio-group v-model="form.gender">
                <el-radio
                    v-for="dict in sys_user_sex"
                    :key="dict.value"
                    :label="parseInt(dict.value)"
                >{{ dict.label }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="客户年龄" prop="age">
              <el-input-number v-model="form.age" :min="6" :max="100" placeholder="年龄"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="微信号" prop="weixin">
              <el-input v-model="form.weixin" placeholder="请输入微信号码"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="QQ号码" prop="qq">
              <el-input v-model="form.qq" placeholder="请输入QQ号码"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Post">
import {listCourse, addCourse, getCourse, updateCourse, delCourse} from "../../../api/tienchin/course";

import {
  listChannels,
  listActivity,
  addClue,
  listClue,
  listUsers,
  assignClue,
  getClueSummaryById,
  updateClue,
  delClue
} from "../../../api/tienchin/clue";

const {proxy} = getCurrentInstance();
const {course_type, course_apply_to, sys_user_sex} = proxy.useDict("course_type", "course_apply_to", "sys_user_sex");

const activityList = ref([]);
const channelList = ref([]);
const courseList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  assignForm: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: undefined,
    phone: undefined,
    owner: undefined,
    channelId: undefined,
    dateRange: undefined,
    status: undefined
  },
  rules: {
    phone: [{required: true, message: "手机号码不能为空", trigger: "blur"}],
    name: [{required: true, message: "客户姓名不能为空", trigger: "blur"}],
  },
  assignFormRules: {
    departmentId: [{required: true, message: "部门ID不能为空", trigger: "blur"}],
    nickName: [{required: true, message: "用户名称不能为空", trigger: "blur"}],
  }
});

const {queryParams, form, rules} = toRefs(data);

function channelChange(channelId) {
  form.value.activityId = undefined;
  listActivity(channelId).then(response => {
    activityList.value = response.data;
    console.log(activityList.value);
  })
}

function getAllChannels() {
  listChannels().then(response => {
    channelList.value = response.data;
  })
}

function getList() {
  loading.value = true;
  listCourse(queryParams.value).then(response => {
    courseList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

/** 取消按钮 */
function cancel() {
  open.value = false;
  reset();
}

/** 表单重置 */
function reset() {
  form.value = {
    applyTo: undefined,
    name: undefined,
    info: undefined,
    type: undefined,
    price: undefined
  };
  proxy.resetForm("courseRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.courseId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加线索";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const courseId = row.courseId || ids.value;
  getCourse(courseId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改线索";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["clueRef"].validate(valid => {
    if (valid) {
      if (form.value.clueId != undefined) {
        updateClue(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addClue(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const courseIds = row.courseId || ids.value;
  proxy.$modal.confirm('是否确认删除线索编号为"' + courseIds + '"的数据项？').then(function () {
    return delCourse(courseIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}


/** 导出按钮操作 */
function handleExport() {
  proxy.download("tienchin/course/export", {
    ...queryParams.value
  }, `course_${new Date().getTime()}.xlsx`);
}

getAllChannels()
getList();
</script>
