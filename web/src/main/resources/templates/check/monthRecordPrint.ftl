<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title></title>
</head>
<link href="/Public/css/print.css" rel="stylesheet" type="text/css"/>

<body>
<div class="main">
	<h2 class="title">隐患排查治理月排查记录</h2>
	<div class="content">
		<p>检查时间：<span class="time"></span><span style="float: right;">排查人员：<span class="name"></span></span></p>
		<div class="table-cont">
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
				<thead>
				<tr>
					<th colspan="3" rowspan="2" width="65%">检查项目及相关要求</th>
					<th colspan="1" rowspan="2">检查方法</th>
					<th colspan="2" rowspan="1">符合性</th>
				</tr>
				<tr>
					<th colspan="1" rowspan="1" width="7%">合格</th>
					<th colspan="1" rowspan="1" width="8%">不合格</th>
				</tr>
				</thead>
				<tbody style="text-align: center">

				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
<script src="/node_modules/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$.ajax({
		type:"GET",
		url:"/safety/checkMonthRecord/checkMonthRecordById",
		data:{id:"44edc9a33cac4d3bb1275b8863d570b7"},
		dataType:"json",
		async:false,
		success:function(data){
			console.log(data);
			var trHt = "";
			var checkTime = new Date(data.data.checkStartTime).getFullYear()+"-"+(new Date(data.data.checkStartTime).getMonth()+1)+"-"+new Date(data.data.checkStartTime).getDate()+"至"+new Date(data.data.checkEndTime).getFullYear()+"-"+(new Date(data.data.checkEndTime).getMonth()+1)+"-"+new Date(data.data.checkEndTime).getDate();
			$(".time").text(checkTime);
			$(".name").text(data.data.checkPersonName);
			data.data.checkMonthList.forEach(function(item,index){
				if(item.index!=null){
					trHt += "<tr><td colspan='1' rowspan='"+item.union+"'>"+item.checkTypeName+"</td>" +
							"<td colspan='1' width='3%'>"+(index+1)+"</td>" +
							"<td colspan='1'>"+item.checkContent+"</td>" +
							"<td colspan='1'>"+item.checkMethod+"</td>" +
							"<td>"+((item.result=='1')?'√':'')+"</td>" +
							"<td>"+((item.result=='0')?'√':'')+"</td></tr>"
				}
				else{
					trHt += "<tr>" +
							"<td colspan='1' width='3%'>"+(index+1)+"</td>" +
							"<td colspan='1'>"+item.checkContent+"</td>" +
							"<td colspan='1'>"+item.checkMethod+"</td>" +
							"<td>"+((item.result=='1')?'√':'')+"</td>" +
							"<td>"+((item.result=='0')?'√':'')+"</td></tr>"
				}
			});
			$("tbody").html(trHt);
		}
	});
</script>
</html>