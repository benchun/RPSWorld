public class ScissorsCritter extends RPSCritter
{
    public int fight(RPSCritter opponent)
    {
	return RPSMatch.SCISSORS;
    }
    
    public String yourName()
    {
	return "ScissorsCritter";
    }
}