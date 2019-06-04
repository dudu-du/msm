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
	.table-cont input{
		width: 80%;
		line-height: 25px;
		border-radius: 5px;
		border: 1px solid #999;
		text-indent: 10px;
	}
	.save{
		width: 100px;
		line-height: 30px;
		float: right;
		margin-right: 35px;
		border-radius: 5px;
		background: #409EFF;
		border: none;
		color: #fff;
		cursor: pointer;
	}
	.add{
		display: inline-block;
		width: 20px;
		height: 20px;
		text-align: center;
		line-height: 20px;
		border-radius: 10px;
		background: #409EFF;
		cursor: pointer;
		color: #fff;
		margin-left: 10px;
	}
</style>
<body>
<div class="main">
	<h2 class="title">岗位安全风险告知卡</h2>
	<p style="width: 100%;overflow: hidden;"><button class="save">保存</button></p>
	<div class="content">
		<div class="table-cont">
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
				<thead>
				<tr>
					<th colspan="1" rowspan="1" width="10%">工作内容</th>
					<th colspan="1" rowspan="2" class="jobName"><input type="text" value="" /></th>
					<th colspan="1" rowspan="1" width="10%">工作场所</th>
					<th colspan="3" rowspan="1" class="jobPosition"><input type="text" value="" /></th>
				</tr>
				</thead>
				<tbody style="text-align: center">
					<tr>
						<td colspan="1" rowspan="1" class="factor">危险有害因素</td>
						<td colspan="1" rowspan="1" class="factorCont"><input type="text" value="" /><span class="add">+</span></td>
						<td colspan="1" rowspan="1" class="type">事故类别</td>
						<td colspan="1" rowspan="1" class="typeCont"><input type="text" value="" /></td>
						<td colspan="1" rowspan="1" class="measure">管控措施</td>
						<td colspan="1" rowspan="1" class="measureCont"><input type="text" value="" /></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="1" rowspan="1" align="center">应急措施</td>
						<td colspan="5" rowspan="1" class="emergencyMeasure"><input type="text" value="" /></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>
</body>
<script src="/node_modules/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(".add").click(function(){
		$("tbody").append("<tr class='new'><td colspan='1' rowspan='1' class='factorCont'><input type='text' value='' /></td><td colspan='1' rowspan='1' class='typeCont'><input type='text' value='' /></td><td colspan='1' rowspan='1' class='measureCont'><input type='text' value='' /></td></tr>");
		$(".factor").attr("rowSpan",$(".new").length+1);
		$(".type").attr("rowSpan",$(".new").length+1);
		$(".measure").attr("rowSpan",$(".new").length+1);
	});
	$(".save").click(function(){
		var list = [];
		for(var i=0;i<$(".factorCont").length;i++){
			list[i] = {id:""+(i+1),controlMeasure:$(".measureCont input")[i].value,harmfulFactors:$(".factorCont input")[i].value,troubleName:$(".typeCont input")[i].value}
		}
		var data = {
			emergencyMeasure:$(".emergencyMeasure input").val(),
			jobName:$(".jobName input").val(),
			jobPosition:$(".jobPosition input").val(),
			safetyNotificationCardList:list
		};
		$.ajax({
			type:"POST",
			url:"/safety/safetyNotificationCard/safetyNotificationCard",
			data:JSON.stringify(data),
			contentType:"application/json",
			dataType:"json",
			async:false,
			success:function(data){
				alert(data.msg);
			}
		});
	});
</script>
</html>