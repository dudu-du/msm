<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title></title>
</head>
<link href="/Public/css/print.css" rel="stylesheet" type="text/css"/>
<body>
<input type="button" id="print" value="打印本页"/>
<div class="main" id="printArea">
	<h2 class="title">隐患排查日治理记录</h2>
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
<script src="/node_modules/jquery/jquery.PrintArea.js"></script>
<script type="text/javascript">
    var oId = window.location.href.split("?")[1];
	$.ajax({
		type:"GET",
		url:"/safety/checkDayRecord/checkDayRecordById",
		data:{id:oId},
		dataType:"json",
		async:false,
		success:function(data){
			var trHt = "";
			var checkTime = (new Date(data.data.checkStartTime).getMonth()+1)+"月-"+new Date(data.data.checkStartTime).getDate()+"日 "+new Date(data.data.checkStartTime).getHours()+":"+new Date(data.data.checkStartTime).getMinutes();
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
    $(document).ready(function(){
        $("#print").click(function(){
            $("#printArea").printArea();
        });
    });
</script>
</html>