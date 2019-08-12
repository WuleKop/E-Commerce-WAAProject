$(document).read(function () {
    var buyer_data = $("#buyer-form").serialize();
    var seller_data = $('#seller-form').serialize();
    var login_data=$('#login-form').serialize();
    $('#btn-login').click(function(evt){
        evt.preventDefault();
        $.ajax({
            type:'GET',
            url:'',
            data:login_data,
            dataType:'json',
            contextType:'application/json',
            success:function (response) {
                
            },
            error:function (errorObject) {
                
            }
        })
    });
    $('#btn-buyer').click(function (evt) {
        evt.preventDefault();
        $.ajax({
            type: 'post',
            dataType: 'json',
            url: '',
            data: buyer_data,
            contextType: 'application/json',
            success: function (response) {
                alert('Account is registered successful!');
            },
            error: function (errorObject) {

            }
        });

    });
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


})