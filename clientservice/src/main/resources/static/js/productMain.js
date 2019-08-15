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
            let productUrl=response.pictureUrls.split("\n");
            let s=productUrl[0];
            console.log(s);
            let product = ('<div class="card mb-3" style="max-width: 540px;"><div class="row no-gutters"><div class="col-md-4">');
            product +=('<img src="/images/'+s+'" class="card-img" alt="ProductPicture"></div><div class="col-md-8"><div class="card-body">');
            product += ('<h5 class="card-title">'+response.name+"</h5>");
            product +=' <p class="card-text">'+response.description+'</p>';
            product +='<p class="card-text">Manufactured Date: '+response.manufacturedDate.substring(0,10)+'</p>';
            product +='<p class="card-text">Quantity: '+response.stockQuantity+'</p>';
            product += '<p class="card-text"><small class="text-muted">Price: $'+ response.price+ '</small></p></div></div></div></div>';
            product+='<p><a href="/getSellerProducts/'+response.sellerId+'" class="btn btn-outline-info btn-sm" style="margin-left: 10px">Back</a></p>';
            $("#result").prepend(product);
            console.log("SUCCESS : ", response);
            $("#submitButton").prop("disabled", false);
            $('#fileUploadForm')[0].reset();
            $.ajax({
                url : '/sendToFollowers/'+response.sellerId,
                method : 'POST',
                data : response.sellerId,

            });
        },
        error: function(jqXHR, textStatus, errorThrown) {

            $("#result").html(jqXHR.responseText);
            console.log("ERROR : ", jqXHR.responseText);
            $("#submitButton").prop("disabled", false);

        }
    });

}