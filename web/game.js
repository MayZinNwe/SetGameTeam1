$(document).ready(function () {
    $("#gameEntryForm").submit(function (event) {
        if(!currentUser){
            alert("Please log in again");
            return;
        }
        // Stop form from submitting normally
        event.preventDefault();
        // Get some values from elements on the page:
        var $form = $(this),
                description = $form.find("input[name='description']").val(),
                maximumPlayer = $form.find("input[name='maximumplayer']").val(),
                url = $form.attr("action");
        var data = {description: description, maximumPlayer: maximumPlayer, creator : currentUser};

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
                $.mobile.navigate( "#page_dashboard" );
                showAllExistingGames();
            }
        }).fail(function (data) {
            console.log(data);
        });

    });
});

var imageurl = new String();  // insert selecting image

var imageurlpointingplace = new String();//used to place index of original place but not yet got 
var imagefirsturl = new String();  //original images with original indexes but not yet done

var currentGame;
var currentUser;
var selectedCount = 0;
var selectedCards = [3];
var selectedImageCards = [];
var cardsOnTable=[];
var myVar = setInterval(function () {
    showCurrentGame()
}, 1000);

function show(gameId) {
    currentGame = gameId;
}
;

function showCurrentGame() {
    if (currentGame) {
        $.mobile.navigate("#page_commonview");
        $.getJSON("api/cardsOnTable/getTableCards/?id=" + currentGame + "&userName=" +currentUser )
                .done(function (data) {
                    $("#id_current_game").empty();
                    $("#id_current_game").append(currentGame);
                    cardsOnTable = data.cards;
                    showCardsOnTable("#games", data.cards);
                    showCardsOnTable("#setTable", data.setCards);
                    showUsersOnTable("#userList", data.onlineUsers);
                }).fail(function () {
            console.log("Not Found");
        });
    }
}


function showAllExistingGames() {
    $.getJSON("api/game/getExistingGames/")
            .done(function (data) {
                // To clear all rows inside the table
                $("#existingGameList").empty();
                for (var index = 0, indexLength = data.games.length; index < indexLength; index++) {
                    var game = data.games[index];
                    addNewGameItem(game);
                }
                //$('#existingGameList').listview();
                $('#existingGameList').listview('refresh');
            }).fail(function () {
        Console.log("Not Found");
    });
}

function addNewGameItem(game) {
//    var listItem = "<li><a id='" + game.id + "' href='#'>"
//            + "<h2>" + game.id + "</h2>"
//            + "<p>" + game.creator + "</p>"
//            + "<p>" + game.date + "</p>"
//            + "</a></li>";
//    return listItem;
    var list = $("#existingGameList");
    var listItem = "";

    listItem += ("<li onclick='show(" + game.id + ")'>");
    listItem += ("<a href='#'>");
    listItem += ("<h1>" + game.description + "("+game.id+ ")</h1>");
    listItem += ("<p> Maximum Players : " + game.maximumPlayer+ "</p>");
    listItem += ("<p> Created By : " + game.creator + "</p>");
    listItem += ("<p> Created Date :" + game.date + "</p>");
    listItem += ("<p> Players :" + game.userCount + "</p>");
//  listItem += ("<button class='btnShow' value='" + game.id + "' onclick='show(" + game.id + ")'>Show</button>");
    listItem += ("</a>");
    listItem += ("</li>");
    list.append($(listItem));
}

function showCardsOnTable(tableId, cards) {
    // To clear all rows inside the table
    $(tableId).empty();
    if (cards) {
        // Add row based on return data
        var row = $("<tr width='100%' />");
        $(tableId).append(row);
        for (var i = 0, il = cards.length; i < il; i++) {
            if (i % 3 === 0) {
                row = $("<tr />");
                $(tableId).append(row);
            }
            drawRow(cards[i], row);
        }
    }
}
;

function showUsersOnTable(tableId, onlineUsers) {
    // To clear all rows inside the table
    $(tableId).empty();
    if (onlineUsers) {
        // Add row based on return data
        var row = $("<tr width='100%' />");
        $(tableId).append(row);
        var list = $("#userList");
        var listItems = "";
        for (var i = 0, il = onlineUsers.length; i < il; i++) {
            if (i % 3 === 0) {
                row = $("<tr />");
                $(tableId).append(row);
            }
            listItems=drawUser(onlineUsers[i], listItems);
        }
        list.append(listItems);
    }
};


function drawUser(userData, listItems) {
    listItems += ("<li>");
    listItems += ("<a href='#'>");
    listItems += ("<h1>" + userData.name+ "("+userData.score+ ")</h1>");
    listItems += ("</a>");
    listItems += ("</li>");
    return listItems;
};
function drawRow(cardData, row) {
    //onClick='checkGameRules("+cardData.imageUrl+")'
    var cell = "<a id='" + cardData.id + "' onclick='checkGameRules(this.id)'><img src='" + cardData.imageUrl + "'/></a>";

    row.append($("<td>" + cell + "</td>"));
    console.log(cardData.imageUrl);
    var i = 0;
    if (imagefirsturl.length >= 1) {
        i++;
        imageUrl
    }
    imagefirsturl[i] = cardData.imageUrl;
    console.log("this is in the imagefirsturl    " + imagefirsturl[i]);
}
;

function createNewGame(game){
      $.getJSON("api/game/createNewGame/")
                .done(function (game) {
                    addNewGameItem(game);
                    showAllExistingGames();
                }).fail(function () {
            Console.log("Not Found");
        });
}

function checkGameRules(id) {
    for (var j = 0; j <= selectedCards.length; j++) {
        var selectedId=selectedCards[j];
        if(selectedId){
            if(id===selectedId){
                alert("You have chosen");
                return;
            }
        }
    }
    selectedCount++;
    selectedCards[selectedCount - 1] = id;
    if (selectedCount === 3) {
        $.getJSON("api/cardsOnTable/checkTableCards/?id=" + currentGame
                + "&card1=" + selectedCards[0]
                + "&card2=" + selectedCards[1]
                + "&card3=" + selectedCards[2]
                + "&userName="+currentUser)
                .done(function (data) {
                    var valid = true;
                    if (valid) {
                        selectedCount = 0;
                        selectedCards = [];
                        // Replace the old cards with new cards
                        //alert(data.status);
                        //
                        showCardsOnTable("#games", data.cards);
                        showCardsOnTable("#setTable", data.setCards);
                    } else {
                        showCardsOnTable("#setTable", data.setCards);
                    }
                }).fail(function () {
            console.log("Not Found");
        });
    }
    $("#id_current_selected").empty();
    $("#id_current_selected").append(selectedCount + " Selected");
}

$(function () {
    $("#btnHint").on("click", function () {
        $.getJSON("api/game/getAllCards/")
                .done(function (data) {
                    // To clear all rows inside the table
                    $("#hintTable").empty();
                    // Add row based on return data
                    var row = $("<tr />")
                    $("#hintTable").append(row);
                    for (var i = 0, il = data.cards.length; i < il; i++) {
                        if (i % 3 === 0) {
                            row = $("<tr />")
                            $("#hintTable").append(row);
                        }
                        drawRow(data.cards[i], row);
                    }
                    $("#panelHintSet").trigger("updatelayout");
                }).fail(function () {
            Console.log("Not Found");
        });
    });

    $("#btnShuffle").on("click", function () {
        $.getJSON("api/game/getShuffleCards/")
                .done(function (data) {
                    showCardsOnTable("#table", data.cards);
                }).fail(function () {
            Console.log("Not Found");
        });
    });

    $("#btnNewGame").on("click", function () {
      $.mobile.navigate( "#page_gameEntry" );      
    });
    
    $("#btnRefresh").on("click", function () {
        showAllExistingGames();
    });
    
    $("#id_back_to_dashboard").on("click", function () {
//        $.getJSON("api/cardsOnTable/getTableCards/?id=" + currentGame + "&userName=" +currentUser )
//                .done(function (data) {
//                    $("#id_current_game").empty();
//                    $("#id_current_game").append(currentGame);
//                    showCardsOnTable("#games", data.cards);
//                    showCardsOnTable("#setTable", data.setCards);
//                    showUsersOnTable("#userTable", data.onlineUsers);
//                }).fail(function () {
//            console.log("Not Found");
//        });
        currentGame=null;
        $.mobile.navigate("#page_dashboard");
    });
});

