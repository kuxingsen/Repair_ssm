package cn.repair.controller;

import cn.repair.dto.Result;
import cn.repair.dto.Seller;
import cn.repair.dto.Service;
import cn.repair.pojo.Record;
import cn.repair.service.RecordService;
import cn.repair.utils.PhoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RecordController{

    @Autowired
    RecordService recordService;

    @RequestMapping("record")
    public Result<Record> insertRecord(Record record, HttpSession session){
        String msg="未登录";
        //将userPhone添加到Record中
        String phone = (String) session.getAttribute("userPhone");
        if(phone == null||phone.trim().equals("")){
            phone = record.getUserPhone();
        }
//        phone="13556507839";
        if(PhoneUtil.isPhone(phone)){
            int r = recordService.insertRecord(record,phone);
            if(r > 0){
                List<Record> recordList = recordService.selectAllRecord(phone);
                if(recordList != null){
//                    System.out.println(recordList);
                    return new Result<>(true,recordList);
                }else {
                    msg = "查无记录";
                }
            }else {
                msg="添加失败";
            }
        }
        return new Result<>(false,msg);
    }

    @RequestMapping("takeRecord")
    public Result takeRecord(Seller seller){
        String msg="商家信息有误";
        if(seller != null && seller.getRecordId() != null){
            int r = recordService.takeRecord(seller);
            if(r > 0){
                return new Result(true);
            }else {
                msg="接单失败";
            }
        }
        return new Result(false,msg);
    }

    @RequestMapping("isToken")
    public Result isToken(String  recordId){
        String msg="查无此记录";
        if(recordId != null){
            int i = recordService.isToken(recordId);
            if(i > 0){
                return new Result(true);
            }else {
                msg = "未维修";
            }
        }
        return new Result(false,msg);
    }

    @RequestMapping("setPartPrice")
    public Result setPartPrice(String  recordId,String partPrice){
        String msg="商家信息有误";
        if(recordId != null && partPrice != null){
            int r = recordService.setPartPrice(recordId,partPrice);
            if(r > 0){
                return new Result(true);
            }else {
                msg="更新失败";
            }
        }
        return new Result(false,msg);
    }
    @RequestMapping("getPartPrice")
    public Result<Double> getPartPrice(String recordId,HttpSession session) {
        String phone = (String) session.getAttribute("userPhone");
//        phone="13556507839";
        String msg = "未登录";
        if(PhoneUtil.isPhone(phone)) {
            double r = recordService.getPartPrice(recordId,phone);
            if(r > 0f) {
                return new Result<>(true,new ArrayList<Double>(){{this.add(r);}});
            } else {
                msg = "查无此订单";
            }
        }
        return new Result<>(false, msg);
    }
    @RequestMapping("setService")
    public Result<Double> setService(Service service, HttpSession session) {
        String phone = (String) session.getAttribute("userPhone");
//        phone="13556507839";
        String msg = "未登录";
        if(PhoneUtil.isPhone(phone)) {
            int r = recordService.setService(service,phone);
            if(r > 0) {
                return new Result<>(true);
            } else {
                msg = "查无此订单";
            }
        }
        return new Result<>(false, msg);
    }
    @RequestMapping("getRecord")
    public Result<Record> getRecord(HttpSession session){
        String msg="未登录";
        String phone = (String) session.getAttribute("userPhone");
//        phone="13556507839";
        if(PhoneUtil.isPhone(phone)){
            List<Record> recordList = recordService.selectAllRecord(phone);
            if(recordList != null){
                System.out.println(recordList);
                return new Result<>(true,recordList);
            }else {
                msg = "查无记录";
            }
        }
        return new Result<>(false,msg);
    }
}
