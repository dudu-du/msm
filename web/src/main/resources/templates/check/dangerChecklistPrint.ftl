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
	<h2 class="title">隐患排查清单列表</h2>
	<div class="content">
		<div class="table-cont">
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
				<thead>
				<tr>
					<th colspan="1" rowspan="1">序号</th>
					<th colspan="1" rowspan="1">风险部位</th>
					<th colspan="1" rowspan="1">风险因素</th>
					<th colspan="1" rowspan="1">风险管控措施</th>
					<th colspan="1" rowspan="1">措施失控表现</th>
					<th colspan="1" rowspan="1">管控部门</th>
					<th colspan="1" rowspan="1">管控责任人</th>
					<th colspan="1" rowspan="1">排查部门</th>
					<th colspan="1" rowspan="1">排查责任人</th>
					<th colspan="1" rowspan="1">排查频次</th>
					<th colspan="1" rowspan="1">备注</th>
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
	function GetRequest() {
		var url = decodeURI(decodeURI(location.search)); //获取url中"?"符后的字串
		var theRequest = new Object();
		if (url.indexOf("?") != -1) {
			var str = url.substr(1);
			strs = str.split("&");
			for(var i = 0; i < strs.length; i ++) {
				theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
			}
		}
		return theRequest;
	}
	var curData = GetRequest();
	$.ajax({
		type:"GET",
		url:"/safety/checkDangerChecklist/checkDangerChecklistByPage",
		data:{currentPage:curData.currentPage,pageSize:curData.pageSize},
		dataType:"json",
		async:false,
		success:function(data){
			console.log(data);
			var trHt = "";
			data.data.list.forEach(function(item,index){
					trHt += "<tr>" +
							"<td colspan='1' width='3%'>"+(index+1)+"</td>" +
							"<td colspan='1'>"+item.riskPosition+"</td>" +
							"<td colspan='1'>"+item.harmfulFactors+"</td>" +
							"<td colspan='1'>"+item.measure+"</td>" +
							"<td colspan='1'>"+item.runawayPerformance+"</td>" +
							"<td colspan='1'>"+item.controlOrgName+"</td>"+
							"<td colspan='1'>"+item.controlOrgPersonName+"</td>" +
							"<td colspan='1'>"+item.investigationOrgName+"</td>" +
							"<td colspan='1'>"+item.investigationOrgPersonName+"</td>" +
							"<td colspan='1'>"+item.investigationCount+"</td>" +
							"<td colspan='1'>"+item.remark+"</td>" +
							"</tr>"

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