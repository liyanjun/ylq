$(function () {
    $("#jqGrid").jqGrid({
        url: '../orderinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单总额', name: 'amountTotal', index: 'amount_total', width: 80 }, 			
			{ label: '订单金额', name: 'amount', index: 'amount', width: 80 }, 			
			{ label: '订单折扣优惠金额', name: 'amountBenifit', index: 'amount_benifit', width: 80 }, 			
			{ label: '订单活动优惠金额（即除了优惠标价外，使用的活动奖励）', name: 'amountActivity', index: 'amount_activity', width: 80 }, 			
			{ label: '订单配送费', name: 'amountDeliveryFee', index: 'amount_delivery_fee', width: 80 }, 			
			{ label: '订单状态，10：新创建，20：已支付，待配送，30：配送中，40：已送达，50已关闭', name: 'status', index: 'status', width: 80 }, 			
			{ label: '关联配送员 ID', name: 'deliveryDistributorId', index: 'delivery_distributor_id', width: 80 }, 			
			{ label: '关联配送员名', name: 'deliveryDistributorName', index: 'delivery_distributor_name', width: 80 }, 			
			{ label: '关联用户 ID', name: 'userInfoId', index: 'user_info_id', width: 80 }, 			
			{ label: '用户名', name: 'username', index: 'username', width: 80 }, 			
			{ label: '订单备注', name: 'remark', index: 'remark', width: 80 }, 			
			{ label: '订单创建时间', name: 'creationTime', index: 'creation_time', width: 80 }, 			
			{ label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 }			
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
		orderInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.orderInfo = {};
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
			var url = vm.orderInfo.id == null ? "../orderinfo/save" : "../orderinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.orderInfo),
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
				    url: "../orderinfo/delete",
				    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../orderinfo/info/"+id, function(r){
                vm.orderInfo = r.orderInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});