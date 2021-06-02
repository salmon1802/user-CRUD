package com.salmon.design.user.service.impl;

import com.salmon.design.user.common.Const;
import com.salmon.design.user.common.ServerResponse;
import com.salmon.design.user.dao.UserMapper;
import com.salmon.design.user.pojo.User;
import com.salmon.design.user.service.IUserService;
import com.salmon.design.user.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @date 2021-5-12 - 22:11
 * Created by Salmon
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired(required = false)
    private UserMapper userMapper;


    /**
     * 登录操作
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        // 密码登录MD5
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, md5Password);
        if(user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }

        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功",user);
    }




    /**
     * 注册操作
     * @param user
     * @return
     */
    public ServerResponse<String> register(User user){
        ServerResponse validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }

        validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if(!validResponse.isSuccess()){
            return validResponse;
        }


        user.setRoleid(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }

        return ServerResponse.createBySuccessMessage("注册成功");
    }

    /**
     * 检查用户名与Email是否已存在
     * @param str
     * @param type
     * @return
     */
    public ServerResponse<String> checkValid(String str,String type){
        if(StringUtils.isNotBlank(type)){
            //开始校验
            if(Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if(resultCount > 0){
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if(Const.EMAIL.equals(type)){
                int resultCount = userMapper.checkEmail(str);
                if(resultCount > 0){
                    return ServerResponse.createByErrorMessage("email已存在");
                }
            }
        }else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }








    /**
     * 更新用户个人信息
     * @param user
     * @return
     */
    public ServerResponse<User> updateInformation(User user){
        //username是不能被更新的
        //email也要进行一个校验，校验新的email是不是已经存在，如果存在的话不能是当前用户的
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(), user.getUid());
        if(resultCount > 0){
            return ServerResponse.createByErrorMessage("Email已经存在，请更换Email再尝试更新");
        }
        User updateUser = new User();
        updateUser.setUid(user.getUid());
        updateUser.setUsername(user.getUsername());
        updateUser.setName(user.getName());
        updateUser.setBz(user.getBz());
        updateUser.setEmail(user.getEmail());
        updateUser.setNumber(user.getNumber());
        updateUser.setPhone(user.getPhone());


        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 0){
            return ServerResponse.createBySuccess("更新个人信息成功",updateUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }

    /**
     * 获取用户详细信息
     * @param userId
     * @return
     */
    public ServerResponse<User> getInformation(Integer userId){
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }


    /**
     * 删除当前用户
     * @param userId
     * @return
     */
    public ServerResponse<String> delete(Integer userId){
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }

        int deleteCount = userMapper.deleteByPrimaryKey(userId);
        if (deleteCount > 0){
            return ServerResponse.createBySuccessMessage("删除此用户成功");
        }
        return ServerResponse.createByErrorMessage("删除此用户失败");
    }






}

