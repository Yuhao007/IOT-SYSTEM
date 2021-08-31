//jqGrid
$.jgrid.defaults.width = 1000;
$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';

//Tools Collection Tools
window.T = {};

// Get the request parameters
// An example
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
};
T.p = url;

//Global configuration
$.ajaxSetup({
	dataType: "json",
	contentType: "application/json",
	cache: false
});

//Rewrite  alert
window.alert = function(msg, callback){
	parent.layer.alert(msg, function(index){
		parent.layer.close(index);
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//Rewrite style box
window.confirm = function(msg, callback){
	parent.layer.confirm(msg, {btn: ['Sure','Cancel']},
	function(){
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}
 layui.use('laydate', function () {
        var laydate = layui.laydate;
    });

function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("Please select a record");
    	return ;
    }

    var selectedIDs = grid.getGridParam("selarrrow");
    if(selectedIDs.length > 1){
    	alert("Only one record can be selected");
    	return ;
    }

    return selectedIDs[0];
}

function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("Please select a record");
    	return ;
    }

    return grid.getGridParam("selarrrow");
}

$(function () {
    $(window).resize(
        function () {
            window.parent.height(document.body.clientHeight)
        }
    ).resize();

    var i = 10;
    var inter = setInterval(function () {
        window.parent.height(document.body.clientHeight)
        i--;
        if(i == 0){
            clearInterval(inter)
        }
    },100)

})

function fmt(type) {
    var dw, t;
    if (type == 1) {
        dw = ''
        t = ' Temperature '
    }
    if (type == 2) {
        dw = ''
        t = ' Humidity '
    }
    if (type == 3) {
        dw = ''
        t = ' Accelerated speedX '
    }
    if (type == 4) {
        dw = ''
        t = ' CO2 '
    }
    if (type == 5) {
        dw = ''
        t = ' Accelerated speed Y '
    }
    if (type == 6) {
        dw = ''
        t = ' Accelerated speed Z '
    }


    return {
        dw: dw, type: t
    }
}