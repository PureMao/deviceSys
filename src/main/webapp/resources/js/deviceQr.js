layui.use([ 'layer', 'laypage', 'element' ],
		function() {
			var layer = layui.layer, laypage = layui.laypage, element = layui
					.element();
			var _href = window.location.href;
			var deviceNo = _href.split("?deviceNo=")[1];

			$("#qr-record").append(deviceNo);

			function singleLine(data, row) {
				console.log(data);
				var $tr = '';
				if(data.flag == 1){
					$tr = $('<tr></tr>');
				}else{
					$tr = $('<tr style="color:red"></tr>');
				}
				$tr.append('<td>' + row + '</td>');
				$tr.append('<td>' + data.dateStr + '</td>');
				$tr.append('<td>' + data.userName + '</td>');
				var _flag = data.flag == 1 ? "是" : "否" ;
				$tr.append('<td>' + _flag + '</td>');
				return $tr;
			}

			$.ajax({
				url : '/deviceSys/device/selectQrJoinExcel.do',
				data : {
					deviceNo : deviceNo
				},
				type : "GET",
				dataType : "json",
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						var row = i + 1;
						$('tbody').append(singleLine(data[i], row));
					}
				}
			});
		});