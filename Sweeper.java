import info.gridworld.grid.Location;

import java.util.ArrayList;

public class Sweeper extends RPSCritter
{
	private int sign;

	public Sweeper(int dir, int sign)
	{
		setDirection(dir);
		this.sign = sign;
		setColor(null);
	}
	
	public int fight(RPSCritter opponent)
    {
    	return sign;
    }
	
	public Location selectMoveLocation(ArrayList<Location> locs)
    {
    	Location next = getLocation().getAdjacentLocation(getDirection());
    	if(locs.contains(next))
    		return next;
    	return null;
    }
    
    public String yourName()
    {
		return "Sweet Sweeper";
    }
}