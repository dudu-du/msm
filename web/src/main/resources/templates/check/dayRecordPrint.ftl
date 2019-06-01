<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title></title>
</head>
<style type="text/css">
	*{
		margin: 0;
		padding: 0;
	}
	body{
		margin:8px;
	}
	.main{
		width: 100%;
	}
	.title{
		height: 60px;
		padding: 0 20px;
		text-align: center;
		font-size: 32px;
		margin:0;
		font-weight: normal;
	}
	.content{
		padding: 20px;
	}
	.table-cont{
		width: 100%;
	}
	.table-cont{
		color: #000;
		border: 1px solid #000;
		border-right: none;
		border-bottom: none;
		font-size: 14px;
	}
	.table-cont th{
		border-bottom: 1px solid #000;
		border-right: 1px solid #000;
		line-height: 40px;
	}
	.table-cont td{
		border-bottom: 1px solid #000;
		border-right: 1px solid #000;
		line-height: 40px;
		padding: 0 10px;
	}
</style>
<body>
<div class="main">
	<h2 class="title">开采班组隐患排查日治理记录</h2>
	<div class="content">
		<p>班组起止工作时间：<span class="time"></span><span style="float: right;margin-right: 50px;">班组长：<span class="name"></span></span></p>
		<div class="table-cont">
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
				<thead>
				<tr>
					<th colspan="5" align="left" style="text-indent: 20px">一、凿岩岗位   1-16为作业前、作业后检查，17-25为作业中检查。&nbsp;&nbsp;&nbsp;<span style="float: right;margin-right: 50px;">检查时间：<span class="checkTime"></span>&nbsp;&nbsp;&nbsp;检查人员：<span class="checkName"></span></span></th>
				</tr>
				<tr>
					<th colspan="1" rowspan="2">序号</th>
					<th colspan="1" rowspan="2" width="65%">检查项目及相关要求</th>
					<th colspan="1" rowspan="2">检查依据</th>
					<th colspan="2" rowspan="1">符合性</th>
				</tr>
				<tr>
					<th colspan="1" rowspan="1" width="7%">是</th>
					<th colspan="1" rowspan="1" width="8%">否</th>
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
		url:"/safety/checkDayRecord/checkDayRecordById",
		data:{id:"a0fd82f072464550b44704acd1ef2c8d"},
		dataType:"json",
		async:false,
		success:function(data){
			console.log(data);
			var trHt = "";
			var checkTime = (new Date(data.data.checkStartTime).getMonth()+1)+"月-"+new Date(data.data.checkStartTime).getDate()+"日 "+new Date(data.data.checkStartTime).getHours()+":"+new Date(data.data.checkStartTime).getMinutes()+"至"+new Date(data.data.checkStartTime).getHours()+":"+new Date(data.data.checkStartTime).getMinutes();
			$(".time").text(checkTime);
			$(".name").text(data.data.checkPersonName);
			$(".checkTime").text(new Date(data.data.checkStartTime).getFullYear()+"-"+(new Date(data.data.checkStartTime).getMonth()+1)+"-"+new Date(data.data.checkStartTime).getDate());
			$(".checkName").text(data.data.checkPersonName);
			data.data.checkDayList.forEach(function(item,index){
				if(item.index!=null){
					trHt += "<tr>" +
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