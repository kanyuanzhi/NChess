import java.util.*;

public class TicTacToe {
    private int[][] checkerborder = new int[3][3];
    private int round = 0;
    private boolean flag = true;

    private ArrayList<Coordinate> coordinates1 = new ArrayList<>();
    private ArrayList<Coordinate> coordinates2 = new ArrayList<>();
    private Map coordinates1_x = new HashMap();
    private Map coordinates1_y = new HashMap();
    private Map coordinates2_x = new HashMap();
    private Map coordinates2_y = new HashMap();

    private int[][] getCheckerborder() {
        return checkerborder;
    }

    private boolean checkFlag() {
        return this.flag;
    }

    private void setFlag() {
        this.flag = !this.flag;
    }

    public void init() {
        for (int i = 0; i < this.getCheckerborder().length; i++) {
            for (int j = 0; j < this.getCheckerborder()[i].length; j++) {
                this.getCheckerborder()[i][j] = 0;
            }
        }
        this.printCheckerborder();
    }

    public void layDownAChessman(int coordinate) {
        if (this.checkCoordinate(coordinate)) {
            int x = (int) (coordinate / 10);
            int y = coordinate % 10;
            if (this.checkOccupation(x, y)) {
                this.getCheckerborder()[x - 1][y - 1] = checkFlag() ? 1 : 2;
                if (checkFlag()) {
                    this.coordinates1.add(new Coordinate(x, y));
                    this.coordinateCount(this.coordinates1_x, this.coordinates1_y,x,y);
                } else {
                    this.coordinates2.add(new Coordinate(x, y));
                    this.coordinateCount(this.coordinates2_x, this.coordinates2_y,x,y);
                }
                this.setFlag();
                this.printCheckerborder();
                this.checkCheckerborder();
            } else {
                System.out.println("occupied!");
            }
        } else {
            System.out.println("illegal coordinate!");
        }
    }

    private boolean checkOccupation(int x, int y) {
        if (this.getCheckerborder()[x - 1][y - 1] == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkCoordinate(int coordinate) {
        if ((11 <= coordinate && coordinate <= 13) || (21 <= coordinate && coordinate <= 23) || (31 <= coordinate && coordinate <= 33)) {
            return true;
        } else {
            return false;
        }
    }

    private void checkCheckerborder() {
        if (this.greaterThanTwo(this.coordinates1_x) || this.greaterThanTwo(this.coordinates1_y)) {
            System.out.println("player 1 wins!");
        }
        if (this.greaterThanTwo(this.coordinates2_x) || this.greaterThanTwo(this.coordinates2_y)) {
            System.out.println("player 2 wins!");
        }
    }

    private void coordinateCount(Map coordinates_x, Map coordinates_y, int x, int y) {
        if (coordinates_x.containsKey(x)){
            coordinates_x.put(x, (int)coordinates_x.get(x)+1);
        }else {
            coordinates_x.put(x, 1);
        }
        if (coordinates_y.containsKey(y)){
            coordinates_y.put(y, (int)coordinates_y.get(y)+1);
        }else {
            coordinates_y.put(y, 1);
        }
    }

    private boolean greaterThanTwo(Map m) {
        for (Object value : m.values()) {
            if ((Integer) value > 2) {
                return true;
            }
        }
        return false;
    }

    private void printCheckerborder() {
        System.out.println("***  Round : " + this.round++ + "  ***");
        for (int i = 0; i < this.getCheckerborder().length; i++) {
            String str = "";
            for (int j = 0; j < this.getCheckerborder()[i].length; j++) {
                str += this.getCheckerborder()[i][j] + "    ";
            }
            System.out.println(str);
            System.out.println();
        }
    }

    class Coordinate {
        private int x;
        private int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
