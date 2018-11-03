<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ新規登録</title>
<link rel="stylesheet"  type="text/css" href="/UserManagement/css/BoxSizing.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
    integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
 <header>
     <div class="head1"> ${userInfo.name} さん
       <a class="head2" href="LogoutServlet">ログアウト</a>
     </div>
    </header>
<h1>ユーザ新規登録</h1>
	<br>
	<c:if test="${errMsg != null}" >
	    <div class="alert alert-danger" role="alert">
		  ${errMsg}
		</div>
	</c:if>

	<br><br>
<div class="box" align="left">
 <div class="box1"style="float:left;">
  &emsp;&emsp;&emsp;&emsp;ログインID<br/><br/>
  &emsp;&emsp;&emsp;&emsp;パスワード<br/><br/>
  &emsp;&emsp;&emsp;&emsp;パスワード(確認)<br/><br/>
  &emsp;&emsp;&emsp;&emsp;ユーザ名<br/><br/>
  &emsp;&emsp;&emsp;&emsp;生年月日
 </div>


 <div class="box2"style="float:right;">
 <form class="form-userCreate" action="UserCreateServlet" method="post">
 <input type="text" name="loginId" autofocus required><br/><br/>
 <input type="password" name="password" required><br/><br/>
 <input type="password" name="rePassword" required><br/><br/>
 <input type="text" name="name" required><br/><br/>
 <input type="date" name="birthDate" value='2014-03-18' required>
 <div align="center">&emsp;&emsp;&emsp;&emsp;<br><br><br>
  <button type="submit" style="width:120px;height:30px;" > <font size="3">登録</font> </button>
</div>
 </form>
 </div>
</div>
<div align="center">&emsp;<br><br><br><br><br><br><br>
<div align="left">
    <a  href="UserListServlet">戻る</a>
</body>
</html>