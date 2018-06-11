$(function () {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1);
    $("#jqGrid").jqGrid({
        url: '../productstockflow/list',
        datatype: "json",
        postData:{'productStockId': r},
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
            { label: '库存变动类型', name: 'type', index: 'type', width: 80 ,
                formatter: function (value, options, row) {
                    return value === 0 ?
                        '添加' :
                        '减少';
                }},
			{ label: '变动数至', name: 'count', index: 'count', width: 80 },
			{ label: '改变前值', name: 'beforeCount', index: 'before_count', width: 80 },
			{ label: '改变后值', name: 'afterCount', index: 'after_count', width: 80 }, 			
			{ label: '库存变动时间', name: 'creationTime', index: 'creation_time', width: 80 }, 			
			{ label: '操作人名称', name: 'operator', index: 'operator', width: 80 },
			{ label: '操作人ID', name: 'operatorId', index: 'operator_id', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		productStockFlow: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function (event) {
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'productStockId': r[0]},
                page:page
            }).trigger("reloadGrid");
		}
	}
});