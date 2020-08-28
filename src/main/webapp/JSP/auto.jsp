<%@page import="com.oval.grabweb.bean.Customer"%>
<%@page import="com.oval.grabweb.vo.CustomerVo"%>
<%@page import="com.oval.grabweb.config.Config"%>
<%@page import="com.oval.grabweb.servlet.PageActionManager"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>页面抓取操作界面</title>
<link type="text/css" rel="stylesheet" href="../asserts/css/bootstrap.min.css" />
</head>
<body style="min-width: 1415px">
	<div class="container-fluid">
	    <div class="row">
	        <nav class="navbar navbar-default navbar-fixed-top">
	            <div class="container">
	                <div class="btn-group">
	                    <a class="btn btn-success" href="../JSP/VeryAuto.jsp">跳转</a>
	                    <button class="btn btn-warning" onclick="retry()">重跑</button>
	                    <button class="btn btn-primary" onclick="refresh()">刷新文件状态</button>
	                    <button class="btn btn-warning" onclick="handled()">待处理</button>
	                    <button class="btn btn-sm btn-danger" onclick="restart()">Restart</button>
	                </div>
	            </div>
	            <div style=" position: fixed; _position: absolute;left: 50%;top:10px;
	                    z-index: 1; " class="btn btn-sm btn-primary" onclick="show()">当前任务列表
	                <ol id="list" style="padding:10px;display:none">
	                </ol>
	            </div>
	            
	            <div style=" position: fixed; _position: absolute;top: 10px; right: 1px; _bottom: auto;
                    padding: 1px;z-index: 9999999; width:100px; height:30px;" class="p-t_10">
                <input class="btn btn-sm btn-primary" type="button" value="执行任务"  onclick="execTask()"/>
            	</div>
	        </nav>
		<div  class="table-responsive">
			<table id="sampleTableA" class="table table-striped" >
				<thead>
					<tr>
						<td class="td_title" align="center">序号</td>
						<td class="td_title" align="center">经销商代码</td>
						<td class="td_title" align="center">经销商名称</td>
						<td class="td_title" align="center">网址</td>
						<td class="td_title" align="center">用户名密码</td>
						<td class="td_title" align="center">执行时间</td>
						<td class="td_title" align="center">加入任务列表</td>
						<td class="td_title" align="center">文件状态</td>
					</tr>
				</thead>
				<tbody>
					<%
						Map<String, CustomerVo> map = Config.getCustomerVos();
						List<CustomerVo> customers = new ArrayList<CustomerVo>(map.values());
						Customer c;
						CustomerVo v;
						for (int i = 0; i < customers.size(); i++) {
							v = customers.get(i);
							c = v.getCustomer();
					%>
					<tr>
						<td align="center"><%=i%></td>
						<td align="center"><%=c.getOrgCode()%></td>
						<td align="center"><%=c.getOrgName()%></td>
						<td align="center"><%=c.getUrl()%></td>
						<td align="center"><%=c.getParams()%></td>
						<td align="center"><%=c.getExecTime()%></td>
						<td class="td_con" align="center"><input type="button"
								value="加入任务列表" onclick="addTask('<%=c.getOrgCode()%>')" /></td>
						<td id="z-<%=c.getOrgCode()%>" class="td_con" align="center">库存:<%=v.getStatus().get(0)%><br />销售:<%=v.getStatus().get(1)%><br />采购:<%=v.getStatus().get(2)%></td>
					</tr>
					<%
						}
					%>
				</tbody>
	
				<tfoot>
	
				</tfoot>
			</table>
		</div>
		</div>
	</div>
	<script src="../asserts/js/jquery.min.js"></script>
	<script src="../js/fancyTable.js"></script>
	<script src="../asserts/js/bootstrap.min.js"></script>
	<script src="../asserts/js/popper.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function () {
	  	  $("#sampleTableA").fancyTable({
	        pagination: true,
	     	   perPage: 120,
	        globalSearch: true
	   	 });
		});
	
	function addTask(orgCode) {
		var data = "orgCode="+orgCode
		console.log(data)
		$.ajax({
			url: "../VerifyActionServlet/addTask",	
			type: "POST",	
			data: data,	
			success: function(html){
				console.log(html)
				let list = html.substring(html.indexOf("[")+1,html.indexOf("]")).split(",");
				console.log(list)
				var str = "";
				for (let code of list) {
					str +="<li>"+code+"</li>";
				}
                $('#list').html(str);
			}
		})
	}
	
	function execTask(){
		$.ajax({
			url: "../VerifyActionServlet/execTask",	
			type: "POST",	
			success: function(html){
				
			}
		})
	}
	
	//当前任务列表
    var flag = true;

    function show() {
        if (flag) {
            $('#list').css('display', 'block');
            flag = false;
        } else {
            $('#list').css('display', 'none');
            flag = true;
        }
    }
	
	</script>
	
</body>
</html>
