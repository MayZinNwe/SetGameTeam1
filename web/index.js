$(document).ready(function () {
//    $.getJSON("api/cardsOnTable/getTableCards/")
//            .done(function (data) {
//                // To clear all rows inside the table
//                $("#table").empty();
//                // Add row based on return data
//                var row = $("<tr />")
//                $("#table").append(row);
//                for (var i = 0, il = data.cards.length; i < il; i++) {
//                    if (i % 3 === 0) {
//                        row = $("<tr />")
//                        $("#table").append(row);
//                    }
//                    drawRow(data.cards[i], row);
//                }
//            }).fail(function () {
//        Console.log("Not Found");
//    });
});
var imageurl = new String();  // insert selecting image

var imageurlpointingplace = new String();//used to place index of original place but not yet got 
var imagefirsturl = new String();  //original images with original indexes but not yet done

var currentGame;
var selectedCount = 0;
var selectedCards = [3];

function drawRow(cardData, row) {
    //onClick='checkGameRules("+cardData.imageUrl+")'
    row.append($("<td>" + "<input  type='image' src='/" + cardData.imageUrl
            + "' id='" + cardData.id
            + "' width='50' height='50' onclick='checkGameRules(this.id)'>" + "</td>"));
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

function resume(gameId) {
    //$.getJSON("api/game/openExistingGame/?id=" + id)
    currentGame = gameId;
    $.getJSON("api/cardsOnTable/getTableCards/?id=" + gameId)
            .done(function (data) {
                showCardsOnTable("#table", data.cards);
            }).fail(function () {
        console.log("Not Found");
    });
}
;

function showCardsOnTable(tableId, cards) {
    // To clear all rows inside the table
    $(tableId).empty();
    // Add row based on return data
    var row = $("<tr />");
    $(tableId).append(row);
    for (var i = 0, il = cards.length; i < il; i++) {
        if (i % 3 === 0) {
            row = $("<tr />");
            $(tableId).append(row);
        }
        drawRow(cards[i], row);
    }
}
;

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
                        showCardsOnTable("#table",data.cards);
                        showCardsOnTable("#setTable",data.setCards);
                    } else {
                        showCardsOnTable("#setTable",data.setCards);
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
                    $("#table").empty();
                    // Add row based on return data
                    var row = $("<tr />")
                    $("#table").append(row);
                    for (var i = 0, il = data.cards.length; i < il; i++) {
                        if (i % 3 === 0) {
                            row = $("<tr />")
                            $("#table").append(row);
                        }
                        drawRow(data.cards[i], row);
                    }
                }).fail(function () {
            Console.log("Not Found");
        });
    });

    $("#btnShuffle").on("click", function () {
        $.getJSON("api/game/getShuffleCards/")
                .done(function (data) {
                    showCardsOnTable("#table",data.cards);
                }).fail(function () {
            Console.log("Not Found");
        });
    });

    $("#btnNewGame").on("click", function () {

        $.getJSON("api/game/createNewGame/")
                .done(function (data) {
                    // Add row based on return data
                    var row = $("<tr id=" + data.id + "/>")
                    $("#games").append(row);
                    row.append($("<td>" + data.id + "</td>"));
                    row.append($("<td>" + data.creator + "</td>"));
                    row.append($("<td>" + data.date + "</td>"));
                    row.append($("<td><button class='btnResume' value='" + data.id + "' onclick='resume(" + data.id + ")'>Resume</button></td>"));
                }).fail(function () {
            Console.log("Not Found");
        });
    });
});