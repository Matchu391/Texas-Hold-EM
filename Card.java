public class Card {
    
        //  23456789TJQKA
        //  CDHS
        public static String values ="23456789TJQKA";
        public static String suits = "CDHS";
        public char val;
        public char suit;
        public int cardValue;
        
        //has a card made with 3 parameters suit, its number/val and howmany points its worth
        public Card(int s, int v, int cval){
            val=values.charAt(v);
            suit =suits.charAt(s);
            cardValue=cval;
        }
        //prints out the card
        public String toString(){
            return "" + val +suit;
        }
        //returns the point value of the card
        public int tVal(){
          return cardValue;
        }
        
        
        
        
    
}