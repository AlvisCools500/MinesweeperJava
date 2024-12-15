import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GridPrint {
    public static void Print(HashMap<Integer, HashMap<Integer, Integer>> Hash, ArrayList<String> NumberType) {
        for (var a : Hash.entrySet()) {
            ArrayList<Object> MyList = new ArrayList<>();

            for (var b : a.getValue().entrySet()) {
                var val = b.getValue();

                if (val == -1) {
                    MyList.add(NumberType.get(0));
                }else if (val == -2) {
                    MyList.add(NumberType.get(1));
                } else {
                    MyList.add(val);
                }


            }

            System.out.println(MyList);
        }
    }
}
