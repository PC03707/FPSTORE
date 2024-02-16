// const linkt = "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province";
// const linkh = "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district";
// const linkx = "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/ward";

// $(document).ready(function() {
// 	window.onload = function() {
// 		tinh();
// 	};
// 	function tinh() {
// 		$.ajax({
// 			type: "GET",
// 			url: "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province",
// 			headers: { token: "0f9ab5f8-89c2-11ee-b1d4-92b443b7a897" },
// 			success: function(data) {
// 				renderData(data.data, "province")
// 				console.log(data);
// 			},
// 			error: function(error) {
// 				alert("Đã xảy ra lỗi: " + error.responseText);
// 			}
// 		})
// 	}
// });

// function huyen(provinceid) {
// 	$.ajax({
// 		type: "GET",
// 		url: "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district",
// 		headers: {
// 			token: "0f9ab5f8-89c2-11ee-b1d4-92b443b7a897"
// 		},
// 		data: { province_id: provinceid },
// 		success: function(data) {
// 			renderDataH(data.data, "district")
// 			console.log(data);
// 		},
// 		error: function(error) {
// 			alert("Đã xảy ra lỗi: " + error.responseText);
// 		}
// 	})
// }

// function phuong(districtid) {
// 	$.ajax({
// 		type: "GET",
// 		url: "linkx",
// 		headers: {
// 			"token": "0f9ab5f8-89c2-11ee-b1d4-92b443b7a897"
// 		},
// 		data: { district_id: districtid },
// 		success: function(data) {
// 			renderDataP(data.data, "ward")
// 			console.log(data);
// 		},
// 		error: function(error) {
// 			alert("Đã xảy ra lỗi: " + error.responseText);
// 		}
// 	})
// }

// function phivc(districtid, wardcode) {

// 	var total = localStorage.getItem("ttcs");
// 	$.ajax({
// 		type: "GET",
// 		url: "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee",
// 		headers: {
// 			token: "0f9ab5f8-89c2-11ee-b1d4-92b443b7a897",
// 			shop_id: 190382,
// 		},
// 		data: {
// 			from_district_id: 1442,
// 			from_ward_code: "20109",
// 			service_id: 0,
// 			service_type_id: 2,
// 			to_district_id: districtid,
// 			to_ward_code: wardcode,
// 			length: 3,
// 			width: 8,
// 			weight: 100,
// 			height: 12,
// 			cod_failed_amount: 2000,
// 			coupon: null,
// 			insurance_value: total
// 		},

// 		success: function(data) {
// 			var discount = 0;
// 			if (localStorage.getItem("discount") != null) {
// 				discount = localStorage.getItem("discount");
// 			}
// 			var ship = data.data.total;
// 			localStorage.setItem("ship", ship);
// 			localStorage.setItem("shipvnp", ship);
// 			const formatship = ship.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ',')
// 			var total = localStorage.getItem("ttcs");
// 			var ttt = parseFloat(total) + ship - parseFloat(discount);
// 			localStorage.setItem("billtotal", ttt);
// 			const formatttt = ttt.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ',')
// 			document.getElementById('ship').innerHTML = formatship + " VNĐ";
// 			document.getElementById('total').innerHTML = formatttt + " VNĐ";
// 		},
// 		error: function(error) {
// 			alert("Đã xảy ra lỗi: " + error.responseText);
// 		}
// 	})
// }

// // Hàm renderData để tạo tùy chọn cho select
// var renderData = (array, select) => {
// 	let row = '<option value="">chọn Tỉnh/Thành Phố</option>';
// 	array.forEach(element => {
// 		row += `<option value="${element.ProvinceID}">${element.ProvinceName}</option>`;
// 	});
// 	document.querySelector("#" + select).innerHTML = row;
// }

// var renderDataH = (array, select) => {
// 	let row = '<option value="">chọn Quận/Huyện</option>';
// 	array.forEach(element => {
// 		row += `<option value="${element.DistrictID}">${element.DistrictName}</option>`;
// 	});
// 	document.querySelector("#" + select).innerHTML = row;
// }

// var renderDataP = (array, select) => {
// 	let row = '<option value="">chọn Phường/Xã</option>';
// 	array.forEach(element => {
// 		row += `<option value="${element.WardCode}">${element.WardName}</option>`;
// 	});
// 	document.querySelector("#" + select).innerHTML = row;
// }

// // Xử lý sự kiện thay đổi tỉnh
// $("#province").change(() => {
// 	var e = document.getElementById("province");
// 	var value = e.value;
// 	huyen(value)
// 	printResult();
// });

// // Xử lý sự kiện thay đổi huyện
// $("#district").change(() => {
// 	var e = document.getElementById("district");
// 	var value = e.value;
// 	phuong(value)
// 	printResult();
// });

// // Xử lý sự kiện thay đổi phường
// $("#ward").change(() => {
// 	// lay id huyen tu select
// 	var district = document.getElementById("district");
// 	var dtid = district.value;
// 	// lay wardcode tu select
// 	var ward = document.getElementById("ward");
// 	var wardcode = ward.value;
// 	phivc(dtid, wardcode)
// 	printResult();
// });

// // Hàm hiển thị kết quả khi tất cả các lựa chọn đã được chọn
// var printResult = () => {
// 	if ($("#district").val() != "" && $("#province").val() != "" &&
// 		$("#ward").val() != "") {
// 		let result = $("#ward option:selected").text() +
// 			", " + $("#district option:selected").text() + ", " +
// 			$("#province option:selected").text();
// 		$("#result").val(result);
// 		if (localStorage.getItem("address") != null) {
// 			localStorage.removeItem("address");
// 		}
// 		localStorage.setItem("address", result);
// 	}
// }
