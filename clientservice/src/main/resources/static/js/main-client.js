var contextRoot = "/" + window.location.pathname.split( '/' )[1];
$(document).ready(function () {
    var buyer_data = $("#buyer-form").serialize();
    var seller_data = $('#seller-form').serialize();

    $('#login-form').submit(function(){

        var login_data=$('#login-form').serialize();
        $.ajax({
            type:'POST',
            url:'/login',
            data:login_data,
            dataType:'json',
            contextType:'application/json',
            success:function (response) {
                console.log(response);
                // if(response==="User found "){
                //     console.log("Testing the returned response")
                //     window.location.href="http://localhost:8084/shop";
                // }else{
                //     console.log("Empty response!!")
                // }
            },
            error:function (errorObject) {
               // var error=errorObject.response

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
                console.log(response);
                if(response != null){
                    alert("You are Successfully registered");
                    location.href="logon";
                }else{
                    alert("Sorry an Error Occured please try again later");
                }
            },
            error: function (errorObject) {
                alert(errorObject);

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
    // $('#btn-seller').click(function (evt) {
    //     evt.preventDefault();
    //     $.ajax({
    //         type: 'post',
    //         url: '',
    //         data: seller_data,
    //         dataType: 'json',
    //         contextType: 'application/json',
    //         success: function (response) {
    //             alert('Account is registered successful!');
    //         },
    //         error: function (errorObject) {
    //
    //         }
    //
    //     });
    //
    // });


});
