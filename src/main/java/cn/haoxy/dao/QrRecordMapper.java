package cn.haoxy.dao;

import cn.haoxy.model.QrRecord;
import cn.haoxy.model.QrRecordExample;
import cn.haoxy.model.QrVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QrRecordMapper {
    long countByExample(QrRecordExample example);

    int deleteByExample(QrRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QrRecord record);

    int insertSelective(QrRecord record);

    List<QrRecord> selectByExample(QrRecordExample example);

    QrRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QrRecord record, @Param("example") QrRecordExample example);

    int updateByExample(@Param("record") QrRecord record, @Param("example") QrRecordExample example);

    int updateByPrimaryKeySelective(QrRecord record);

    int updateByPrimaryKey(QrRecord record);
    
    List<QrVo> selectQrJoinExcel(String deviceNo);
}