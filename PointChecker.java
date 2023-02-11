import java.util.Arrays;

public class PointChecker {
    Card [] cards = new Card[7];
    int total=0;
    int match=0;
    int points=0;
  //every thing you need to keep track of
    int spade=0;
    int dia=0;
    int heart=0;
    int club=0;
    int twoOf=0;
    int threeOf=0;
    int fourOf=0;
    int straight=0;
  //used for calculating straight later
    int [] sorts=new int[7];
    int hrow=0;
    int row=0;
    
    //empty constructor
    public PointChecker(){

    }
    //places the hand you are checking in an array with the table your checking
    public void setup(Table t, Hand h){
        total=t.total+h.total;
        for(int i=0; i<t.total+2; i++){
            if(i<2){
                cards[i]=h.hand[i];
            }else{
                cards[i]=t.table[i-2];
            }
        }
        check();
    }
    
    public void check(){
      //resets variables in case ran multiple times
        spade=0;
        dia=0;
        heart=0;
        club=0;
        twoOf=0;
        threeOf=0;
        fourOf=0;
        straight=0;
    //checks for every suit and checks how many there are   
       for(int i=0; i<total; i++){
            //  23456789TJQKA
            //  CDHS
            if(cards[i].suit=='S'){
                spade+=1;  
            }
            if(cards[i].suit=='D'){
                dia+=1;  
            }
            if(cards[i].suit=='H'){
                heart+=1; 
            }
            if(cards[i].suit=='C'){
                club+=1;  
            }
        }
        int match=0;
        //checks for how many matches of cards there are
        for(int k=2; k<16; k++){
            if(match==4){
                fourOf++;
            }
            if(match==3){
                threeOf++;
            }
            if(match==2){
                twoOf++;
            }
            
            match=0;
            for(int i=0; i<total; i++){
                if(k==cards[i].cardValue){
                    match+=1;
                }
            }
        }
    //     //checks for if there is a straight
        sort();
      //goes through and every time point value increases by 1 adds one to row
        for(int i=0; i< sorts.length-1; i++){
            if(sorts[i+1]-sorts[i]==1){
                row++;
   
                if(row>hrow){
                    hrow=row;
                }
            }
          //if 5 numbers were in a row it becomes a sraight
            if(row>=5){
                straight+=1;
            }
        }    
    }   
    
    //in order to check if there is a straight first you must create an int array of the cards actual point values and use the built in method for sorting them highest to lowest
    public void sort(){
        
        for(int i=0; i<total; i++){
            sorts[i]=cards[i].cardValue;
        }
        Arrays.sort(sorts); 
    }
}