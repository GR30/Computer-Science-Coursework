// Lab Exercise 1: PieceWorker.java
// PieceWorker class extends Employee.

public class PieceWorker extends Employee 
{
   double wage = 0.0;
   double pieces = 0;

   // five-argument constructor
   public PieceWorker( String first, String last, String ssn, 
      double wagePerPiece, int piecesProduced )
   {
      super( first, last, ssn);
      wage = wagePerPiece;
      pieces = piecesProduced;
   } // end five-argument PieceWorker constructor

   public void setWage(double x){wage = x;}
   
   public double getWage(){ return wage;}

   public void setPiecesProduced(int x){pieces = x;}

   public double getPiecesProduced(){return pieces;}

   public double earnings(){return wage * pieces;} 

   public String toString()
   {
       return String.format( "%s: %s\n%s: %,.0f; %s: %,.2f", 
          "peiceworker employee", super.toString(), 
          "peices produced", getPiecesProduced(), 
          "wage per piece produced", getWage() );
   }
} 

