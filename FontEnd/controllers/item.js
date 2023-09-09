function getAllItem() {
    $("#tblItem").empty();
    $.ajax({
        url:"http://localhost:8080/aad/pages/item",
        success : function (response){
            let item = response.data;
            console.log(item);

            for (let i in item) {
                let items=item[i];
                let code=items.code;
                let description=items.description;
                let qtyOnHand=items.qtyOnHand;
                let unitePrice=items.unitPrice;

                console.log(code,description,qtyOnHand,unitePrice);

                let row= `<tr><td>${code}</td><td>${description}</td><td>${qtyOnHand}</td><td>${unitePrice}</td></tr>`;
                $("#tblItem").append(row);
            }
            bindRowClickOnAction();
            clearItem("","","","");
        }
    });

}

$("#btnItemGetAll").click(function (){
    getAllItem();
});


$("#btnItem").click(function (){
    let formData=$("#itemForm").serialize();
    console.log(formData);
    $.ajax({
        url:"http://localhost:8080/aad/pages/item",
        method: "POST",
        data: formData,
        success:function (res){
            getAllItem();
            alert(res.message);
            clearItem();
        },
        error:function (error){
            console.log(error.responseJson);
            alert(error.responseJson.message);
        }
    });
});

$("#btnItemUpdate").click(function (){

    let itemCode = $("#txtItemCode").val();
    let itemName=$("#txtItemName").val();
    let itemQty=$("#txtItemQty").val();
    let itemPrice=$("#txtItemPrice").val();

    var itemDb={
        code : itemCode,
        description : itemName,
        qtyOnHand: itemQty,
        unitPrice: itemPrice,
    }

    $.ajax({
        url: "http://localhost:8080/aad/pages/item",
        method: "PUT",
        data: JSON.stringify(itemDb),
        contentType:"application/json",
        success:function (res) {
            getAllItem();
            alert(res.message)
            clearItem();
        },
        error:function (error){
            console.log(error.responseJson);
            alert(error.responseJson.message);
        }
    })
});

$("#btnItemDelete").click(function (){
    let code=$("#txtItemCode").val();
    console.log(code);

    $.ajax({
        url: "http://localhost:8080/aad/pages/item?code=" + code,
        method:"DELETE",
        // data : formData,
        success:function (res){
            getAllItem();
            alert(
                res.message
            );
            clearItem();
        },
        error:function (error) {
            // console.log(error.responseJson);
            // alert(error.responseJson.message);
        }
    });
});



function bindRowClickOnAction() {
    $("#tblItem>tr").click(function (){
        let code=$(this).children(":eq(0)").text();
        let description=$(this).children(":eq(1)").text();
        let qtyOnHand=$(this).children(":eq(2)").text();
        let unitPrice=$(this).children(":eq(3)").text();

        $("#txtItemCode").val(code);
        $("#txtItemName").val(description);
        $("#txtItemQty").val(qtyOnHand);
        $("#txtItemPrice").val(unitPrice);
    });
}

function clearItem(code,description,qtyOnHand,unitPrice) {
    $("#txtItemCode").val(code);
    $("#txtItemName").val(description);
    $("#txtItemQty").val(qtyOnHand);
    $("#txtItemPrice").val(unitPrice);
}

