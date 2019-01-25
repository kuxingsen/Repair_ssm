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

    /**
     * 添加记录
     * @param record 参数需包含name 申请人姓名、address 地址,content 维修内容
     * @return 失败（false）返回msg，成功（true）则返回记录的id（data[0]）
     */
    @RequestMapping("record")
    public Result<String> insertRecord(Record record, HttpSession session){
        String msg="未登录";
        //将userPhone添加到Record中
        String phone = (String) session.getAttribute("userPhone");
        if(phone == null|| "".equals(phone.trim())){
            phone = record.getUserPhone();
        }
//        phone="13556507839";
        if(PhoneUtil.isPhone(phone)){
            String r = recordService.insertRecord(record,phone);
            if(r != null){
                return new Result<>(true,new ArrayList<String>(){{add(r);}});
            }else {
                msg="添加失败";
            }
        }
        return new Result<>(false,msg);
    }

    /**
     * 商家接单
     * @param seller 需要recordId 记录id，sellerName 商家姓名，sellerPhone 商家电话
     * @return 失败（false）或成功（true）
     */
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
    /**
     * 用户是否同意商家接单
     * @param recordId  记录id
     * @param agree 0-同意 其他-拒绝
     * @return 失败（false）或成功（true）
     */
    @RequestMapping("userToken")
    public Result userToken(String recordId,String agree){
        String msg="记录错误";
        if(recordId != null && !"".equals(recordId)){
            int r;
            if("0".equals(agree)){
                r = recordService.agreeToken(recordId);
            }else{
                r = recordService.rejectToken(recordId);
            }
            if(r > 0){
                return new Result(true);
            }else {
                msg="拒绝失败";
            }
        }
        return new Result(false,msg);
    }
    /**
     * 是否已有商家接单
     * @param recordId  记录id
     * @return 失败（false）或成功（true）返回商家姓名、电话
     */
    @RequestMapping("isToken")
    public Result<Seller> isToken(String  recordId){
        String msg="查无此记录";
        if(recordId != null){
            int i = recordService.isToken(recordId);
            if(i > 0){
                Seller seller = recordService.getSellerByRecordId(recordId);
                return new Result<>(true,new ArrayList<Seller>(){{add(seller);}});
            }else {
                msg = "未维修";
            }
        }
        return new Result<>(false,msg);
    }

    /**
     * 商家设定零件价格
     * @param recordId 记录id
     * @param partPrice 零件价格
     * @return 失败（false）或成功（true）
     */
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

    /**
     * 是否已有零件总价
     * @param recordId 记录id
     * @return 失败（false）返回msg，成功（true）则返回零件总价（data[0]）
     */
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

    /**
     * 设置服务时长和服务总价（每小时15元）
     * @param service 需要recordId 记录id，serviceDuration 服务时长，servicePrice 服务总价
     * @return 失败（false）或成功（true）
     */
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

    /**
     * 获取用户的所有记录
     * @return 失败（false）返回msg，成功（true）则返回用户的维修记录
     */
    @RequestMapping("getRecord")
    public Result<Record> getRecord(HttpSession session){
        String msg="未登录";
        String phone = (String) session.getAttribute("userPhone");
//        phone="13556507839";
        if(PhoneUtil.isPhone(phone)){
            List<Record> recordList = recordService.selectAllRecord(phone);
            if(recordList != null){
                return new Result<>(true,recordList);
            }else {
                msg = "查无记录";
            }
        }
        return new Result<>(false,msg);
    }
}
