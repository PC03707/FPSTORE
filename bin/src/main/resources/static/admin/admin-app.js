app = angular.module("admin-app", ["ngRoute"]);

app.config(function($routeProvider) {
	$routeProvider
		.when("/product", {
			templateUrl: "/admin/product/index.html",
			controller: "product-ctrl"
		})
		.when("/user", {
			templateUrl: "/admin/user/index.html",
			controller: "account-ctrl"
		})
		.when("/order", {
			templateUrl: "/admin/order/index.html",
			controller: "order-ctrl"
		})
		.when("/author", {
			templateUrl: "/admin/author/index.html",
			controller: "author-ctrl"
		})
		.when("/category", {
			templateUrl: "/admin/category/index.html",
			controller: "category-ctrl"
		})
		.when("/statiscal", {
			templateUrl: "/admin/report/statiscal.html",
			controller: "statistic-ctrl"
		})
		.when("/statiscalproduct", {
			templateUrl: "/admin/report/statiscalproduct.html",
			controller: "statisticalProduct-ctrl"
		})
		.when("/statiscalCate", {
			templateUrl: "/admin/report/statiscalcate.html",
			controller: "statisticalcate-ctrl"
		})
		.when("/authority", {
			templateUrl: "/admin/authority/index.html",
			controller: "authority-ctrl"
		}).when("/voucher", {
			templateUrl: "/admin/voucher/index.html",
			controller: "voucher-ctrl"
		}).when("/discount", {
			templateUrl: "/admin/discount/index.html",
			controller: "discount-ctrl"
		})

		.otherwise({
			templateUrl: "/admin/Home.html",
			controller: "statis-ctrl"
		})




})