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
	<h2 class="title">综合检查（季节性）</h2>
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
<script src="/node_modules/jquery/jquery.PrintArea.js"></script>
<script type="text/javascript">
	$.ajax({
		type:"GET",
		url:"/safety/checkSeasonRecord/checkSeasonRecordById",
		data:{id:"f2b5b755610b40a98528b3a544b80c2f"},
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
			data.data.checkComprehensiveSeasonList.forEach(function(item,index){
				trHt += "<tr>" +
						"<td colspan='1' rowspan='1'>"+(index+1)+"</td>" +
						"<td colspan='2' rowspan='1'>"+item.content+"</td>" +
						"<td colspan='1' rowspan='1'>"+((item.result=="1")?"是":"否")+"</td>" +
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