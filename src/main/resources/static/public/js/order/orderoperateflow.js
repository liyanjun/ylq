$(function () {
    $("#jqGrid").jqGrid({
        url: '../orderoperateflow/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{
				label: '处理类型',
				name: 'type',
				index: 'type',
				width: 80,
                formatter: function (value, options, row) {
                    return value === 10 ?
                        '手工选定配送员' :
                        '取消订单';
                }
			},
			{ label: '手工处理前状态', name: 'beforeStatus', index: 'before_status', width: 80 ,
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
                    } else if(value == 60){
                        return "已评论";
                    }
                }},
			{ label: '手工处理后状态', name: 'afterStatus', index: 'after_status', width: 80 ,
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
                    } else if(value == 60){
                        return "已评论";
                    }
                }},
            { label: '订单ID', name: 'orderId', index: 'order_id', width: 80 },
			{ label: '操作时间', name: 'operatorTime', index: 'operator_time', width: 80 },
			{ label: '操作人', name: 'operatorName', index: 'operator_name', width: 80 }
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
            orderId: null
        },
		showList: true,
		title: null,
		orderOperateFlow: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
        reset: function () {
            $("#searchKey").val("");
            vm.q.orderId = "";
            vm.reload();
        },
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../orderoperateflow/delete",
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
			$.get("../orderoperateflow/info/"+id, function(r){
                vm.orderOperateFlow = r.orderOperateFlow;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'orderId': vm.q.orderId},
                page:page
            }).trigger("reloadGrid");
		}
	}
});