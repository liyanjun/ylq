$(function () {
    $("#jqGrid").jqGrid({
        url: '../userrecommendapproval/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '用户id', name: 'userId', index: 'user_id', width: 80 }, 			
			{ label: '用户名', name: 'username', index: 'username', width: 80 }, 			
			// { label: '身份证正面照片', name: 'positiveIdPhoto', index: 'positive_id_photo', width: 80 },
			// { label: '身份证反面照片', name: 'reverseIdPhoto', index: 'reverse_id_photo', width: 80 },
			{ label: '验证手机号码', name: 'phone', index: 'phone', width: 80 }, 			
			{ label: '个人能力', name: 'remarkPersonalAbility', index: 'remark_personal_ability', width: 80 },
			{ label: '申请时间', name: 'applicationTime', index: 'application_time', width: 80 }, 			
			{ label: '审批时间', name: 'approveTime', index: 'approve_time', width: 80 }, 			
			{ label: '审批意见', name: 'approveOpinion', index: 'approve_opinion', width: 80 }, 			
			{ label: '是否通过审批', name: 'isApproved', index: 'is_approved', width: 80,
                formatter: function (value, options, row) {
                    if (value === 10) {
                        return '<font color="green">通过</font>';
                    } else if (value === 20) {
                        return '<font color="red">未通过</font>'
                    } else {
                        return "尚未审批"
                    }
                }
			}
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
            userName: null
        },
		showList: true,
		title: null,
		userRecommenderApproval: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
        reset: function () {
            $("#searchKey").val("");
            vm.q.userName = "";
            vm.reload();
        },
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.userRecommenderApproval = {};
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
			var url = vm.userRecommenderApproval.id == null ? "../userrecommendapproval/save" : "../userrecommendapproval/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.userRecommenderApproval),
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
				    url: "../userrecommendapproval/delete",
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
			$.get("../userrecommendapproval/info/"+id, function(r){
                vm.userRecommenderApproval = r.userRecommenderApproval;
                $("#uploadPositiveIdPhoto").css("background-image", "url(" + vm.userRecommenderApproval.positiveIdPhoto + ")");
                $("#uploadReverseIdPhoto").css("background-image", "url(" + vm.userRecommenderApproval.reverseIdPhoto + ")");
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'userName': vm.q.userName},
                page:page
            }).trigger("reloadGrid");
		}
	}
});