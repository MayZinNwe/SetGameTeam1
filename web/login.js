
$(document).ready(function () {

    function showGames(data) {
        $.mobile.navigate("#page_dashboard");
        showAllExistingGames();
    };

    $("#loginForm").submit(function (event) {

        // Stop form from submitting normally
        event.preventDefault();
        // Get some values from elements on the page:
        var $form = $(this),
                userName = $form.find("input[name='username']").val(),
                password = $form.find("input[name='password']").val(),
                url = $form.attr("action");
        var data = {userName: userName, userPassword: password};

        //alert(JSON.stringify(data));
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
                currentUser = userName;
                showGames(data);
            }else{
                $("#id_error_message").empty();
                $("#id_error_message").append("<p>Invalid log in</p>");
            }
        }).fail(function (data) {
            console.log(data);
            $("#id_error_message").empty();
             $("#id_error_message").append("<p>Invalid log in</p>");
        });

    });

    $("#registrationForm").submit(function (event) {

        // Stop form from submitting normally
        event.preventDefault();
        // Get some values from elements on the page:
        var $form = $(this),
                userName = $form.find("input[name='username']").val(),
                password = $form.find("input[name='password']").val(),
                url = $form.attr("action");
        var data = {userName: userName, userPassword: password};

        //alert(JSON.stringify(data));
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
                currentUser = userName;
                showGames(data);
            }
        }).fail(function (data) {
            console.log(data);
        });

    });
});