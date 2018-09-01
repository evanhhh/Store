<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>测试下拉菜单</title>
</head>
<body>
<script type="text/javascript" 
	src="../js/jquery.min.js"></script>
	<pre>
	第一步：请求路径：/test/adress.do
	控制器接收到请求后获得所有省信息
	然后将其转发到/web/test.jsp
	第二步，在test.jsp中通过jstl遍历所有省信息
</pre>
	<select id="provinces" style="width:120px">
	<option value="0">----- 请选择 -----</option>
	<c:forEach items="${provinces }" var="p">
	<option value="${p.code }">${p.name }</option>
	</c:forEach>
</select>

<select id="cities" style="width:120px">
	<option value="0">----- 请选择 -----</option>
</select>
<select id="areas" style="width:120px">
	<option value="0">----- 请选择 -----</option>
</select>

<script type="text/javascript">
	var defaultoption = "<option value="+0+">----- 请选择 -----</option>";
$("#provinces").change(function() {
	var provinceCode = $("#provinces").val();
	if (provinceCode == 0) {
		$("#cities").html(defaultoption);
		$("#areas").html(defaultoption);
		return;
	}
	
	$.ajax({
		"url": "../city/list.do",
		"data": "provinceCode=" + provinceCode,
		"type": "GET",
		"dataType": "json",
		"success": function(obj) {
	
			$("#cities").html(defaultoption);
			$("#areas").html(defaultoption);
			var List = obj.data;					
			for(var i=0; i < List.length; i++) {
				var opt = "<option value=\"" + List[i].code + "\">" + List[i].name + "</option>";
				$("#cities").append(opt);
			}
		}
	});
});
$("#cities").change(function() {
	// 获取当前选中的“市”的代号
	var cityCode = $("#cities").val();
	// 判断代码是否有效
	if (cityCode == 0) {
		// 默认选项
		var defaultOption = "<option value=\"0\">----- 请选择 -----</option>";
		// “市”变成了“请选择”，把“区”列表还原为默认状态
		$("#areas").html(defaultOption);
		return;
	}
	// 确定提交到的网址
	var url = "../area/list.do";
	// 确定请求参数
	var data = "cityCode=" + cityCode;
	// 提交并处理ajax请求
	$.ajax({
		"url": url,
		"data": data,
		"type": "GET",
		"dataType": "json",
		"success": function(obj) {
			// 先清空“区”列表
			$("#areas").empty();
			// 获取响应数据中的列表数据
			var list = obj.data;
			// 处理响应的列表数据
			for (var i = 0; i < list.length; i++) {
				// 生成每一个列表项
				var opt = "<option value=\"" + list[i].code + "\">" + list[i].name + "</option>";
				// 将列表项添加到整个列表中
				$("#areas").append(opt);
			}
		}
	});
});
	</script>
</body>
</html>