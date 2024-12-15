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
        int TotalX = 14;
        int TotalZ = 14;
        double MultiplyMines = 0.16;

        GeneratedGrid ResGrid = LevelGenerator.Generate(TotalX, TotalZ, MultiplyMines);

        Scanner scan = new Scanner(System.in);

        HashMap<Integer, HashMap<Integer, Integer>> DataGrid = ResGrid.DataGrid;
        HashMap<Integer, HashMap<Integer, Integer>> GameGrid = ResGrid.GameGrid;
        ArrayList<GridSlot> MinesSlot = ResGrid.MineSlot;

        Random rand = new Random();

        ArrayList<String> NumberType = new ArrayList<>();

        NumberType.add("M"); // Mine [0]
        NumberType.add("="); // Grass [1]
        NumberType.add("F"); // Flag [2]

        while (true) {
            System.out.println("Put Code?");
            String str = scan.nextLine();

            System.out.println("Checking");
            if (str.equalsIgnoreCase("Run")) {

                break;
            }
            System.out.println("Nope!");
        }

        GridPrint.Print(DataGrid, NumberType);

        scan.close();


    }
}