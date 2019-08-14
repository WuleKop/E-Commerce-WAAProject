
$('.approveBtn').click(function(event){
    event.preventDefault();
    var accountId = $(this).attr("data");
    $.ajax({
        url: '/approve/sellers' + accountId,
        type: 'POST',
        dataType: "json",
        success: function(response){
            alert("Account Approved");
        },
        error: function(){
            alert('Cannot Approve account at this moment');
        }
    });
});

