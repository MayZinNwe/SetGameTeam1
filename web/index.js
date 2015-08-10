var imageurl = new String();  // insert selecting image

var imageurlpointingplace = new String();//used to place index of original place but not yet got 
var imagefirsturl = new String();  //original images with original indexes but not yet done

var currentGame;
var currentUser;
var selectedCount = 0;
var selectedCards = [3];

function show(gameId) {
    //$.getJSON("api/game/openExistingGame/?id=" + id)
    $.mobile.navigate("#page_commonview");
    currentGame = gameId;
    $.getJSON("api/cardsOnTable/getTableCards/?id=" + gameId)
            .done(function (data) {
                $("#id_current_game").empty();
                $("#id_current_game").append(currentGame);
                showCardsOnTable("#games", data.cards);
                showCardsOnTable("#setTable", data.setCards);
            }).fail(function () {
        console.log("Not Found");
    });
}
;

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


function drawRow(cardData, row) {
    //onClick='checkGameRules("+cardData.imageUrl+")'
    var cell = "<a id='" + cardData.id + "' onclick='checkGameRules(this.id)'><img src='/" + cardData.imageUrl + "'/></a>";

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
    selectedCount++;
    selectedCards[selectedCount - 1] = id;
    if (selectedCount === 3) {
        $.getJSON("api/cardsOnTable/checkTableCards/?id=" + currentGame
                + "&card1=" + selectedCards[0]
                + "&card2=" + selectedCards[1]
                + "&card3=" + selectedCards[2])
                .done(function (data) {
                    var valid = true;
                    if (valid) {
                        selectedCount = 0;
                        selectedCards = [];
                        // Replace the old cards with new cards
                        alert(data.status);
                        //
                        showCardsOnTable("#games", data.cards);
                        showCardsOnTable("#setTable", data.setCards);
                        $("#panelCompletedSet").trigger("updatelayout");

                    } else {
                        showCardsOnTable("#setTable", data.setCards);
                    }
                }).fail(function () {
            console.log("Not Found");
        });
    }
//    console.log(selectedCount);
//    if (selectedCount < 3) {
//        imageurl[selectedCount] = id;
//        console.log(imagefirsturl.length);
//
////to get original index of the original array
//        for (var i = 0; i <= imagefirsturl.length; i++) {
//            var selectedCountt = 0;
//            if (imagefirsturl[i].toString() === imageurl[selectedCount].toString()) {
//                imageurlpointingplace[selectedCountt] = i;
//                console.log(imageurlpointingplace.toString());
//                selectedCountt++;
//            }
//        }
//        //
//        console.log(imageurl[selectedCount]);
//    }
//    else {
//        alert("" + imageurlpointingplace[0] + imageurlpointingplace[1] + imageurlpointingplace[2] + "You have chosen three times");
//    }
//    selectedCount++;
//    return;
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
});