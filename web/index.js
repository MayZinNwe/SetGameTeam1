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

function drawRow(cardData, row) {
    row.append($("<td>" + "<input type='image' src='/" + cardData.imageUrl + "' width='50' height='50'>" + "</td>"));
}
;

function resume(id) {
    //$.getJSON("api/game/openExistingGame/?id=" + id)
    $.getJSON("api/cardsOnTable/getTableCards/?id="+id)
            .done(function (data) {
                showCardsOnTable(data);
            }).fail(function () {
        Console.log("Not Found");
    });
};

function showCardsOnTable(data){
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
};

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
        $.getJSON("api/cardsOnTable/shuffle/")
                .done(function (data) {
                showCardsOnTable(data);
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




