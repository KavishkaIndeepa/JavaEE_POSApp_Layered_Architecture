function getAllCustomer() {
    $("#tblCustomer").empty();
    $.ajax({
        url:"http://localhost:8080/aad/pages/customer",
        success : function (response){
            let customer = response.data;
            console.log(customer);

            for (let i in customer) {
                let cus=customer[i];
                let id=cus.id;
                let name=cus.name;
                let address=cus.address;

                console.log(id,name,address);

                let row= `<tr><td>${id}</td><td>${name}</td><td>${address}</td></tr>`;
                $("#tblCustomer").append(row);
            }
            bindRowClickOnAction();
            clearCustomer();
        }
    });

}

$("#btnGetAll").click(function (){
    getAllCustomer();
});

$("#btnCustomer").click(function (){
    let formData=$("#customerForm").serialize();
    console.log(formData);

    $.ajax({
        url: "http://localhost:8080/aad/pages/customer",
        method:"POST",
        data : formData,
        success:function (res){
            getAllCustomer();
            alert(
                res.message
            );
            clearCustomer();
        },
        error:function (error) {
            console.log(error.responseJson);
            alert(error.responseJson.message);
        }
    });
});

$("#btnUpdate").click(function (){

    let cusId = $("#txtCustomerID").val();
    let cusName=$("#txtCustomerName").val();
    let cusAddress=$("#txtCustomerAddress").val();

    var cusDB={
        id : cusId,
        name : cusName,
        address: cusAddress
    }

    $.ajax({
        url: "http://localhost:8080/aad/pages/customer",
        method: "PUT",
        data: JSON.stringify(cusDB),
        contentType:"application/json",
        success:function (res) {
            getAllCustomer();
            alert(res.message)
            clearCustomer();
        },
        error:function (error){
            console.log(error.responseJson);
            alert(error.responseJson.message);
        }
    })
});

$("#btnCusDelete").click(function (){
    let id=$("#txtCustomerID").val();
    console.log(id);

    $.ajax({
        url: "http://localhost:8080/aad/pages/customer?id=" + id,
        method:"DELETE",
        // data : formData,
        success:function (res){
            getAllCustomer();
            alert(
                res.message
            );
            clearCustomer();
        },
        error:function (error) {
            // console.log(error.responseJson);
            // alert(error.responseJson.message);
        }
    });
});


function bindRowClickOnAction() {
    $("#tblCustomer>tr").click(function (){
        let id=$(this).children(":eq(0)").text();
        let name=$(this).children(":eq(1)").text();
        let address=$(this).children(":eq(2)").text();

        $("#txtCustomerID").val(id);
        $("#txtCustomerName").val(name);
        $("#txtCustomerAddress").val(address);
    });
}

function clearCustomer(id,name,address) {
    $("#txtCustomerID").val(id);
    $("#txtCustomerName").val(name);
    $("#txtCustomerAddress").val(address);
}

