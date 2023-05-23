

public class Student
{
   private int studentID;
   private String firstName;
   private String lastName;
   private double grade;
   
   // no-argument constructor calls other constructor with default values
   public Student() 
   {
      this( 0, "", "", 0.0 ); // call four-argument constructor
   } // end no-argument Student constructor
  
   // initialize a record
   public Student( int id, String first, String last, double grade )
   {
      setStudentID( id );
      setFirstName( first );
      setLastName( last );
      setGrade( grade );
   } // end four-argument Student constructor

   // set student ID number   
   public void setStudentID( int id )
   {
      studentID = id;
   } // end method setStudentID

   // get student ID number   
   public int getStudentID() 
   { 
      return studentID; 
   } // end method getStudentID
   
   // set first name   
   public void setFirstName( String first )
   {
      firstName = first;
   } // end method setFirstName

   // get first name   
   public String getFirstName() 
   { 
      return firstName; 
   } // end method getFirstName
   
   // set last name   
   public void setLastName( String last )
   {
      lastName = last;
   } // end method setLastName

   // get last name   
   public String getLastName() 
   {
      return lastName; 
   } // end method getLastName
   
   // set grade  
   public void setGrade( double gradeValue )
   {
      grade = gradeValue;
   } // end method setGrade

   // get grade   
   public double getGrade() 
   { 
      return grade; 
   } // end method getGrade
} // end class Student

