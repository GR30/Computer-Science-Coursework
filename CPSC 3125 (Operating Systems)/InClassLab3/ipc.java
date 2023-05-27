//This is your driver class.
class Driver {
public static void main(String [ ] args) {
Process p1 = new Process(1); 
Process p2 = new Process(2);
p1.setPriority(5);
p2.setPriority(5);
p1.start();
p2.start();
}
}//end class Driver
//This class implements a simulator for a process
class Process extends Thread {
private int process_id ;
public Process(int i) {
process_id = i ;
}
private int random(int n) {
return (int) Math.round( n * Math.random() - 0.5 ) ;
}
private void do_something() { 
try{ sleep(random(1000)) ; } catch (InterruptedException e) { }
}
private void non_critical() {
System.out.println("PROCESS " + process_id + " is now inside non_critical().") ;
do_something() ;
}
private void critical() {
System.out.println("PROCESS " + process_id + " has left non_critical() and is now inside critical().") ;
do_something() ;
System.out.println("PROCESS " + process_id + " is leaving critical().") ;
}
public void run() {
for (int i=1;i<=25;i++ )
{ 
non_critical() ; 
critical() ;
} 
}//end run()
}//end class Process

// Class Semaphore implements a counting semaphore.
// It acts as a mutex if the value field is initialized to 1.
// Notice the wait() and notify() function calls. (i.e., sleep and wakeup)
class Semaphore {
protected int value = 0 ;
protected Semaphore() { value = 0 ; }
protected Semaphore(int initial) { value = initial ; }
public synchronized void down() {
value-- ;
if (value < 0)
try { wait() ; } catch( InterruptedException e ) { }
}
public synchronized void up() {
value++ ; if (value <= 0) notify() ;
}//end up()
}//end class Semaphore
