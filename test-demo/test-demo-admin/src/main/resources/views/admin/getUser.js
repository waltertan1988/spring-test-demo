var getUser = (function($) {
    return {
    	init: function() {
    		$("#searchBtn").click(function(){
    			$("#getUserForm").ajaxSubmit({
    				success:function(userList){
    					var tplData = {"content":userList};
    					$("#userInfo").html($("#userInfoTpl").tmpl(tplData));
    				}
    			});
    		});
    	}
    };
} (jQuery));

$(function() {
	getUser.init();
});