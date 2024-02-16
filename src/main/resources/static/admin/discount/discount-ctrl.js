app.controller("discount-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};
	$scope.roles = {};



	$scope.initialize = function() {
		$http.get('/rest/bestseller').then(function(response) {
			$scope.items = response.data;
			$scope.items.forEach(item => {
				$scope.items.forEach(item => {
					item.startdate = new Date(item.startdate);
					item.enddate = new Date(item.enddate);
				})
			})


		});

		$http.get('/rest/products').then(function(response) {
			$scope.product = response.data;

		});
	}




	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$scope.index = -1;
		window.scrollTo({
			top: 0,
			behavior: 'smooth'
		});
	}

	$scope.reset = function() {
		$scope.form = {
			startdate: new Date(),
		};

	}


	$scope.reset_smooth_table = function() {
		$scope.form = {};
		window.scrollTo({
			top: 1000,
			behavior: 'smooth'
		});
	}

	$scope.create = function() {

		var itema = angular.copy($scope.form);

		var item = $scope.items.find(item => item.book.id == itema.book.id);

		if (item != null) {
			Swal.fire({
					type: 'error',
					title: 'Thêm không thành công',
					text: 'Lỗi trùng mã sản phẩm',
					icon: "error",
					showConfirmButton: false,
					timer: 2000
				})
		} else {
			$http.post(`/rest/bestseller`, item).then(resp => {
				$scope.items.push(resp.data);
				$scope.initialize();
				$scope.reset();
				Swal.fire({
					type: 'success',
					title: 'Thêm mã thành công',
					text: 'Thêm mã thành công',
					icon: "success",
					showConfirmButton: false,
					timer: 2000
				})
				$scope.reset_smooth_table();
			}).catch(error => {
				Swal.fire({
					type: 'error',
					title: 'Lỗi thêm mã',
					text: error,
					icon: "error",
					showConfirmButton: false,
					timer: 2000
				})
				console.log("Error", error);
			})


		}



	}

	// Cập nhật
	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/bestseller/${item.id}`, item).then(function(response) {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			Swal.fire({
				type: 'success',
				title: 'Cập nhật  thành công',
				text: 'Thông mã đã được cập nhật',
				icon: "success",
				showConfirmButton: false,
				timer: 2000
			})
			$scope.reset_smooth_table();
		}).catch(function(error) {
			Swal.fire({
				type: 'error',
				title: 'Lỗi cập nhật mã',
				text: error,
				icon: "error",
				showConfirmButton: false,
				timer: 2000
			})
			console.log("Erorr", err);
		})
	}

	// Xóa
	$scope.delete = function(item) {
		$http.delete(`/rest/bestseller/${item.id}`).then(function(response) {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			Swal.fire({
				title: 'Xóa sản phẩm giảm giá!',
				text: "Bạn chắc chắn muốn chứ ?",
				type: 'warning',
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Vâng, Tôi đồng ý!'
			}).then(function() {
				Swal.fire(
					'Deleted!',
					'Đã xóa thành công',
					'success'
				);
			})
		}).catch(function(err) {
			Swal.fire({
				type: 'error',
				title: 'Lỗi xóa ',
				text: 'Xóa không thành công !',
				icon: "error",
				showConfirmButton: false,
				timer: 2000
			})
			console.log("Erorr", err);
		})
	}

	$scope.pager = {
		page: 0,
		size: 4,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}

	}

	$scope.initialize();
})