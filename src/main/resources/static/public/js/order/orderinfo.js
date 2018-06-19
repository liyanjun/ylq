$(function () {
    $("#jqGrid").jqGrid({
        url: '../orderinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单金额', name: 'amount', index: 'amount', width: 80 },
            {
            	label: '订单状态',
				name: 'status',
				index: 'status',
				width: 80,
                formatter: function (value, options, row) {
                    if (value === 10) {
                        return '新创建';
                    } else if (value === 20) {
                        return '已支付，待配送'
                    } else if (value == 30) {
                        return '配送中';
                    } else if (value == 40) {
                        return '已到达';
                    } else if (value == 50) {
                        return '关闭';
                    } else if (value == 60) {
                        return '人工处理';
                    } else if (value == 70) {
                        return '人工派单';
                    }
                }
			},
            { label: '关联配送员名', name: 'deliveryDistributorName', index: 'delivery_distributor_name', width: 80 },
			{ label: '用户名', name: 'username', index: 'username', width: 80 },
			{ label: '订单创建时间', name: 'creationTime', index: 'creation_time', width: 80 }
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
        showDetail: false,//显示配送单和订单商品信息
		title: null,
		orderInfo: {},
        orderDeliveryInfo: {},
        orderProductList: []
	},
	methods: {
		query: function () {
			vm.reload();
		},
		detail: function(){
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
			vm.showList = false;
            vm.showDetail = true;
			vm.title = "详情";
			vm.getInfo(id);
			vm.getOrderDeliveryInfo(id);
			vm.getOrderProductInfo(id);
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
			$.get("../orderinfo/info/"+id, function(r){
                vm.orderInfo = r.orderInfo;
            });
		},
        getOrderDeliveryInfo: function(id){
            $.get("../orderdeliveryinfo/infoByOrderId/"+id, function(r){
                vm.orderDeliveryInfo = r.orderDeliveryInfo;
            });
        },
        getOrderProductInfo:function(id){
            /*$.get("../orderproductdetail/listByOrderId/"+id, function(r){
                vm.orderProductList = r.orderProductList;
            });*/
            $("#jqGridProduct").jqGrid({
                url: '../orderproductdetail/listByOrderId',
                postData:{
                    'id': id
                },
                datatype: "json",
                colModel: [
                    { label: 'id', name: 'id', index: 'id', width: 50, key: true },
                    { label: '商品名称', name: 'productName', index: 'product_name', width: 80 },
                    { label: '商品数量', name: 'count', index: 'count', width: 80 },
                ],
                viewrecords: true,
                height: 385,
                width:500,
                rowNum: 10,
                rowList : [10,30,50],
                rownumbers: true,
                rownumWidth: 25,
                autowidth:false,
                multiselect: false,
                pager: "#jqGridProductPager",
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
                    $("#jqGridProduct").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
                }
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