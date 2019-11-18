package bus;

import cache.Cache;

public class DataRequest extends Request{
    public DataRequest(Cache sender, BusEvent busEvent, int address, int cyclesToExecute) {
        super(sender, busEvent, address, cyclesToExecute);
    }
}
