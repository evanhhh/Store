<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的订单 - 达内学子商城</title>
    <link href="../css/orders.css" rel="stylesheet"/>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
    <link href="../css/personage.css" rel="stylesheet" />
    <style>
	.button {
		display: block;
		width: 160px;
		height: 42px;
		line-height: 42px;
		text-align: center; 
		background: #27b1f6;
		color: #fff;
		border-radius: 2px;
		margin-top: 30px;
	}
	.button:hover {
		background: #37c1e6;
	}
	</style>
</head>
<body>
<c:import url="header.jsp"></c:import>

<!-- 我的订单导航栏-->
<div id="nav_order">
    <ul>
        <li><a href="">首页<span>&gt;</span>个人中心</a></li>
    </ul>
</div>
<!--我的订单内容区域 #container-->
<div id="container" class="clearfix">
    <c:import url="left_menu.jsp"></c:import>
    
	<!-- 右边栏-->
    <div class="rightsidebar_box rt">	
        <!--标题栏-->
        <div class="rs_header">
            <span class="address_title">收获地址管理</span>
        </div>
        <!--收货人信息填写栏-->
        <div class="rs_content">
        	<form method="post" action="addnew.do">
	            <!--收货人姓名-->
	            <div class="recipients">
	                <span class="red">*</span><span class="kuan">收货人：</span><input type="text" name="recvName" id="receiverName"/>
	            </div>
	            <!--收货人所在城市等信息-->
	            <div data-toggle="distpicker" class="address_content">
					 <span class="red">*</span>
					 <span class="kuan">省&nbsp;&nbsp;份：</span>
					 <select name="recvProvince" id="provinces" style="width: 120px;">
						<option value="0">----- 请选择 -----</option>
						<c:forEach items="${provinces }" var="p">
						<option value="${p.code }">${p.name }</option>
						</c:forEach>
					 </select>
					  城市：
					 <select name="recvCity" id="cities" style="width: 120px;">
						<option value="0">----- 请选择 -----</option>
					 </select>
					  区/县：
					 <select name="recvArea" id="areas" style="width: 120px;">
						<option value="0">----- 请选择 -----</option>
					 </select>
				</div> 
	            
	            
	            <!--收货人详细地址-->
	            <div class="address_particular">
	                <span class="red">*</span><span class="kuan">详细地址：</span><textarea name="recvAddress" id="receiverAddress" cols="60" rows="3" placeholder="建议您如实填写详细收货地址"></textarea>
	            </div>
	            <!--收货人地址-->
	            <div class="address_tel">
	                <span class="red">*</span>
	                <span class="kuan">手机号码：</span>
	                <input type="tel" id="receiverMobile" name="recvPhone"/>
	                	固定电话：
	                <input type="text" name="recvTel" id="receiverPhone"/>
	            </div>
	            <!--邮政编码-->
	            <div class="address_postcode">
	                <span class="red">&nbsp;</span class="kuan"><span>邮政编码：</span>&nbsp;<input type="text" name="recvZip"/>
	            </div>
	            <!--地址名称-->
	            <div class="address_name">
	                <span class="red">&nbsp;</span class="kuan"><span>地址名称：</span>&nbsp;<input type="text" id="addressName" name="tag"/>如：<span class="sp">家</span><span class="sp">公司</span><span class="sp">宿舍</span>
	            </div>
	            <!--保存收货人信息-->
	            <div>
               		<input class="button" 
               			type="submit" 
               			value="保存收货人信息" />
	            </div>
	
    		</form>
            <!--已有地址栏-->
            <div style="margin: 50px 0 0 0;" class="address_information_manage">
                <div class="aim_title">
                    <span class="dzmc dzmc_title">地址名称</span><span class="dzxm dzxm_title">姓名</span><span class="dzxq dzxq_title">地址详情</span><span class="lxdh lxdh_title">联系电话</span><span class="operation operation_title">操作</span>
                </div>
                
                <c:forEach items="${addresses }" var="address">
                <div class="aim_content_two 
                	<c:if test="${address.isDefault == 1 }">
                	aim_active
                	</c:if>
                	">
                    <span class="dzmc 
                    <c:if test="${address.isDefault == 1 }">
                    dzmc_active
                    </c:if>
                    <c:if test="${address.isDefault == 0 }">
                    dzmc_normal
                    </c:if>
                    ">${address.tag }</span>
                    <span class="dzxm dzxm_normal">${address.recvName }</span>
                    <span class="dzxq dzxq_normal">${address.recvDistrict } ${address.recvAddress }</span>
                    <span class="lxdh lxdh_normal">${address.recvPhone }</span>
                    <span class="operation operation_normal">
                    	<a href="#${address.id }" class="aco_change">修改</a> |
                    	<a href="delete.do?id=${address.id }" 
                    		class="aco_delete"
                    		onclick="return confirm('确定删除吗？')">删除</a>
                    </span>
                    <span class="swmr swmr_normal">
                    	<c:if test="${address.isDefault == 0 }">
                    	<a href="#${address.id }" 
                    		class="swmr swmr_normal">设为默认</a>
                    	</c:if>
                    </span>
                </div>
                </c:forEach>
                
            </div>
        </div>
    </div>
</div>

<!-- 品质保障，私人定制等-->
<div id="foot_box">
    <div class="icon1 lf">
        <img src="../images/footer/icon1.png" alt=""/>

        <h3>品质保障</h3>
    </div>
    <div class="icon2 lf">
        <img src="../images/footer/icon2.png" alt=""/>

        <h3>私人定制</h3>
    </div>
    <div class="icon3 lf">
        <img src="../images/footer/icon3.png" alt=""/>

        <h3>学员特供</h3>
    </div>
    <div class="icon4 lf">
        <img src="../images/footer/icon4.png" alt=""/>

        <h3>专属特权</h3>
    </div>
</div>
<!-- 页面底部-->
<div class="foot_bj">
    <div id="foot">
        <div class="lf">
             <p class="footer1"><img src="../images/footer/logo.png" alt="" class=" footLogo"/></p>
             <p class="footer2"><img src="../images/footer/footerFont.png" alt=""/></p>
        </div>
        <div class="foot_left lf">
            <ul>
                <li><a href="#"><h3>买家帮助</h3></a></li>
                <li><a href="#">新手指南</a></li>
                <li><a href="#">服务保障</a></li>
                <li><a href="#">常见问题</a></li>
            </ul>
            <ul>
                <li><a href="#"><h3>商家帮助</h3></a></li>
                <li><a href="#">商家入驻</a></li>
                <li><a href="#">商家后台</a></li>
            </ul>
            <ul>
                <li><a href="#"><h3>关于我们</h3></a></li>
                <li><a href="#">关于达内</a></li>
                <li><a href="#">联系我们</a></li>
                <li>
                    <img src="../images/footer/wechat.png" alt=""/>
                    <img src="../images/footer/sinablog.png" alt=""/>
                </li>
            </ul>
        </div>
        <div class="service">
            <p>学子商城客户端</p>
            <img src="../images/footer/ios.png" class="lf">
            <img src="../images/footer/android.png" alt="" class="lf"/>
        </div>
        <div class="download">
            <img src="../images/footer/erweima.png">
        </div>
		<!-- 页面底部-备案号 #footer -->
        <div class="record">
            &copy;2017 达内集团有限公司 版权所有 京ICP证xxxxxxxxxxx
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/jquery.page.js"></script>
<script type="text/javascript" src="../js/orders.js"></script>
<script type="text/javascript" src="../js/distpicker1.data.js"></script>
<script type="text/javascript" src="../js/distpicker1.js"></script>
<script type="text/javascript" src="../js/personal.js"></script>
<script type="text/javascript">
	$(".lxdh_normal").each(function(i,e) {
		var phone = $(e).html();
		$(e).html(changePhone(phone));
	});
</script>

<script type="text/javascript">
$("#provinces").change(function() {
	// 默认选项
	var defaultOption = "<option value=\"0\">----- 请选择 -----</option>";
	// 获取当前选中的省的代号
	var provinceCode = $("#provinces").val();
	// 判断代号是否有效，即选中的是不是“请选择”
	if (provinceCode == 0) {
		// 把“市”、“区”列表还原为默认状态
		$("#cities").html(defaultOption);
		$("#areas").html(defaultOption);
		return;
	}
	
	// 提交并处理ajax请求	
	$.ajax({
		"url": "../city/list.do",
		"data": "provinceCode=" + provinceCode,
		"type": "GET",
		"dataType": "json",
		"success": function(obj) {
			// 省变了，先把“市”列表还原为默认状态
			$("#cities").html(defaultOption);
			// 把“区”列表还原为默认状态
			$("#areas").html(defaultOption);
			
			// 获取响应数据中的列表数据
			var list = obj.data;
			// 遍历列表数据
			for(var i=0; i < list.length; i++) {
				// 得到列表项
				var opt = "<option value=\"" + list[i].code + "\">" + list[i].name + "</option>";
				// 将列表项添加到下拉列表中
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
</html>