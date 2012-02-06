import java.util.ArrayList;

public class RockCritter extends RPSCritter
{
    public int fight(RPSCritter opponent)
    {
	/*
	System.out.println("RockCritter about to play " + opponent.getClass().toString());
	ArrayList<RPSMatch> mr = RPSWorld.getMatchRecord( opponent.getClass() );
	for(RPSMatch m : mr)
	    System.out.println( m );
	*/

	return RPSMatch.ROCK;
    }
    
    public String yourName()
    {
	return "RockCritter";
    }
}