
// HaoDi Liu (hl3022)

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck

	// add more instance variables if needed
	
	public Deck(){
		// make a 52 card deck here
		cards = makeDeck();
        top = 0;
	}
	
	public void shuffle(){
		// shuffle the deck here
		for (int i = 0; i <= 1000; i++)
        {
            int c1 = (int) (52*Math.random());
            int c2 = (int) (52*Math.random());
            
            Card temp = cards[c1];
            cards[c1] = cards[c2];
            cards[c2] = temp;
        }
	}
	
	public Card deal(){
		// deal the top card in the deck
		top ++;
        return cards[top-1];
	}
	
	// add more methods here if needed
    
    // return a well-ordered 52 card deck
    public Card[] makeDeck(){
        Card[] deck = new Card[52];
        for (int i=0; i < 13; i++)
        {
            deck[i] = new Card (1, i+1);
        }
        
        for (int j=13; j < 26; j++)
        {
            deck[j] = new Card(2, j-12);
        }
        
        for (int k=26; k < 39; k++)
        {
            deck[k] = new Card(3, k-25);
        }
        
        for (int h=39; h < 52; h++)
        {
            deck[h] = new Card(4, h-38);
        }
        
        return deck;
    }


    public int getTop(){
        
        return top;
    }
}