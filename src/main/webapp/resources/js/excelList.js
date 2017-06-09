layui.use([ 'layer', 'laypage', 'element' ], function() {
			var layer = layui.layer, laypage = layui.laypage, element = layui.element();
			var _href = window.location.href;
			var id = _href.split("?locationId=")[1];
			var location = layui.data("location"+id);
			var locate = location.name;
			$('#location-name').text(locate);
			function singleLine(excel,row){
				var $tr = $('<tr></tr>');
				$tr.append('<td>'+row+'</td>');
				$tr.append('<td>'+excel.dateStr+'</td>');
				$tr.append('<td>'+excel.userName+'</td>');
				$tr.append('<td><a href="/deviceSys/page/excel.html?date='+excel.date+'&id='+excel.id+'" target="_blank" class="layui-btn layui-btn-primary layui-btn-mini">详情</a></td>');
				return $tr;
			}
			
			$.ajax({
				url:'/deviceSys/device/selectExcelRecordByLocation.do',
				data:{ locationId:id },
				type:"GET",
				dataType:"json",
				success:function(excel){
					for(var i=0;i<excel.length;i++){
						var row = i + 1;
						$('tbody').append(singleLine(excel[i],row));
					}
				}
			});
		});