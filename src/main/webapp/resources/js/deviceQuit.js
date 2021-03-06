layui.use([ 'layer', 'laypage', 'element' ], function() {
			var layer = layui.layer, laypage = layui.laypage, element = layui.element();
			var _href = window.location.href;
			var id = _href.split("?locationId=")[1];
			var location = layui.data("location"+id);
			var locate = location.name;
			$('#location-name').text(locate);
			function singleLine(device,row){
				var $tr = $('<tr></tr>');
				$tr.append('<td>'+row+'</td>');
				$tr.append('<td>'+device.type+'</td>');
				$tr.append('<td>'+device.name+'</td>');
				$tr.append('<td>'+device.model+'</td>');
				$tr.append('<td>'+device.unit+'</td>');
				$tr.append('<td>'+device.deviceNo+'</td>');
				$tr.append('<td>'+device.place+'</td>');
				$tr.append('<td>'+device.maker+'</td>');
				$tr.append('<td><span>'+device.remark+'</span></td>');
				return $tr;
			}
			
			
			
			$.ajax({
				url:'/deviceSys/device/selectQuitedDeviceByLocation.do',
				data:{ locationId:id },
				type:"GET",
				dataType:"json"
			}).done(function(data){
						for(var i=0;i<data.length;i++){
							var row = i + 1;
							$('tbody').append(singleLine(data[i],row));
						}
			});
		});