layui.use([ 'layer', 'laypage', 'element' ], function() {
			var layer = layui.layer, laypage = layui.laypage, element = layui.element();

			var _href = window.location.href;
			var typeId = _href.split("?typeId=")[1];
			var typeObj = layui.data("type"+typeId);
			var type = typeObj.name;
			$('#device-name').text(type);
			
			function singleLine(data,num){
				var $tr = $('<tr></tr>');
				$tr.append('<td>'+num+'</td>');
				$tr.append('<td>'+data.location+'</td>');
				$tr.append('<td>'+data.number+'</td>');
				return $tr;
			}
			
			$.ajax({
				url:'/deviceSys/device/selectDeviceDistribution.do',
				data:{
					typeId : typeId
				},
				type:"GET",
				dataType:"json",
				success:function(data){
					for(var i=0;i<data.length;i++){
						var num = i + 1;
						$('tbody').append(singleLine(data[i],num));
					}
				}
			});
		});