package chess;

public class Coordinates {

    public boolean success;
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

    public Coordinates(String figureCoordStr) {


        if (figureCoordStr.length() >= 3) {
            this.success = false;
            System.out.println("You make an mistake");
        } else {
            x = figureCoordStr.charAt(0);
            y = figureCoordStr.charAt(1);
            if ((x >= 'a' && x <= ('a' + 7)) && (y - 49 <= 8)) {
                this.success = true;
                x = x - 'a';
                y = y - 49  ;
                System.out.println(this.x + "   " +this.y);
            }
            else{
                System.out.println(x +"   " + y );
                System.out.println("You missed in square");
                this.success = false;
            }
        }

    }

    public int getCharX() {
        int num = x - ('a' - 1);
        System.out.println(num);
        return num;
    }

    public int getX() {
        return x;
    }
}
