package cn.haoxy.dao;

import cn.haoxy.model.ExcelRecord;
import cn.haoxy.model.ExcelRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExcelRecordMapper {
    long countByExample(ExcelRecordExample example);

    int deleteByExample(ExcelRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExcelRecord record);

    int insertSelective(ExcelRecord record);

    List<ExcelRecord> selectByExample(ExcelRecordExample example);

    ExcelRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExcelRecord record, @Param("example") ExcelRecordExample example);

    int updateByExample(@Param("record") ExcelRecord record, @Param("example") ExcelRecordExample example);

    int updateByPrimaryKeySelective(ExcelRecord record);

    int updateByPrimaryKey(ExcelRecord record);
}