package cache.mesi;

import cache.Cache;
import cache.instruction.CacheInstruction;
import cpu.Cpu;

public final class MesiCache extends Cache {


    public MesiCache(int cacheSize, int blockSize, int associativity) {
        super(cacheSize, blockSize, associativity);
        this.cacheBlocks = new MesiCacheBlock [numLines][associativity];
        for (int i = 0; i < numLines;i++){
            for(int j = 0; j < associativity ;j++){
                cacheBlocks[i][j] = new MesiCacheBlock(blockSize);
            }
        }
    }

    @Override
    public void ask(CacheInstruction instruction) {

    }
}
