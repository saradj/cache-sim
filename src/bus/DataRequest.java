package bus;

import cache.Cache;

public class DataRequest extends Request{
    public DataRequest(int senderId, BusEvent busEvent, int address, int cyclesToExecute) {
        super(senderId, busEvent, address, cyclesToExecute);
    }
}
