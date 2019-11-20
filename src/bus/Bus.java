package bus;

import cache.Cache;
import cache.Protocol;
import common.Clocked;
import common.Constants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public final class Bus implements Clocked {

    private BusController busController;
    private Request currentRequest;


    public Bus(){
        this.currentRequest = null;
    }

    public void attachTo (BusController controller){
        this.busController = controller;
    }

    @Override
    public void runForOneCycle() {
        if (currentRequest != null){
            currentRequest.decrementCyclesToExecute();
        }
        if (currentRequest.done()){
            busController.alert();
        }
    }

    public void setCurrentRequest(Request request){
        this.currentRequest = request;
    }

public boolean askOthers(int senderId, int address) {

    for (Cache cache : caches) {
        if (cache.getId() != senderId && cache.cacheHit(address))
            return true;
    }
    return false;
}

}

