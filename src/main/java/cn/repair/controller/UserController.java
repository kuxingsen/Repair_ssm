package cn.repair.controller;

import cn.repair.dto.Result;
import cn.repair.pojo.PhoneUser;
import cn.repair.service.UserService;
import cn.repair.utils.PhoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
public class UserController{
    @Autowired
    private UserService userService;

    /**
     * 获取验证码
     * @param phoneNum 手机号
     * @return 失败（false）或成功（true）+验证码（data[0]）
     */
    @RequestMapping("getCode")
    public Result<String> getCode(String phoneNum){
        String msg;
        //校验手机号格式
        if(PhoneUtil.isPhone(phoneNum)){
            //获取验证码
            String code = userService.getCode(phoneNum);
            if(code != null){
                return new Result<>(true,new ArrayList<String>(){{add(code);}});//使用代码块的方式初始化List
            }else {
                msg="无法获取验证码";
            }
        }else {
            msg="不是正确的手机号格式";
            System.out.println(phoneNum);
        }
        return new Result<>(false,msg);
    }

    /**
     * 用户登录
     * @param phoneUser 需要phoneNum 手机号，code 验证码
     * @return 失败（false）或成功（true）
     */
    @RequestMapping("login")
    public Result loginByPhone(PhoneUser phoneUser, HttpSession session){
        String msg;
        //校验手机号和验证码格式
        if(PhoneUtil.isPhone(phoneUser.getPhoneNum()) && PhoneUtil.isCode(phoneUser.getCode())){
            //查看是否匹配
            int r = userService.loginByPhone(phoneUser);
            if(r > 0){
                session.setAttribute("userPhone",phoneUser.getPhoneNum());
                return new Result<>(true);
            }else {
                msg="验证码错误";
            }
        }else {
            msg="不是正确的手机号或验证码格式";
        }
        return new Result<>(false,msg);
    }

    /**
     * 免登陆直接进入（需要用户曾经登录过且数据库中没有验证码）
     * @param phoneNum 手机号码
     * @return 失败（false）或成功（true）
     */
    @RequestMapping("loginAgain")
    public Result loginAgain(String phoneNum, HttpSession session){
        String msg="不是正确的手机号或未登录";
        //校验手机号和验证码格式
        if(PhoneUtil.isPhone(phoneNum)){
            //查看是否匹配
            int r = userService.loginAgain(phoneNum);
            if(r > 0){
                session.setAttribute("userPhone",phoneNum);
                return new Result<>(true);
            }
        }
        return new Result<>(false,msg);
    }

}
