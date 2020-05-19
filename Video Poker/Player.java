
// HaoDi Liu (hl3022)
import java.util.ArrayList;

public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	// you may choose to use more instance variables
		
	public Player(){		
	    // create a player here
	    hand = new ArrayList<Card>();
        bankroll = 100.0;
        bet = 0.0; 
	}

	public void addCard(Card c){
	    // add the card c to the player's hand
	    hand.add(c);
	}

	public void removeCard(Card c){
	    // remove the card c from the player's hand
	    for (int i=0; i < hand.size(); i++)
        {
            if (hand.get(i).getRank()==c.getRank() && hand.get(i).getSuit()==c.getSuit())
            {
                hand.remove(i);
            }
            
        }
        
    }
		
    public void bets(double amt){
        // player makes a bet
        bet = amt;
        
    }

    public void winnings(double odds){
        //	adjust bankroll if player wins
        bankroll = bankroll - bet + bet*odds;
        
    }

    public double getBankroll(){
        // return current balance of bankroll
        return bankroll;
        
    }

        // you may wish to use more methods here
    public ArrayList<Card> getHand(){
        
        return hand;
    }
    
    public void clearHand(){
        
        hand = new ArrayList<Card>();
        
    }
    
}


