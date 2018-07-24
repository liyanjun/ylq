$(function () {
    $("#jqGrid").jqGrid({
        url: '../userproductticketflow/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '用户id', name: 'userId', index: 'user_id', width: 80 }, 			
			{ label: '用户名', name: 'username', index: 'username', width: 80 }, 			
			{ label: '用户水票id', name: 'userProductTicketId', index: 'user_product_ticket_id', width: 80 }, 			
			{ label: '关联商品 ID', name: 'productInfoId', index: 'product_info_id', width: 80 }, 			
			{ label: '关联商品名', name: 'productName', index: 'product_name', width: 80 }, 			
			{ label: '使用数量', name: 'usedCount', index: 'used_count', width: 80 }, 			
			{ label: '剩余数量', name: 'remainderCount', index: 'remainder_count', width: 80 }, 			
			{ label: '使用时间', name: 'creationTime', index: 'creation_time', width: 80 }			
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
            userName: null
        },
		showList: true,
		title: null,
		userProductTicketFlow: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
        reset: function () {
            $("#searchKey").val("");
            vm.q.userName = "";
            vm.reload();
        },
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.userProductTicketFlow = {};
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
			var url = vm.userProductTicketFlow.id == null ? "../userproductticketflow/save" : "../userproductticketflow/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.userProductTicketFlow),
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
				    url: "../userproductticketflow/delete",
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
			$.get("../userproductticketflow/info/"+id, function(r){
                vm.userProductTicketFlow = r.userProductTicketFlow;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'userName': vm.q.userName},
                page:page
            }).trigger("reloadGrid");
		}
	}
});