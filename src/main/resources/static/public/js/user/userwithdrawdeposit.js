$(function () {
    $("#jqGrid").jqGrid({
        url: '../userwithdrawdeposit/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '对应用户 ID', name: 'userInfoId', index: 'user_info_id', width: 80 }, 			
			{
				label: '是否处理',
				name: 'isHandle',
				index: 'is_handle',
				width: 80,
                formatter: function (value, options, row) {
                    return value === 10 ?
                        '<font color="red">未处理</font>' :
                        '<font color="green">已处理</font>';
                }
			},
			{ label: '创建时间', name: 'creationTime', index: 'creation_time', width: 80 }, 			
			{ label: '处理时间', name: 'handleTime', index: 'handle_time', width: 80 }			
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
		userWithdrawDeposit: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		handle: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}

			confirm('确定要处理选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../userwithdrawdeposit/handle",
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
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});