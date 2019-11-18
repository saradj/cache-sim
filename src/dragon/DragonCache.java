package dragon;

import bus.BusEvent;
import bus.Request;
import cache.Cache;
import cache.instruction.CacheInstruction;

public class DragonCache extends Cache {

    public DragonCache(int id, int cacheSize, int blockSize, int associativity) {
        super(id, cacheSize, blockSize, associativity);
    }

    @Override
    public void notifyChange(Request processingRequest) {
        DragonCacheBlock dragonCacheBlock= (DragonCacheBlock) this.getCacheBlock(processingRequest.getAddress());
        BusEvent busEvent= processingRequest.getBusEvent();
        if(dragonCacheBlock==null)
            return;
        if(processingRequest.getSenderId()==this.getId()){
            switch (dragonCacheBlock.getState()){
                //shouldnt be null since it's this cache that generated the request for it
                case EXCLUSIVE: //if it's a read stay, if it's a write automaticaly you should be in M no bus events sent

                    break;
                case SM:
                    break;
                case SC: if(busEvent==BusEvent.BusUpd)
                    dragonCacheBlock.setState(DragonState.SM);//write back so not going to M
                    break;
                case MODIFIED:
                    break;
            }

        } else {//some other cache send the request
            switch (dragonCacheBlock.getState()){
                //shouldnt be null since it's this cache that generated the request for it
                case EXCLUSIVE:
                    if(busEvent==BusEvent.BusRd)
                        dragonCacheBlock.setState(DragonState.SC);
                    break;
                case SM:
                    if(busEvent==BusEvent.BusUpd)
                        dragonCacheBlock.setState(DragonState.SC);
                    break;
                case SC:
                    break;
                case MODIFIED:
                    if(busEvent==BusEvent.BusRd)
                        dragonCacheBlock.setState(DragonState.SM);
                    break;
            }

        }
    }

    private DragonCacheBlock getCacheBlock(int address) {
        //todo
        return null;
    }

    @Override
    public void ask(CacheInstruction instruction) {

    }
}
