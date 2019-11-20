import bus.Bus;
import bus.BusController;
import cache.Cache;
import common.Constants;
import dragon.DragonCache;
import cache.Protocol;
import cache.mesi.MesiCache;
import cpu.Cpu;
import instruction.Instruction;
import instruction.InstructionParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public final class Main {


    public static void main(String[] args) {

        if (args.length < 5) {
            System.err.println("Too few arguments, Usage : Protocol Input cache_size " +
                    "associativity block_size");
            System.exit(1);
        }

        String protocolString = args[0];
        String traceFile = args[1];
        int cacheSize = Integer.parseInt(args[2]);
        int associativity = Integer.parseInt(args[3]);
        int blockSize = Integer.parseInt(args[4]);
        Protocol protocol = protocolString.toLowerCase().equals("mesi") ? Protocol.MESI : Protocol.Dragon;

        List<Cpu> processors = new ArrayList<Cpu>();
        List<Cache> caches = new ArrayList<Cache>();
        BusController controller = new BusController();
        Bus bus = new Bus();
        bus.attachTo(controller);
        controller.attachTo(bus);

        String[] filenames = new String[Constants.NUM_CPUS];
        for (int i = 0; i < Constants.NUM_CPUS; i++) {
            filenames[i] = traceFile + "_" + i + ".data";
        }

        for (int i = 0; i < Constants.NUM_CPUS; i++) {
            Cache cache = protocol == Protocol.MESI ?
                    new MesiCache(i,cacheSize, blockSize, associativity) :
                    new DragonCache(i,cacheSize, blockSize, associativity);
            Cpu p = new Cpu(cache);
            Queue<Instruction> instructions = InstructionParser.parseInstructions(filenames[i]);
            p.setInstructions(instructions);
            processors.add(p);
            caches.add(cache);
            cache.linkCpu(p);
            controller.attach(cache);
        }

        //runUntilEnd(processors,caches,bus);
        printResults (processors,caches,bus);
    }

    private static void runUntilEnd(List<Cpu> processors,List<Cache>caches,Bus bus){

        while (!allFinished(processors)){
            bus.runForOneCycle();
            caches.forEach(c -> c.runForOneCycle());
            Collections.shuffle(processors);
            processors.forEach(p -> p.runForOneCycle());
        }
    }

    private static boolean allFinished(List <Cpu> processors){
        return processors.stream().allMatch(p -> p.finishedExecution());
    }

    private static void printResults(List<Cpu> processors,List<Cache>caches,Bus bus){

    }
}
