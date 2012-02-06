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
    	int gridSize = 50;
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
    	// BUILT-IN CONTESTANTS
		contestants.add( new RockCritter() );
		contestants.add( new PaperCritter() );
		contestants.add( new ScissorsCritter() );
		contestants.add( new RandomCritter() );
		contestants.add( new AvoidCritter() );
		
		// THIRD PERIOD CONTESTANTS
		contestants.add( new BrickCritter() );
		contestants.add( new AngryCritter() );
		contestants.add( new BieberMafia() );
		contestants.add( new TechSupportBear() );
		//contestants.add( new YouMad() );
		contestants.add( new BruceLeeCritter() );
		contestants.add( new NotGoingToWinCritter() );
		contestants.add( new PacManCritter() );
		contestants.add( new AbbyCritter() );
		contestants.add( new DoggyCritter() );
		//contestants.add( new Shield() );
		contestants.add( new JaeyuujiCritter() );
		contestants.add( new HeartStopperCritter() );
		contestants.add( new AngelaCritter() );
		contestants.add( new JanitorCritter() );
		contestants.add( new RPSSauceCritter() );
		contestants.add( new TotoroCritter() );
		contestants.add( new UncleSam() );
		contestants.add( new Symbol() );
		contestants.add( new PokéCritter() );
		contestants.add( new Annihilator() );
		contestants.add( new ScottCritter() );
		contestants.add( new CactuarCritter() );
		contestants.add( new GoldfishCritter() );

		// DISQUALIFIED - COMPILER PROBLEMS
		//contestants.add( new CindyCritter() );
	
		// DISQUALIFIED - RUNTIME EXCEPTIONS	
		//contestants.add( new SmartCritter() );
		//contestants.add( new KingArthur() );
		
    }
	
}