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
	<h2 class="title">隐患整改回执单</h2>
	<div class="content">
		<div class="table-cont">
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
				<thead>
					<tr>
						<th colspan="1" rowspan="1" width="10%">安全检查人员</th>
						<th colspan="1" rowspan="1" class="checkPersonName"></th>
						<th colspan="1" rowspan="1">检查日期</th>
						<th colspan="1" rowspan="1" class="checkTime"></th>
						<th colspan="1" rowspan="1">编号</th>
						<th colspan="1" rowspan="1" class="checkCode"></th>
					</tr>
					<tr>
						<th colspan="1" rowspan="1">隐患整改部门</th>
						<th colspan="2" rowspan="1" class="orgName"></th>
						<th colspan="1" rowspan="1">整改部门负责人</th>
						<th colspan="2" rowspan="1" class="orgPersonName"></th>
					</tr>
				</thead>
				<tbody style="text-align: center">
					<tr>
						<td colspan="1" rowspan="1" width="300px">隐患内容及整改要求</td>
						<td colspan="5" rowspan="1" class="rectContent" align="left" style="position: relative;text-indent: 20px;"></td>
					</tr>
					<tr>
						<td colspan="6" rowspan="1">整改期限，自即日起至<span class="limTime"></span>前整改完毕。</td>
					</tr>
					<tr>
						<td colspan="1" rowspan="1" width="300px" height="200px">整改措施</td>
						<td colspan="5" rowspan="1" class="rectMeasure" align="left" style="position: relative;text-indent: 20px;"></td>
					</tr>
					<tr>
						<td colspan="1" rowspan="1" width="300px">验收意见或处理的结果</td>
						<td colspan="5" rowspan="1" class="rectResult" align="left" style="position: relative;text-indent: 20px;"></td>
					</tr>
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
		url:"/safety/checkRectificationReceipt/checkRectificationReceipt",
		data:{id:"9862f1cb4f0645e88750b40a7097f695"},
		dataType:"json",
		async:false,
		success:function(data){
			console.log(data);
			$(".checkPersonName").text(data.data.checkPersonName);
			$(".checkTime").text(new Date(data.data.checkTime).getFullYear()+"-"+(new Date(data.data.checkTime).getMonth()+1)+"-"+new Date(data.data.checkTime).getDate());
			$(".checkCode").text(data.data.checkCode);
			$(".orgName").text(data.data.rectificationOrgName);
			$(".orgPersonName").text(data.data.rectificationPersonName);
			$(".rectContent").html(data.data.rectificationContent+"<img style='float:right;width:50%;margin-right: 20%;' src='"+data.data.rectificationContentUrl+"' />");
			$(".limTime").text(new Date(data.data.rectificationTime).getFullYear()+"年"+(new Date(data.data.rectificationTime).getMonth()+1)+"月"+new Date(data.data.rectificationTime).getDate()+"日");
			$(".rectMeasure").html(data.data.rectificationMeasure+"<p style='position:absolute;right: 150px;bottom:60px;'>整改责任人（签字）：</p><p style='position:absolute;right: 150px;bottom:15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</p>");
			$(".rectResult").html(data.data.rectificationResult+"<img style='float:right;width:50%;margin-right: 20%;' src='"+data.data.rectificationResultUrl+"' /><p style='position:absolute;right: 150px;bottom:60px;'>验收人（签字）：</p><p style='position:absolute;right: 150px;bottom:15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</p>");
		}
	});
</script>
</html>