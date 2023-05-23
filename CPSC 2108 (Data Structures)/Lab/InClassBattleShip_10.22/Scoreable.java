
/**
 * Write a description of class Scoreable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scoreable
{
    public int CurrentScore;
    public int ScoreToWin;
    
    public Scoreable(int ScoreToWin)
    {
        CurrentScore = 0;
        this.ScoreToWin = ScoreToWin;
    }
    
    public void addPoint()
    {
        CurrentScore++;
    }
    
    public int GetScore()
    {
        return CurrentScore;
    }
    
    public Boolean HasWon()
    {
        return CurrentScore >= ScoreToWin;
    }
}
