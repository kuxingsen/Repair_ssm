package cn.repair.service;

import cn.repair.mapper.RecordMapper;
import cn.repair.pojo.Record;
import cn.repair.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService{

    @Autowired
    RecordMapper recordMapper;

    public int insertRecord(Record record, String phone) {
        //为记录添加手机号、时间
        record.setUserPhone(phone);
        record.setTime(DateTimeUtil.now());
        //将记录添加到数据库
        return recordMapper.insertRecord(record);
    }

    public List<Record> selectAllRecord(String phone) {
        //取出所有维修记录
        return recordMapper.selectAllRecord(phone);
    }
}
