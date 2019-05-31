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
			color: #909399;
			border: 1px solid #EBEEF5;
			border-right: none;
			border-bottom: none;
			font-size: 14px;
		}
		.table-cont th{
			border-bottom: 1px solid #EBEEF5;
			border-right: 1px solid #EBEEF5;
			line-height: 30px;
		}
		.table-cont td{
			border-bottom: 1px solid #EBEEF5;
			border-right: 1px solid #EBEEF5;
			line-height: 40px;
		}
	</style>
	<body>
		<div class="main">
			<h2 class="title">专项检查</h2>
			<div class="content">
				<p>检查时间：<span class="time"></span>检查范围：<span class="scope"></span>检查人员：<span class="name"></span></p>
				<div class="table-cont">
					<table cellspacing="0" cellpadding="0" border="0" width="100%">
						<thead>
							<tr>
								<th colspan="2" rowspan="2" width="10%">检查项目</th>
								<th colspan="2" rowspan="2" width="60%">检查内容</th>
								<th colspan="2" rowspan="1">检查结果</th>
							</tr>
							<tr>
								<th colspan="1" rowspan="1">合格</th>
								<th colspan="1" rowspan="1" width="15%">不合格</th>
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
					"<td>"+((item.result=='0')?'√':'')+"</td>"
				}
			});
			$("tbody").html(trHt);
		}
	});
</script>
</html>