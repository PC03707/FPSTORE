app.controller("account-ctrl", function ($scope, $http) {

	$scope.items = [];
	$scope.form = {};
	$scope.roles = {};
	
	
	
	$scope.initialize = function () {
		$http.get('/rest/accounts').then(function (response) {
			$scope.items = response.data;
		});
	}


	$scope.edit = function (item) {
		$scope.form = angular.copy(item);
		$scope.index = -1;
		window.scrollTo({
			top: 0,
			behavior: 'smooth'
		});
	}

	$scope.reset = function () {
		$scope.form = {};
		$scope.index = 0;
		window.scrollTo({
			top: 0,
			behavior: 'smooth'
		});
	}
	$scope.imageChanged = function (files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/user', data, {
			transformRequest: angular.identity,
			headers: {
				'Content-Type': undefined
			}
		}).then(resp => {
			$scope.form.photo = resp.data.name;
			Swal.fire({
				type: 'success',
				title: 'Thêm ảnh thành công',
				text: '',
				icon: "success",
				showConfirmButton: false,
				timer: 2000
			  })
		}).catch(error => {
			Swal.fire({
				type: 'error',
				title: 'Lỗi thêm ảnh',
				text: error,
				icon: "error",
				showConfirmButton: false,
				timer: 2000
			  })
			console.log("Error", error);
		})
	}
	
	$scope.reset_smooth_table = function () {
		$scope.form = {};
		window.scrollTo({
			top: 1000,
			behavior: 'smooth'
		});
	}

	$scope.create = function () {
		var item = angular.copy($scope.form);
		$http.post(`/rest/accounts`, item).then(resp => {
			$scope.initialize();
			Swal.fire({
				type: 'success',
				title: 'Thêm thành công',
				text: 'Người dùng được sắp xếp theo tên',
				icon: "success",
				showConfirmButton: false,
				timer: 2000
			  })
			$scope.reset_smooth_table();
		}).catch(error => {			
			Swal.fire({
				type: 'error',
				title: 'Lỗi thêm người dùng',
				text: error,
				icon: "error",
				showConfirmButton: false,
				timer: 2000
			  })
			console.log("Error", error);
		})
		
	}

	// Cập nhật
	$scope.update = function () {
		var item = angular.copy($scope.form);
		$http.put(`/rest/accounts/${item.username}`, item).then(function (response) {
			var index = $scope.items.findIndex(p => p.username == item.username);
			$scope.items[index] = item;
			Swal.fire({
				type: 'success',
				title: 'Cập nhật thành công',
				text: 'Thông tin người dùng đã được cập nhật',
				icon: "success",
				showConfirmButton: false,
				timer: 2000
			  })
			$scope.reset_smooth_table();
		}).catch(function (error) {
			Swal.fire({
				type: 'error',
				title: 'Lỗi cập nhật thông tin người dùng',
				text: error,
				icon: "error",
				showConfirmButton: false,
				timer: 2000
			  })
			console.log("Erorr", err);
		})
	}

	// Xóa
	$scope.delete = function (item) {
		
		
		
		$http.delete(`/rest/accounts/${item.username}`).then(function (response) {
			var index = $scope.items.findIndex(p => p.username == item.username);
			$scope.items.splice(index, 1);
			$scope.reset();
			Swal.fire({
				title: 'Xóa người dùng!',
				text: "Bạn chắc chắn muốn xóa người dùng này chứ ?",
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
		}).catch(function (err) {
			Swal.fire({
				type: 'error',
				title: 'Lỗi xóa người dùng',
				text: 'Người dùng đang ở trạng thái hoạt động !',
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