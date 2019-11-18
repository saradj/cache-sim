package dragon;

import bus.Request;
import cache.Cache;
import cache.instruction.CacheInstruction;

public class DragonCache extends Cache {

    public DragonCache(int id, int cacheSize, int blockSize, int associativity) {
        super(id, cacheSize, blockSize, associativity);
    }

    @Override
    public void notifyChange(Request processingRequest) {

    }

    @Override
    public void ask(CacheInstruction instruction) {

    }
}
