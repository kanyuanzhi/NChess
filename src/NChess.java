import java.util.*;

public class NChess {
    private static final int SIZE = 9;
    private static final int N = 5;
    private int[][] checkerborder = new int[SIZE][SIZE];
    private int round = 0;
    private boolean flag = true;
    private boolean ending = true;

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
        this.ending = true;
        this.printCheckerborder();
    }

    public void begin() {
        while (this.ending) {
            if (flag) {
                System.out.println("player1 走棋，请输入坐标：");
            } else {
                System.out.println("player2 走棋，请输入坐标：");
            }
            try {
                String str = "";
                Scanner input = new Scanner(System.in);
                int coordinate = input.nextInt();
                switch (this.layDownAChessman(coordinate)) {
                    case 0:
                        break;
                    case 1:
                        System.out.println("player 1 wins!");
                        this.ending = false;
                        break;
                    case 2:
                        System.out.println("player 2 wins!");
                        this.ending = false;
                        break;
                    case 3:
                        System.out.println("occupied!");
                        break;
                    case 4:
                        System.out.println("illegal coordinate!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("非法输入！");
                //e.printStackTrace();
            }
        }
    }

    private int layDownAChessman(int coordinate) {
        if (this.checkCoordinate(coordinate)) {
            int x = (int) (coordinate / 10);
            int y = coordinate % 10;
            if (this.checkOccupation(x - 1, y - 1)) {
                this.getCheckerborder()[x - 1][y - 1] = checkFlag() ? 1 : 2;
                this.printCheckerborder();
                if (this.checkFlag() && this.checkLine(new Coordinate(x - 1, y - 1))) {
                    return 1;
                }
                if (!this.checkFlag() && this.checkLine(new Coordinate(x - 1, y - 1))) {
                    return 2;
                }
                this.setFlag();
                return 0;
            } else {
                return 3;
            }
        } else {
            return 4;
        }
    }

    private boolean checkOccupation(int x, int y) {
        if (this.getCheckerborder()[x][y] == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkCoordinate(int coordinate) {
//        if ((11 <= coordinate && coordinate <= 13) || (21 <= coordinate && coordinate <= 23) || (31 <= coordinate && coordinate <= 33)) {
//            return true;
//        } else {
//            return false;
//        }
        return true;
    }

    private boolean checkLine(Coordinate coo) {
        int x = coo.getX();
        int y = coo.getY();
        int px = x;
        int py = y;
        int value = this.getCheckerborder()[x][y];
        System.out.println("输入坐标：" + x + "," + y);
        int count135 = 1;   //135度方向
        int count45 = 1;    //45度方向
        int count0 = 1;     //水平方向
        int count90 = 1;    //垂直方向
        while (px - 1 >= 0 && py - 1 >= 0) {
            if (this.getCheckerborder()[px - 1][py - 1] == value) {
                px = px - 1;
                py = py - 1;
                count135++;
            } else {
                break;
            }
        }
        px = x;
        py = y;
        while (px + 1 < SIZE && py + 1 < SIZE) {
            if (this.getCheckerborder()[px + 1][py + 1] == value) {
                px = px + 1;
                py = py + 1;
                count135++;
            } else {
                break;
            }
        }
        px = x;
        py = y;
        if (count135 >= N) {
            return true;
        }

        while (px + 1 < SIZE && py - 1 >= 0) {
            if (this.getCheckerborder()[px + 1][py - 1] == value) {
                px = px + 1;
                py = py - 1;
                count45++;
            } else {
                break;
            }
        }
        px = x;
        py = y;
        while (px - 1 >= 0 && py + 1 < SIZE) {
            if (this.getCheckerborder()[px - 1][py + 1] == value) {
                px = px - 1;
                py = py + 1;
                count45++;
            } else {
                break;
            }
        }
        px = x;
        py = y;
        if (count45 >= N) {
            return true;
        }

        while (py - 1 >= 0) {
            if (this.getCheckerborder()[px][py - 1] == value) {
                py = py - 1;
                count0++;
            } else {
                break;
            }
        }
        py = y;
        while (py + 1 < SIZE) {
            if (this.getCheckerborder()[px][py + 1] == value) {
                py = py + 1;
                count0++;
            } else {
                break;
            }
        }
        py = y;
        if (count0 >= N) {
            return true;
        }

        while (px - 1 >= 0) {
            if (this.getCheckerborder()[px - 1][py] == value) {
                px = px - 1;
                count90++;
            } else {
                break;
            }
        }
        px = x;
        while (px + 1 < SIZE) {
            if (this.getCheckerborder()[px + 1][py] == value) {
                px = px + 1;
                count90++;
            } else {
                break;
            }
        }
        px = x;
        if (count90 >= N) {
            return true;
        }
        System.out.println(count0);
//        System.out.println(count45);
//        System.out.println(count90);
//        System.out.println(count135);
//        if (count0 >= N || count45 >= N || count90 >= N || count135 >= N) {
//            return true;
//        } else return false;

        return false;
    }

    // TODO: 2018/3/8 0008  将每一方向上的判断抽象为一个函数
    public void checkLineDirection(int angle, int x, int y) {
        String condition1 = "";
        String condition2 = "";
        String px1 = "";
        String px2 = "";
        String py1 = "";
        String py2 = "";
        switch (angle) {
            case 135: {
                condition1 = "px - 1 > 0 && py - 1 > 0";
                px1 = "px - 1";
                condition2 = "px + 1 > 0 && py + 1 > 0";
            }
        }

    }

    private void printCheckerborder() {
        System.out.println("***  Round : " + this.round++ + "  ***");
        String head = "    ";
        for (int k =0;k<SIZE;k++){
            head+= k+1 +"    ";
        }
        System.out.println(head);
        System.out.println();
        for (int i = 0; i < this.getCheckerborder().length; i++) {
            String str = i+1+"   ";
            for (int j = 0; j < this.getCheckerborder()[i].length; j++) {
                str += this.getCheckerborder()[i][j] + "    ";
            }
            System.out.println(str);
            System.out.println();
        }
    }

    private class Coordinate {
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
