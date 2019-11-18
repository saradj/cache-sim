package cache;

import bus.Bus;
import bus.Request;
import cache.instruction.CacheInstruction;
import cache.lru.LruQueue;
import common.Clocked;
import cpu.Cpu;
import dragon.DragonCacheBlock;


public abstract class Cache implements Clocked {

    private final int cacheSize;
    private final int blockSize;
    private final LruQueue[] lruQueues;


    protected final int numLines;
    protected final int associativity;
    protected final int id;

    private Cpu cpu;
    private Bus bus;

    public Cache(int id, int cacheSize, int blockSize, int associativity) {

        this.id=id;
        this.cacheSize = cacheSize;
        this.blockSize = blockSize;
        this.associativity = associativity;
        this.numLines = cacheSize / (blockSize * associativity);
        this.lruQueues = new LruQueue[this.numLines];
        for (int i = 0; i < numLines; i++) {
            lruQueues[i] = new LruQueue(associativity);
        }
    }

    public abstract void notifyChange(Request processingRequest) ;
    public abstract void ask(CacheInstruction instruction);

    public int getId(){
        return id;
    }
    public void notifyOver() {
    }

    public void linkBus(Bus bus){
        this.bus = bus;
    }
    public boolean containsBlock(int address) {
        return true;
    }
    public Bus getBus(){
        return bus;
    }
    public void linkCpu(Cpu p) {
        this.cpu = p;
    }

    protected int getTag(int address) {
        return address / numLines;
    }
    protected int getLineNumber(int address) {
        return address % numLines;
    }


}
