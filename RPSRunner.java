import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;

import java.awt.Color;
import java.util.ArrayList;

public class RPSRunner
{
    public static void main(String[] args)
    {
    	int gridSize = 25;
    	int numEach = 10;
    	
        RPSWorld world = new RPSWorld( new BoundedGrid<Actor>(gridSize,gridSize) );

        ArrayList<RPSCritter> contestants = new ArrayList<RPSCritter>();
        for(int i=0; i<numEach; i++)
        {
        	addOneOfEach(contestants);
        }

      	world.addRandomly(contestants);
        
        world.show();
    }

    public static void addOneOfEach(ArrayList<RPSCritter> contestants)
    {
	contestants.add( new RockCritter() );
	contestants.add( new PaperCritter() );
	contestants.add( new ScissorsCritter() );
    }
	
}