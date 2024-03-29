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
	<h2 class="title">岗位安全风险告知卡</h2>
	<div class="content">
		<div class="table-cont">
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
				<thead>
				<tr>
					<th colspan="1" rowspan="1" width="10%">工作内容</th>
					<th colspan="1" rowspan="2" class="jobName"></th>
					<th colspan="1" rowspan="1" width="10%">工作场所</th>
					<th colspan="3" rowspan="1" class="jobPosition"></th>
				</tr>
				</thead>
				<tbody style="text-align: center">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="1" rowspan="1">应急措施</td>
						<td colspan="5" rowspan="1" class="emergencyMeasure"></td>
					</tr>
				</tfoot>
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
		url:"/safety/safetyNotificationCard/safetyNotificationCardById",
		data:{id:oId},
		dataType:"json",
		async:false,
		success:function(data){
			$(".jobName").text(data.data.jobName);
			$(".jobPosition").text(data.data.jobPosition);
			$(".emergencyMeasure").text(data.data.emergencyMeasure);
			var trHt = "";
			data.data.safetyNotificationCardList.forEach(function(item,index){
				if(index==0){
					trHt += "<tr>" +
							"<td colspan='1' rowspan='"+data.data.safetyNotificationCardList.length+"'>危险有害因素</td>" +
							"<td colspan='1' rowspan='1'>"+item.harmfulFactors+"</td>" +
							"<td colspan='1' rowspan='"+data.data.safetyNotificationCardList.length+"'>事故类别</td>" +
							"<td colspan='1' rowspan='1'>"+item.troubleName+"</td>" +
							"<td colspan='1' rowspan='"+data.data.safetyNotificationCardList.length+"' width='10%'>管控措施</td>" +
							"<td colspan='1' rowspan='1'>"+item.controlMeasure+"</td>" +
							"</tr>"
				}
				else{
					trHt += "<tr>" +
							"<td colspan='1' rowspan='1'>"+item.harmfulFactors+"</td>" +
							"<td colspan='1' rowspan='1'>"+item.troubleName+"</td>" +
							"<td colspan='1' rowspan='1'>"+item.controlMeasure+"</td>" +
							"</tr>"
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