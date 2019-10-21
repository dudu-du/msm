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
					<th colspan="1" rowspan="2">负责人</th>
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
<script src="/node_modules/jquery/jquery.PrintArea.js"></script>
<script type="text/javascript">
	var a = "重大风险";
	var b = "较大风险";
	var c = "一般风险";
	var d = "低风险";
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
	console.log(curData);
	$.ajax({
		type:"GET",
		url:"/safety/riskIdentification/riskIdentification",
		data:{orgId:curData.orgId,year:curData.year,postName:curData.postName,levelName:curData.levelName},
		dataType:"json",
		async:false,
		success:function(data){
			console.log(data);
			$(".time").text(data.data.createTime[0]+"年");
			var trHt = "";
			data.data.riskIdentificationList.forEach(function(item,index){
				if(item.index!=null){
					trHt += "<tr>" +
							"<td colspan='1' rowspan='"+item.union+"'>"+item.index+"</td>" +
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
							"<td colspan='1' style='background:"+((item.levelName)==a?'#FF0000':(item.levelName)==b?'#DAA520':(item.levelName)==c?'#FFFF00':'#4169E1')+"'>"+item.levelName+"</td>" +
							"<td colspan='1'>"+item.personName+"</td>" +
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
							"<td colspan='1' style='background:"+((item.levelName)==a?'#FF0000':(item.levelName)==b?'#DAA520':(item.levelName)==c?'#FFFF00':'#4169E1')+"'>"+item.levelName+"</td>" +
							"<td colspan='1'>"+item.personName+"</td>" +
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