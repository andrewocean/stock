<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
<script type="text/javascript">
	function showMsg(msg){	
		var uid = document.getElementById('toast_msg');
		uid.innerHTML = msg;
	}
	
	function doOnIDBlur(){
		showMsg('check for id');
	}
	
	function doOnPSWBlur(){
		
		showMsg('check for psw');
	}
	
	function checkNull(value){
		return value;
	}
	
	function doOnLogin(){		
		var uid = document.getElementById('uid');
		var upsw = document.getElementById('upsw');
		
       var successMsg =   '<font style="color:#6F0">Log in success</font>';
       var failedMsg =   '<font style="color:#F00">Log in failed</font>';
	
		if( checkNull(uid.value) && checkNull(upsw.value)){
			showMsg(successMsg.toString());
		 }else{
			showMsg(failedMsg.toString());
		 }
		
}
	
	function doOnLogout(){
		var uid = document.getElementById('uid');
		var upsw = document.getElementById('upsw');
		uid.value = '';
		upsw.value = '';
		showMsg('Log out success!');
	}
	
	function refreshPage(){
			window.location.reload();
	}
	
</script>
</head>
<body>
<table border="2">
<td align="center"><div class="section">User information<br>
          ID:
          <input name="" type="text" id="uid" />
          <br>
          Password:
          <input name="" type="text" id="upsw"/>
          <br>
          <input name=""  value="login" type="button" id="btn_login"  onclick="doOnLogin()"/>
          <input name=""  value="logout" type="button" id="btn_logout"  onclick="doOnLogout()"/>
          <div id="toast_msg" />
        </div></td>
        </table>
</body>
</html>