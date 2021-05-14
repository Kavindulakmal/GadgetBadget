$(document).ready(function () {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});


// CLIENT-MODEL================================================================
function validateItemForm() {
    // CODE
    if ($("#cliCode").val().trim() == "") {
        return "Insert Register Code.";
    }
    // EMAIL
    if ($("#cliEmail").val().trim() == "") {
        return "Insert Register Email.";
    }
    // BUDGET-------------------------------
    if ($("#cliBudget").val().trim() == "") {
        return "Insert Research Budget.";
    }
    // is numerical value
    var tmpPrice = $("#cliBudget").val().trim();
    if (!$.isNumeric(tmpPrice)) {
        return "Insert a numerical value for Research Budget.";
    }
    // convert to decimal price
    $("#cliBudget").val(parseFloat(tmpPrice).toFixed(2));
    // DESCRIPTION------------------------
    if ($("#itemDesc").val().trim() == "") {
        return "Insert Research Description.";
    }
    // COMPANY------------------------
    if ($("#cliCom").val().trim() == "") {
        return "Insert Company.";
    }
    return true;
}

$(document).on("click", "#btnSave", function (event) {
    // Clear alerts---------------------
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    // Form validation-------------------
    var status = validateItemForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }
    // If valid------------------------
    var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
    $.ajax(
        {
            url: "ItemsAPI",
            type: type,
            data: $("#formItem").serialize(),
            dataType: "text",
            complete: function (response, status) {
                onItemSaveComplete(response.responseText, status);
            }
        });
});

function onItemSaveComplete(response, status) {
    if (status == "success") {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "success") {
            $("#alertSuccess").text("Successfully saved.");
            $("#alertSuccess").show();
            $("#divItemsGrid").html(resultSet.data);
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
    $("#hidItemIDSave").val("");
    $("#formItem")[0].reset();
}

$(document).on("click", ".btnUpdate", function (event) {
    $("#hidItemIDSave").val($(this).data("itemid"));
    $("#cliCode").val($(this).closest("tr").find('td:eq(0)').text());
    $("#cliEmail").val($(this).closest("tr").find('td:eq(1)').text());
    $("#cliBudget").val($(this).closest("tr").find('td:eq(2)').text());
    $("#cliDesc").val($(this).closest("tr").find('td:eq(3)').text());
    $("#cliCom").val($(this).closest("tr").find('td:eq(4)').text());
});

$(document).on("click", ".btnRemove", function (event) {
    $.ajax(
        {
            url: "ItemsAPI",
            type: "DELETE",
            data: "itemID=" + $(this).data("itemid"),
            dataType: "text",
            complete: function (response, status) {
                onItemDeleteComplete(response.responseText, status);
            }
        });
});

function onItemDeleteComplete(response, status) {
    if (status == "success") {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "success") {
            $("#alertSuccess").text("Successfully deleted.");
            $("#alertSuccess").show();
            $("#divItemsGrid").html(resultSet.data);
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