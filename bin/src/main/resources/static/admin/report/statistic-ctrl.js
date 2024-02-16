
app.controller('statistic-ctrl', function($scope, $http) {
    $scope.items = [];
    $scope.products = [];
    $scope.pager = {
        page: 0,
        size: 5,
        get items(){
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count(){
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
        first(){
			this.page = 0;
		},
		prew(){
			this.page--; 
			if(this.page < 0){
				this.last();
			}
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		},
		last(){
			this.page = this.count - 1;
		}
    };
    
    $scope.startDate = new Date().toISOString().split('T')[0];
    $scope.endDate = new Date().toISOString().split('T')[0];

    $scope.loadProducts = function() {
        // Gửi yêu cầu đến API để lấy dữ liệu
        $http.get('/reports', {
            params: {
                startDate: formatDate($scope.startDate),
                endDate: formatDate($scope.endDate)
            }
        }).then(function(response) {
            console.log(response.data);
            $scope.items = response.data;
            $scope.pager.count = Math.ceil($scope.items.length / $scope.pager.size);
        }, function(error) {
            console.log(error);
        });
    };
    
    function formatDate(date) {
        // Hàm này để định dạng ngày thành yyyy-MM-dd để gửi đến server
        var d = new Date(date);
        var month = '' + (d.getMonth() + 1);
        var day = '' + d.getDate();
        var year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day].join('-');
    }
    
    // Load dữ liệu lúc trang web được khởi tạo
    $scope.loadProducts();
});
