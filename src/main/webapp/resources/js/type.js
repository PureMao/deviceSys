layui.use([ 'layer', 'laypage', 'element','form', 'layedit', 'laydate' ], function() {
			var layer = layui.layer, laypage = layui.laypage, element = layui.element();
			
			var form = layui.form();
			
			function singleLine(index,data){
				var $tr = $('<tr></tr>');
				$tr.append('<td>'+index+'</td>');
				$tr.append('<td>'+data.name+'</td>');
				$tr.append('<td>'+data.model+'</td>');
				$tr.append('<td>'+data.unit+'</td>');
				$tr.append('<td>'+data.type+'</td>');
				$tr.append('<td>'+data.maker+'</td>');
				return $tr;
			};
			
			$.ajax({
				url:'/deviceSys/device/selectDeviceTypeList.do',
				type:"GET",
				dataType:"json"
			}).done(function(data){
				var index = 0;
				for(var i=0;i<data.length;i++){
					index = i + 1 ;
					$('tbody').append(singleLine(index,data[i]));
				}
			});
			
			$('#submitNewType').on('click',function(){
				var name = $('#name').val();
				var model = $('#model').val();
				var type = $('#type').val();
				var maker = $('#maker').val();
				var unit = $('#unit').val();
				if(name == null || name == "" || name.trim() == ""){
					layer.msg("器材名称不能为空");
				}else if(model == null || model == "" || model.trim() == ""){
					layer.msg("器材型号不能为空");
				}else if(type == null || type == "" || type.trim() == ""){
					layer.msg("器材类型不能为空");
				}else if(maker == null || maker == "" || maker.trim() == ""){
					layer.msg("器材厂家不能为空");
				}else{
					$.ajax({
						url:'/deviceSys/device/createType.do',
						type:"POST",
						data:{
							'name':name,
							'model':model,
							'unit':unit,
							'type':type,
							'maker':maker
						}
					}).done(function(data){
						if(data == 0){
							 layer.msg("添加成功");
							 setTimeout(function(){
								 window.location.href = window.location.href;
							 },1500);
							 
						}else{
							layer.msg("添加失败");
						}
					});
				}
			});
			
		});