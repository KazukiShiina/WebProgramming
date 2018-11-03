<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User" import="dao.UserDao" %>

<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ユーザ情報詳細参照</title>
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
<h1 align="center">ユーザ情報詳細参照</h1>
<br/><br/>
<div class="box" align="left">
 <div class="box1"style="float:left;">
  &emsp;&emsp;&emsp;&emsp;ログインID<br/><br/>
  &emsp;&emsp;&emsp;&emsp;ユーザ名<br/><br/>
  &emsp;&emsp;&emsp;&emsp;生年月日<br/><br/>
  &emsp;&emsp;&emsp;&emsp;登録日時<br/><br/>
  &emsp;&emsp;&emsp;&emsp;更新日時
 </div>

 <div class="box2"style="float:right;">
 <div>${userDetail.loginId}</div><br/><div>${userDetail.name}</div><br/><div>${userDetail.birthDate}</div><br/>
 <div>${userDetail.createDate}</div><br/><div>${userDetail.updateDate}</div><br><br><div>パスワード:${userDetail.password}</div>
 </div>
</div>
<div align="center">&emsp;<br><br><br><br><br><br><br>
<div align="left">
    <a  href="UserListServlet">戻る</a>
</div>
</body>
</html>