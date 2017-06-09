layui.use([ 'layer', 'laypage', 'element' ],
		function() {
			var layer = layui.layer, laypage = layui.laypage, element = layui
					.element();
			var _href = window.location.href;
			var id = _href.split("&id=")[1];

			var halfUrl = _href.split("?date=")[1];
			var date = halfUrl.split("&id=")[0];
			var dateStr = getLocalTime(date);
			$("#qr-record-title").prepend(dateStr);


			function singleLine(data, row) {
				var isCheck = data.isLocalDevice == 1 ? "是" : "否";
				var $tr;
				if(data.isLocalDevice == 1){
					$tr = $('<tr></tr>');
				}else{
					$tr = $('<tr style="color:red"></tr>');
				}
				$tr.append('<td>' + row + '</td>');
				$tr.append('<td><a href="/deviceSys/page/deviceQr.html?deviceNo='+data.deviceNo+'" target="_blank">'+data.deviceNo+'</a></td>');
				$tr.append('<td>' + isCheck + '</td>');
				return $tr;
			}

			$.ajax({
				url : '/deviceSys/device/selectDeviceQrRecordByExcel.do',
				data : {
					excelRecordId : id
				},
				type : "GET",
				dataType : "json",
				success : function(data) {
					console.log(data);
					for (var i = 0; i < data.length; i++) {
						var row = i + 1;
						$('tbody').append(singleLine(data[i], row));
					}
				}
			});
		});