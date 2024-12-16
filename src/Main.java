import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class GridSlot {
    int X; // PosX
    int Z; // PosZ
    int V; // Value
}

public class Main {

    public static void main(String[] args) {
        //Settings
        int TotalX = 9;
        int TotalZ = 9;
        double MultiplyMines = 0.16;

        GeneratedGrid ResGrid = LevelGenerator.Generate(TotalX, TotalZ, MultiplyMines);

        Scanner scan = new Scanner(System.in);

        HashMap<Integer, HashMap<Integer, Integer>> DataGrid = ResGrid.DataGrid;
        HashMap<Integer, HashMap<Integer, Integer>> GameGrid = ResGrid.GameGrid;
        ArrayList<GridSlot> MinesSlot = ResGrid.MineSlot;
        HashMap<Integer, HashMap<Integer, Integer>> FlagSlot = new HashMap<>();

        Random rand = new Random();

        ArrayList<String> NumberType = new ArrayList<>();

        NumberType.add("M"); // Mine [0]
        NumberType.add("="); // Grass [1]
        NumberType.add("*"); // Flag [2]
        String ResultSTR = "Command Example: x1z2";

        GridLib.Mine(GameGrid, DataGrid, ResGrid.StartSlotX, ResGrid.StartSlotZ);

        boolean FlagMode = false;

        while (true) {
            GridLib.Print(GameGrid, NumberType, FlagSlot);
            System.out.println(ResultSTR);
            String input = scan.nextLine();

            ResultSTR = "Command Example: x1z2";

            int TargX = 0;
            int TargZ = 0;

            String CharT = "x";
            String NumAdd = "";

            if (input.equalsIgnoreCase("end")  || input.equalsIgnoreCase("End") ) {
                break;
            } else if (input.equals("reveal")) {
                GridLib.Print(DataGrid, NumberType, FlagSlot);
            } else if (input.equals("flag")) {
                if (FlagMode) {
                    FlagMode = false;
                    ResultSTR = "Flag Off";
                }else {
                    FlagMode = true;
                    ResultSTR = "Flag On";
                }
            } else {
                // Get the Position from Input
                for (int i = input.length() - 1; i >= 0; i--) {
                    char Curr = input.charAt(i);
                    String MySTR = String.valueOf(Curr);

                    if (Character.isLetter(Curr)) {
                        if (!CharT.equals("")) {
                            if (MySTR.equals("z") || MySTR.equals("Z")) {
                                TargX = Integer.parseInt(NumAdd);
                            } else if (MySTR.equals("x") || MySTR.equals("X")) {
                                TargZ = Integer.parseInt(NumAdd);
                            }
                            NumAdd = "";
                        }

                        CharT = MySTR;

                    } else if (Character.isDigit(Curr)) {
                        NumAdd = MySTR + NumAdd;
                    }




                }

                if (FlagMode) {
                    GridLib.Flag(FlagSlot, TargX, TargZ);
                    ResultSTR = "Flagged x" + TargZ + " z" + TargX;
                } else {
                    GridLib.Mine(GameGrid, DataGrid, TargX, TargZ);
                    ResultSTR = "Mined x" + TargZ + " z" + TargX;
                }

            }
        }
        scan.close();
    }


}