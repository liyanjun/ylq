$(function () {
    $("#jqGrid").jqGrid({
        url: '../orderinfo/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true},
            {
                label: '订单金额', name: 'amount', index: 'amount', width: 80,
                formatter: function (value, options, row) {
                    return value + "元";
                }
            },
            {
                label: '订单状态', name: 'status', index: 'status', width: 80,
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
                    }
                }
            },
            {
                label: '订单配送状态', name: 'type', index: 'type', width: 80,
                formatter: function (value, options, row) {
                    if (value === 10) {
                        return '<font color="green">正常</font>';
                    } else if (value === 20) {
                        return '<font color="red">异常</font>'
                    } else {
                        return '未知';
                    }
                }
            },
            {label: '配送员', name: 'deliveryDistributorName', index: 'delivery_distributor_name', width: 80},
            {label: '用户名', name: 'username', index: 'username', width: 80},
            {label: '下单时间', name: 'creationTime', index: 'creation_time', width: 80}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        showDetail: false,//显示配送单和订单商品信息
        title: null,
        orderInfo: {},
        orderDeliveryInfo: {},
        orderProductList: []
    },
    methods: {
        query: function () {
            vm.reload();
        },
        detail: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.showDetail = true;
            vm.title = "详情";
            vm.getInfo(id);
            vm.getOrderDeliveryInfo(id);
            vm.getOrderProductInfo(id);
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        handDistribute: function () {
            var id = getSelectedRow();
            var gr = jQuery("#jqGrid").jqGrid('getRowData', id);
            if (id == null) {
                return;
            }
            var url = "./ordersubmit.html?orderId="+id
            // encodeURI 编码
            window.location.assign(encodeURI(url));
        },
        handle: function (event) {
            var remark = prompt("请填写手工处理备注信息", "")
            $.ajax({
                type: "POST",
                url: "../orderinfo/handle?orderId=" + vm.orderInfo.id + "&remark=" + remark,
                contentType: "application/json",
                data:{
                    "orderId":vm.orderInfo.id,
                    "remark":remark
                },
                success: function (r) {
                    if (r.code == 0) {
                        alert('操作成功', function (index) {
                            window.location.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.orderInfo.id == null ? "../orderinfo/save" : "../orderinfo/update";
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.orderInfo),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        getInfo: function (id) {
            $.get("../orderinfo/info/" + id, function (r) {
                vm.orderInfo = r.orderInfo;
            });
        },
        getOrderDeliveryInfo: function (id) {
            $.get("../orderdeliveryinfo/infoByOrderId/" + id, function (r) {
                vm.orderDeliveryInfo = r.orderDeliveryInfo;
            });
        },
        getOrderProductInfo: function (id) {
            /*$.get("../orderproductdetail/listByOrderId/"+id, function(r){
                vm.orderProductList = r.orderProductList;
            });*/
            $("#jqGridProduct").jqGrid({
                url: '../orderproductdetail/listByOrderId',
                postData: {
                    'id': id
                },
                datatype: "json",
                colModel: [
                    {label: 'id', name: 'productInfoId', index: 'productInfoId', width: 50, key: true},
                    {label: '商品名称', name: 'productName', index: 'product_name', width: 80},
                    {label: '商品数量', name: 'count', index: 'count', width: 80},
                ],
                viewrecords: true,
                height: 385,
                width: 600,
                rowNum: 10,
                rowList: [10, 30, 50],
                rownumbers: true,
                rownumWidth: 25,
                autowidth: false,
                multiselect: false,
                pager: "#jqGridProductPager",
                jsonReader: {
                    root: "page.list",
                    page: "page.currPage",
                    total: "page.totalPage",
                    records: "page.totalCount"
                },
                prmNames: {
                    page: "page",
                    rows: "limit",
                    order: "order"
                },
                gridComplete: function () {
                    //隐藏grid底部滚动条
                    $("#jqGridProduct").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
                }
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