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
        url: "/testAdvertissement",
        data: data,

        // prevent jQuery from automatically transforming the data into a query string
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000,
        success: function(response, textStatus, jqXHR) {
            let advertUrl=response.pictureUrls.split("\n");
            let s=advertUrl[0];
            console.log(s);
             let advert = ('<div class="card mb-3" style="max-width: 540px;"><div class="row no-gutters"><div class="col-md-4">');
             advert +=('<img src="'+s+'" class="card-img" alt="AdvertPicture"></div><div class="col-md-8"><div class="card-body">');
             advert += ('<h5 class="card-title">'+response.name+"</h5>");
             advert +=' <p class="card-text">'+response.description+'</p>';
             advert +='<p class="card-text">Date Created: '+response.createdDate.substring(0,10)+'</p>';
            // advert += '<p class="card-text"><small class="text-muted">Price: $'+ response.price+ '</small></p>' +
                 '</div></div></div></div>';
            $("#result").prepend(advert);
            console.log("SUCCESS : ", response);
            $("#submitButton").prop("disabled", false);
            $('#fileUploadForm')[0].reset();
        },
        error: function(jqXHR, textStatus, errorThrown) {

            $("#result").html(jqXHR.responseText);
            console.log("ERROR : ", jqXHR.responseText);
            $("#submitButton").prop("disabled", false);

        }
    });

}