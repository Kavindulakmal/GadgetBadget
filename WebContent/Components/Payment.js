$(document).ready(function () {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});


// CLIENT-MODEL================================================================
function validatePayForm() {
    
    if ($("#payusername").val().trim() == "") {
        return "Insert Your UserName.";
    }
    
    if ($("#productid").val().trim() == "") {
        return "Insert Product ID.";
    }
   
    if ($("#payamount").val().trim() == "") {
        return "Insert the Amount.";
    }
    
  
    if ($("#paymethod").val().trim() == "") {
        return "Insert the Method.";
    }
    
    // is numerical value
    var tmpPrice = $("#payamount").val().trim();
    if (!$.isNumeric(tmpPrice)) {
        return "Insert a numerical value for Amount.";
    }
    /*// convert to decimal price
    $("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));
    // DESCRIPTION------------------------
    if ($("#itemDesc").val().trim() == "") {
        return "Insert Item Description.";
    }*/
    return true;
}

$(document).on("click", "#btnSave", function (event) {
    // Clear alerts---------------------
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    // Form validation-------------------
    var status = validatePayForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }
    // If valid------------------------
    var type = ($("#hidPayIDSave").val() == "") ? "POST" : "PUT";
    $.ajax(
        {
            url: "PaymentsAPI",
            type: type,
            data: $("#formpayment").serialize(),
            dataType: "text",
            complete: function (response, status) {
            	onPaySaveComplete(response.responseText, status);
            }
        });
});

function onPaySaveComplete(response, status) {
    if (status == "success") {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "success") {
            $("#alertSuccess").text("Successfully saved.");
            $("#alertSuccess").show();
            $("#divpayGrid").html(resultSet.data);
        } else if (resultSet.status.trim() == "error") {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Error while saving.");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unknown error while saving..");
        $("#alertError").show();
    }
    $("#hidPayIDSave").val("");
    $("#formpayment")[0].reset();
}

$(document).on("click", ".btnUpdate", function (event) {
    $("#hidPayIDSave").val($(this).data("payid"));
    $("#UserID").val($(this).closest("tr").find('td:eq(0)').text());
    $("#payusername").val($(this).closest("tr").find('td:eq(1)').text());
    $("#productid").val($(this).closest("tr").find('td:eq(2)').text());
    $("#payamount").val($(this).closest("tr").find('td:eq(3)').text());
    $("#paymethod").val($(this).closest("tr").find('td:eq(4)').text());
});

$(document).on("click", ".btnRemove", function (event) {
    $.ajax(
        {
            url: "PaymentsAPI",
            type: "DELETE",
            data: "payid=" + $(this).data("payid"),
            dataType: "text",
            complete: function (response, status) {
                onPayDeleteComplete(response.responseText, status);
            }
        });
});

function onPayDeleteComplete(response, status) {
    if (status == "success") {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "success") {
            $("#alertSuccess").text("Successfully deleted.");
            $("#alertSuccess").show();
            $("#divpayGrid").html(resultSet.data);
        } else if (resultSet.status.trim() == "error") {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Error while deleting.");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unknown error while deleting..");
        $("#alertError").show();
    }
}