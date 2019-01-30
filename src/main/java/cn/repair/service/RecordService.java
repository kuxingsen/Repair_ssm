package cn.repair.service;

import cn.repair.dto.Seller;
import cn.repair.mapper.RecordMapper;
import cn.repair.pojo.Record;
import cn.repair.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecordService{

    @Autowired
    RecordMapper recordMapper;

    public String insertRecord(Record record, String phone) {
        //为记录添加手机号、时间
        record.setUserPhone(phone);
        record.setTime(DateTimeUtil.now());
        //将记录添加到数据库
        int i = recordMapper.insertRecord(record);
        if(i > 0){
            return record.getId();
        }
        return null;
    }

    public List<Record> selectAllRecord(String phone) {
        //取出所有维修记录
        List<Record> recordList = recordMapper.selectAllRecord(phone);
        for(Record record:recordList){
            record.setStatus(getStatus(Byte.valueOf(record.getStatus())));
            record.setTime(record.getTime().replace(".0",""));
        }
        return recordList;
    }

    public int takeRecord(Seller seller) {
        return recordMapper.takeRecord(Integer.valueOf(seller.getRecordId()),seller.getSellerName(),seller.getSellerPhone());
    }

    public int setPartPrice(String  recordId,String partPrice) {
        return recordMapper.setPartPrice(Integer.valueOf(recordId),partPrice);
    }

    public double getPartPrice(String recordId, String phone) {
        String partPrice = recordMapper.getPartPrice(Integer.valueOf(recordId),phone);
        if(partPrice != null){
            BigDecimal r= new BigDecimal(partPrice);
//        System.out.println(r.doubleValue());
            double f1 = r.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println(f1);
            return f1;
        }
        return -1f;
    }

    public int setService(cn.repair.dto.Service service, String phone) {
        int i = recordMapper.setService(Integer.valueOf(service.getRecordId()), service.getServiceDuration(),service.getServicePrice(), phone);
        if(i > 0){
            return recordMapper.pay(Integer.valueOf(service.getRecordId()));
        }
        return i;
    }

    public String getStatus(String recordId) {
        return getStatus(recordMapper.getStatus(recordId));
    }

    private String getStatus(byte status) {
        String s;
        switch(status){
            case 0:
                s="用户申请维修";break;
            case 1:
                s="商家接单，等待用户确认";break;
            case 2:
                s="用户确认订单，商家维修中";break;
            case 3:
                s="维修价格已确定，等待用户支付";break;
            case 4:
                s="用户已支付";break;
            default:
                s=null;
        }
        return s;
    }

    public int isToken(String recordId) {
        return recordMapper.getStatus(recordId);
    }

    public Seller getSellerByRecordId(String recordId) {
        return recordMapper.getSellerByRecordId(recordId);
    }

    public int rejectToken(String recordId) {
        return recordMapper.rejectToken(recordId);
    }

    public int agreeToken(String recordId) {
        return recordMapper.agreeToken(recordId);
    }

    public Record getARecord(String recordId) {
        return recordMapper.getARecord(recordId);
    }
}
