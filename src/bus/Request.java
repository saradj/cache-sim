package bus;

import cache.Cache;

public class Request {
    private int senderId;
    private int cyclesToExecute;
    private BusEvent busEvent;
    private int address;
    public Request(int senderId, BusEvent busEvent, int address, int cyclesToExecute){
        this.senderId=senderId;
        this.address=address;
        this.cyclesToExecute=cyclesToExecute;
        this.busEvent=busEvent;
    }
public int getCyclesToExecute(){
        return cyclesToExecute;
}
    public int getSenderId() {
        return senderId;
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
