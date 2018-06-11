$(function () {
    $("#jqGrid").jqGrid({
        url: '../commentdelivery/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '商品品牌名称', name: 'deliveryDistributorId', index: 'delivery_distributor_id', width: 80 }, 			
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

var vm;
vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        commentDelivery: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        info: function (event) {
            /*var id = getSelectedRow();
            if(id == null){
                return ;
            }*/
            vm.showList = false;
            vm.title = "详情";

            vm.getInfo(id)
        },
        //TODO 评论用textarea,打分用星星

        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: "../commentdelivery/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (id) {
            $.get("../commentdelivery/info/" + id, function (r) {
                vm.commentDelivery = r.commentDelivery;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        }
    }
});