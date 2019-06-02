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

				</tbody>
				<tfoot>
					<tr>
						<td colspan="1" rowspan="1">应急措施</td>
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
	var oId = window.location.href.split("?")[1];
	var curData;
	$.ajax({
		type:"GET",
		url:"/safety/safetyNotificationCard/safetyNotificationCardById",
		data:{id:oId},
		dataType:"json",
		async:false,
		success:function(data){
			curData = data.data;
			$(".jobName input").val(data.data.jobName);
			$(".jobPosition input").val(data.data.jobPosition);
			$(".emergencyMeasure input").val(data.data.emergencyMeasure);
			var trHt = "";
			data.data.safetyNotificationCardList.forEach(function(item,index){
				if(index==0){
					trHt += "<tr>" +
							"<td colspan='1' rowspan='"+data.data.safetyNotificationCardList.length+"'>危险有害因素</td>" +
							"<td colspan='1' rowspan='1' class='factorCont'><input type='text' value='"+item.harmfulFactors+"' /></td>" +
							"<td colspan='1' rowspan='"+data.data.safetyNotificationCardList.length+"'>事故类别</td>" +
							"<td colspan='1' rowspan='1' class='typeCont'><input type='text' value='"+item.troubleName+"' /></td>" +
							"<td colspan='1' rowspan='"+data.data.safetyNotificationCardList.length+"' width='10%'>管控措施</td>" +
							"<td colspan='1' rowspan='1' class='measureCont'><input type='text' value='"+item.controlMeasure+"' /></td>" +
							"</tr>"
				}
				else{
					trHt += "<tr>" +
							"<td colspan='1' rowspan='1' class='factorCont'><input type='text' value='"+item.harmfulFactors+"' /></td>" +
							"<td colspan='1' rowspan='1' class='typeCont'><input type='text' value='"+item.troubleName+"' /></td>" +
							"<td colspan='1' rowspan='1' class='measureCont'><input type='text' value='"+item.controlMeasure+"' /></td>" +
							"</tr>"
				}
			});
			$("tbody").html(trHt);
		}
	});
	$(".save").click(function(){
		for(var i=0;i<$(".factorCont").length;i++){
			curData.safetyNotificationCardList[i].controlMeasure=$(".measureCont input")[i].value;
			curData.safetyNotificationCardList[i].harmfulFactors=$(".factorCont input")[i].value;
			curData.safetyNotificationCardList[i].troubleName=$(".typeCont input")[i].value;
		}
		curData.emergencyMeasure = $(".emergencyMeasure input").val();
		curData.jobName = $(".jobName input").val();
		curData.jobPosition = $(".jobPosition input").val();
		$.ajax({
			type:"PUT",
			url:"/safety/safetyNotificationCard/safetyNotificationCard",
			data:JSON.stringify(curData),
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