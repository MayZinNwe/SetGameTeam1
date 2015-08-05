package com.CATest.Team1.Model;

public class CardOnTable {

    public Card[] tableCard = new Card[12];

    int deckArray;
//    Card[] selectCard = new Card[3];
//    Card[] cardOnTable = new Card[12];
//    Card[] Cardfor81 = new Card[81];
    CardOnDeck deck = new CardOnDeck();
//    SetEngine set = new SetEngine();
//    int[] input = new int[3];
//    Card c1 = new Card();
//    Card c2 = new Card();
//    Card c3 = new Card();

//    public CardOnTable getTableCards(){
//        int counter = 0;
//        String imageUrl;
//        String folder = "setgame/images/";
//        int imgNum = 0;
//        final String[] colors = {"Red", "Purple", "Green"};
//        final String[] symbols = {"Squiggle", "Diamond", "Oval"};
//        final String[] shadings = {"Solid", "Striped", "Outline"};
//        final int[] numbers = {1, 2, 3};
//
//        for (String shading : shadings) {
//            for (String symbol : symbols) {
//                for (String color : colors) {
//                    for (int number : numbers) {
//                        imgNum++;
//                        Card card = new Card(color, symbol, shading, number);
//                        imageUrl = folder + new DecimalFormat("00").format(imgNum) + ".gif";
//                        card.setImageUrl(imageUrl);
//                        tableCard[counter] = card;
//                        counter++;
//                    }
//                }
//            }
//        }
//        return this;
//    }
    public CardOnTable() {
        deck.getCards().shuffleCards();
        for (deckArray = 0; deckArray < tableCard.length; deckArray++) {
            tableCard[deckArray] = deck.gameCards[deckArray];
            deck.gameCards[deckArray] = null;
            //System.out.println("Card On Table " + deckArray + " : " + cardOnTable[deckArray]);
        }
    }

    public CardOnTable showOnTable(boolean shuffle) {
        //deck.shuffleCards();
        //deck.showCards();
        //System.out.println(">>>>>> Card on the Deck <<<<<<<<<<<");
//        int counter = 0;
//        String imageUrl;
//        String folder = "setgame/images/";
//        int imgNum = 0;
//        final String[] colors = {"Red", "Purple", "Green"};
//        final String[] symbols = {"Squiggle", "Diamond", "Oval"};
//        final String[] shadings = {"Solid", "Striped", "Outline"};
//        final int[] numbers = {1, 2, 3};
//
//        for (String shading : shadings) {
//            for (String symbol : symbols) {
//                for (String color : colors) {
//                    for (int number : numbers) {
//                        imgNum++;
//                        Card card = new Card(color, symbol, shading, number);
//                        imageUrl = folder + new DecimalFormat("00").format(imgNum) + ".gif";
//                        card.setImageUrl(imageUrl);
//                        deck.gameCards[counter] = card;
//                        counter++;
//                    }
//                }
//            }
//        }
        if (shuffle) {
            deck.getCards().shuffleCards();
            for (deckArray = 0; deckArray < tableCard.length; deckArray++) {
                tableCard[deckArray] = deck.gameCards[deckArray];
                deck.gameCards[deckArray] = null;
                //System.out.println("Card On Table " + deckArray + " : " + cardOnTable[deckArray]);
            }
        }

        //deck.showCards();
        return this;
    }

}
