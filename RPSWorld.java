import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashMap;


public class RPSWorld extends ActorWorld
{
    private static int day = 0;
    private static boolean complete = false;
    private static int[] populationRecord = new int[365];
    private static HashMap<Class, ArrayList<RPSMatch>> matchRecord = new HashMap<Class, ArrayList<RPSMatch>>();


    // constructors

    public RPSWorld()
    {
    }
    
    public RPSWorld(Grid<Actor> grid)
    {
        super(grid);
    }


    /**
     * Getter method for the current day. Maybe you want to change
     * your strategy as time passes.
     *
     * @return The current day in this RPSWorld
     */
    public static int getDay()
    {
	return day;
    }
    
    /**
     * This method can be called by an RPSCritter to help it decide
     * what to throw in a given match.
     *
     * @param c - The class whose match record you would like
     *
     * @return An ArrayList containing RPSMatch objects that represent
     * every match that an RPSCritter of class c has been part of
     * during the entire history of this RPSWorld
     */
    public static ArrayList<RPSMatch> getMatchRecord(Class c)
    {
	return matchRecord.get(c);
    }


    // boring implementation details

    public void step()
    {
    	if(complete) return;
    	
    	System.out.println("\n> > >  RPSWORLD -- Day " + day +"  < < <");
    	
    	// find all the RPSCritters
    	Grid<Actor> gr = getGrid();
        ArrayList<RPSCritter> fighters = new ArrayList<RPSCritter>();
        TreeSet<String> fighterClasses = new TreeSet<String>();
        for (Location loc : gr.getOccupiedLocations())
	{
	    if (gr.get(loc) instanceof RPSCritter)
	    {
		RPSCritter r = (RPSCritter)gr.get(loc);
		fighters.add(r);
		fighterClasses.add(r.getClass().toString());
		System.out.println(r.yourName() + " at " + r.getLocation());
	    }
	}
        
        // check for winner
        if(fighterClasses.size() == 1)
	{
	    System.out.print("\tWe have a champion: "+ fighters.get(0).yourName());
	    System.out.println(" ("+fighters.get(0).getClass() +")");
	    complete = true;
	    return;
	}
        
        // if the world has had the same population for too long, add some new blood
        populationRecord[day%populationRecord.length] = fighters.size();

        //System.out.println("RPSWorld population = " + Arrays.toString(populationRecord));

        boolean allSame = true;
	for(int f=0; f<populationRecord.length-1; f++)
	{
	    if(populationRecord[f] != populationRecord[f+1])
	    {
		allSame = false;
		break;
	    }
	}
	if(allSame)
	{
	    System.out.println("*** STAGNANT POPULATION -- ADDING MORE CRITTERS ***");
	    ArrayList<RPSCritter> newborns = new ArrayList<RPSCritter>();
	    RPSRunner.addOneOfEach(newborns);
	    addRandomly(newborns);
	}
        
        // neighbors always fight
        for (RPSCritter r : fighters)
	{
	    // maybe r was defeated by a previous RPSCritter
	    if (r.getGrid() == gr)
            {
		ArrayList<Location> battleGrounds = gr.getOccupiedAdjacentLocations(r.getLocation());
		for(Location hotSpot : battleGrounds)
		{
		    // maybe r lost to a previous neighbor
		    if (r.getGrid() != gr) break;
		    // if r is still alive, check for a fight
		    if(gr.get(hotSpot) instanceof RPSCritter)
		    {
			playMatch(r, (RPSCritter)gr.get(hotSpot));
		    }
		}
	    }
        }
        
       	// then everyone gets to act
    	super.step();
    	day++;
    }
    
    public void addRandomly(ArrayList<RPSCritter> contestants)
    {
	while(contestants.size() > 0)
	{
	    int randIndex = (int)(Math.random()*contestants.size());
	    RPSCritter randCrit = contestants.get(randIndex);
	    Location randLoc = getRandomEmptyLocation();
	    add(randLoc, randCrit);
	    contestants.remove(randIndex);
	}
    }

    public static void playMatch(RPSCritter one, RPSCritter two)
    {
	String oneId = one.yourName() + " (" + one.getClass() + ")";
	String twoId = two.yourName() + " (" + one.getClass() + ")";
    	System.out.println("\n\tMatch between " + oneId + " and " + twoId);

	RPSMatch m = new RPSMatch(one, two);

    	if (m.isDraw())
	    System.out.println("\t> draw, both shall live");
	else if(m.getWinner() == one)
	    declareVictory(one, two);
	else
	    declareVictory(two, one);

	enterMatchInRecord(one, m);
	enterMatchInRecord(two, m);
    }

    private static void enterMatchInRecord(RPSCritter c, RPSMatch m)
    {
	// lazy init for matchRecord ArrayLists
	if(!matchRecord.containsKey(c.getClass()))
	{
	    matchRecord.put(c.getClass(), new ArrayList<RPSMatch>() );
	}

	matchRecord.get(c.getClass()).add(m);
    }

        
    private static void declareVictory(RPSCritter winner, RPSCritter loser)
    {
    	System.out.println("\t> " + winner.yourName() + " triumphs!");
    	System.out.println("\t> " + loser.yourName() + " shall perish");
    	loser.removeSelfFromGrid(); 
    }
    

}