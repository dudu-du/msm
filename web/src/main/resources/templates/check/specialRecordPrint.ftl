<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
	</head>
	<link href="/Public/css/print.css" rel="stylesheet" type="text/css"/>

	<body>
		<div class="main">
			<h2 class="title">专项检查</h2>
			<div class="content">
				<p>检查时间：<span class="time"></span>检查范围：<span class="scope"></span><span style="float: right;">检查人员：<span class="name"></span></span></p>
				<div class="table-cont">
					<table cellspacing="0" cellpadding="0" border="0" width="100%">
						<thead>
							<tr>
								<th colspan="2" rowspan="2" width="10%">检查项目</th>
								<th colspan="2" rowspan="2">检查内容</th>
								<th colspan="2" rowspan="1">检查结果</th>
							</tr>
							<tr>
								<th colspan="1" rowspan="1" width="8%">合格</th>
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
		url:"/safety/checkSpecialRecord/checkSpecialRecordById",
		data:{id:"cef5e250950841c9b2b0cbf2466671d7"},
		dataType:"json",
		async:false,
		success:function(data){
			$(".time").text(data.data.createTime);
			$(".name").text(data.data.checkPersonName);
			var trHt = "";
			data.data.checkSpecialList.forEach(function(item,index){
				if(item.index!=null){
					trHt += "<tr><td colspan='2' rowspan='"+item.union+"'>"+item.checkTypeName+"</td>" +
							"<td colspan='2'>"+item.checkContent+"</td>" +
							"<td>"+((item.result=='1')?'√':'')+"</td>" +
							"<td>"+((item.result=='0')?'√':'')+"</td></tr>"
				}
				else{
					trHt += "<tr><td colspan='2'>"+item.checkContent+"</td>" +
					"<td>"+((item.result=='1')?'√':'')+"</td>" +
					"<td>"+((item.result=='0')?'√':'')+"</td></tr>"
				}
			});
			$("tbody").html(trHt);
		}
	});
</script>
</html>