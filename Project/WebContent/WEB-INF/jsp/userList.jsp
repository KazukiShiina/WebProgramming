<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ユーザ一覧画面</title>
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

    <!-- body -->
    <h1>ユーザ一覧</h1>
 <div class="migi">
  <a href="/UserManagement/UserCreateServlet">新規登録</a>&emsp;
 </div>
 <div class="box" align="left">
  	<div class="box1"style="float:left;">
    		ログインID<br/><br/>
     	ユーザ名<br/><br/>
     	生年月日<br/><br/>
  	</div>
 	<div class="box2"style="float:right;">
  		<form method="post" action="#" class="form-horizontal">
     			<input style="width:305px;" type="text" class="loginId" name="loginId"><br/><br/>
     		    <input style="width:305px;" type="text" class="userNm" name="userNm"><br/><br/>
      		<div style="display:inline-flex">
      			<input style="width:145px;" type="date" class="birthday" name="birthdate">〜
     		    <input style="width:145px;" type="date" class="birthday" name="birthdate2">
        		<div class="text-right">
                  <button type="submit" value="検索" class="btn btn-primary form-submit">検索</button>
      		</div></div>
  		</form>
  	</div>
  &emsp;&emsp;
  <br>
  <hr width="600" align="center">
  <br>

 <div align="left" class="maintable">
  <table border="1" cellspacing="0" bordercolor="#000000" style="width:800px;" >
   <thead>
       <tr>
          <th>ログインID</th><th>ユーザ名</th><th>生年月日</th><th></th>
       <tr>
   </thead>



      <tbody>
         <c:forEach var="user" items="${userList}" > <!-- forEachによる繰り返し -->
           <tr>
              <td>${user.loginId}</td>
              <td>${user.name}</td>
              <td>${user.birthDate}</td>
            <!-- ；ログインボタンの表示制御を行う -->

		<c:choose>
         	<c:when test="${userInfo.loginId != 'admin'}" >
              <td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                <a class="btn btn-primary" href="UserDetailServlet?id=${user.id}">詳細</a>&emsp;&emsp;&emsp;&emsp;
              		<c:if test="${userInfo.loginId == user.loginId}" >
                			<a class="btn btn-success" href="UserUpdateServlet?id=${user.id}">更新</a>&emsp;&emsp;&emsp;&emsp;
                		</c:if>
              </td>
             </c:when>
             <c:otherwise>
              <td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                <a class="btn btn-primary" href="UserDetailServlet?id=${user.id}">詳細</a>&emsp;&emsp;&emsp;&emsp;
                <a class="btn btn-success" href="UserUpdateServlet?id=${user.id}">更新</a>&emsp;&emsp;&emsp;&emsp;
                <a class="btn btn-danger" href ="UserDeleteServlet?id=${user.id}">削除</a>
              </td>
        		</c:otherwise>
        	</c:choose>
            </tr>
          </c:forEach>
        </tbody>
    </table>
  </div>
  </div>

  </body>
</html>
