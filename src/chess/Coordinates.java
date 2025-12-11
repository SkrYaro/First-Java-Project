package chess;

public class Coordinates {

    public int x;
    public int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(char x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int getCharX(char x) {
        int num = x - ('a' - 1);
        System.out.println(num);
        return num;
    }

    public int getX() {
        return x;
    }


}
