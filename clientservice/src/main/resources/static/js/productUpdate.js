$(document).ready(function() {

    $("#submitButton").click(function(event) {

        // Stop default form Submit.
        event.preventDefault();

        // Call Ajax Submit.

        ajaxSubmitForm();

    });

});

function ajaxSubmitForm() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);


    $("#submitButton").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/testProduct",
        data: data,

        // prevent jQuery from automatically transforming the data into a query string
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000,
        success: function(response, textStatus, jqXHR) {
            $('#updateDiv').hide();
            $('#fileUploadForm').empty();
            let p ='<h4>Updated Successfully</h4>';
            p+=('<a href="/getSellerProducts/'+response.sellerId+'" class="btn btn-outline-info btn-sm" style="margin-left: 10px">Go Back</a>');
            $("#upload").append(p);
            console.log("SUCCESS : ", response);

        },
        error: function(jqXHR, textStatus, errorThrown) {

            $("#result").html(jqXHR.responseText);
            console.log("ERROR : ", jqXHR.responseText);
            $("#submitButton").prop("disabled", false);

        }
    });

}