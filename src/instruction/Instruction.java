package instruction;

public final class Instruction {

    private final InstructionType type;
    private final int secondField;

    public InstructionType getType() {
        return type;
    }

    public int getSecondField() {
        return secondField;
    }

    public Instruction(InstructionType type, int secondField) {
        this.type = type;
        this.secondField = secondField;
    }

}
