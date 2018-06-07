$(function () {
    $("#jqGrid").jqGrid({
        url: '../userdeposit/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '对应用户 ID', name: 'userInfoId', index: 'user_info_id', width: 80 }, 			
			{ label: '押金总额', name: 'depositAmount', index: 'deposit_amount', width: 80 }, 			
			{ label: '有效的押金', name: 'enableDepositAmount', index: 'enable_deposit_amount', width: 80 }, 			
			{ label: '无效的押金', name: 'disableDepositAmount', index: 'disable_deposit_amount', width: 80 },
			{ label: '用户持有空桶数', name: 'emptyBucketNumber', index: 'empty_bucket_number', width: 80 }			
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
		userDeposit: {}
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
            vm.title = "详情";

            vm.getInfo(id)
		},
		/*add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.userDeposit = {};
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
			var url = vm.userDeposit.id == null ? "../userdeposit/save" : "../userdeposit/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.userDeposit),
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
				    url: "../userdeposit/delete",
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
		},*/
		getInfo: function(id){
			$.get("../userdeposit/info/"+id, function(r){
                vm.userDeposit = r.userDeposit;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'key': vm.q.key},
                page:page
            }).trigger("reloadGrid");
		}
	}
});