package dragon;

import cache.Cache;
import cache.instruction.CacheInstruction;

public class DragonCache extends Cache {

    public DragonCache(int cacheSize, int blockSize, int associativity) {
        super(cacheSize, blockSize, associativity);
    }

    @Override
    public void ask(CacheInstruction instruction) {

    }
}
