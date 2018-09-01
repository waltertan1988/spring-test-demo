<@layout.admin title="出错啦">
错误信息：${exception.message}
<p>
	<#list exception.stackTrace as e>
		${e}<br>
	</#list>
</p>
</@layout.admin>