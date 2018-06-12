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
			//{ label: '用于点对点登录时的推送，由APP在登录的时候一起上传', name: 'clientId', index: 'clientId', width: 80 },
			{
				label: '当前状态',
				name: 'status',
				index: 'status',
				width: 80,
                formatter: function (value, options, row) {
                    if (value === 10) {
                        return '<font color=green>可配送</font>';
                    } else if (value === 20) {
                        return '<font color="red">不可配送</font>'
                    }
                }
			},
			//{ label: '身份证号（备用）', name: 'identifycation', index: 'identifycation', width: 80 },
			{ label: '身份证照片地址', name: 'identifycationUrl', index: 'identifycation_url', width: 80 }, 			
			{ label: '健康证地址', name: 'healthUrl', index: 'health_url', width: 80 }, 			
			{ label: '所属配送点', name: 'deliveryEndpointName', index: 'delivery_endpoint_name', width: 80 }
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
        deliveryEndpointId:"",
		deliveryDistributor: {
		},
        deliveryEndpointList: []
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.deliveryDistributor = {};
			//获取配送点信息
            this.getDeliveryEndpointList();
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id);
            //获取配送点信息
            this.getDeliveryEndpointList();
            for (var i=0;i<vm.deliveryEndpointList.length;i++)
            {
                if(vm.deliveryEndpointList[i].id===vm.deliveryDistributor.deliveryEndpointId){//循环找出配送员所属配送点
                    vm.deliveryEndpointId = vm.deliveryDistributor.deliveryEndpointId ;
                    $("#selected option:selected").text = vm.deliveryDistributor.deliveryEndpointName ;
                    break;
                }
            }
		},
		saveOrUpdate: function (event) {
            vm.deliveryDistributor.deliveryEndpointId = vm.deliveryEndpointId;
            vm.deliveryDistributor.deliveryEndpointName = $("#selected option:selected").text();
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
            });
		},
        comment: function () {
            var id = getSelectedRow();
            var gr = jQuery("#jqGrid").jqGrid('getRowData', id);
            if (id == null) {
                return;
            }
            var url = "../comment/commentdelivery.html?deliveryDistributorId="+id +"&deliveryDistributorName="+gr.name;
            // encodeURI 编码
            window.location.assign(encodeURI(url));
        },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'key': vm.q.key},
                page:page
            }).trigger("reloadGrid");
		},
		getDeliveryEndpointList:function () {
            $.get("../deliveryendpoint/select", function(r){
                vm.deliveryEndpointList = r.deliveryEndpointEntities;
                //alert(r.deliveryEndpointEntities[0].id)
            });
        }
	}
});