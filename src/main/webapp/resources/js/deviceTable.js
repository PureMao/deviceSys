layui.use([ 'layer', 'laypage', 'element' ], function() {
			var layer = layui.layer, laypage = layui.laypage, element = layui.element();
			var _href = window.location.href;
			var id = _href.split("?id=")[1];
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
				$tr.append('<td> <button data-id="'+device.id+'" class="layui-btn layui-btn-primary layui-btn-mini deleteDevice">删除此设备</button></td>');
				return $tr;
			}
			
			$('table').on('click','button',function(){
				var id = $(this).attr('data-id')
				layer.confirm('确实要删除这件设备吗？', {
					  btn: ['确定', '取消']
					}, function(index, layero){
						$.ajax({
							url:'/deviceSys/device/quitDevice.do',
							data:{ id:id },
							type:"POST",
							dataType:"json"
						}).done(function(data){
							console.log(data);
							if(data == 0){
								layer.closeAll();
								layer.msg("删除成功");
								 setTimeout(function(){
									 window.location.href = window.location.href;
								 },1500);
							}else{
								layer.closeAll();
								layer.msg("删除失败");
							}
						});
					}, function(index){
					  layer.closeAll();
					}); 
			});
			
			$.ajax({
				url:'/deviceSys/device/queryDeviceByLocation.do',
				data:{ id:id },
				type:"GET",
				dataType:"json"
			}).done(function(data){
						for(var i=0;i<data.length;i++){
							var row = i + 1;
							$('tbody').append(singleLine(data[i],row));
						}
			});
		});