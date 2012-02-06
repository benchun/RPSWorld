import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;

/**
 * An RPSCritter resolves disagreements over territory with other
 * RPSCritters in the time-honored tradition of warriors everywhere:
 * by playing the game Rock-Paper-Scissors.
 *
 * Since disputes are handled in accordance with the outcome of a
 * match, RPSCritters must not add, move, or remove any Actors
 * directly. On other words, an RPSCritter may never call
 * removeSelfFromGrid or putSelfInGrid, may never call the Grid
 * methods put or remove, and may never call the method moveTo on
 * anyone except itself.
 *
 * An RPSCritter must never call the fight method of another
 * RPSCritter.
 *
 * Finally, RPSCritter subclasses may never call any method of
 * RPSWorld, with the exception of RPSWorld.getMatchRecord
 */
public abstract class RPSCritter extends Critter
{	
    /**
     * This method is called by RPSWorld.playMatch when this
     * RPSCritter is in a match with another RPSCritter.
     *
     * Postcondition: the state of opponent is unchanged
     *
     * @param opponent - The other RPSCritter
     *
     * @return Must be one of RPSMatch.ROCK, RPSMatch.PAPER, or
     * RPSMatch.SCISSORS
     */
    public abstract int fight(RPSCritter opponent);
	
	
    /**
     * @return An identifying String for this RPSCritter
     */
    public abstract String yourName();
	
	
    /** 
     * An RPSCritter is not allowed to move into another RPSCritter's
     * current Location without playing a match
     *
     * @param newLocation - The new location
     */
    public final void moveTo(Location newLocation)
    {
    	if(newLocation == getLocation()) return;
    	
    	Grid grid = getGrid(); 
    	if (grid == null) return;
    	
    	if((grid.get(newLocation)) != null)
    	{
		    if(grid.get(newLocation) instanceof RPSCritter)
	    	{
				RPSWorld.playMatch(this, (RPSCritter)grid.get(newLocation));
		    }
	    }
    	// if we're still in the grid, that means we won
    	if(getGrid() == grid)
    	{
		    // well, unless it was a draw and our newLocation still
		    // isn't empty. in which case, we just don't move
		    if((grid.get(newLocation)) == null)
		    {
				super.moveTo(newLocation);
		    }
    	}
    }
    
    /**
     * An RPSCritter can only move one step at a time
     *
     * @return a list of possible locations for the next move
     */
	public final ArrayList<Location> getMoveLocations()
	{
		ArrayList<Location> options = getGrid().getValidAdjacentLocations(getLocation());
		options.add(getLocation());
		return options;
	}


    // superclass methods that may not be overriden
    public final void putSelfInGrid(Grid<Actor> gr, Location loc)
    {
    	super.putSelfInGrid(gr, loc);
    }
    public final void removeSelfFromGrid()
    {
    	super.removeSelfFromGrid();
    }
    public final void act()
    {
    	super.act();
    }
    public final Grid<Actor> getGrid()
    {
    	return super.getGrid();
    }
    public final Location getLocation()
    {
    	return super.getLocation();
    }
}
