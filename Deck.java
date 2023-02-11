public class Deck {
    //shuffle, deal, return, remove
    //creates a 52 long array of Cards 
    Card[] newCards = new Card[52];
    Card[] deck=new Card[52];
    public Deck(){
        
        int count=0;
        int v=0;
      //goes through all possible cards and adds them too the deck
        for(int i=0; i<4; i++){
            for(int j=0; j<13; j++){
                v=j+2;
                //v=point value, starts at +2 because that is the lowest value of the cards
                deck[count] = new Card(i,j,v);
                count++;
            }
        }
    }
    
    
    
    //goes through all card and prints them(good for testing)
    public void rDeck(){
        for(int i=0; i< deck.length; i++){
            System.out.println(deck[i].toString() + "V: "+ deck[i].tVal());
        }
        System.out.println("");
    }

    // picks to random spots and swaps the cards at those spots using a tempoporary card placeholder
    public void shuffle(){
       for(int i = 0; i < 52; i++){
           int c = (int)(Math.random()*52);
           Card a = deck[c];
           deck[c] = deck[i];
           deck[i] = a;
       }  
    }
    //deletes the top card of the deck
    public void remove(){
      //creates a temporray array to shorten the length of first
        Card[] newCards = new Card[deck.length-1];
      //adds all cards in the deck to the temp exept the first card
        for(int i=0; i<deck.length-1; i++){
            newCards[i]=deck[i+1];
        }
        //sets real to temp
        deck=newCards;
    }
}