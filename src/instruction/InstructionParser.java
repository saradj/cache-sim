package instruction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public final class InstructionParser {

    private final static int HEX_RADIX = 16;

    private  InstructionParser (){};

    public static Queue<Instruction> parseInstructions (String fileName){
        Queue <Instruction> instructions = new LinkedList<>();

        try (Scanner scanner = new Scanner(new File(fileName))){
            while(scanner.hasNextLine()){
                InstructionType type = InstructionType.values()[scanner.nextInt()];
                int otherField = scanner.nextInt(HEX_RADIX);
                Instruction instruction = new Instruction(type,otherField);
                instructions.add(instruction);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File" + fileName + "not found");
        }
        catch (IllegalStateException e){
            System.out.println ("Queue maximum capacity reached");
        }

        return instructions;

    }
}

