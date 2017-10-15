<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>多级联动的展示</title>
<script type="text/javascript" src = "${pageContext.request.contextPath }/js/jquery-1.12.1.js"></script>
<script type="text/javascript">
function chCity(obj) {
	$(obj).parent().nextAll().remove();
	var id = obj.value;
	if (id > 0 && id!=1 && id!=2 && id!=9 && id!=22) {
		$.post(
					"addressServlet01",
						{
							method : "getCity",
							id : id
						},
						function(data,status) {
							if (data != null && data.length > 0) {
								var content = "<div> <select name='CityId' id='city0' > <option value='0'>-- 请选择市 --</option>";
								for ( var city in data) {  //第一个参数表示数组的下标，第二个参数表示数组
									content += "<option value='"+data[city].id+"'>"
											+ data[city].name + "</option>";
								}
								content += "</select></div>";
								$("#types").append(content);
							}
						}, "json");
	}

}
</script>
</head>
<body>
	<div id="types">
		<label class="col-md-12 control-label" for="address">所在地区 </label>
		<div>
			<select name="ProvinceId" 
				onchange="chCity(this)" id="province0">
				<option value="0">-- 请选择省 --</option>
				<c:forEach items="${provinces }" var="item">
					<option value="${item.id}">${item.name}</option>
				</c:forEach>
			</select>
		</div>
	</div>
</body>
</html>