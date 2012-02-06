public class PaperCritter extends RPSCritter
{
    public int fight(RPSCritter opponent)
    {
	return RPSMatch.PAPER;
    }
    
    public String yourName()
    {
	return "PaperCritter";
    }
}