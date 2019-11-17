package cache.mesi;

import cache.CacheBlock;

public final class MesiCacheBlock extends CacheBlock {

    private MesiState mesiState;

    public MesiCacheBlock(int size) {
        super(size);
        mesiState = MesiState.INVALID;
    }

    @Override
    public void update(BusEvent busEvent) {

    }
}
