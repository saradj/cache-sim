import bus.Bus;
import cache.Cache;
import dragon.DragonCache;
import cache.Protocol;
import cache.mesi.MesiCache;
import cpu.Cpu;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String s_protocol = args[0];
        Protocol protocol= s_protocol.equals("MESI")? Protocol.MESI:Protocol.Dragon;
        String trace_file = args[1];
        int cache_size = Integer.parseInt(args[2]);
        int associativity = Integer.parseInt(args[3]);
        int block_size = Integer.parseInt(args[4]);
        ArrayList<Cpu> processors = new ArrayList<Cpu>();
        Bus bus = new Bus(protocol);

        File directory = new File(trace_file);
        File[] files=directory.listFiles();

        for (int i = 0; i < 4; i++) {
            Cache cache = protocol==Protocol.MESI? new MesiCache(cache_size, block_size,associativity):new DragonCache(cache_size,block_size,associativity);
            Cpu p = new Cpu(cache, files[i].getAbsolutePath());//and input file then?
            processors.add(p);
            cache.linkCpu(p);
            bus.addCache(cache);
        }

}
