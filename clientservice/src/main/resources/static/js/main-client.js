var contextRoot = "/" + window.location.pathname.split( '/' )[1];
$(document).ready(function () {
    var buyer_data = $("#buyer-form").serialize();
    var seller_data = $('#seller-form').serialize();

    $('#login-form').submit(function(evt){
        var login_data=$('#login-form').serialize();
        $.ajax({
            type:'GET',
            url:'/account/login',
            data:login_data,
            dataType:'json',
            contextType:'application/json',
            success:function (response) {
                
            },
            error:function (errorObject) {
                
            }
        })
    });
    $("#buyer-form").submit(function () {
        var data=$("#buyer-form").serialize();
        $.ajax({
            type: 'post',
            dataType: 'json',
            url: '/registration',
            data: data,
            contextType: 'application/json',
            success: function (response) {
                alert('Account is registered successful!');
            },
            error: function (errorObject) {

            }
        });
    })
    // $('#btn-buyer').click(function (evt) {
    //     evt.preventDefault();
    //     $.ajax({
    //         type: 'post',
    //         dataType: 'json',
    //         url: '/registration',
    //         data: buyer_data,
    //         contextType: 'application/json',
    //         success: function (response) {
    //             alert('Account is registered successful!');
    //         },
    //         error: function (errorObject) {
    //
    //         }
    //     });
    //
    // });
    $('#btn-seller').click(function (evt) {
        evt.preventDefault();
        $.ajax({
            type: 'post',
            url: '',
            data: seller_data,
            dataType: 'json',
            contextType: 'application/json',
            success: function (response) {
                alert('Account is registered successful!');
            },
            error: function (errorObject) {

            }

        });

    });


});
