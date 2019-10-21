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
	<h2 class="title">隐患治理信息台账</h2>
	<div class="content">
		<div class="table-cont">
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
				<thead>
				<tr>
					<th colspan="1" rowspan="1">序号</th>
					<th colspan="1" rowspan="1">排查时间</th>
					<th colspan="1" rowspan="1">排查人</th>
					<th colspan="1" rowspan="1">隐患部位</th>
					<th colspan="1" rowspan="1">隐患名称</th>
					<th colspan="1" rowspan="1">隐患等级</th>
					<th colspan="1" rowspan="1">治理措施</th>
					<th colspan="1" rowspan="1">完成时限</th>
					<th colspan="1" rowspan="1">责任部门</th>
					<th colspan="1" rowspan="1">责任人</th>
					<th colspan="1" rowspan="1">复查时间</th>
					<th colspan="1" rowspan="1">复查人</th>
					<th colspan="1" rowspan="1">复查结果</th>
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
		url:"/safety/checkDangerLedger/checkDangerLedgerByPage",
		data:{currentPage:curData.currentPage,pageSize:curData.pageSize},
		dataType:"json",
		async:false,
		success:function(data){
			console.log(data);
			var trHt = "";
			data.data.list.forEach(function(item,index){
					trHt += "<tr>" +
							"<td colspan='1' width='3%'>"+(index+1)+"</td>" +
							"<td colspan='1'>"+item.investigationTime+"</td>" +
							"<td colspan='1'>"+item.investigationOrgPersonName+"</td>" +
							"<td colspan='1'>"+item.rectificationPosition+"<img width='300px' style='float: right' src='"+item.rectificationPositionUrl+"'/></td>" +
							"<td colspan='1'>"+item.rectificationName+"</td>" +
							"<td colspan='1'>"+item.rectificationLevel+"</td>"+
							"<td colspan='1'>"+item.governmentMeasure+"</td>" +
							"<td colspan='1'>"+item.complateTime+"</td>" +
							"<td colspan='1'>"+item.controlOrgName+"</td>" +
							"<td colspan='1'>"+item.controlOrgPersonName+"</td>" +
							"<td colspan='1'>"+item.reviewTime+"</td>" +
							"<td colspan='1'>"+item.reviewPersonName+"</td>" +
							"<td colspan='1'>"+item.reviewResult+"<img width='300px' style='float: right' src='"+item.reviewResultUrl+"'/></td>" +
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