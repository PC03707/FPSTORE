app.controller("statis-ctrl", function($scope, $http) {

	$scope.initialize = function() {
		$http.get("/rest/products/demslsp").then(resp => {
			$scope.slsp = resp.data;
		});
		$http.get("/rest/products/demslkh").then(resp => {
			$scope.slkh = resp.data;
		});
		$http.get("/rest/products/demsldh").then(resp => {
			$scope.sldh = resp.data;
		});
		$http.get("/rest/products/demslcd").then(resp => {
			$scope.slcd = resp.data;
		});
		$http.get("/rest/products/ddhanghna").then(resp => {
			$scope.ddhanghna = resp.data;
		});
		$http.get("/rest/products/tongtienhomnay").then(resp => {
			$scope.tongtienhomnay = resp.data;
		});
		$http.get("/rest/products/tongthunhap").then(resp => {
			$scope.tongthunhap = resp.data;
		});

	}


	$scope.showOrderDetail = function(orderId) {
		$http.get("/rest/orderDetails/" + orderId)
			.then(function(response) {
				$("#donhangdanggiao").modal("hide");
				$("#dagiao").modal("hide");
				$("#dahuy").modal("hide");
				$scope.selectedOrderDetails = response.data;
				$('#orderDetailModal').modal('show'); // Hiển thị modal chứa danh sách sản phẩm
			})
			.catch(function(error) {
				console.error("Error fetching order details:", error);
			});
	};
	$scope.closeModal = function() {
		$("#orderDetailModal").modal("hide");
	};


	$scope.initialize();
});