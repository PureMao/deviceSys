layui.use([ 'layer', 'laypage', 'element' ], function() {
			var layer = layui.layer, laypage = layui.laypage, element = layui.element();
			function singleLocation(location){
				var $li = $("<li></li>");
				$li.append('<a href="/deviceSys/page/deviceTable.html?id='+location.id+'" target="_blank" class="layui-btn layui-btn-big">'+location.location+'</a>')
				return $li;
			}
			function singleCheck(location){
				var $li = $("<li></li>");
				$li.append('<a href="/deviceSys/page/deviceCheck.html?id='+location.id+'" target="_blank" class="layui-btn layui-btn-big">'+location.location+'</a>')
				return $li;
			}
			function singleExcel(location){
				var $li = $("<li></li>");
				$li.append('<a href="/deviceSys/page/excelList.html?locationId='+location.id+'" target="_blank" class="layui-btn layui-btn-big">'+location.location+'</a>')
				return $li;
			}
			function singleDeviceQuit(location){
				var $li = $("<li></li>");
				$li.append('<a href="/deviceSys/page/deviceQuit.html?locationId='+location.id+'" target="_blank" class="layui-btn layui-btn-big">'+location.location+'</a>')
				return $li;
			}
			$.ajax({
				url:"/deviceSys/device/queryLocationList.do",
				type:"GET",
				dataType:"json"
			}).done(function(data){
				for(var i=0;i<data.length;i++){
					layui.data('location'+data[i].id,{
						key:'name',
						value:data[i].location
					});
					$(".location-ul").append(singleLocation(data[i]));
					$(".deviceCheck-ul").append(singleCheck(data[i]));
					$(".excel-ul").append(singleExcel(data[i]));
					$(".deviceQuit-ul").append(singleDeviceQuit(data[i]));
				}
				$(".location-ul li").css("margin","5px");
				$(".deviceCheck-ul li").css("margin","5px");
				$(".excel-ul li").css("margin","5px");
				$(".deviceQuit-ul li").css("margin","5px");
			});
		});