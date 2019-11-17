package cache;

import bus.Request;
import cache.instruction.CacheInstruction;
import cache.lru.LruQueue;
import cpu.Cpu;

public abstract class Cache {

    private final int cacheSize;
    private final int blockSize;
    private final int associativity;

    private final Cpu cpu;

    private final LruQueue[] lruQueues;
    protected final int numLines;
    protected CacheBlock[][] cacheBlocks;

    public Cache(int cacheSize, int blockSize, int associativity, Cpu cpu) {
        this.cacheSize = cacheSize;
        this.blockSize = blockSize;
        this.associativity = associativity;
        this.cpu = cpu;
        this.numLines = cacheSize / (blockSize * associativity);
        this.lruQueues = new LruQueue[this.numLines];

        for (int i = 0; i < numLines; i++){
            lruQueues[i] = new LruQueue(associativity);
        }
    }
        public void notifyOver() {
    }


    public int getState() {
    }

    public void notifyChange(Request processingRequest) {

    }

    public boolean containsBlock(int address) {
    }
    public abstract void ask (CacheInstruction instruction);

    private int getTag (int address){
        return address / numLines;
    }
    private int getLineNumber (int address){
        return address % numLines;
    }

}
