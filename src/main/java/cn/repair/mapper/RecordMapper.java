package cn.repair.mapper;

import cn.repair.dto.Seller;
import cn.repair.dto.Service;
import cn.repair.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecordMapper{
    int insertRecord(Record record);

    List<Record> selectRecord(String phoneNum);

    int takeRecord(@Param("recordId") Integer recordId,@Param("sellerName") String sellerName, @Param("sellerPhone") String sellerPrice);

    int setPartPrice(@Param("recordId")Integer recordId, @Param("partPrice")String partPrice);

    String getPartPrice(@Param("recordId")Integer recordId, @Param("phoneNum")String phone);

    int setService(@Param("recordId")Integer recordId, @Param("serviceDuration") String serviceDuration,@Param("servicePrice") String servicePrice, @Param("phoneNum")String phone);

    int pay(@Param("recordId")Integer recordId);

    byte getStatus(String recordId);

    Seller getSellerByRecordId(String recordId);

    int rejectToken(String recordId);

    int agreeToken(String recordId);

    Record getARecord(String recordId);

    List<Record> selectAllRecord();
}
