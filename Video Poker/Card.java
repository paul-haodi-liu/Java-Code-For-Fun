
// HaoDi Liu (hl3022)


public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){
		//make a card with suit s and value r
		suit = s;
        rank = r;
        
	}
	
	public int compareTo(Card c){
		// use this method to compare cards so they 
		// may be easily sorted
        return this.getRank() - c.getRank();
	}
	
	public String toString(){
		// use this method to easily print a Card object
		String report;
        
        report = getStringSuit() + getRank();
        
        return report;
	}
	// add some more methods here if needed
    
    //get the rank of the card
    public int getRank(){
        
        return rank;
        
    }
    
    //get the suit of the card as a string
    public String getStringSuit(){
        
        String str_suit = "";
        if (suit == 1)
            str_suit = "c";
        
        else if (suit == 2)
            str_suit = "d";
        
        else if (suit == 3)
            str_suit = "h";
        
        else if (suit == 4)
            str_suit = "s";
        
        return str_suit;
    }
    
    public int getSuit(){
        
        return suit;
    }
    
}
