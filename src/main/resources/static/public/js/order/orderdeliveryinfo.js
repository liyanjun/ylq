$(function () {
    $("#jqGrid").jqGrid({
        url: '../orderdeliveryinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '收货人姓名', name: 'name', index: 'name', width: 80 },
			{ label: '用户手机号', name: 'phone', index: 'phone', width: 80 },
			{
				label: '配送单状态',
				name: 'status',
				index: 'status',
				width: 80,
                formatter: function (value, options, row) {
                    if (value === 10) {
                        return '未支付';
                    } else if (value === 20) {
                        return '未分配'
                    } else if (value == 30) {
                        return '分配中';
                    } else if (value == 40) {
                        return '配送中';
                    } else if (value == 50) {
                        return '配送结束';
                    } else if (value == 60) {
                        return '异常';
                    } else if (value == 70) {
                        return '';
                    }
                }
			},
			{ label: '配送单创建时间', name: 'creationTime', index: 'creation_time', width: 80 },
			{ label: '期望配送时间', name: 'deliveryTime', index: 'delivery_time', width: 80 }, 			
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
		orderDeliveryInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.orderDeliveryInfo = {};
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
			var url = vm.orderDeliveryInfo.id == null ? "../orderdeliveryinfo/save" : "../orderdeliveryinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.orderDeliveryInfo),
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
				    url: "../orderdeliveryinfo/delete",
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
			$.get("../orderdeliveryinfo/info/"+id, function(r){
                vm.orderDeliveryInfo = r.orderDeliveryInfo;
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