import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class GeneratedGrid {
    HashMap<Integer, HashMap<Integer, Integer>> DataGrid;
    HashMap<Integer, HashMap<Integer, Integer>> GameGrid;
    ArrayList<GridSlot> MineSlot;
}

public class LevelGenerator {


    public static GeneratedGrid Generate(int TotalX, int TotalZ, double MultiplyMines){
        HashMap<Integer, HashMap<Integer, Integer>> MyGrid = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Integer>> GameGrid = new HashMap<>();
        ArrayList<GridSlot> MinesSlot = new ArrayList<>();
        Random rand = new Random();

        //Total Values
        int TotalMines = Math.toIntExact(Math.round((TotalX * TotalZ) * MultiplyMines));

        // Creating the Grid Hash
        for (int GX=1; GX<=TotalX; GX++) {
            HashMap<Integer, Integer> HashX = new HashMap<>();

            for (int GZ=1; GZ<=TotalZ; GZ++) {
                HashX.put(GZ, 0);
            }

            MyGrid.put(GX, HashX);
        }

        // Add Mines to randomly position
        for (int i=1; i<=TotalMines; i++) {
            for (int t=1; t<=10; t++) {
                int x = rand.nextInt(1, TotalX);
                int z = rand.nextInt(1, TotalZ);

                HashMap<Integer, Integer> HashX = MyGrid.get(x);

                if (HashX.get(z) == 0) {
                    HashX.put(z, -1);

                    GridSlot MySlot = new GridSlot();

                    MySlot.X = x;
                    MySlot.Z = z;
                    MySlot.V = -1;

                    MinesSlot.add((MinesSlot.size() - 1) + 1, MySlot);

                    break;
                }
            }
        }


        // Add Warning Num
        for (GridSlot MySlot : MinesSlot) {
            for (int a = 0; a <= 2; a++) {
                for (int b = 0; b <= 2; b++) {

                    int x = a - 1;
                    int z = b - 1;

                    int GX = MySlot.X + x;
                    int GZ = MySlot.Z + z;

                    if (GX != MySlot.Z && GZ != MySlot.X) {
                        if (MyGrid.get(GX) != null) {
                            if (MyGrid.get(GX).get(GZ) != null) {
                                if (MyGrid.get(GX).get(GZ) != -1) {
                                    HashMap<Integer, Integer> MyHash = MyGrid.get(GX);

                                    MyHash.put(GZ, MyHash.get(GZ) + 1);

                                }
                            }
                        }
                    }
                }
            }
        }


        GeneratedGrid ResultGrid = new GeneratedGrid();
        ResultGrid.DataGrid = MyGrid;
        ResultGrid.GameGrid = GameGrid;
        ResultGrid.MineSlot = MinesSlot;

        return ResultGrid;
    }
}
