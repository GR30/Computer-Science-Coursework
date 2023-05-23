import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;
import java.util.Calendar;

public class CellDeviceStore implements Assignment3Store
{
    public Queue<Device> Peach = new LinkedList<Device>();
    public Queue<Device> Cyborg = new LinkedList<Device>();
    public static boolean quit = false;
    
    public static void main(){
        CellDeviceStore store = new CellDeviceStore();
        for(int i=0; i<10; i++){
            Random rand = new Random();
            int type = rand.nextInt(2);
            store.enqueueDevice(new Device(type));
        }
        
        long time;
        System.out.println("Peach");
         while(!store.Peach.isEmpty()){ 
            time = store.dequeuePeach().getArrivalTime();
            System.out.println(time); 
        }            
        
        System.out.println("Cyborg");
         while(!store.Cyborg.isEmpty()){ 
            time = store.dequeueCyborg().getArrivalTime();
            System.out.println(time);
        }    
    }
    
    public void enqueueDevice(Device dev){
        long time = Calendar.getInstance().getTimeInMillis();
        dev.setArrivalTime(time);
        if (dev.getBrand() == 1){ Peach.add(dev); }
        else if (dev.getBrand() == 0){ Cyborg.add(dev); }
    }
    
    public Device dequeueCyborg(){
        return Cyborg.remove(); 
    }
    
    public Device dequeuePeach(){
        return Peach.remove();
    }
    
    public Device dequeueAnyDevice(){
        if (Peach.peek().isOlder(Cyborg.peek())){ return Peach.remove(); }
        else{ return Cyborg.remove(); }
    }
}
