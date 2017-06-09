package cn.haoxy.service;

import java.util.List;

import cn.haoxy.model.Device;
import cn.haoxy.model.DeviceCheck;
import cn.haoxy.model.DeviceCheckRecordVo;
import cn.haoxy.model.DeviceDistribution;
import cn.haoxy.model.DeviceGroupCount;
import cn.haoxy.model.DeviceType;
import cn.haoxy.model.ExcelRecord;
import cn.haoxy.model.Location;
import cn.haoxy.model.Place;
import cn.haoxy.model.QrRecord;
import cn.haoxy.model.QrVo;

public interface DeviceService {
	
	public List<Device> queryDeviceByLocation(Location location);
	
	public List<DeviceCheck> queryDeviceCheckRecordByDevice(Device device);
	
	public List<Location> queryLocationList();
	
	public List<DeviceGroupCount> selectDeviceCountGroup();
	
	public List<DeviceDistribution> selectDeviceDistribution(Integer typeId);
	
	public boolean createLocation(Location location);
	
	public boolean deleteLocation(int id);
	
	public List<DeviceType> selectDeviceTypeList();
	
	public boolean createType(DeviceType deviceType);
	
	public List<Place> getPlaceList(int locationId);
	
	public boolean addPlace(Place place);
	
	public int addSingleDevice(Device device);
	
	public int selectCheckOverTime(int locationId);
	
	public List<DeviceCheckRecordVo> selectDeviceCheckRecord(int locationId);
	
	public int saveCheckRecord(List<String> deviceNoList,int locationId,String name);
	
	public boolean quitDevice(Integer id);
	
	public List<Device> selectQuitedDeviceByLocation(int locationId);
	
	public List<ExcelRecord> selectExcelRecordByLocation(int locationId);
	
	public List<QrRecord> selectDeviceQrRecordByDevice(String deviceNo);
	
	public List<QrRecord> selectDeviceQrRecordByExcel(int excelRecordId);
	
	public List<QrVo> selectQrJoinExcel(String deviceNo);
	
}
