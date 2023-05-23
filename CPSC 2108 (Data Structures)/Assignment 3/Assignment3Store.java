/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

/**
 *
 * @author csu
 */
public interface Assignment3Store 
{
    public void enqueueDevice(Device dev);
    public Device dequeueAnyDevice();
    public Device dequeuePeach();
    public Device dequeueCyborg();
    
}
