layui.use([ 'layer', 'laypage', 'element','form' ], function() {
	var layer = layui.layer, laypage = layui.laypage, element = layui.element();
	var form = layui.form();
	var url = window.location.href;
	var halfUrl = url.split('?id=')[1];
	var locationId = halfUrl.split('&')[0];
	$('#locationId').val(locationId);
	var location = layui.data("location"+locationId);
	var locate = location.name;
	$('#leg-name').text(locate+"检查记录");
	$('#submitExcel').unbind();
	function singleLine(index,data){
		var $tr = $('<tr></tr>');
		$tr.append('<td>'+index+'</td>');
		$tr.append('<td>'+data.deviceType+'</td>');
		$tr.append('<td>'+data.deviceName+'</td>');
		$tr.append('<td>'+data.deviceNo+'</td>');
		$tr.append('<td>'+data.cTime+'</td>');
		$tr.append('<td>'+data.remark+'</td>');
		return $tr;
	};
	
	$.ajax({
		url:'/deviceSys/device/selectDeviceCheckRecord.do?locationId='+locationId,
		type:"GET",
		dataType:"json"
	}).done(function(data){
		var index = 0;
		for(var i=0;i<data.length;i++){
			index = i + 1 ;
			$('tbody').append(singleLine(index,data[i]));
		}
	});
	
	$('#submitExcel').on('click',function(){
		
		if($('#remark').val() == null || typeof($('#remark').val()) == undefined || $('#remark').val() == ""){
			layer.msg("请填写录入人姓名.");
		}else{
			var form = $('#form1').ajaxSubmit({target:"submitFormDiv"});
			var xhr = form.data('jqxhr');
			xhr.done(function(data) {
				console.log(data);
				if(data.code == 0){
					var count = data.body.insertCount;
					if(isNaN(count) || count == 0){
						layer.msg("读取文件完毕，没有符合条件的检查记录。");
					}else{
						layer.msg("上传检查记录成功，共上传" + data.body.insertCount + "条记录.");
						 setTimeout(function(){
							 window.location.href = window.location.href;
						 },1500);
					}
				}else{
					layer.msg("上传失败,请检查文件格式或网络连接");
				}
			});
		}
	});
});