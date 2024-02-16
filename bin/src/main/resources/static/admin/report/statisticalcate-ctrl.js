
app.controller('statisticalcate-ctrl', function($scope, $http) {
    $scope.items = []; // Initialize items array
    $scope.pager = {
        page: 0,
        count: 0,
        pageSize: 10, // Số mục trên mỗi trang
        first: function() {
            this.page = 0;
            $scope.loadProducts();
        },
        prew: function() {
            if (this.page > 0) {
                this.page--;
                $scope.loadProducts();
            }
        },
        next: function() {
            if (this.page < this.count - 1) {
                this.page++;
                $scope.loadProducts();
            }
        },
        last: function() {
            this.page = this.count - 1;
            $scope.loadProducts();
        }
    };

    $scope.loadProducts = function() {
        var url = '/reportCate';
        
        $http.get(url)
            .then(function(response) {
                $scope.items = response.data; // Assign the response data to items
                $scope.pager.count = Math.ceil($scope.items.length / $scope.pager.pageSize);
            })
            .catch(function(error) {
                console.error('Error loading data:', error);
            });
    };

    // Call loadProducts to initialize data
    $scope.loadProducts();
});