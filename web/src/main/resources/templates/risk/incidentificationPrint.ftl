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
	<h2 class="title">风险辨识清单列表</h2>
	<div class="content">
		<p><span class="time" style="margin-right: 15px"></span><span class="name" style="margin-right: 15px"></span><span class="postName" style="margin-right: 15px"></span><span class="levelName" style="margin-right: 15px"></span></p>
		<div class="table-cont">
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
				<thead>
				<tr>
					<th colspan="1" rowspan="2">序号</th>
					<th colspan="1" rowspan="2">岗位（设备设施/作业活动）单元</th>
					<th colspan="5" rowspan="1">安全风险辨识</th>
					<th colspan="3" rowspan="1">安全风险分析</th>
					<th colspan="4" rowspan="1">LEC风险分析法</th>
					<th colspan="1" rowspan="2" width="5%">安全风险评价</th>
				</tr>
				<tr>
					<th colspan="1" rowspan="1" width="5%">危险有害因素</th>
					<th colspan="1" rowspan="1">事故类型</th>
					<th colspan="1" rowspan="1">原因</th>
					<th colspan="1" rowspan="1">后果</th>
					<th colspan="1" rowspan="1" width="6%">影响范围</th>
					<th colspan="1" rowspan="1">可能性</th>
					<th colspan="1" rowspan="1">严重性</th>
					<th colspan="1" rowspan="1" width="18%">现有措施有效性</th>
					<th colspan="1" rowspan="1">事故发生的可能性(L)</th>
					<th colspan="1" rowspan="1" width="6%">暴露于危险环境的频繁程度(E)</th>
					<th colspan="1" rowspan="1">发生事故产生的后果(C)</th>
					<th colspan="1" rowspan="1">D值</th>
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
		url:"/safety/riskIdentification/riskIdentification",
		data:{orgId:"0528160b1191413d81862615dbdd15c3",year:"2019",postName:"",levelName:""},
		dataType:"json",
		async:false,
		success:function(data){
			console.log(data);
			$(".time").text(data.data.createTime[0]+"年");
			var trHt = "";
			data.data.riskIdentificationList.forEach(function(item,index){
				if(item.index!=null){
					trHt += "<tr>" +
							"<td colspan='1' rowspan='"+item.union+"'>"+(index+1)+"</td>" +
							"<td colspan='1' rowspan='"+item.union+"'>"+item.postName+"</td>" +
							"<td colspan='1'>"+item.harmfulFactors+"</td>" +
							"<td colspan='1'>"+item.troubleName+"</td>" +
							"<td colspan='1'>"+item.cause+"</td>" +
							"<td colspan='1'>"+item.consequence+"</td>" +
							"<td colspan='1'>"+item.incidence+"</td>" +
							"<td colspan='1'>"+item.possibility+"</td>" +
							"<td colspan='1'>"+item.seriousness+"</td>" +
							"<td colspan='1'>"+item.measure+"</td>" +
							"<td colspan='1'>"+item.numL+"</td>" +
							"<td colspan='1'>"+item.numE+"</td>" +
							"<td colspan='1'>"+item.numC+"</td>" +
							"<td colspan='1'>"+item.numD+"</td>" +
							"<td colspan='1'>"+item.levelName+"</td>" +
							"</tr>"
				}
				else{
					trHt += "<tr>" +
							"<td colspan='1'>"+item.harmfulFactors+"</td>" +
							"<td colspan='1'>"+item.troubleName+"</td>" +
							"<td colspan='1'>"+item.cause+"</td>" +
							"<td colspan='1'>"+item.consequence+"</td>" +
							"<td colspan='1'>"+item.incidence+"</td>" +
							"<td colspan='1'>"+item.possibility+"</td>" +
							"<td colspan='1'>"+item.seriousness+"</td>" +
							"<td colspan='1'>"+item.measure+"</td>" +
							"<td colspan='1'>"+item.numL+"</td>" +
							"<td colspan='1'>"+item.numE+"</td>" +
							"<td colspan='1'>"+item.numC+"</td>" +
							"<td colspan='1'>"+item.numD+"</td>" +
							"<td colspan='1'>"+item.levelName+"</td></tr>"
				}
			});
			$("tbody").html(trHt);
		}
	});
</script>
</html>