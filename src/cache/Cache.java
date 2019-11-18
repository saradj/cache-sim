package cache;

import bus.Bus;
import bus.Request;
import cache.instruction.CacheInstruction;
import cache.lru.LruQueue;
import common.Clocked;
import cpu.Cpu;
import dragon.DragonCacheBlock;

import java.time.Clock;

public abstract class Cache implements Clocked {

    private final int cacheSize;
    private final int blockSize;
    private final int associativity;

    private Cpu cpu;

    private final LruQueue[] lruQueues;
    protected final int numLines;
    protected CacheBlock[][] cacheBlocks;
    private final int id;
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

    public int getId(){
        return id;
    }
    public void notifyOver() {
    }
    
    public void linkBus(Bus bus){
        this.bus=bus;
    }

    public abstract void notifyChange(Request processingRequest) ;

    public boolean containsBlock(int address) {
        return true;
    }

    public abstract void ask(CacheInstruction instruction);

    private int getTag(int address) {
        return address / numLines;
    }

    private int getLineNumber(int address) {
        return address % numLines;
    }
    public Bus getBus(){
        return bus;
    }

    public void linkCpu(Cpu p) {
        this.cpu = p;
    }

}
