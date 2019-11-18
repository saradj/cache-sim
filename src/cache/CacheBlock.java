package cache;

import bus.BusEvent;

public abstract class CacheBlock {

    private final int size;

    private int tag;
    private boolean valid;

    public CacheBlock(int size) {
        this.size = size;
        this.valid = false;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public abstract void update(BusEvent busEvent);

}
