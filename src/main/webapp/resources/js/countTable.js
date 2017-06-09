layui.use([ 'layer', 'laypage', 'element' ], function() {
			var layer = layui.layer, laypage = layui.laypage, element = layui.element();
			
			function singleLine(data){
				var $tr = $('<tr></tr>');
				$tr.append('<td><a href="/deviceSys/page/DeviceDistribution.html?typeId='+data.typeId+'" target="_blank">'+data.type+'</a></td>');
				$tr.append('<td>'+data.number+'</td>');
				return $tr;
			}
			
			$.ajax({
				url:'/deviceSys/device/selectDeviceCountGroup.do',
				type:"GET",
				dataType:"json",
				success:function(data){
					for(var i=0;i<data.length;i++){
						layui.data('type'+data[i].typeId,{
							key:'name',
							value:data[i].type
						});
						$('tbody').append(singleLine(data[i]));
					}
				}
			});
		});