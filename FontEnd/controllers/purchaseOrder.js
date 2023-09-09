loadAllItems();
loadAllCustomers();


var genarateOrderID = 0;


$("#txtOrderID").val('0-00' + (genarateOrderID += 1));

const currentDate = new Date();

// Format the current date as yyyy-MM-dd
const formattedDate = formatDate(currentDate);

$('#txtDate').val(formattedDate);

function formatDate(date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

function loadAllCustomers() {
    $("#selectCusID").empty();
    $.ajax({
        url: "http://localhost:8080/aad/pages/customer",
        dataType: "json",
        success: function (resp) {
            console.log(resp);
            for (let cus of resp.data) {
                $("#selectCusID").append(`<option value="${cus.id}">${cus.id}</option>`);
            }
        }
    });
}

function searchCustomer(id) {
    let response = "";
    $.ajax({
        url: "http://localhost:8080/aad/pages/customer",
        dataType: "json",
        async: false,
        success: function (resp) {
            response = resp.data.filter((c) => {
                return c.id == id;
            });
        }
    });
    return response;
}

$("#selectCusID").change(function () {
    let cusID = $("#selectCusID").val();
    $("#selectCusID").val(cusID);
    let res = searchCustomer(cusID);
    if (res.length > 0) {
       $("#orderCustomerName").val(res[0].name);
       $("#orderCustomerAddress").val(res[0].address);
    }

});


function loadAllItems() {
    $("#selectItemCode").empty();
    $.ajax({
        url: "http://localhost:8080/aad/pages/item",
        success: function (res) {
            for (let c of res.data) {
                let code = c.code;
                $("#selectItemCode").append(`<option value="${code}">${code}</option>`);
            }
        },
        error: function (error) {
            let message = JSON.parse(error.responseText).message;
            alert(message);
        }
    });
}

function searchItem(code) {
    let response = "";
    $.ajax({
        url: "http://localhost:8080/aad/pages/item",
        dataType: "json",
        async: false,
        // headers:{
        //     Auth:"user=admin,pass=admin"
        // },
        success: function (resp) {
            response = resp.data.filter((i) => {
                return i.code == code;
            });
        }
    });
    return response;
}

$("#selectItemCode").change(function () {
    let code = $("#selectItemCode").val();
    $("#selectItemCode").val(code);
    let res = searchItem(code);
    if (res.length > 0) {
        $("#txtItemName").val(res[0].description);
        $("#txtItemPrice").val(res[0].unitPrice);
        $("#txtQTYOnHand").val(res[0].qtyOnHand);
    }
});

let orderItems= [];

$("#addToCart").click(function (){
    let code = $("#selectItemCode").val();
    let name = $("#txtItemName").val();
    let price = $("#txtItemPrice").val();
    let qty = $("#txtQty").val();
    let total = price*qty;

    orderItems.push({
        code:code,
        description:name,
        unitPrice:price,
        qty:qty,
        total:total
    })

    updateOrderTable();
    updateTotals();
});


function updateOrderTable() {
    let tableBody = $("#orderTable");
    tableBody.empty();

    for (let item of orderItems) {
        let row = `<tr>
            <td>${item.code}</td>
            <td>${item.description}</td>
            <td>${item.unitPrice}</td>
            <td>${item.qty}</td>
            <td>${item.total}</td>
        </tr>`;
        tableBody.append(row);
    }
}


function updateTotals() {
    let totalAmount = 0;

    for (let item of orderItems) {
        totalAmount += item.total;
    }

    let subtotalAmount = totalAmount;
    let cashAmount = parseFloat($("#txtCash").val()) || 0;
    let discountAmount = parseFloat($("#txtDiscount").val()) || 0;

    let balanceAmount = cashAmount - discountAmount - subtotalAmount;

    $("#total").text(totalAmount.toFixed(2));
    $("#subtotal").text(subtotalAmount.toFixed(2));
    $("#txtBalance").val(balanceAmount.toFixed(2));
}

let disTOGave = 0;

$('#txtDiscount').on('keyup', function() {
    let dis = parseFloat($('#txtDiscount').val());
    let tot = parseFloat($('#txtBalance').val());

    console.log(dis + "==" + tot);

    let totMin = tot * (dis / 100);
    console.log("Discount Amount: " + totMin);

    let subTot = tot - totMin;
    disTOGave = totMin;

    $('#subtotal').val(subTot);
    updateTotals();
});


$("#placeOrder").click(function (){
    let orderId =$("#txtOrderID").val();
    let orderDate =$("#txtDate").val();
    let cusId =$("#selectCusID").val();
    let orderDetail =getItemDetails();

    let ob ={
        oid: orderId,
        date: orderDate,
        id: cusId,
        orderDetails: orderDetail
    }

    $.ajax({
        url:"http://localhost:8080/aad/pages/placeOrder",
        method:"POST",
        dataType:"json",
        data:JSON.stringify(ob),
        contentType: "application/json",
        success:function (resp){
            alert(resp.message);
            clear();
        },
        // error: function (error) {
        //     alert(JSON.parse(error.responseText).message);
        // }
    });
});

function getItemDetails(){
    let rows = $("#orderTable").children().length;

    var array=[];

    for (let i = 0; i < rows; i++) {
        let code = $("#orderTable").children().eq(i).children(":eq(0)").text();
        let byQty = $("#orderTable").children().eq(i).children(":eq(3)").text();
        let qty = $("#orderTable").children().eq(i).children(":eq(4)").text();
        let price = $("#orderTable").children().eq(i).children(":eq(2)").text();
        array.push({code: code, byQty: byQty, qty: qty, price: price});
    }
}

function clear() {
    $("#orderTable").empty();
    $("#txtOrderID").val("");
    $("#orderCustomerName").val("");
    $("#orderCustomerAddress").val("");
    $("#txtItemCode").val("");
    $("#txtItemDescription").val("");
    $("#txtItemPrice").val("");
    $("#txtQTYOnHand").val("");
    $("#txtQty").val("");
}
