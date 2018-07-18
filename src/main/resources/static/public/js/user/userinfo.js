$(function () {
    $("#jqGrid").jqGrid({
        url: '../userinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '用户名', name: 'username', index: 'username', width: 80 },
			{ label: '手机号', name: 'phone', index: 'phone', width: 80 },
			{ label: '微信 ID', name: 'uid', index: 'uid', width: 80 },
			{ label: '状态', name: 'status', index: 'status', width: 80 },
			{ label: '总押金金额', name: 'depositAmount', index: 'deposit_amount', width: 80 }, 			
			/*{ label: '可用押金金额', name: 'enableDepositAmount', index: 'enable_deposit_amount', width: 80 },
			{ label: '不可用押金金额', name: 'disableDepositAmout', index: 'disable_deposit_amount', width: 80 },
			*/{ label: '持有空桶数', name: 'emptyBucketNumber', index: 'empty_bucket_number', width: 80 },
			{ label: '注册时间', name: 'creationTime', index: 'creation_time', width: 80 }
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
        q:{
            key: null
        },
		showList: true,
		title: null,
		userInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
        reset: function () {
            $("#searchKey").val("");
            vm.q.key = "";
            vm.reload();
        },
		detail: function(){
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.title = "详情";
            vm.getInfo(id)
		},
        emptyBucketFlow: function () {
            var userInfoId = getSelectedRow();
            if (userInfoId == null) {
                return;
            }
            var url = "../user/useremptybucketflow.html?userInfoId="+userInfoId;
            // encodeURI 编码
            window.location.assign(encodeURI(url));
        },
		getInfo: function(id){
			$.get("../userinfo/info/"+id, function(r){
                vm.userInfo = r.userInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			// var page = $("#jqGrid").jqGrid('getGridParam','page');
			var page = 1;
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'key': vm.q.key},
                page:page
            }).trigger("reloadGrid");
		}
	}
});