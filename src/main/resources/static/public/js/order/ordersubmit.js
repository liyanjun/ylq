$(function () {
    vm.orderId = T.p("orderId");
    vm.getDeliveryEndpointList();
});

var vm = new Vue({
    el:'#rrapp',
    data:{
        title: null,
        deliveryEndpointId : null,
        deliveryDistributorId : null,
        deliveryEndpoint: {},
        deliveryEndpointList : [],
        deliveryDistributorList : []
    },
    methods: {
        summit: function (event) {
            var url = "../order/orderSubmmit";
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: {
                    "deliveryEndpointId":vm.deliveryEndpointId,
                    "deliveryDistributorId":vm.deliveryDistributorId,
                    "orderId":vm.orderId
                },
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            //vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        getDeliveryEndpointList:function () {
            $.get("../deliveryendpoint/select", function(r){
                vm.deliveryEndpointList = r.deliveryEndpointEntities;
            });
        },
    }
});
function changeDeliveryEndpointSelect(deliveryEndpointId) {
    $.get("../deliverydistributor/select?deliveryEndpointId="+deliveryEndpointId, function(r){
        vm.deliveryDistributorList = r.deliveryDistributorEntities;
    });
}