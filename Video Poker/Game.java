
// HaoDi Liu (hl3022)

import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	private Player p;
	private Deck cards;
    //private ArrayList<Card> myhand;
	// you'll probably need some more here
	
	
	public Game(String[] testHand){
		// This constructor is to help test your code
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace - king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
		p = new Player();
        cards = new Deck();
        //myhand = new ArrayList<Card>();
        for (int i=0; i < testHand.length; i++)
        {
            if ((testHand[i].substring(0,1)).equals("c")){
                p.addCard(new Card(1,Integer.parseInt(testHand[i].substring(1,testHand[i].length()))));
            }
            
            if ((testHand[i].substring(0,1)).equals("d")){
                p.addCard(new Card(2,Integer.parseInt(testHand[i].substring(1,testHand[i].length()))));
            }
            
            if ((testHand[i].substring(0,1)).equals("h")){
                p.addCard(new Card(3,Integer.parseInt(testHand[i].substring(1,testHand[i].length()))));
            }
            
            if ((testHand[i].substring(0,1)).equals("s")){
                p.addCard(new Card(4,Integer.parseInt(testHand[i].substring(1,testHand[i].length()))));
            }
        }
        
		
		
	}
	
	public Game(){
		// This no-argument constructor is to actually play a normal game
		p = new Player();
        cards = new Deck();
	}
	
	public void play(){
		// this method should play the game
		//check length of hand, finalize a hand, check, update bankroll	
		//init bet
		Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the video poker game!");
        System.out.println("How much do you want to bet? 1-5");
        p.bets(input.nextDouble());
        ArrayList<Card> hand = p.getHand();
        if (hand.size() == 0)
        {
            cards.shuffle();
            for (int i=0; i<5; i++)
            {
                p.addCard(cards.deal());
            }
            
            System.out.println("Here is your current hand: " + hand);
            System.out.println("Do you want to replace the fifth card ? 1 for yes and 0 for no.");
            if (input.nextInt() == 1){
                p.removeCard(hand.get(4));
                p.addCard(cards.deal());
            }
            
            System.out.println("Do you want to replace the fourth card ? 1 for yes and 0 for no.");
            if (input.nextInt() == 1){
                p.removeCard(hand.get(3));
                p.addCard(cards.deal());
            }
            
            System.out.println("Do you want to replace the third card ? 1 for yes and 0 for no.");
            if (input.nextInt() == 1){
                p.removeCard(hand.get(2));
                p.addCard(cards.deal());
            }
            
            System.out.println("Do you want to replace the second card ? 1 for yes and 0 for no.");
            if (input.nextInt() == 1){
                p.removeCard(hand.get(1));
                p.addCard(cards.deal());
            }
            
            System.out.println("Do you want to replace the first card ? 1 for yes and 0 for no.");
            if (input.nextInt() == 1){
                p.removeCard(hand.get(0));
                p.addCard(cards.deal());
            }
        }
        
        System.out.println("Here is your finalized hand: " + hand);
        System.out.println(checkHand(hand));
        System.out.println("Your bankroll after this round of game is "+ p.getBankroll());
        System.out.println("Do you want to play the game again ? 1 for yes and 0 for no.");
        if (input.nextInt()==1){
            
            p.clearHand();
            play();
        }
        else
            System.out.println("Game Over!");
    }
	
	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String
		// call winning here
		Collections.sort(hand);
        String report = "";
        if (royalFlush(hand)){
            report = "Congratulations! You get a Royal Flush!";
            p.winnings(250.0);
            return report;
        }
        
        else if (straightFlush(hand)){
            report = "You get a Straight Flush!";
            p.winnings(50.0);
            return report;
        }
        
        else if (fourOfAKind(hand)){
            report = "You get a Four of a Kind!";
            p.winnings(25.0);
            return report;
        }
        
        else if (fullhouse(hand)){
            report = "You get a Full House!";
            p.winnings(6.0);
            return report;
        }
        
        else if (flush(hand)){
            report = "You get a Flush!";
            p.winnings(5.0);
            return report;
        }
        
        else if (straight(hand)){
            report = "You get a Straight!";
            p.winnings(4.0);
            return report;
        }
        
        else if (threeOfAKind(hand)){
            report = "You get a Three of a kind!";
            p.winnings(3.0);
            return report;
        }
        
        else if (twoPairs(hand)){
            report = "You get a Two pairs.";
            p.winnings(2.0);
            return report;
        }
        
        else if (onePair(hand)){
            report = "You get a One pair.";
            p.winnings(1.0);
            return report;
        }
        
        else {
            report = "LOL. You get a No pair....";
            p.winnings(0.0);
            return report;
        }
		
	}
	
	
	// you will likely want many more methods here
	// per discussion in class

    public boolean atLeastOnePair(ArrayList<Card> hand){
         
        boolean pair = false;
        for (int i=0; i < hand.size() - 1;i++){
             
            if (hand.get(i).getRank()==hand.get(i+1).getRank()){
                pair = true;
                i = hand.size();
            }
        }
        return pair;
    }
    
    public boolean flush(ArrayList<Card> hand){
        
        boolean ifflush = true; 
        for (int i=1; i < hand.size(); i++)
        {
            if (hand.get(0).getSuit() != hand.get(i).getSuit())
            {
                ifflush = false;
                i = hand.size();
            }
        }
        
        return ifflush;
    }
    
    public boolean straight(ArrayList<Card> hand){
        
        boolean normal = (hand.get(hand.size()-1).getRank()-hand.get(0).getRank()==4);
        boolean ace = (hand.get(0).getRank()==1);
        boolean ten = (hand.get(1).getRank()==10);
        boolean jack = (hand.get(2).getRank()==11);
        boolean queen = (hand.get(3).getRank()==12);
        boolean king = (hand.get(4).getRank()==13);
        boolean nopair = !atLeastOnePair(hand);
        boolean special = ace&&ten&&jack&&queen&&king;
        return (nopair && normal)||special;
        
    }
    
    public boolean fullhouse(ArrayList<Card> hand){
        
        int temp = 0;
        boolean three = false;
        for (int i=0; i < hand.size() - 2; i++)
        {
            boolean haha = (hand.get(i).getRank()==hand.get(i+1).getRank());
            boolean hehe = (hand.get(i).getRank()==hand.get(i+2).getRank());
            if (haha && hehe)
            {
                three = true;
                temp = i;
                i = hand.size();
            }
        }
        
        if (three)
        {
            ArrayList<Card> handcopy = new ArrayList<Card>(hand);
            for (int j=0; j<3; j++)
            {
                handcopy.remove(temp);
            }
            if (atLeastOnePair(handcopy))
                return true;
            else
                return false;
        }
        
        else
            return false;
    }
    
    public boolean fourOfAKind(ArrayList<Card> hand){
        
        boolean four = false;
        for (int i=0; i < hand.size()-3; i++){
            boolean haha = (hand.get(i).getRank()==hand.get(i+1).getRank());
            boolean hehe = (hand.get(i).getRank()==hand.get(i+2).getRank());
            boolean hoho = (hand.get(i).getRank()==hand.get(i+3).getRank());
            if (haha && hehe && hoho)
            {
                four = true;
                i = hand.size();
            }
            
        }
        
        return four;
    }
    
    public boolean threeOfAKind(ArrayList<Card> hand){
        
        boolean three = false;
        for (int i=0; i < hand.size()-2; i++){
            
            boolean haha = (hand.get(i).getRank()==hand.get(i+1).getRank());
            boolean hehe = (hand.get(i).getRank()==hand.get(i+2).getRank());
            
            if (haha && hehe)
            {
                three = true;
                i = hand.size();
            }
            
        }
        
        return three;
    }
    
    public boolean twoPairs(ArrayList<Card> hand){
        
        int temp = 0;
        boolean pair = false;
        for (int i=0; i < hand.size() - 1;i++){
             
            if (hand.get(i).getRank()==hand.get(i+1).getRank()){
                pair = true;
                temp = i;
                i = hand.size();
            }
        }
        
        if (pair)
        {   
            ArrayList<Card> handcopy = new ArrayList<Card>(hand);
            for (int j=0; j<2; j++)
            {
                handcopy.remove(temp);
            }
            if (atLeastOnePair(handcopy))
                return true;
            else
                return false;
        }
        
        else
            return false;
    }
    
    public boolean onePair(ArrayList<Card> hand){
        
        boolean pair = atLeastOnePair(hand);
        return pair;
    }
    
    public boolean straightFlush(ArrayList<Card> hand){
        
        return straight(hand) && flush(hand);
    }
    
    public boolean royalFlush(ArrayList<Card> hand){
        
        if (flush(hand))
        {
            boolean ace = (hand.get(0).getRank()==1);
            boolean ten = (hand.get(1).getRank()==10);
            boolean jack = (hand.get(2).getRank()==11);
            boolean queen = (hand.get(3).getRank()==12);
            boolean king = (hand.get(4).getRank()==13);
            
            if (ace&&ten&&jack&&queen&&king)
                return true;
            else
                return false;
        }
        
        else
            return false;
    }


}
