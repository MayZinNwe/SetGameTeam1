$(document).ready(function () {
    $.getJSON("api/cardsOnTable/getTableCards/")
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

function drawRow(cardData, row) {
    row.append($("<td>" + "<input type='image' src='/" + cardData.imageUrl + "' width='50' height='50'>" + "</td>"));
}
;

$(function () {
    $("#btnReset").on("click", function () {


        $.getJSON("api/cards/getAllCards/")
                .done(function (data) {
                    // To clear all rows inside the table
                    $("#table").empty();
                    // Add row based on return data
                    var row = $("<tr />")
                    $("#table").append(row);
                    for (var i = 0, il = data.cards.length; i < il; i++) {
                        if (i % 9 === 0) {
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


        $.getJSON("api/cardsOnTable/getTableCards/")
                .done(function (data) {
                    // To clear all rows inside the table
                    $("#table").empty();
                    // Add row based on return data
                    var row = $("<tr />")
                    $("#table").append(row);
                    for (var i = 0, il = data.cardsOnTable.length; i < il; i++) {
                        if (i % 3 === 0) {
                            row = $("<tr />")
                            $("#table").append(row);
                        }
                        drawRow(data.cardsOnTable[i], row);
                    }
                }).fail(function () {
            Console.log("Not Found");
        });
    });

});




