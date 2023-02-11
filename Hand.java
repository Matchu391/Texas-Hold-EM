public class Hand {
    //return,
    public int money=100;
    public int betting=0;
    public boolean fold=false;
    public int points=0;
    Card [] hand = new Card[2];
    int total=0;
    
    //prints the cards int the hand and the current money and betting
    public void rHand(){
        System.out.println("Total Money: " + money + " Betting: " + betting);
        System.out.println("Hand:");
        for(int i=0; i<total; i++){
            System.out.print(hand[i].toString() + " ");
        }
        System.out.println("");
        System.out.println("");
    }
    
    //Draws a card taking the deck your drawing from as a parameter
    public void draw(Deck c){
        total+=1;
        hand[total-1]=c.deck[0];
      //deletes the card from the deck after its added to the hand
        c.remove();
    }
}