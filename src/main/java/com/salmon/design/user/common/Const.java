package com.salmon.design.user.common;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * @date 2021-5-13 - 14:42
 * Created by Salmon
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";

    public static final String PHONE = "phone";

    public static final String USERNAME = "username";

    public static final String EMAIL = "email";

    public interface  ActivityListOrderBy{
        Set<String> PEOPLE_ASC_DESC = Sets.newHashSet("people_desc","people_asc");
    }

    public interface MyActivity{
        int CHECKED = 1;//即活动选中状态
        int UN_CHECKED = 0;//活动未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    public interface Role {
        String ROLE_CUSTOMER = "普通用户"; //普通用户
        String ROLE_ADMIN = "管理员";  //管理员
    }

    public enum ActivityStatusEnum{


        ON_PROCEED(1,"进行");

        private String value;
        private int code;
        ActivityStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }





}
