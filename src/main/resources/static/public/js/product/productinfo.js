var editor;
var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            key: null,
            status: null
        },
        showList: true,
        title: null,
        brandId: "",
        productInfo: {},
        brandList: [],
        defaultImgUrl : "http://pa23ubi36.bkt.clouddn.com/upload/20180611/98eca8e408c14c10b7049403d1385269",
        statusSelect:[
            {id:"10",name:"新创建"},
            {id:"20",name:"已上架"},
            {id:"30",name:"已下架"}
        ],
        btn: null
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reset: function () {
            $("#searchKey").val("");
            vm.q.key = "";
            vm.q.status = "";
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.productInfo = {};
            vm.brandId = "";
            document.getElementById("upload").style.backgroundImage="url('"+vm.defaultImgUrl+"')";

            editor.txt.html("");
            //获取品牌信息
            this.getBrandList();
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            this.getBrandList();
            vm.getInfo(id);
        },
        getBrandList: function () {
            $.get("../productbrand/queryAll", function (r) {
                vm.brandList = r.list;
            });
        },
        saveOrUpdate: function (event) {
            vm.productInfo.brandId = vm.brandId;
            vm.productInfo.brandName = $("#selected option:selected").text();
            var url = vm.productInfo.id == null ? "../productinfo/save" : "../productinfo/update";
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.productInfo),
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
        del: function (event) {
            vm.btn = event.target.id;
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: "../productinfo/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
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
            });
        },
        getInfo: function (id) {
            $.get("../productinfo/info/" + id, function (r) {
                vm.productInfo = r.productInfo;
               // vm.productInfoEntity = r.productInfo.productInfoEntity;
                editor.txt.html(r.productInfo.content);
                vm.brandId = vm.productInfo.brandId;
                $("#selected option:selected").text = vm.productInfo.brandName ;
                $("#upload").css("background-image", "url(" + vm.productInfo.img + ")");
            });
        },
        comment: function () {
            var id = getSelectedRow();
            var gr = jQuery("#jqGrid").jqGrid('getRowData', id);
            if (id == null) {
                return;
            }
            var url = "../comment/commentproduct.html?productId="+id +"&productName="+gr.name;
            // encodeURI 编码
            window.location.assign(encodeURI(url));
        },
        onShelves: function(){
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }
            sheleves(ids, 20);
        },
        offShelves: function(){
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }
            sheleves(ids, 30);
        },
        reload: function (event) {
            vm.showList = true;
            var page = (vm.btn == "del")?1: $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'key': vm.q.key,
                    "status":vm.q.status
                },
                page: page
            }).trigger("reloadGrid");
        }
    }
});
//商品上下架
function sheleves(ids, status){
    $.ajax({
        type: "POST",
        url: "../productinfo/shelves",
        contentType:"application/json",
        data: JSON.stringify({
            "ids" : ids,
            "status" : status
        }),
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
}

$(function () {
    $("#jqGrid").jqGrid({
        url: '../productinfo/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true},
            {label: '名称', name: 'name', index: 'name', width: 80},
            {label: '编号', name: 'productNum', index: 'product_num', width: 50},
            {label: '规格', name: 'productSpecifications', index: 'product_specifications', width: 50},
            {label: '售价', name: 'amountShow', index: 'amount_show', width: 60,
                formatter: function (value, options, row) {
                    return value + '元';
                }
            },
            {label: '优惠价', name: 'amount', index: 'amount', width: 60,
                formatter: function (value, options, row) {
                    return value + '元';
                }
            },
            {label: '品牌', name: 'brandName', index: 'brand_name', width: 80},
            {label: '桶类型', name: 'bucketType', index: 'bucket_type', width: 80,
                formatter: function (value, options, row) {
                    if (value === 10) {
                        return '一次性桶装水';
                    } else if (value === 20) {
                        return '循环桶装水'
                    } else {
                        return '瓶装水';
                    }
                }
            },
            {label: '饮用水类型', name: 'waterType', index: 'water_type', width: 60,
                formatter: function (value, options, row) {
                    if (value === 10) {
                        return '矿泉水';
                    } else if (value === 20) {
                        return '山泉水'
                    } else {
                        return '纯净水';
                    }
                }
            },
            {label: '配送费', name: 'deliveryFee', index: 'delivery_fee', width: 50,
                formatter: function (value, options, row) {
                    return value + '元';
                }
            },
            {label: '一键送水', name: 'isQuick', index: 'isQuick', width: 50,
                formatter: function (value, options, row) {
                    return value === 10 ?
                        '<font color="green">是</font>' :
                        '<font color="red">否</font>';
                }
            },
            {label: '状态', name: 'status', index: 'status', width: 50,
                formatter: function (value, options, row) {
                    if (value === 10) {
                        return '<font color="red">新建</font>';
                    } else if (value === 20) {
                        return '<font color="green">已上架</font>'
                    } else {
                        return '<font color="gray">已下架</font>';
                    }
                }
            },
            {label: '排序', name: 'sort', index: 'sort', width: 40}
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

    // 初始化图片上传
    new AjaxUpload('#upload', {
        action: '../sys/oss/upload',
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r.code == 0) {
                $("#upload").css("background-image", "url(" + r.url + ")");
                vm.productInfo.img = r.url;
            } else {
                alert(r.msg);
            }
        }
    });

    // 初始化富文本编辑器
    var E = window.wangEditor
    editor = new E('#editor')
    editor.customConfig.uploadImgServer = '../sys/oss/editor/upload'
    editor.customConfig.uploadFileName = 'file'
    editor.customConfig.uploadImgMaxSize = 1 * 1024 * 1024
    // 限制一次最多上传 1 张图片
    editor.customConfig.uploadImgMaxLength = 1
    editor.customConfig.onchange = function (html) {
        // 监控变化，同步更新到 vm 对象
        vm.productInfo.content = html;
    }
    editor.create()

});

//只能输入两位小数
function checkDecimal(data) {
    var re = /([0-9]+\.[0-9]{2})[0-9]*/;
    var num = data.replace(re,"$1");
    return num;
}