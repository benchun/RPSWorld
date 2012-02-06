/**
 * An RPSMatch represents a single game of Rock-Paper-Scissors. A new
 * instance of this class is created for every match, which the
 * RPSWorld stores in its match record.
 */

public class RPSMatch
{
    private RPSCritter one;
    private RPSCritter two;
    private int oneThrow;
    private int twoThrow;
    private boolean oneValid;
    private boolean twoValid;
    
    //constants
    public static final int ROCK = 1;
    public static final int PAPER = 2;
    public static final int SCISSORS = 3;


    public RPSMatch(RPSCritter one, RPSCritter two)
    {
	this.one = one;
	this.two = two;

	oneThrow = one.fight(two);
    	twoThrow = two.fight(one);
	oneValid = validThrow(oneThrow);
	twoValid = validThrow(twoThrow);
    	
    	String oneThrowName = getThrowName(oneThrow);
    	String twoThrowName = getThrowName(twoThrow);
    	
    	System.out.println("\t\t" + one.yourName() + " throws: " + oneThrowName);
    	System.out.println("\t\t" + two.yourName() + " throws: " + twoThrowName);

	if (!oneValid)
	{
	    System.out.println("\t> "+one.yourName() + " forfeits, invalid throw");
	    one.removeSelfFromGrid();
	}
	if (!twoValid)
	{
	    System.out.println("\t> "+two.yourName() + " forfeits, invalid throw");
	    two.removeSelfFromGrid();
	}
    }

    public String toString()
    {
	String o = one.getClass().toString() + ":" + getThrowName(oneThrow);
	String t = two.getClass().toString() + ":" + getThrowName(twoThrow);
	return "[" + o + " vs " + t + "]";
    }

    public boolean validThrow(int sign)
    {
    	if (sign == ROCK) return true;
    	if (sign == PAPER) return true;
    	if (sign == SCISSORS) return true;
    	return false;
    }

    public Class getCritterOneClass()
    {
	return one.getClass();
    }

    public int getCritterOneThrow()
    {
	return oneThrow;
    }

    public Class getCritterTwoClass()
    {
	return two.getClass();
    }

    public int getCritterTwoThrow()
    {
	return twoThrow;
    }

    public boolean isDraw()
    {
	return oneThrow == twoThrow;
    }

    public RPSCritter getWinner()
    {
	if(!oneValid || !twoValid)
	{
	    if(!oneValid && !twoValid) return null;
	    if(!oneValid) return two;
	    if(!twoValid) return one;
	}
	else if(oneThrow == ROCK)
    	{
	    if (twoThrow == PAPER) return two;
	    if (twoThrow == SCISSORS) return one;
    	}
    	
    	else if(oneThrow == PAPER)
    	{
	    if (twoThrow == SCISSORS) return two;
	    if (twoThrow == ROCK) return one;
    	}
    	
    	else if(oneThrow == SCISSORS)
    	{
	    if (twoThrow == ROCK) return two;
	    if (twoThrow == PAPER) return one;
    	}
	return null;
    }

    private String getThrowName(int t)
    {
    	if(t == ROCK) return "ROCK";
    	if(t == PAPER) return "PAPER";
    	if(t == SCISSORS) return "SCISSORS";
    	return "invalid";
    }


}