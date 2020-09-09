package com.yjh.controller;

//import com.yjh.aspect.PrincipalAspect;
import com.yjh.constant.CodeType;
import com.yjh.model.User;
import com.yjh.service.UserService;
import com.yjh.utils.DataMap;
import com.yjh.utils.JsonResult;
import com.yjh.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterControl {
    Logger log = LoggerFactory.getLogger(RegisterControl.class);
    @Autowired
    UserService userService;

    @PostMapping(value = "/register",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String register(User user){
        try {
//            String authCode = request.getParameter("authCode");

//            String trueMsgCode = (String) stringRedisService.get(user.getPhone());

            String trueMsgCode = user.getPhone();
            //判断手机号是否正确
            if(trueMsgCode == null){
                return JsonResult.fail(CodeType.PHONE_ERROR).toJSON();
            }
            //判断验证码是否正确
////            if(!authCode.equals(trueMsgCode)){
////                return JsonResult.fail(CodeType.AUTH_CODE_ERROR).toJSON();
////            }
//            //判断用户名是否存在
////            if(userService.usernameIsExist(user.getUsername()) || user.getUsername().equals(PrincipalAspect.ANONYMOUS_USER)){
////                return JsonResult.fail(CodeType.USERNAME_EXIST).toJSON();
////            }
            if(userService.usernameIsExist(user.getUsername())){
                return JsonResult.fail(CodeType.USERNAME_EXIST).toJSON();
            }
            //注册时对密码进行MD5加密
            MD5Util md5Util = new MD5Util();
            user.setPassword(md5Util.encode(user.getPassword()));

            //注册结果
            DataMap data = userService.insert(user);
////            if (0 == data.getCode()){
////                //注册成功删除redis中的验证码
////                stringRedisService.remove(user.getPhone());
////            }
            return JsonResult.build(data).toJSON();
        } catch (Exception e){
            log.error("User [{}] register exception", user, e);
        }
        return JsonResult.fail(CodeType.SERVER_EXCEPTION).toJSON();
    }


    @PostMapping(path = "/register2")
    public String register2(User user){
        String trueMsgCode = user.getPhone();
        System.out.println("trueMsgCode");
        //判断手机号是否正确
        if(trueMsgCode == null){
            return JsonResult.fail(CodeType.PHONE_ERROR).toJSON();
        }
        if(userService.usernameIsExist(user.getUsername())){
            return JsonResult.fail(CodeType.USERNAME_EXIST).toJSON();
        }
        //注册结果
        DataMap data = userService.insert(user);
////            if (0 == data.getCode()){
////                //注册成功删除redis中的验证码
////                stringRedisService.remove(user.getPhone());
////            }
        return JsonResult.build(data).toJSON();
    }

    @PostMapping(value = "/register3")
    public String register3(@RequestBody User user){
        String trueMsgCode = user.getPhone();
        //判断手机号是否正确
        if(trueMsgCode == null){
            return JsonResult.fail(CodeType.PHONE_ERROR).toJSON();
        }
        if(userService.usernameIsExist(user.getUsername())){
            return JsonResult.fail(CodeType.USERNAME_EXIST).toJSON();
        }
        //注册结果
        DataMap data = userService.insert(user);
////            if (0 == data.getCode()){
////                //注册成功删除redis中的验证码
////                stringRedisService.remove(user.getPhone());
////            }
        return JsonResult.build(data).toJSON();
    }

}
