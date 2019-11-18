package cache.instruction;

import instruction.Instruction;
import instruction.InstructionType;

public final class CacheInstruction  {

    private final int address;
    private final CacheInstructionType cacheInstructionType;

    public CacheInstruction(CacheInstructionType cacheInstructionType, int address) {
        this.address = address;
        this.cacheInstructionType = cacheInstructionType;
    }

    public int getAddress() {
        return address;
    }

    public CacheInstructionType getCacheInstructionType() {
        return cacheInstructionType;
    }
}

