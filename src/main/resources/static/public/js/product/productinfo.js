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
		productInfo: {},
        brandList: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.productInfo = {};

			// 初始化富文本编辑器
            var E = window.wangEditor
            var editor = new E('#editor')
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
			// 初始化图片上传
            new AjaxUpload('#upload', {
                action: '../sys/oss/upload',
                name: 'file',
                autoSubmit:true,
                responseType:"json",
                onSubmit:function(file, extension){
                    if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
                        alert('只支持jpg、png、gif格式的图片！');
                        return false;
                    }
                },
                onComplete : function(file, r){
                    if(r.code == 0){
                        $("#upload").css("background-image","url("+r.url+")");
                        vm.productInfo.img = r.url;
                    }else{
                        alert(r.msg);
                    }
                }
            });

            //获取品牌信息
            this.getBrandList();
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
            //获取品牌信息
            this.getBrandList();
		},
        getBrandList: function(){
            $.get("../productbrand/queryAll", function(r){
                vm.brandList = r.list;
            });
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
                // 初始化富文本编辑器
                var E = window.wangEditor
                var editor = new E('#editor')
                editor.customConfig.uploadImgServer = '../sys/oss/editor/upload'
                editor.customConfig.uploadFileName = 'file'
                editor.customConfig.uploadImgMaxSize = 1 * 1024 * 1024
                // 限制一次最多上传 1 张图片
                editor.customConfig.uploadImgMaxLength = 1
                editor.customConfig.onchange = function (html) {
                    // 监控变化，同步更新到 vm 对象
                    vm.productInfo.content = html;
                }
                editor.create();
                editor.txt.html(r.productInfo.content);
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