package bus;

import cache.Cache;
import cache.Protocol;
import common.Clocked;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bus implements Clocked {
    private boolean isBusy;
    private List<Cache> caches;
    private List<Request> requests;
    Request processingRequest;

    public Bus() {
        caches = new ArrayList<>();
        requests = new LinkedList<>();
        isBusy = false;

    }

    public boolean isBusy(){
        return isBusy;
    }

    public void addCache(Cache cache) {
        caches.add(cache);
        cache.linkBus(this);
    }

    public void addRequest(Request request) {
        requests.add(request);
    }

    //}
    /*will be called in an infinite loop in main!*/
    public void processRequests() {
        if (processingRequest == null) {
            if (requests.isEmpty()) {
                isBusy = false;
                return;
            }
            processingRequest = requests.remove(0);
            isBusy = true;
        }
        if (processingRequest.getCyclesToExecute() == 0) {
            executeTransition(processingRequest);
            //caches.get(processingRequest.getSenderId()).notifyOver(); will happen by executing the transition
            processingRequest = null;
            return;
        }
        processingRequest.decrementCyclesToExecute();
    }

    private void executeTransition(Request processingRequest) {
        for (Cache cache : caches) {
            cache.notifyChange(processingRequest);
        }
    }

    public void flushClean(DataRequest request){ //reading data from E
        requests.add(0,request);
    }

    public void flushMemory(DataRequest request){ //reading data from M
        requests.add(0,request);
    }

    public boolean askOthers(Request processingRequest) {
        int addressNeeded = processingRequest.getAddress();
        int senderId = processingRequest.getSenderId();
        for (Cache cache : caches) {
            if (cache.getId() != senderId && cache.containsBlock(addressNeeded))
                return true;
        }
        return false;
    }


}
