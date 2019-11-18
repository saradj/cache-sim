package bus;

import cache.Cache;
import cache.Protocol;
import common.Clocked;

import java.util.*;

public class Bus implements Clocked {
    private boolean isBusy;
    private List<Cache> caches;
    private Queue<Request> requests;
    Request processingRequest;
    Protocol protocol;
    public Bus(Protocol protocol){
        caches= new ArrayList<>();
        requests = new LinkedList<>();
        isBusy=false;
        this.protocol=protocol;
    }
    public void addCache(Cache cache){
        caches.add(cache);
        cache.linkBus(this);
    }
    public void requestMessage(Request request){
        requests.add(request);
    }
//    public void executeMessages(){
//        Request br = requests.poll();
//        Cache target_cache = requests.ge
//        CacheLine block = target_cache.getCacheBlock(br.getAddress());
    //}
    /*will be called in an infinite loop in main!*/
    public void processRequests(){
        if(processingRequest==null) {
            if (requests.isEmpty()) {
                isBusy = false;
                return;
            }
            processingRequest = requests.poll();
            isBusy = true;
        }
        if(processingRequest.getCyclesToExecute()==0) {
            executeTransition(processingRequest);
            caches.get(processingRequest.getSenderId()).notifyOver();
            processingRequest=null;
            return;
        }
        processingRequest.decrementCyclesToExecute();
    }

    private void executeTransition(Request processingRequest) {
        for(Cache cache: caches){
            cache.notifyChange(processingRequest);
        }
    }

    public boolean askOthers(Request processingRequest) {
        int addressNeeded= processingRequest.getAddress();
        int senderId=processingRequest.getSenderId();
        for(Cache cache:caches){
            if(cache.getId()!=senderId&&cache.containsBlock(addressNeeded))
                return true;
        }
        return false;
    }


//    private void executeTransition(Request processingRequest) {
//        if(protocol==Protocol.MESI){
//            switch (processingRequest.getBusEvent()){
//
//                case BusRd: switch (processingRequest.getSender().getState()){
//                    case 0://case invalid and rd go to shared, must notify modified
//                        for(Cache c: caches){
//                            c.notifyChange(processingRequest);
//                        }
//                }
//                    break;
//
//                case BusRdX:
//                    switch (processingRequest.getSender().getState()){
//                        case 0: //invalid
//                             for(Cache c: caches){
//                            c.notifyChange(processingRequest);
//                             }
//                             break;
//                        case 1: // exclusive only move this to modified
//                            processingRequest.getSender().notifyChange(processingRequest);
//                            break;
//                        case 2: //shared must move to modified and invalidate all others
//                            processingRequest.getSender().notifyChange(processingRequest);
//                            invalidateAllOther(processingRequest.getSender());
//                            break;
//                        case 3: //modified nothing happens!
//                            break;
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }else //dragon
//            switch (processingRequest.getBusEvent()){
//
//                case BusRd:
//                    break;
//                case BusUpd:
//                    break;
//                case Flush:
//                    break;
//            }
//    }
//
//    private void invalidateAllOther(Request request) {
//        for(Cache c:caches){
//            if(c.containsBlock(request.getAddress())&&
//                    !c.equals(request.getSender()))
//              //  c.getBlock(request.getAddress()).setState(Invalid);
//                return;
//        }
//    }


}
