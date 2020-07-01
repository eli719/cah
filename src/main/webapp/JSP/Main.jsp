<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>页面抓取操作界面</title>
  </head>
  
  <body>
    <div align="center">
      <table>
         <tr><td><a href="./JSP/Console.jsp">WEB验证码网页抓取页面</a></td></tr>
         <tr><td><a href="./JSP/VeryAuto.jsp">WEB无验证码网页抓取页面</a></td></tr>
      </table>
    </div>
  </body>
</html>
