app.controller('statisticalProduct-ctrl', function($scope, $http) {
    $scope.products = [];

    $scope.loadData = function() {
        $http.get('/reportProduct').then(function(response) {
            $scope.products = response.data.slice(0, 10); // Lấy chỉ 10 phần tử đầu tiên
        });
    };

    $scope.loadData();
});
