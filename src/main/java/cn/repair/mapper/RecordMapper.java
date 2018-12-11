package cn.repair.mapper;

import cn.repair.pojo.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper{
    int insertRecord(Record record);

    List<Record> selectAllRecord(String phoneNum);
}
