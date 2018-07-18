$(function () {
    vm.productId = T.p("productId");
    vm.productName = T.p("productName");
    var param = "";
    if(vm.productId != undefined){
        param = "?productId=" + vm.productId;
    }
    $("#jqGrid").jqGrid({
        url: '../productticket/list'+param,
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '水票编号', name: 'productTicketNum', index: 'product_ticket_num', width: 80 }, 			
			// { label: '水票图片(备用)', name: 'img', index: 'img', width: 80 },
			{ label: '标题', name: 'title', index: 'title', width: 80 },
			// { label: '副标题', name: 'subtitle', index: 'subtitle', width: 80 },
			// { label: '关联产品 ID', name: 'productInfoId', index: 'product_info_id', width: 80 },
			{ label: '关联商品名称', name: 'productInfoName', index: 'product_info_name', width: 90 },
			// { label: '购买桶数', name: 'purchaseCount', index: 'purchase_count', width: 80 },
			// { label: '赠送桶数', name: 'giftCount', index: 'gift_count', width: 80 },
			{ label: '起送桶数', name: 'minDilivery', index: 'min_dilivery', width: 80 },
			{ label: '水币', name: 'waterCoin', index: 'water_coin', width: 80 },
			{ label: '状态', name: 'status', index: 'status', width: 60 ,
                formatter: function (value, options, row) {
                    if (value === 10) {
                        return '新创建';
                    } else if (value === 20) {
                        return '上架'
                    } else {
                        return '下架';
                    }
                }
			},
			{ label: '包含产品数量', name: 'cout', index: 'cout', width: 90 },
			{ label: '价格', name: 'amout', index: 'amout', width: 60 },
			// { label: '使用须知', name: 'notes', index: 'notes', width: 80 },
			// { label: '使用说明', name: 'instructions', index: 'instructions', width: 80 },
			// { label: '创建时间', name: 'creationTime', index: 'creation_time', width: 80 },
			{ label: '使用截止日期', name: 'deadline', index: 'deadline', width: 90 },
			{ label: '备注', name: 'remarks', index: 'remarks', width: 80 }			
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
        productId:"",
        productName:"",
		productTicket: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.productTicket = {};
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
			var url = vm.productTicket.id == null ? "../productticket/save" : "../productticket/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.productTicket),
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
				    url: "../productticket/delete",
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
			$.get("../productticket/info/"+id, function(r){
                vm.productTicket = r.productTicket;
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