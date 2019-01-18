package cn.repair.mapper;

import cn.repair.pojo.PhoneUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

//@Repository
@Mapper
public interface PhoneUserMapper{
    //无则插入，有则更新
    int replacePhone(@Param("phoneNum") String phoneNum, @Param("code") String code);

    int selectPhoneUser(PhoneUser phoneUser);

    void deleteCode(String phoneNum);

    int hasLogin(String phoneNum);
}
