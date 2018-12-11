package cn.repair.controller;

import cn.repair.dto.Result;
import cn.repair.pojo.Record;
import cn.repair.service.RecordService;
import cn.repair.utils.PhoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class RecordController{

    @Autowired
    RecordService recordService;

    @RequestMapping(value = "record",produces = "application/json;charset=utf-8")
    public Result<Record> loginByPhone(Record record, HttpSession session){
        String msg;
        //将userPhone添加到Record中
        String phone = (String) session.getAttribute("userPhone");
//        phone="13556507839";
        if(PhoneUtil.isPhone(phone)){
            int r = recordService.insertRecord(record,phone);
            if(r > 0){
                List<Record> recordList = recordService.selectAllRecord(phone);
                if(recordList != null){
                    return new Result<>(true,recordList);
                }else {
                    msg = "查无记录";
                }
            }else {
                msg="添加失败";
            }
        }else {
            msg="未登录";
        }
        return new Result<>(false,msg);
    }
}
