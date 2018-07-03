$(function () {
    $("#jqGrid").jqGrid({
        url: '../productstock/list',
        datatype: "json",
        colModel: [			
			{ label: '商品id', name: 'productInfoId', index: 'product_info_id', width: 80 },
			{ label: '商品名称', name: 'productName', index: 'product_name', width: 80 },
			{ label: '配送点编号', name: 'deliveryEndpointId', index: 'delivery_endpoint_id', width: 80 },
			{ label: '配送点名', name: 'deliveryName', index: 'delivery_name', width: 80 }, 			
			{ label: '库存数', name: 'count', index: 'count', width: 80 }			
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
            productName: null,
			deliveryEndpointName: null
        },
		showList: true,
		title: null,
		productStock: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
        reset: function () {
            $("#productName").val("");
            $("#endpointName").val("");
            vm.q.productName = "";
            vm.q.deliveryEndpointName = "";
            vm.reload();
        },
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.productStock = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            $("#toFlow").attr("href","productstockflow.html?"+id);
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.productStock.id == null ? "../productstock/save" : "../productstock/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.productStock),
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
				    url: "../productstock/delete",
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
			$.get("../productstock/info/"+id, function(r){
                vm.productStock = r.productStock;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                	'productName': vm.q.productName,
					'deliveryEndpointName': vm.q.deliveryEndpointName
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});