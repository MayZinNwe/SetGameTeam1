$(document).ready(function () {
    $("#gameEntryForm").submit(function (event) {

        // Stop form from submitting normally
        event.preventDefault();
        // Get some values from elements on the page:
        var $form = $(this),
                description = $form.find("input[name='description']").val(),
                maximumPlayer = $form.find("input[name='maximumplayer']").val(),
                url = $form.attr("action");
        var data = {description: description, maximumPlayer: maximumPlayer, creator : currentUser};

        alert(JSON.stringify(data));
        // Send the data using post
        $.ajax({
            type: "POST"
            , url: url
            , contentType: "application/json"
            , data: JSON.stringify(data)
            , dataType: "json"
        }).done(function (data) {
            console.log(data);
            if (data.success === true) {
                $.mobile.navigate( "#page_dashboard" );
                showAllExistingGames();
            }
        }).fail(function (data) {
            console.log(data);
        });

    });
});



