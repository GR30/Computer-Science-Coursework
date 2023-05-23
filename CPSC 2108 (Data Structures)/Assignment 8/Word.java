
/**
 * Write a description of class Word here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Word extends stringWords
{
    private String word;
    private Integer count;
    public Word(String word){
        this.word = word;
        count = 0;
    }

    public String getWord(){ return word; }
    public void upCnt(){ count++; }
    public Integer getCnt(){ return count; }
    public int compareTo(Word x){ 
        if(this.count > x.getCnt()){ return 1; }
        else if(this.count < x.getCnt()){ return -1; }
        else{ return 0; }
    }
    public String toString(){ return word + "\t(" + count + ")"; }
}
