package cn.repair.service;

import cn.repair.mapper.PhoneUserMapper;
import cn.repair.pojo.PhoneUser;
import cn.repair.utils.PhoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    PhoneUserMapper phoneUserMapper;

    public String getCode(String phoneNum) {
        //根据时间生成六位数校验码
        String code = PhoneUtil.get6Code();
        System.out.println(code);
        //将手机号和校验码保存到数据库
        int isSuccess = phoneUserMapper.replacePhone(phoneNum,code);//无则插入，有则更新
        //返回校验码
        if(isSuccess > 0){
            return code;
        }
        return null;
    }

    public int loginByPhone(PhoneUser phoneUser) {
        //验证登录
        int i = phoneUserMapper.selectPhoneUser(phoneUser);
        if(i != 0){
            //删除数据库中的验证码
            phoneUserMapper.deleteCode(phoneUser.getPhoneNum());
            return 1;
        }
        return 0;
    }

}
