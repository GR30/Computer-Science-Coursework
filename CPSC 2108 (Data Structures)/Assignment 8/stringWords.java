public abstract class stringWords<T extends Comparable>
{
    private String word;
    private Integer count;
    abstract String getWord();
    abstract void upCnt();
    abstract Integer getCnt();
    abstract int compareTo(Word x);
}
