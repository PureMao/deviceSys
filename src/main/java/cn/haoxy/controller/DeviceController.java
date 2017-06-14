package cn.haoxy.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.haoxy.constant.ConstVal;
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
import cn.haoxy.restful.RestResponse;
import cn.haoxy.service.DeviceService;

@Controller
@RequestMapping("/device")
public class DeviceController {

	private static Logger logger = LoggerFactory.getLogger(DeviceController.class);

	static {
		logger.info("DeviceController.class");
	}

	@Resource
	private DeviceService deviceService;

	/**
	 * 查看某地点设备台账
	 */
	@RequestMapping(value = "/queryDeviceByLocation.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Device> queryDeviceByLocation(Location location) {
		return deviceService.queryDeviceByLocation(location);
	}

	/**
	 * 查看某台设备的检查记录
	 */
	@RequestMapping(value = "/queryDeviceCheckRecordByDevice.do", method = RequestMethod.GET)
	@ResponseBody
	public List<DeviceCheck> queryDeviceCheckRecordByDevice(Device device) {
		return deviceService.queryDeviceCheckRecordByDevice(device);
	}

	/**
	 * 地点列表
	 */
	@RequestMapping(value = "/queryLocationList.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Location> queryLocationList() {
		return deviceService.queryLocationList();
	}

	/**
	 * 添加一个地点
	 */
	@RequestMapping(value = "/createLocation.do", method = RequestMethod.POST)
	@ResponseBody
	public int createLocation(String locate) {
		Location location = new Location();
		location.setLocation(locate);
		return deviceService.createLocation(location) ? ConstVal.SUCCESS_MARK : ConstVal.ERROR_MARK;
	}

	/**
	 * 删除一个地点
	 */
	@RequestMapping(value = "/deleteLocation.do", method = RequestMethod.POST)
	@ResponseBody
	public int deleteLocation(int id) {
		return deviceService.deleteLocation(id) ? ConstVal.SUCCESS_MARK : ConstVal.ERROR_MARK;
	}

	/**
	 * 设备按类型汇总
	 */
	@RequestMapping(value = "/selectDeviceCountGroup.do", method = RequestMethod.GET)
	@ResponseBody
	public List<DeviceGroupCount> selectDeviceCountGroup() {
		return deviceService.selectDeviceCountGroup();
	}
	
	/**
	 * 设备离场
	 */
	@RequestMapping(value = "/quitDevice.do", method = RequestMethod.POST)
	@ResponseBody
	public int quitDevice(Integer id) {
		int result = deviceService.quitDevice(id) ? ConstVal.SUCCESS_MARK : ConstVal.ERROR_MARK;
		return result;
	}
	
	/**
	 * 查看某工区已经退出的设备
	 */
	@RequestMapping(value = "/selectQuitedDeviceByLocation.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Device> selectQuitedDeviceByLocation(Integer locationId){
		return deviceService.selectQuitedDeviceByLocation(locationId);
	}
	
	/**
	 * 查看某工区上传文件记录
	 */
	@RequestMapping(value = "/selectExcelRecordByLocation.do", method = RequestMethod.GET)
	@ResponseBody
	public List<ExcelRecord> selectExcelRecordByLocation(Integer locationId){
		List<ExcelRecord> list = deviceService.selectExcelRecordByLocation(locationId);
		DateFormat fomatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Stream<ExcelRecord> steam = list.stream();
		steam.filter(excel -> excel.getDate() != null).forEach(excel -> excel.setDateStr(fomatter.format(excel.getDate())));
		return list;
	}
	
	/**
	 * 查看某设备扫码记录
	 */
	@RequestMapping(value = "/selectDeviceQrRecordByDevice.do", method = RequestMethod.GET)
	@ResponseBody
	public List<QrRecord> selectDeviceQrRecordByDevice(String deviceNo){
		List<QrRecord> list =  deviceService.selectDeviceQrRecordByDevice(deviceNo);
		return list;
	}
	
	/**
	 * 查看某设备扫码记录
	 */
	@RequestMapping(value = "/selectQrJoinExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public List<QrVo> selectQrJoinExcel(String deviceNo){
		List<QrVo> list = deviceService.selectQrJoinExcel(deviceNo);
		DateFormat fomatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		list.stream().filter(qr -> qr.getDateTime()!= null).forEach(qr -> qr.setDateStr(fomatter.format(qr.getDateTime())));
		return list;
	}
	
	/**
	 * 查看某次上传扫码记录
	 */
	@RequestMapping(value = "/selectDeviceQrRecordByExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public List<QrRecord> selectDeviceQrRecordByExcel(int excelRecordId){
		return deviceService.selectDeviceQrRecordByExcel(excelRecordId);
	}

	/**
	 * 查找某一设备的分布
	 */
	@RequestMapping(value = "/selectDeviceDistribution.do", method = RequestMethod.GET)
	@ResponseBody
	public List<DeviceDistribution> selectDeviceDistribution(Integer typeId) {
		return deviceService.selectDeviceDistribution(typeId);
	}

	/**
	 * 查找设备类型列表
	 */
	@RequestMapping(value = "/selectDeviceTypeList.do", method = RequestMethod.GET)
	@ResponseBody
	public List<DeviceType> selectDeviceTypeList() {
		return deviceService.selectDeviceTypeList();
	}

	/**
	 * 新增设备类型
	 */
	@RequestMapping(value = "/createType.do", method = RequestMethod.POST)
	@ResponseBody
	public int createType(DeviceType deviceType) {
		return deviceService.createType(deviceType) ? ConstVal.SUCCESS_MARK : ConstVal.ERROR_MARK;
	}

	/**
	 * 根据存放地点获取存放位置列表
	 */
	@RequestMapping(value = "/getPlaceList.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Place> getPlaceList(int locationId) {
		return deviceService.getPlaceList(locationId);
	}

	/**
	 * 添加一个位置
	 */
	@RequestMapping(value = "/addPlace.do", method = RequestMethod.POST)
	@ResponseBody
	public int addPlace(Place place) {
		return deviceService.addPlace(place) ? ConstVal.SUCCESS_MARK : ConstVal.ERROR_MARK;
	}

	/**
	 * 新增一件设备
	 */
	@RequestMapping(value = "/addSingleDevice.do", method = RequestMethod.POST)
	@ResponseBody
	public int addSingleDevice(Device device) {
		return deviceService.addSingleDevice(device);
	}

	/**
	 * 按照地点查询检查记录
	 */
	@RequestMapping(value = "/selectDeviceCheckRecord.do", method = RequestMethod.GET)
	@ResponseBody
	public List<DeviceCheckRecordVo> selectDeviceCheckRecord(int locationId) {
		List<DeviceCheckRecordVo> result = deviceService.selectDeviceCheckRecord(locationId);
		DateFormat fomatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Stream<DeviceCheckRecordVo> steam = result.stream();
		steam.filter(record -> record.getCheckTime() != null)
		.forEach(record -> record.setcTime(fomatter.format(record.getCheckTime())));
		return result;
	}

	/**
	 * 按照地点查询过期未检查的设备数量
	 */
	@RequestMapping(value = "/selectCheckOverTime.do", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse selectCheckOverTime(int locationId) {
		RestResponse response = new RestResponse();
		response.setBody(deviceService.selectCheckOverTime(locationId));
		response.setCode(ConstVal.SUCCESS_MARK);
		response.setStatus(ConstVal.STATE_OK);
		return response;
	}

	/**
	 * 按照地点查询检查记录  存扫码记录和文件记录
	 */
	@RequestMapping(value = "/writeCheckRecordExcel.do", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse writeCheckRecordExcel(int locationId, MultipartFile files, String name) {
		RestResponse response = new RestResponse();
		String fileName = files.getOriginalFilename();
		boolean isE2007 = false;
		if (fileName.endsWith("xlsx")) {
			isE2007 = true;
		}
		Workbook wb = null;
		try {
			InputStream input = files.getInputStream();
			if (isE2007) {
				wb = new XSSFWorkbook(input);
			} else {
				wb = new HSSFWorkbook(input);
			}
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			List<String> deviceNoList = new ArrayList<String>();
			while (rows.hasNext()) {
				Row row = rows.next();
				Iterator<Cell> cells = row.cellIterator();
				Cell cell = cells.next();
				String val = null;
				if(cell.getCellTypeEnum() == CellType.STRING){
					val = cell.getStringCellValue();
					if(val != null && !"".equals(val.trim()) ){
						deviceNoList.add(val);
					}
				}else if(cell.getCellTypeEnum() == CellType.NUMERIC){
					val = cell.getNumericCellValue() + "";
					deviceNoList.add(val);
				}
			}
			int result = deviceService.saveCheckRecord(deviceNoList,locationId,name);
			logger.info("插入结束，共计"+result+"条记录");
			response.setCode(ConstVal.SUCCESS_MARK);
			response.setStatus(ConstVal.STATE_OK);
			Map<String,Integer> map = new HashMap<String, Integer>();
			map.put("insertCount", result);
			response.setBody(map);
			return response;
		} catch (IOException e) {
			response.setCode(ConstVal.ERROR_MARK);
			response.setStatus(ConstVal.STATE_ERROR);
			return response;
		} finally {
			try {
				if (wb != null) {
					wb.close();
				}
			} catch (IOException e) {
				logger.error("--读取EXCEL文件发生IO错误--", e);
				e.printStackTrace();
			}
		}

	}
}
