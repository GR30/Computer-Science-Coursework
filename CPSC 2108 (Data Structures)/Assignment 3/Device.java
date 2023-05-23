/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

/**
 *
 * @author csu
 */
public class Device 
{
    public static final int CYBORG_VAL = 0;
    public static final int PEACH_VAL = 1;
    
    
    private long arrivalTime;
    private int deviceBrand;
    
    public Device(int brand)
    {
        this.deviceBrand = brand;
    }        
    
    public long getArrivalTime()
    {
        return arrivalTime;
    }        
    
    public void setArrivalTime(long time)
    {
        this.arrivalTime = time;
    }    
    
    public int getBrand()
    {
        return this.deviceBrand;
    }      
    
    public boolean isOlder(Device dev)
    {
        if(this.arrivalTime < dev.arrivalTime)
        {
            return true;
        }    
        return false;
    }        
}
