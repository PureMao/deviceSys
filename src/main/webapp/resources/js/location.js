layui.use([ 'layer', 'laypage', 'element' ], function() {
			var layer = layui.layer, laypage = layui.laypage, element = layui.element();
			
			function singleLine(index,data){
				var $tr = $('<tr></tr>');
				$tr.append('<td>'+index+'</td>');
				$tr.append('<td>'+data.location+'</td>');
				var $td = $('<td>');
				$td.append('<button id="del_'+data.id+'" class="layui-btn layui-btn-primary layui-btn-mini del_btn">删除此位置</button>');
				$td.append('<a href="/deviceSys/page/place.html?id='+data.id+'" target="_blank" class="layui-btn layui-btn-primary layui-btn-mini">管理存放位置</a>');
				$tr.append($td);
				return $tr;
			}
			
			$.ajax({
				url:'/deviceSys/device/queryLocationList.do',
				type:"GET",
				dataType:"json",
				success:function(data){
					var index = 0;
					for(var i=0;i<data.length;i++){
						index = i + 1 ;
						$('tbody').append(singleLine(index,data[i]));
					}
				}
			});
			
			$('#submitLocation').on('click',function(){
				var location = $('#newLocation').val();
				if(location == null || location == "" || location.trim() == ""){
					layer.msg("不能为空名称");
				}else{
					$.ajax({
						url:'/deviceSys/device/createLocation.do',
						type:"POST",
						data:{locate:location}
					}).done(function(data){
						if(data == 0){
							 window.location.href = window.location.href;
						}else{
							layer.msg("添加失败");
						}
					});
				}
				
			});
			
			$('tbody').on('click','button',function(){
				var val = $(this).attr('id');
				var arr = val.split('_');
				var id = arr[1];
				var operate = arr[0];
				if(id == null || id == "" || id.trim() == ""){
					layer.msg("不能识别地点");
				}else if(isNaN(id.trim())){
					layer.msg("不能识别地点");
				}else{
					if(operate == 'del'){
						$.ajax({
							url:'/deviceSys/device/deleteLocation.do',
							type:"POST",
							data:{id:id}
						}).done(function(data){
							if(data == 0){
								 window.location.href = window.location.href;
							}else{
								layer.msg("添加失败");
							}
						});
					}
					
					// else if(operate == 'place'){}
					
				}
			});
		});