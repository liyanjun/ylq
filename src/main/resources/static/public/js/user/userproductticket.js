$(function () {
    vm.userId = T.p("userId");
    var param = "";
    if(vm.userId != undefined){
        param = "?userId=" + vm.userId;
    }
    $("#jqGrid").jqGrid({
        url: '../userproductticket/list' +param,
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
            { label: '用户名', name: 'userName', index: 'username', width: 80 },
            // { label: '水票 ID', name: 'productTicketId', index: 'product_ticket_id', width: 80 },
            // { label: '水票编号', name: 'productTicketNum', index: 'product_ticket_num', width: 80 },
            { label: '水票标题', name: 'productTicketTitle', index: 'product_ticket_title', width: 80 },
            // { label: '水票副标题', name: 'productTicketSubtitle', index: 'product_ticket_subtitle', width: 80 },
            //{ label: '关联产品 ID', name: 'productId', index: 'product_id', width: 80 },
            { label: '产品名称', name: 'productName', index: 'product_name', width: 80 },
            { label: '总共兑换数量', name: 'totalCount', index: 'total_count', width: 80 },
            // { label: '已使用数量', name: 'useCount', index: 'use_count', width: 80 },
            { label: '剩余数量', name: 'remainderCount', index: 'remainder_count', width: 80 },
            { label: '水票状态', name: 'status', index: 'status', width: 80,
                formatter: function (value, options, row) {
                    if (value === 10) {
                        return '待支付';
                    } else if (value === 20) {
                        return '已支付'
                    } else if (value === 30) {
                        return '兑付完毕'
                    } else {
                        return '已关闭';
                    }
                }
            },
            // { label: '兑付结束时间', name: 'finishTime', index: 'finish_time', width: 80 },
            { label: '过期时间', name: 'endTime', index: 'end_time', width: 80 },
            // { label: '创建时间', name: 'creationTime', index: 'creation_time', width: 80 },
            // { label: '获赠水币的用户id', name: 'benifitUserId', index: 'benifit_user_id', width: 80 },
            { label: '获赠用户名', name: 'benifitUsername', index: 'benifit_username', width: 80 },
            { label: '送出桶数', name: 'benifitCount', index: 'benifit_count', width: 80 },
            // { label: '赠送水币的用户id', name: 'fromUserId', index: 'from_user_id', width: 80 },
            { label: '赠送用户名', name: 'fromUsername', index: 'from_username', width: 80 },
            { label: '得到桶数', name: 'fromCount', index: 'from_count', width: 80 }
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
        q: {
            userName: null,
            productTicketTitle: null,
            productName: null,
            status: null
        },
        userId:"",
		showList: true,
		title: null,
		userProductTicket: {},
        statusSelect:[
            {id:"10",name:"待支付"},
            {id:"20",name:"已支付"},
            {id:"30",name:"兑付完毕"},
            {id:"30",name:"已关闭"}
        ]
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.userProductTicket = {};
		},
        reset: function () {
            $("#userName").val("");
            $("#productTicketTitle").val("");
            $("#productName").val("");
            $("#statusSelect").val("");
            vm.q.userName = "";
            vm.q.productTicketTitle = "";
            vm.q.productName = "";
            vm.q.status = "";
            vm.reload();
        },
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.userProductTicket.id == null ? "../userproductticket/save" : "../userproductticket/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.userProductTicket),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../userproductticket/delete",
				    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								window.location.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../userproductticket/info/"+id, function(r){
                vm.userProductTicket = r.userProductTicket;
            });
		},
        detail: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.title = "详情";

            vm.getInfo(id)
        },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    userName: vm.q.userName,
                    productTicketTitle: vm.q.productTicketTitle,
                    productName: vm.q.productName,
                    status: vm.q.status
                },
                page:page
            }).trigger("reloadGrid");
		},
        btnback: function () {
            window.location.href="userinfo.html";
        }
	}
});