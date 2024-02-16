
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

	//////////////////////////////////////////////////////////////

	$scope.getSumCatesByMonth = function(year) {
		return new Promise(function(resolve, reject) {

			var cate = [];
			var cateID = [];

			$http.get('/sumCategory', {
				params: {
					year: year
				}
			}).then(function(resp) {
				var rep = resp.data;
				console.log(rep)
				for (var i = 0; i < rep.length; i++) {
					cate.push(rep[i][1]);    // Pushes the string at index 0 to 'cate'
					cateID.push(rep[i][0]);  // Pushes the number at index 1 to 'cateID'
				}
				resolve({ cate: cate, cateID: cateID }); // Trả về một đối tượng chứa cả hai mảng
				console.log(cate, cateID);
			}, function(error) {

				reject(error);
			});
		});
	};

	$scope.chart = function() {
		$scope.getSumCatesByMonth(2023).then(function(data) {
			var totalIncomeChart = document.getElementById('totalIncomeChart').getContext('2d');
			console.log(data)
			var mytotalIncomeChart = new Chart(totalIncomeChart, {
				type: 'bar',
				data: {
					labels: data.cateID, // Sử dụng mảng cateID làm nhãn
					datasets: [{
						label: "Đơn hàng/Tháng",
						backgroundColor: '#272eff',
						borderColor: 'rgb(23, 125, 255)',
						data: data.cate,
					}],
				},
				options: {
					// Các tùy chọn khác của biểu đồ
				}
			});
		}).catch(function(error) {
			console.error('Error:', error);
		});
	};

	// Call loadProducts to initialize data	$scope.chart();
	$scope.loadProducts();
	$scope.chart();
});