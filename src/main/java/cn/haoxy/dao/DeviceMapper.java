package cn.haoxy.dao;

import cn.haoxy.model.Device;
import cn.haoxy.model.DeviceDistribution;
import cn.haoxy.model.DeviceExample;
import cn.haoxy.model.DeviceGroupCount;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceMapper {
    long countByExample(DeviceExample example);

    int deleteByExample(DeviceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    int insertSelective(Device record);

    List<Device> selectByExample(DeviceExample example);

    Device selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByExample(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
    
    List<Device> selectDeviceInfoByLocationID(Integer locationID);
    
    List<Device> selectQuitDeviceInfoByLocationID(Integer locationID);
    
    List<DeviceGroupCount> selectDeviceCountGroup();
    
    List<DeviceDistribution> selectDeviceDistribution(Integer typeId);
    
    int checkIfdeviceNoExist(String deviceNo);
    
    int updateDeleteDeviceById(Device device);
    
    int updateQuitDeviceById(Device device);
    
}