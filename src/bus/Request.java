package bus;

import cache.Cache;

public class Request {
    Cache sender;
    private int cyclesToExecute;
    private BusEvent busEvent;
    private int address;
    public Request(Cache sender, BusEvent busEvent, int address, int cyclesToExecute){
        this.sender=sender;
        this.address=address;
        this.cyclesToExecute=cyclesToExecute;
        this.busEvent=busEvent;
    }
public int getCyclesToExecute(){
        return cyclesToExecute;
}
    public Cache getSender() {
        return sender;
    }
    public void decrementCyclesToExecute(){
        cyclesToExecute--;
    }
    public BusEvent getBusEvent(){
        return busEvent;
    }

    public int getAddress() {
        return address;
    }
}
