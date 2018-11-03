<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User" import="dao.UserDao" %>

<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ユーザ削除確認</title>
    <link rel="stylesheet"  type="text/css" href="/UserManagement/css/BoxSizing.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
    integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  </head>
  <body>
   <!-- header -->
    <header>
     <div class="head1"> ${userInfo.name} さん
       <a class="head2" href="LogoutServlet">ログアウト</a>
     </div>
    </header>
    <!-- /header -->
<h1 align="center">ユーザ削除確認</h1>
<br/><br/>
<div class="left">ログインID:${userDetail.loginId}さん<br/>
を本当に削除してよろしいでしょうか。
</div>
<div class="box">
<div class="box1" style="float:left;">
<div align="center">&emsp;&emsp;&emsp;&emsp;<br><br><br>
  <a class="cb" href="UserListServlet"><button type="submit" style="width:120px;height:30px;" ><font size="3">
  </font>キャンセル</button></a>
</div>
</div>
<div class="box2" style="float:right;">
<form action="UserDeleteServlet" method="post">
 <input type="hidden" name="id" value="${userDetail.id}">
<div align="center">&emsp;&emsp;&emsp;&emsp;<br><br><br>
 <button type="submit" style="width:120px;height:30px;"><font size="3">
  </font>OK</button>
</div>
</form>
</body>
</html>