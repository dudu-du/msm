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
	}
</style>
<body>
<div class="main">
	<h2 class="title">综合检查（节假日、复产前）</h2>
	<div class="content">
		<div class="table-cont">
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
				<thead>
				<tr>
					<th colspan="1" rowspan="1">检查负责人</th>
					<th colspan="1" rowspan="1" class="chargeName" width="30%"></th>
					<th colspan="1" rowspan="1">检查时间</th>
					<th colspan="1" rowspan="1" class="checkTime" width="30%"></th>
				</tr>
				<tr>
					<th colspan="1" rowspan="1">检查人员</th>
					<th colspan="3" rowspan="1" class="checkName"></th>
				</tr>
				<tr>
					<th colspan="1" rowspan="1">受检查部门</th>
					<th colspan="1" rowspan="1" class="orgName"></th>
					<th colspan="1" rowspan="1">参加人员</th>
					<th colspan="1" rowspan="1" class="joinName"></th>
				</tr>
				<tr>
					<th colspan="1" rowspan="1">序号</th>
					<th colspan="2" rowspan="1">检查内容</th>
					<th colspan="1" rowspan="1">检查结果</th>
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
		url:"/safety/checkHolidayRecord/checkHolidayRecordById",
		data:{id:"c618041f84b7462585cd7c98b23e07ec"},
		dataType:"json",
		async:false,
		success:function(data){
			console.log(data);
			var trHt = "";
			var checkTime = new Date(data.data.checkStartTime).getFullYear()+"-"+(new Date(data.data.checkStartTime).getMonth()+1)+"-"+new Date(data.data.checkStartTime).getDate()
			$(".chargeName").text("");
			$(".checkTime").text(checkTime);
			$(".checkName").text(data.data.checkPersonName);
			$(".orgName").text("");
			$(".joinName").text("");
			data.data.checkComprehensiveHolidayList.forEach(function(item,index){
				trHt += "<tr>" +
						"<td colspan='1' rowspan='1'>"+(index+1)+"</td>" +
						"<td colspan='2' rowspan='1'>"+item.content+"</td>" +
						"<td colspan='1' rowspan='1'>"+((item.result=="1")?"是":"否")+"</td>" +
						"</tr>"
			});
			$("tbody").html(trHt);
		}
	});
</script>
</html>