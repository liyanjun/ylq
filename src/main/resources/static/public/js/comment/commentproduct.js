$(function () {
    vm.productId = T.p("productId");
    vm.productName = T.p("productName");
    var param = "";
    if(vm.productId != undefined){
        param = "?productId=" + vm.productId;
    }
    $("#jqGrid").jqGrid({
        url: '../commentproduct/list'+param,
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden:true },
            { label: '用户id', name: 'userId', index: 'user_id', width: 80 },
            { label: '用户名', name: 'userName', index: 'user_name', width: 80 },
            { label: '商品id', name: 'productId', index: 'product_id', width: 80 },
			{
			    label: '商品名',
                name: 'productName',
                index: 'product_name',
                width: 80 ,
                hidden: vm.productId==undefined?true:false,
                formatter: function (cellValue) {
                    return vm.productName;
                }
            },
			{ label: '评论内容', name: 'comment', index: 'comment', width: 80 },
			{ label: '打分', name: 'level', index: 'level', width: 80 },
			{ label: '评论时间', name: 'creationTime', index: 'creation_time', width: 80 }			
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
        productId:"",
        productName:"",
		title: null,
		commentProduct: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
        info: function (event) {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.title = "详情";

            vm.getInfo(id)
        },
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.commentProduct = {};
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
			var url = vm.commentProduct.id == null ? "../commentproduct/save" : "../commentproduct/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.commentProduct),
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
				    url: "../commentproduct/delete",
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
			$.get("../commentproduct/info/"+id, function(r){
                vm.commentProduct = r.commentProduct;
                vm.fnShow(vm.commentProduct.level)
            });
		},
        fnShow: function(num){
            var lis = document.getElementsByTagName("li");
            for (var i = 0; i < lis.length; i++) {
                lis[i].className = i < num? "light" : "";//点亮星星就是加class为light的样式
            }
        },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
        btnback: function () {
            window.location.href="../product/productinfo.html";
        }
	}
});