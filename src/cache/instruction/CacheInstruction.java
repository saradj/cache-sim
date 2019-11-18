package cache.instruction;

import instruction.Instruction;
import instruction.InstructionType;

public final class CacheInstruction extends Instruction {

    private final int address;
    private final InstructionType cacheInstructionType;

    public CacheInstruction(InstructionType cacheInstructionType, int address) {
        super(cacheInstructionType,address);
        this.address = address;
        this.cacheInstructionType = cacheInstructionType;
    }

    public int getAddress() {
        return address;
    }

    public InstructionType getCacheInstructionType() {
        return cacheInstructionType;
    }
}

