package cn.haoxy.dao;

import cn.haoxy.model.DeviceCheck;
import cn.haoxy.model.DeviceCheckExample;
import cn.haoxy.model.DeviceCheckRecordVo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DeviceCheckMapper {
    long countByExample(DeviceCheckExample example);

    int deleteByExample(DeviceCheckExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeviceCheck record);

    int insertSelective(DeviceCheck record);

    List<DeviceCheck> selectByExample(DeviceCheckExample example);

    DeviceCheck selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeviceCheck record, @Param("example") DeviceCheckExample example);

    int updateByExample(@Param("record") DeviceCheck record, @Param("example") DeviceCheckExample example);

    int updateByPrimaryKeySelective(DeviceCheck record);

    int updateByPrimaryKey(DeviceCheck record);
    
    Integer selectCheckOverTime(Integer locationId);
    
    List<DeviceCheckRecordVo> selectDeviceCheckRecord(Integer locationId);
    
    Integer selectDeviceIdBySerial(Map<String,Object> map);
    
    int checkIfdeviceNoExist(String deviceNo);
    
}