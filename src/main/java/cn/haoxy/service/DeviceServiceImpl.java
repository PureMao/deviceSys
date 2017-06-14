package cn.haoxy.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.haoxy.constant.ConstVal;
import cn.haoxy.dao.DeviceCheckMapper;
import cn.haoxy.dao.DeviceMapper;
import cn.haoxy.dao.DeviceTypeMapper;
import cn.haoxy.dao.ExcelRecordMapper;
import cn.haoxy.dao.LocationMapper;
import cn.haoxy.dao.PlaceMapper;
import cn.haoxy.dao.QrRecordMapper;
import cn.haoxy.model.Device;
import cn.haoxy.model.DeviceCheck;
import cn.haoxy.model.DeviceCheckExample;
import cn.haoxy.model.DeviceCheckRecordVo;
import cn.haoxy.model.DeviceDistribution;
import cn.haoxy.model.DeviceExample;
import cn.haoxy.model.DeviceGroupCount;
import cn.haoxy.model.DeviceType;
import cn.haoxy.model.DeviceTypeExample;
import cn.haoxy.model.ExcelRecord;
import cn.haoxy.model.ExcelRecordExample;
import cn.haoxy.model.Location;
import cn.haoxy.model.LocationExample;
import cn.haoxy.model.Place;
import cn.haoxy.model.PlaceExample;
import cn.haoxy.model.QrRecord;
import cn.haoxy.model.QrRecordExample;
import cn.haoxy.model.QrVo;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService{

	private static Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);
	
	static{
		logger.info("DeviceServiceImpl.class");
	}
	
	@Resource
	private DeviceMapper deviceMapper;
	
	@Resource
	private DeviceCheckMapper deviceCheckMapper;
	
	@Resource
	private LocationMapper locationMapper;
	
	@Resource
	private DeviceTypeMapper deviceTypeMapper;
	
	@Resource
	private PlaceMapper placeMapper;
	
	@Resource
	private ExcelRecordMapper excelRecordMapper;
	
	@Resource
	private QrRecordMapper qrRecordMapper;
	
	public List<Device> queryDeviceByLocation(Location location){
		return deviceMapper.selectDeviceInfoByLocationID(location.getId());
	}

	@Override
	public List<DeviceCheck> queryDeviceCheckRecordByDevice(Device device) {
		DeviceCheckExample example = new DeviceCheckExample();
		DeviceCheckExample.Criteria criteria = example.createCriteria();
		criteria.andDeviceIdEqualTo(device.getId());
		criteria.andDeleteMarkEqualTo(ConstVal.NO_DELETE_MARK);
		return deviceCheckMapper.selectByExample(example);
	}

	@Override
	public List<Location> queryLocationList() {
		LocationExample example = new LocationExample();
		LocationExample.Criteria criteria = example.createCriteria();
		criteria.andDeleteMarkEqualTo(ConstVal.NO_DELETE_MARK);
		example.setOrderByClause("id");
		return locationMapper.selectByExample(example);
	}

	@Override
	public List<DeviceGroupCount> selectDeviceCountGroup() {
		return deviceMapper.selectDeviceCountGroup();
	}

	@Override
	public List<DeviceDistribution> selectDeviceDistribution(Integer typeId) {
		return deviceMapper.selectDeviceDistribution(typeId);
	}

	@Override
	public boolean createLocation(Location location) {
		location.setDeleteMark(ConstVal.NO_DELETE_MARK);
		int result = locationMapper.insert(location);
		return result > 0 ? true : false;
	}

	@Override
	public boolean deleteLocation(int id) {
		Location location = new Location();
		location.setId(id);
		location.setDeleteMark(ConstVal.DELETE_MARK);
		int result = locationMapper.updateByPrimaryKeySelective(location);
		return result > 0 ? true : false;
	}

	@Override
	public List<DeviceType> selectDeviceTypeList() {
		DeviceTypeExample example = new DeviceTypeExample();
		DeviceTypeExample.Criteria criteria = example.createCriteria();
		criteria.andDeleteMarkEqualTo(ConstVal.NO_DELETE_MARK);
		return deviceTypeMapper.selectByExample(example);
	}

	@Override
	public boolean createType(DeviceType deviceType) {
		deviceType.setDeleteMark(ConstVal.NO_DELETE_MARK);
		int result = deviceTypeMapper.insert(deviceType);
		return result > 0 ? true : false;
	}

	@Override
	public List<Place> getPlaceList(int locationId) {
		PlaceExample example = new PlaceExample();
		PlaceExample.Criteria criteria = example.createCriteria();
		criteria.andLocationIdEqualTo(locationId);
		return placeMapper.selectByExample(example);
	}

	@Override
	public boolean addPlace(Place place) {
		place.setDeleteMark(ConstVal.NO_DELETE_MARK);
		int result = placeMapper.insert(place);
		return result > 0 ? true : false;
	}

	@Override
	@Transactional
	public int addSingleDevice(Device device) {
		String serial = device.getDeviceNo();
		device.setQuitMark(ConstVal.NO_DELETE_MARK);
		device.setRegisterTime(System.currentTimeMillis());
		device.setDeviceNo(serial.trim());
		// 检查序列号冲突
		int existNum = deviceMapper.checkIfdeviceNoExist(device.getDeviceNo());
		if(existNum == 0){
			int result = deviceMapper.insert(device);
			int deviceId = findDeviceIdBySerial(serial);
			if(deviceId > 0){
				DeviceCheck record = new DeviceCheck();
				record.setCheckTime(System.currentTimeMillis());
				record.setDeleteMark(ConstVal.NO_DELETE_MARK);
				record.setRemark("系统自动检查录入");
				record.setStatus("");
				record.setDeviceId(deviceId);
				deviceCheckMapper.insert(record);
			}
			
			return result;
		}else{
			return ConstVal.TOO_MANY_RECORD_EXIST;
		}
	}
	
	private int findDeviceIdBySerial(String serial){
		DeviceExample example = new DeviceExample();
		DeviceExample.Criteria criteria = example.createCriteria();
		criteria.andDeviceNoEqualTo(serial);
		criteria.andQuitMarkEqualTo(ConstVal.NO_DELETE_MARK);
		List<Device> list = deviceMapper.selectByExample(example);
		if(list != null && list.size() > 0 && list.get(0).getId() != null && list.get(0).getId() > 0){
			return list.get(0).getId();
		}else{
			return 0;
		}
	}

	@Override
	public int selectCheckOverTime(int locationId) {
		return deviceCheckMapper.selectCheckOverTime(locationId);
	}

	@Override
	public List<DeviceCheckRecordVo> selectDeviceCheckRecord(int locationId) {
		return deviceCheckMapper.selectDeviceCheckRecord(locationId);
	}

	@Override
	@Transactional
	public int saveCheckRecord(List<String> deviceNoList,int locationId,String name) {
		DeviceCheck record = new DeviceCheck();
		record.setCheckTime(System.currentTimeMillis());
		record.setDeleteMark(ConstVal.NO_DELETE_MARK);
		record.setRemark(name);
		record.setStatus("");
		saveExcelRecordAndQrRecords(deviceNoList,locationId,name);
		int count = 0;
		for (int i = 0; i < deviceNoList.size(); i++) {
			if(deviceNoList.get(i) == null){
				count += 0;
			}else{
				count += saveSingleCheckRecord(record,locationId,deviceNoList.get(i));
			}
		}
		return count;
	}
	
	private void saveExcelRecordAndQrRecords(List<String> deviceNoList,int locationId,String name){
		ExcelRecord excel = saveExcelRecordAndReturn(locationId,name);
		List<Device> deviceShouldList = selectDeviceShouldListByLocationId(locationId);
		Set<String> deviceShouldSet = deviceShouldList.stream().map(Device::getDeviceNo).collect(Collectors.toSet());
		Set<String> deviceRealSet = new HashSet<String>();
		deviceRealSet.addAll(deviceNoList);
		saveQrRecords(deviceShouldSet,deviceRealSet,excel);
	}
	
	private ExcelRecord saveExcelRecordAndReturn(int locationId,String name){
		ExcelRecord excel = new ExcelRecord();
		excel.setUserId(0); // FIXME 暂时用0
		excel.setUserName(name);
		excel.setDate(System.currentTimeMillis());
		excel.setLocationId(locationId);
		excel.setRemark("");
		excel.setDeleteMark(ConstVal.NO_DELETE_MARK);
		excelRecordMapper.insertSelective(excel);
		return excel;
	}
	
	private List<Device> selectDeviceShouldListByLocationId(int locationId){
		DeviceExample example = new DeviceExample();
		DeviceExample.Criteria criteria = example.createCriteria();
		criteria.andLocationIdEqualTo(locationId).andQuitMarkEqualTo(ConstVal.NO_DELETE_MARK);
		List<Device> deviceShouldList = deviceMapper.selectByExample(example);
		return deviceShouldList;
	}
	
	private void saveQrRecords(Set<String> deviceShouldSet,Set<String> deviceRealSet,ExcelRecord excelRecord){
		QrRecord qrRecord = null;
		Iterator<String> it = deviceShouldSet.iterator();
		String devNo = null;
		while(it.hasNext()){
			devNo = it.next();
			qrRecord = new QrRecord();
			qrRecord.setDeleteMark(ConstVal.NO_DELETE_MARK);
			qrRecord.setDeviceNo(devNo);
			qrRecord.setExcelId(excelRecord.getId());
			qrRecord.setIsLocalDevice(deviceRealSet.contains(devNo) ? ConstVal.FIND_OUT : ConstVal.FIND_OFF);
			qrRecordMapper.insert(qrRecord);
		}
	}
	
	private int saveSingleCheckRecord(DeviceCheck record,int locationId,String deviceNo){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("deviceNo",deviceNo);
		map.put("locationId",locationId);
		// 如果该设备不在这个工区或者该设备根本不存在，则跳过
		Integer id = deviceCheckMapper.selectDeviceIdBySerial(map);
		if(id == null || id == 0){
			return ConstVal.ZERO;
		}else{
			record.setDeviceId(id);
			int result = deviceCheckMapper.insert(record);
			return result;
		}
	}

//	private List<Device> selectDeviceRealListByLocationId(int locationId,List<String> deviceNoList){
//		DeviceExample example = new DeviceExample();
//		DeviceExample.Criteria criteria = example.createCriteria();
//		criteria.andLocationIdEqualTo(locationId);
//		criteria.andDeviceNoIn(deviceNoList);
//		List<Device> deviceRealList = deviceMapper.selectByExample(example);
//		return deviceRealList;
//	}

	@Override
	public boolean deleteDevice(Integer id) {
		Device device = new Device();
		device.setId(id);
		device.setDeviceNo("delete" + System.currentTimeMillis() + "-" + new Random().nextInt(200)%(101) + 100);
		device.setQuitTime(System.currentTimeMillis());
		boolean flag = deviceMapper.updateDeleteDeviceById(device) > 0 ? true : false ;
		return flag;
	}
	
	@Override
	public boolean quitDevice(Integer id,String remark){
		Device device = new Device();
		device.setId(id);
		device.setRemark(remark);
		device.setQuitTime(System.currentTimeMillis());
		boolean flag = deviceMapper.updateQuitDeviceById(device) > 0 ? true : false ;
		return flag;
	};

	@Override
	public List<Device> selectQuitedDeviceByLocation(int locationId) {
		return deviceMapper.selectQuitDeviceInfoByLocationID(locationId);
	}

	@Override
	public List<ExcelRecord> selectExcelRecordByLocation(int locationId) {
		ExcelRecordExample example = new ExcelRecordExample();
		ExcelRecordExample.Criteria criteria = example.createCriteria();
		criteria.andLocationIdEqualTo(locationId).andDeleteMarkEqualTo(ConstVal.NO_DELETE_MARK);
		return excelRecordMapper.selectByExample(example);
	}

	@Override
	public List<QrRecord> selectDeviceQrRecordByDevice(String deviceNo) {
		QrRecordExample example = new QrRecordExample();
		QrRecordExample.Criteria criteria = example.createCriteria();
		criteria.andDeleteMarkEqualTo(ConstVal.NO_DELETE_MARK).andDeviceNoEqualTo(deviceNo);
		return qrRecordMapper.selectByExample(example);
	};
	
	@Override
	public List<QrRecord> selectDeviceQrRecordByExcel(int excelRecordId){
		QrRecordExample example = new QrRecordExample();
		QrRecordExample.Criteria criteria = example.createCriteria();
		criteria.andDeleteMarkEqualTo(ConstVal.NO_DELETE_MARK).andExcelIdEqualTo(excelRecordId);
		return qrRecordMapper.selectByExample(example);
	}

	@Override
	public List<QrVo> selectQrJoinExcel(String deviceNo) {
		return qrRecordMapper.selectQrJoinExcel(deviceNo);
	}

	@Override
	public boolean updateDevicePlace(int placeId, int deviceId) {
		Device device = new Device();
		device.setId(deviceId);
		device.setPlaceId(placeId);
		boolean flag = deviceMapper.updateDevicePlace(device) > 0 ? true : false ;
		return flag;
	};
	
	
}
