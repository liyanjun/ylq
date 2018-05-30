$(function () {
    $("#jqGrid").jqGrid({
        url: '../productinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '商品名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '标价', name: 'amountShow', index: 'amount_show', width: 80 }, 			
			{ label: '实价', name: 'amount', index: 'amount', width: 80 }, 			
			{ label: '商品品牌 ID', name: 'brandId', index: 'brand_id', width: 80 }, 			
			{ label: '商品品牌', name: 'brandName', index: 'brand_name', width: 80 }, 			
			{ label: '桶类型，10：一次性桶，20：可回收桶', name: 'bucketType', index: 'bucket_type', width: 80 }, 			
			{ label: '配送费', name: 'deliveryFee', index: 'delivery_fee', width: 80 }, 			
			{ label: '商品创建时间', name: 'creationTime', index: 'creation_time', width: 80 }, 			
			{ label: '商品最后一次更新时间', name: 'updateTime', index: 'update_time', width: 80 }, 			
			{ label: '商品创建人 ID', name: 'creatorId', index: 'creator_id', width: 80 }, 			
			{ label: '商品创建人名', name: 'creatorName', index: 'creator_name', width: 80 }, 			
			{ label: '商品更新人ID', name: 'updatorId', index: 'updator_id', width: 80 }, 			
			{ label: '商品更新人名', name: 'updatorName', index: 'updator_name', width: 80 }			
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
		productInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.productInfo = {};
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
			var url = vm.productInfo.id == null ? "../productinfo/save" : "../productinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.productInfo),
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
				    url: "../productinfo/delete",
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
			$.get("../productinfo/info/"+id, function(r){
                vm.productInfo = r.productInfo;
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