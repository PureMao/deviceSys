$(function(){
	$('body').prepend('<div class="warningBar"></div>');
	
	var url = window.location.href;
	
	var halfUrl = url.split('?id=')[1];
	
	var locationId = halfUrl.split('&')[0];
	
	$.ajax({
		url:"/deviceSys/device/selectCheckOverTime.do?locationId=" + locationId,
		type:"GET",
		dataType:"json"
	}).done(function(data){
		console.log(data);
		if(data.code == 0 && parseInt(data.body) > 0){
			var num = parseInt(data.body);
			var $info = $('<i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#x1007;</i><b style="font-size: 30px; color: #1E9FFF;">有'+num+'件设备超过24小时未扫入</b>');
			$('.warningBar').append($info);
		}
	});
	
	
})