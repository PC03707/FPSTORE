app.controller("product-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};
	$scope.image = {};
	$scope.proofreads = {};


	$scope.initialize = function() {
		//load product
		$http.get("/rest/products").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)
			})
		});

		//load categories
		$http.get("/rest/categories").then(resp => {
			$scope.cates = resp.data;
		});

		//load auth
		$http.get("/rest/authors").then(resp => {
			$scope.auth = resp.data;
		});

	}

	//Khởi đầu
	$scope.initialize();

	//Xóa form
	$scope.reset = function() {
		$scope.form = {}
		$scope.image = {}
	}

	//Hiển thị lên form
	$scope.edit = function(item) {

		$scope.form = angular.copy(item)
		$(".nav-tabs a:eq(0)").tab('show')
	}




	//Thêm sản phẩm mới
	$scope.create = function() {
		var item = angular.copy($scope.form);
		console.log(item);
		$http.post(`/rest/products`, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.initialize();
			$scope.reset();
			Swal.fire("Success", "Thêm sách thành công!", "success");
		}).catch(error => {
			Swal.fire("Error", "Thêm sách thất bại!", "error");
			console.log("Error", error);
		})
	}

	//Cập nhật sản phẩm
	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			$scope.initialize();
			Swal.fire("Success", "Cập nhật thành công!", "success");
		}).catch(error => {
			Swal.fire("Error", "Cập nhật thất bại!", "error");
			console.log("Error", error);
		})
	}

	//Xóa sản phẩm
	$scope.delete = function(item) {
		$http.delete(`/rest/products/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xóa sản phẩm thành công")
		}).catch(error => {
			alert("Lỗi xóa sản phẩm");
			console.log("Error", error);
		})
	}

	//Upload hình ảnh
	//	$scope.imageChanged = function(files) {
	//		var data = new FormData();
	//		data.append('file', files[0]);
	//		$http.post('/rest/upload/book', data, {
	//			transformRequest: angular.identity,
	//			headers: { 'Content-Type': undefined }
	//		}).then(resp => {
	//			$scope.form.image = resp.data.name;
	//		}).catch(error => {
	//			alert("Lỗi upload hình ảnh");
	//			console.log("Error", error);
	//		})
	//	}
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/book', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name;
			if (files && files.length > 0) {
				$scope.form.imageName = files[0].name;
			} else {
				$scope.form.imageName = null;
			}
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}






	//addnhieuanhsanpham
	$scope.editanh = function(item) {
		$http.get(`/rest/images/${item}`).then(resp => {
			$scope.itemsimages = resp.data;
			console.log(resp.data)

			$(".nav-tabs a:eq(2)").tab('show')

		});

	}

	$scope.editanhphu = function(item) {
		$scope.image = angular.copy(item)
		window.scrollTo({ top: 0, behavior: 'smooth' });
	}

	$scope.createimage = function() {
		var image = angular.copy($scope.image);
		console.log(image);
		$http.post(`/rest/images`, image).then(resp => {
			$scope.reset();
			$scope.editanh(image.book.id)
			Swal.fire("Success", "Thêm ảnh sách thành công!", "success");
		}).catch(error => {
			Swal.fire("Error", "Thêm ảnh sách thất bại!", "error");
			console.log("Error", error);
		})
	}

	$scope.updateimage = function() {
		var item = angular.copy($scope.image);
		$http.put(`/rest/images/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			$scope.editanh(item.book.id)
			Swal.fire("Success", "Cập nhật thành công!", "success");
		}).catch(error => {
			Swal.fire("Error", "Cập nhật thất bại!", "error");
			console.log("Error", error);
		})
	}




	//Xóa sản phẩm
	$scope.deleteimage = function(item) {
		$http.delete(`/rest/images/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.editanh(item.book.id)
			$scope.reset();
			alert("Xóa sản phẩm thành công")
		}).catch(error => {
			alert("Lỗi xóa sản phẩm");
			console.log("Error", error);
		})
	}



	//Upload hình ảnh
	$scope.imageChangedadd = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/bookdec', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.image.link = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}




	//ảnh đọc thử

	$scope.editproofreads = function(item) {
		$http.get(`/rest/proofread/${item}`).then(resp => {
			$scope.itemsproofreads = resp.data;
			console.log(resp.data)

			$(".nav-tabs a:eq(3)").tab('show')

		});

	}



	$scope.editfreads = function(item) {
		$scope.proofreads = angular.copy(item)
		window.scrollTo({ top: 0, behavior: 'smooth' });
	}

	$scope.imageChangedproofreads = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/proofread', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.proofreads.images = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}

	$scope.createproofreads = function() {
		var proofreads = angular.copy($scope.proofreads);
		$http.post(`/rest/proofread`, proofreads).then(resp => {
			$scope.reset();
			$scope.editproofreads(proofreads.book.id)
			Swal.fire("Success", "Thêm ảnh sách thành công!", "success");
		}).catch(error => {
			Swal.fire("Error", "Thêm ảnh sách thất bại!", "error");
			console.log("Error", error);
		})
	}

	$scope.deleteproofreads = function(item) {
		$http.delete(`/rest/proofread/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.editproofreads(item.book.id);
			$scope.reset();
			alert("Xóa sản phẩm thành công")
		}).catch(error => {
			alert("Lỗi xóa sản phẩm");
			console.log("Error", error);
		})
	}

	$scope.updateproofreads = function() {
		var item = angular.copy($scope.proofreads);
		$http.put(`/rest/proofread/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			$scope.editproofreads(item.book.id);
			Swal.fire("Success", "Cập nhật thành công!", "success");
		}).catch(error => {
			Swal.fire("Error", "Cập nhật thất bại!", "error");
			console.log("Error", error);
		})
	}




	//	$scope.upload = function(files) {
	//		var proofreads = new FormData();
	//		for (var i = 0; i < files.length; i++) {
	//			proofreads.append("files", files[i]);
	//			
	//		}
	//		$http.post("/rest/files/proofread", proofreads, {			
	//			transformRequest: angular.identity,
	//			headers: { 'Content-Type': undefined }
	//			
	//		}).then(resp => {
	//			$scope.filenames.push(...resp.data);
	//			alert("Ok")
	//		}).catch(error => {
	//			alert("aLỗiaa"),
	//			console.log("Error", error);
	//		});
	//
	//
	//	};
















	//phân trang
	$scope.pager = {
		page: 0,
		size: 6,
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
});