<@layout.admin title="getUser" scripts="/admin/getUser.js">
<form id="getUserForm" action="${base}/admin/getUserData" method="post">
	<label>姓名：</label><input type="text" id="userRealName" name="userRealName"/>
	<input type="button" id="searchBtn" value="查询"/>
</form>

<table id="userInfo" style="border-collapse:collapse" border="1"></table>

<script id="userInfoTpl" type="text/x-jquery-tmpl">
<#noparse>
{{if content.length > 0}}
	<thead>
		<th>账号</th>
		<th>姓名</th>
		<th>性别</th>
		<th>出生日期</th>
	</thead>
	<tbody>
		{{each(i,user) content}}
			<tr>
				<td>${user.username}</td>
				<td>${user.userRealName}</td>
				<td>{{if user.gender=='M'}}男{{else}}女{{/if}}</td>
				<td>${user.birthday}</td>
			</tr>
		{{/each}}
	</tbody>
{{/if}}
</#noparse>
</script>
</@layout.admin>