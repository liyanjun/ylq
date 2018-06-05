$(function () {
    $("#jqGrid").jqGrid({
        url: '../deliverydistributor/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '姓名', name: 'name', index: 'name', width: 80 },
			{ label: '手机号', name: 'phone', index: 'phone', width: 80 },
			//{ label: '登录密码', name: 'password', index: 'password', width: 80 },
			{ label: '生日', name: 'birthday', index: 'birthday', width: 80 },
			{ label: '用于点对点登录时的推送，由APP在登录的时候一起上传', name: 'clientId', index: 'clientId', width: 80 },
			{ label: '当前状态，10：可配送，20：不可配送', name: 'status', index: 'status', width: 80 }, 			
			{ label: '身份证号（备用）', name: 'identifycation', index: 'identifycation', width: 80 }, 			
			{ label: '身份证照片地址', name: 'identifycationUrl', index: 'identifycation_url', width: 80 }, 			
			{ label: '健康证地址', name: 'healthUrl', index: 'health_url', width: 80 }, 			
			{ label: '所属配送点', name: 'deliveryEndpointId', index: 'delivery_endpoint_id', width: 80 }			
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
		deliveryDistributor: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.deliveryDistributor = {};
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
			var url = vm.deliveryDistributor.id == null ? "../deliverydistributor/save" : "../deliverydistributor/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.deliveryDistributor),
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
				    url: "../deliverydistributor/delete",
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
			$.get("../deliverydistributor/info/"+id, function(r){
                vm.deliveryDistributor = r.deliveryDistributor;
                //获取下拉列表对象
                var selections = document.getElementById("selections");

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