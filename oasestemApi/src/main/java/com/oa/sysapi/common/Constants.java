package com.oa.sysapi.common;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String POST_STAFF = "员工";
    public static final String POST_FM = "部门经理";
    public static final String POST_GM = "总经理";
    public static final String POST_CASHIER = "财务";

    public static List<String> getPosts() {
        return Arrays.asList(POST_STAFF, POST_FM, POST_GM, POST_CASHIER);
    }

    public static List<String> getItems() {
        return Arrays.asList("交通", "住宿", "饮食", "办公");
    }

    public static final String CLAIMVOUCHER_CREATED = "新创建";
    public static final String CLAIMVOUCHER_SUBMIT = "已提交";
    public static final String CLAIMVOUCHER_APPROVED = "已审核";
    public static final String CLAIMVOUCHER_BACK = "已打回";
    public static final String CLAIMVOUCHER_TERMINATED = "已终止";
    public static final String CLAIMVOUCHER_RECHECK = "待复审";
    public static final String CLAIMVOUCHER_PAID = "已打款";

    public static final Double LIMIT_CHECK = 5000.00;

    public static final String DEAL_CREATE = "创建";
    public static final String DEAL_SUBMIT = "提交";
    public static final String DEAL_UPDATE = "修改";
    public static final String DEAL_BACK = "打回";
    public static final String DEAL_REJECT = "拒绝";
    public static final String DEAL_PASS = "通过";
    public static final String DEAL_PAID = "打款";
}
