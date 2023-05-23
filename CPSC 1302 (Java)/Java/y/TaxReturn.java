
/**
 * Write a description of class TaxReturn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TaxReturn
{
   public static final int SINGLE = 1;
   public static final int MARRIED = 2;
   
   public static final double RATE1 = 0.10;
   public static final double RATE2 = 0.25;
   public static final double RATE1_SINGLE_LIMIT = 32000;
   public static final double RATE1_MARRIED_LIMIT = 64000;
   
   private double income;
   private int status;
   
   //constructor
   public TaxReturn(double anIncome, int aStatus){
       income = anIncome;
       status = aStatus;
   }
   
   public double getTax(){
       double tax1 = 0;
       double tax2 = 0;
       
       if (status == SINGLE){
           if(income <= RATE1_SINGLE_LIMIT){
               tax1 = RATE1 * income; 
           }
           else{
               tax1 = RATE1 * RATE1_SINGLE_LIMIT;
               tax2 = RATE1 * (income - RATE1_SINGLE_LIMIT);
           }
       }
       else{
           if(income <= RATE1_MARRIED_LIMIT){
               tax1 = RATE2 * income; 
           }
           else{
               tax1 = RATE2 * RATE1_MARRIED_LIMIT;
               tax2 = RATE2 * (income - RATE1_MARRIED_LIMIT);
           }
       }
       return tax1+tax2;
   }
   
}
