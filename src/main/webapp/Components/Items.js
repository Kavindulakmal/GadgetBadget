$(document).ready(function () {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});


// CLIENT-MODEL================================================================
function validateItemForm() {
    // Name
    if ($("#userName").val().trim() == "") {
        return "Insert Research Name.";
    }
    // Email
    if ($("#email").val().trim() == "") {
        return "Insert Research Email.";
    }
    // Price-------------------------------
    if ($("#price").val().trim() == "") {
        return "Insert Research Price.";
    }
    // is numerical value
    var tmpPrice = $("#price").val().trim();
    if (!$.isNumeric(tmpPrice)) {
        return "Insert a numerical value for Research Price.";
    }
    // convert to decimal price
    $("#price").val(parseFloat(tmpPrice).toFixed(2));
    // DESCRIPTION------------------------
    if ($("#descripc").val().trim() == "") {
        return "Insert Research Description.";
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
    var type = ($("#hiduserIDSave").val() == "") ? "POST" : "PUT";
    $.ajax(
        {
            url: "ResearchAPI",
            type: type,
            data: $("#ResearchItem").serialize(),
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
    $("#ResearchItem")[0].reset();
}

$(document).on("click", ".btnUpdate", function (event) {
    $("#hiduserIDSave").val($(this).data("itemid"));
    $("#userName").val($(this).closest("tr").find('td:eq(0)').text());
    $("#email").val($(this).closest("tr").find('td:eq(1)').text());
    $("#title").val($(this).closest("tr").find('td:eq(2)').text());
    $("#descrip").val($(this).closest("tr").find('td:eq(3)').text());
    $("#price").val($(this).closest("tr").find('td:eq(4)').text());
});

$(document).on("click", ".btnRemove", function (event) {
    $.ajax(
        {
            url: "ResearchAPI",
            type: "DELETE",
            data: "userID=" + $(this).data("userid"),
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
            $("#divResearchGrid").html(resultSet.data);
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