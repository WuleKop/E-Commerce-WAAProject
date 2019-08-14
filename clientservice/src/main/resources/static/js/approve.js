

$('.approveBtn').click(function(event){
    event.preventDefault();
    var accountId = $(this).attr("data");
    $.ajax({
        url: '/admin/account/approve/' + accountId,
        type: 'PUT',
        dataType: "json",
        success: function(response){
            alert("Account Approved");
        },
        error: function(){
            alert('Error while request..');
        }
    });
});

