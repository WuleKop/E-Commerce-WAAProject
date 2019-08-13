var contextRoot = "/" + window.location.pathname.split( '/' )[1];
$(document).ready(function () {
    var buyer_data = $("#buyer-form").serialize();
    var seller_data = $('#seller-form').serialize();

    $('#login-form').submit(function(){
        var login_data=$('#login-form').serialize();
        alert("this is a final test");
        alert(login_data);
        $.ajax({
            type:'POST',
            url:'login',
            data:login_data,
            success:function (response) {
                if(response === 'user found') {
                    alert("okay");
                    location.reload();
                }else{
                    alert("Invalid username or password");
                }
            },
            error:function (errorObject) {
               // var error=errorObject.response
                console.log(errorObject);
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
