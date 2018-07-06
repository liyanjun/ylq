$(function () {
    vm.orderId = T.p("orderId");
    vm.getDeliveryEndpointList();
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        title: null,
        deliveryEndpointId: null,
        deliveryDistributorId: null,
        deliveryEndpoint: {},
        deliveryEndpointList: [],
        deliveryDistributorList: []
    },
    methods: {
        summit: function (event) {
            var url = "../orderinfo/handDistribute?deliveryEndpointId=" + vm.deliveryEndpointId + "&deliveryDistributorId=" + vm.deliveryDistributorId + "&orderId=" + vm.orderId;
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
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
        getDeliveryEndpointList: function () {
            $.get("../deliveryendpoint/select", function (r) {
                vm.deliveryEndpointList = r.deliveryEndpointEntities;
            });
        },
        reload: function () {
            window.location.href="../order/orderinfo.html?orderId="+vm.orderId;
        }
    }
});

function changeDeliveryEndpointSelect(deliveryEndpointId) {
    $.get("../deliverydistributor/select?deliveryEndpointId=" + deliveryEndpointId, function (r) {
        vm.deliveryDistributorList = r.deliveryDistributorEntities;
    });
}