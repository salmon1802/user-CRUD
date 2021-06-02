package com.salmon.design.user.service;

import com.salmon.design.user.common.ServerResponse;
import com.salmon.design.user.pojo.User;




/**
 * @date 2021-5-12 - 22:09
 * Created by Salmon
 */

public interface IUserService {

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str,String type);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInformation(Integer userId);

    ServerResponse<String> delete(Integer userId);

    ServerResponse<User> login(String username, String password);



}
