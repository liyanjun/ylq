$(function () {
    vm.userInfoId = T.p('userInfoId');
    var param = "";
    if(vm.userInfoId != undefined){
        param = "?userInfoId=" + vm.userInfoId;
    }
    $("#jqGrid").jqGrid({
        url: '../useremptybucketflow/list' + param,
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden:true },
            { label: '用户 ID', name: 'userInfoId', index: 'user_info_id', width: 80 },
            { label: '用户名', name: 'userName', index: 'username', width: 80 },
			{ label: '流水类型', name: 'type', index: 'type', width: 80 ,
                formatter: function (value, options, row) {
                    if (value === 10) {
                        return '归还空桶';
                    } else if (value === 20) {
                        return '获取空桶'
                    }
                }
			},
			{ label: '流水前空桶数', name: 'beforeEmptyBucket', index: 'before_empty_bucket', width: 80 }, 			
			{ label: '流水后空桶数', name: 'afterEmptyBucket', index: 'after_empty_bucket', width: 80 }, 			
			{ label: '操作空桶数', name: 'emptyBucketNumber', index: 'empty_bucket_number', width: 80 },
			{ label: '操作关联 ID', name: 'operatorId', index: 'operator_id', width: 80 },
			{ label: '流水时间', name: 'creationTime', index: 'creation_time', width: 80 }			
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
		userEmptyBucketFlow: {},
        userInfoId: null
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
        btnback: function () {
            window.location.href="../user/userinfo.html";
        }
	}
});