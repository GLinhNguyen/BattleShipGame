public class Boards {

    // create board array
    private char[][] board1 = new char[11][11];
    private char[][] board2 = new char[11][11];

    // constructor
    public Boards() {
        // initialize method board
        initBoard1();
        initBoard2();
    }

    /* method initBoard1() and initBoard2() */
    public void initBoard1() {
        board1[0][0] = ' ';
        int num = 1;

        // first row
        for (char i = 48; i <= 57; i++) {
            board1[0][num] = i;
            num++;
        }

        // first column;
        for (int i = 48; i <= 57; i++) {
            board1[num][0] = (char) i;
            num++;
        }

        // rest of board
        for (int i = 1; i < board1.length; i++) {
            for (int j = i; j < board1[0].length; j++) {
                board1[i][j] = '~';
            }
        }
    }

    public void initBoard2() {
        board2[0][0] = ' ';
        int num = 1;

        // first row
        for (char i = 48; i <= 57; i++) {
            board2[0][num] = i;
            num++;
        }

        // first column;
        for (int i = 48; i <= 57; i++) {
            board2[num][0] = (char) i;
            num++;
        }

        // rest of board
        for (int i = 1; i < board2.length; i++) {
            for (int j = i; j < board2[0].length; j++) {
                board2[i][j] = '~';
            }
        }
    }

    /* method to check if this location is available to place ship or not */
    public boolean isValidLocation(Coordinate crd, Ship s) {
        int xCoordinate = crd.getX() + 1;
        int yCoordinate = crd.getY() + 1;

        // check if the coordinate is outside of the board or not
        if (xCoordinate < 1 || xCoordinate >= board1[0].length) {
            return false;
        }
        if (yCoordinate < 1 || yCoordinate >= board1[0].length) {
            return false;
        }

        // check all the case of all direction
        // if the ship direction is pointed upward
        if (s.getDirection() == "upward") {
            // if the front of the ship's coordinate is outside the boundary, it is not
            // valid location
            if (yCoordinate - (s.getSize + 1) < 1) {
                return false;
            }
            // check if any ship was placed in that location
            for (int i = yCoordinate; i > yCoordinate - s.getSize(); i--) {
                if (board1[i][xCoordinate] != '~') {
                    return false;
                }
            }
        }
        // if the ship direction is pointed downward
        else if (s.getDirection() == "downward") {
            if (yCoordinate + (s.getSize() + 1) >= board1.length) {
                return false;
            }

            for (int i = yCoordinate; i <= yCoordinate + (s.getSize() - 1); i++) {
                if (board1[i][xCoordinate] != '~') {
                    return false;
                }
            }
        }
        // if the ship direction is point to right
        else if (s.getDirection == "right") {
            if (xCoordinate + (s.getSize() - 1) >= board1[0].length) {
                return false;
            }
            for (int i = xCoordinate; i <= xCoordinate + (s.getSize() - 1); i++) {
                if (board1[yCoordinate][i] != '~') {
                    return false;
                }
            }
        }

        // if the ship direction is point to left
        else if (s.getDirection() == "left") {
            if (xCoordinate - (s.getSize() + 1) < 1) {
                return false;
            }
            for (int i = xCoordinate; i > xCoordinate - s.getSize(); i--) {
                if (board1[yCoordinate][i] != '~') {
                    return false;
                }
            }
        }

        return true;
    }

    /* check if player attack is inside the board's boundary or not */
    public boolean isValidAttack(Coordinate crd) {
        int xCoordinate = crd.getX() + 1;
        int yCoordinate = crd.getY() + 1;

        if (xCoordinate < 1 || xCoordinate >= board1[0].length) {
            return false;
        }

        if (yCoordinate < 1 || yCoordinate >= board1.length) {
            return false;
        }

        return true;
    }

    /* update on the board after player attack */
    public char boardResult(Coordinate crd, Player opposition) {
        int xCoordinate = crd.getX() + 1;
        int yCoordinate = crd.getY() + 1;

        // if we hit the ship, opposition board will display 'X'
        if (opposition.getBoard1()[yCoordinate][xCoordinate] != '~') {
            this.getBoard2()[yCoordinate][xCoordinate] = 'X';
            opposition.getBoard1()[yCoordinate][xCoordinate] = 'X';
            return 'X';
        }
        // if we miss the ship, opposition board will display 'M' (miss)
        else {
            this.getBoard2()[yCoordinate][xCoordinate] = 'M';
            return 'M';
        }
    }

    /* placeship method */
    public void placeShips(Coordinate crd, Ship s) {
        int xCoordinate = crd.getX() + 1;
        int yCoordinate = crd.getY() + 1;

        board1[yCoordinate][xCoordinate] = s.getLetter();

        if (s.getDirection() == "upward") {
            for (int i = yCoordinate; i >= yCoordinate - (s.getSize - 1); i--) {
                board1[i][xCoordinate] = s.getLetter();
            }
        }

        else if (s.getDirection() == "downward") {
            for (int i = yCoordinate; i <= yCoordinate + (s.getSize() - 1); i++) {
                board1[i][xCoordinate] = s.getLetter;
            }
        }

        else if (s.getDirection() == "right") {
            for (int i = xCoordinate; i <= xCoordinate + (s.getSize() - 1); i++) {
                board1[yCoordinate][i] = s.getLetter();
            }
        }

        else if (s.getDirection() == "left") {
            for (int i = xCoordinate; i >= xCoordinate - (s.getSize() - 1); i--) {
                board1[yCoordinate][i] = s.getLetter();
            }
        }
    }

    public char[][] getBoard1() {
        return board1;
    }

    public char[][] getBoard2() {
        return board2;
    }

    // display method
    public void displayBoard1() {
        for (int i = 0; i < board1.length; i++) {
            for (int j = 0; j < board1[0].length; j++) {
                System.out.println(board1[i][j]);
            }
            System.out.println();
        }
    }

    public void displayBoard2() {
        for (int i = 0; i < board2.length; i++) {
            for (int j = 0; j < board2[0].length; j++) {
                System.out.println(board2[i][j]);
            }
            System.out.println();
        }
    }
}