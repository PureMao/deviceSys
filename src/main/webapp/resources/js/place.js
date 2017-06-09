layui.use([ 'layer', 'laypage', 'element','form', 'layedit', 'laydate' ], function() {
			var layer = layui.layer, laypage = layui.laypage, element = layui.element();
			
			var form = layui.form();
			
			var url = window.location.href;

			var locationId = url.split('?id=')[1];
			
			function singleLine(index,data){
				var $tr = $('<tr></tr>');
				$tr.append('<td>'+index+'</td>');
				$tr.append('<td>'+data.place+'</td>');
				return $tr;
			};
			
			$.ajax({
				url:'/deviceSys/device/getPlaceList.do',
				type:"GET",
				data:{
					locationId : locationId
				},
				dataType:"json"
			}).done(function(data){
				var index = 0;
				for(var i=0;i<data.length;i++){
					index = i + 1 ;
					$('tbody').append(singleLine(index,data[i]));
				}
			});
			
			$('#submitNewPlace').on('click',function(){
				var locationid = $('#location').val();
				var place = $('#new-place').val();
				if(place == null || place == "" || place.trim() == ""){
					layer.msg("存放位置不能为空");
				}else{
					$.ajax({
						url:'/deviceSys/device/addPlace.do',
						type:"POST",
						data:{
							'place' : place ,
							'locationId' : locationId
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