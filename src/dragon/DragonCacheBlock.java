package dragon;

import bus.BusEvent;
import cache.CacheBlock;

public class DragonCacheBlock extends CacheBlock {

    private DragonState state;
    public DragonCacheBlock(int size) {
        super(size);
        this.state=DragonState.SC;
    }

    public DragonState getState() {
        return state;
    }

    public void setState(DragonState state) {
        this.state = state;
    }

    @Override
    public void update(BusEvent busEvent) {

    }
}
