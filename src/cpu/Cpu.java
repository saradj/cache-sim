package cpu;

import cache.Cache;
import cache.instruction.CacheInstruction;
import cache.instruction.CacheInstructionType;
import instruction.Instruction;

import java.util.LinkedList;

import java.util.Queue;

public final class Cpu {

    private final Cache cache;

    private Queue<Instruction> instructions;
    private CpuState state;
    private long cycleCount;
    private int instructionCount;

    private int executingCyclesLeft;

    private int totalComputingCycles;
    private int totalIdleCycles;
    private int numLoadStore;

    public Cpu(Cache cache) {
        this.cache = cache;
        this.cycleCount = 0;
        this.instructionCount = 0;
        this.state = CpuState.IDLE;
        instructions = new LinkedList<>();
        executingCyclesLeft = 0;
        totalComputingCycles = 0;
        totalIdleCycles = 0;
        numLoadStore = 0;
    }

    public void executeOneCycle (){
        switch (state){
            case IDLE:
                Instruction instruction = instructions.poll();
                if (instruction != null){
                    executeInstruction (instruction);
                    instructionCount ++;
                }
                break;
            case BLOCKING:
                totalIdleCycles ++;
                break;
            case EXECUTING:
                executingCyclesLeft --;
                if (executingCyclesLeft == 0){
                    setState(CpuState.IDLE);
                }
        }
        cycleCount ++;
    }

    private void executeInstruction(Instruction instruction) {
        switch (instruction.getType()){
            case READ: {
                numLoadStore ++;
                CacheInstruction cacheInstruction = new CacheInstruction(CacheInstructionType.READ,
                        instruction.getSecondField());
                cache.ask(cacheInstruction);
                setState(CpuState.BLOCKING);
                break;
            }
            case WRITE: {
                numLoadStore ++;
                CacheInstruction cacheInstruction = new CacheInstruction(CacheInstructionType.WRITE,
                        instruction.getSecondField());
                cache.ask(cacheInstruction);
                setState(CpuState.BLOCKING);
                break;
            }
            case OTHER: {
                this.executingCyclesLeft = instruction.getSecondField();
                setState(CpuState.EXECUTING);
                break;
            }
        }
    }

    public void setInstructions(Queue<Instruction> instructions) {
        this.instructions = new LinkedList<>(instructions);
    }


    public void wake(){
        assert (this.state == CpuState.BLOCKING);
        setState(CpuState.IDLE);
    }


    private void setState(CpuState state) {
        this.state = state;
    }


}

