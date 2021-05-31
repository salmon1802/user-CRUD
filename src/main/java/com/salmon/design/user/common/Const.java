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



    public interface Role {
        String ROLE_CUSTOMER = "普通用户"; //普通用户
        String ROLE_ADMIN = "管理员";  //管理员
    }






}
