layui.use([ 'layer', 'laypage', 'element','form' ], function() {
			var layer = layui.layer, laypage = layui.laypage, element = layui.element();
			
			$('#place').empty();
			$('#place').append($('<option value="">--请选择--</option>'));
			
			var form = layui.form();
			$('#submitRegist').unbind();
			
			function singleTypeOption(type){
				var $option = $('<option value="'+type.id+'">'+type.name+'-'+type.model+'</option>');
				return $option;
			}
			
			function singleLocationOption(location){
				var $option = $('<option value="'+location.id+'">'+location.location+'</option>');
				return $option;
			}
			
			function singlePlaceOption(place){
				var $option = $('<option value="'+place.id+'">'+place.place+'</option>');
				return $option;
			}
			
			var $emptyOption = $('<option value="">--请选择--</option>');
			
			var $defaultOption = $('<option value="">--无选项--</option>');
			
			$.ajax({
				url:'/deviceSys/device/selectDeviceTypeList.do',
				type:"GET",
				dataType:"json"
			}).done(function(typeList){
				$('#type').empty();
				var eo = '<option value="">--请选择--</option>';
				if(typeList.length > 0){
					$('#type').empty();
					$('#type').append(eo);
					for(var i=0;i<typeList.length;i++){
						$('#type').append(singleTypeOption(typeList[i]));
					}
					form.render('select');
				}else{
					$('#type').empty();
					$('#type').append(eo);
					form.render('select');
				}
			});
			
			$.ajax({
				url:'/deviceSys/device//queryLocationList.do',
				type:"GET",
				dataType:"json"
			}).done(function(locationList){
				$('#location').empty();
				var eo = '<option value="">--请选择--</option>';
				if(locationList.length > 0){
					$('#location').append(eo);
					for(var i=0;i<locationList.length;i++){
						$('#location').append(singleLocationOption(locationList[i]));
					}
					form.render('select');
				}else{
					$('#location').append(eo);
					form.render('select');
				}
			});
			
			$('#submitRegist').on('click',function(){
				var typeId = $('#type').val();
				var locationId = $('#location').val();
				var placeId = $('#place').val();
				var deviceNo = $('#serial').val();
				var remark = $('#remark').val();
				var flag1 = (typeId == '' || typeId == null);
				var flag2 = (locationId == '' || locationId == null);
				var flag3 = (placeId == '' || placeId == null);
				var flag4 = (deviceNo == '' || deviceNo == null);
				if(flag1){
					layer.msg('请选择一个类型');
					return;
				}
				if(flag2){
					layer.msg('请选择一个地点');
					return;
				}
				if(flag3){
					layer.msg('请选择一个位置');
					return;
				}
				if(flag4){
					layer.msg('请输入一个序列号');
					return;
				}
				
				console.log("typeId: "+typeId);
				console.log("locationId: "+locationId);
				console.log("placeId: "+placeId);
				console.log("deviceNo: "+deviceNo);
				console.log("remark: "+remark);
				
				var f = true;
				
				if(f){
					$.ajax({
						url:'/deviceSys/device/addSingleDevice.do',
						type:"POST",
						dataType:"json",
						data:{
							typeId : typeId,
							locationId : locationId,
							placeId : placeId,
							deviceNo : deviceNo,
							remark : remark
						}
					}).done(function(result){
						if(result == 1){
							layer.msg('添加成功！');
							setTimeout(function(){
								 window.location.href = window.location.href;
							 },1500);
						}else if(result == 0){
							layer.msg('添加失败！');
						}else if(result == 3){
							layer.msg('序列号冲突');
						}else{
							layer.msg('请检查连接');
						}
					});
				}
			});
			
			form.on('select(loca)',function(data){
				var location = data.value;
				$.ajax({
					url:'/deviceSys/device/getPlaceList.do?locationId=' + location,
					type:"GET",
					dataType:"json"
				}).done(function(places){
					$('#place').empty();
					var eo = '<option value="">--请选择--</option>';
					$('#place').append(eo);
					for(var i=0;i<places.length;i++){
						$('#place').append(singlePlaceOption(places[i]));
					}
					form.render('select');
				});
			});
			
			
		});