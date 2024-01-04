import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        String[] board = { "-", "-", "-", "-", "-", "-", "-", "-", "-" };
        Scanner sc = new Scanner(System.in);
        System.out.println("SORTING FIRST PLAYER!!!");
        String player = sortPlayer();

        while (true) {
            System.out.println("PLAYER: " + player);

            printBoardPositions();
            System.out.println("\n");
            printBoard(board);
            System.out.println("\n");

            while (true) {
                System.out.print("Position: ");
                int position = getPosition(sc);

                if (position < 0 || position > 8) {
                    System.out.println("Position must be between 0 and 8");
                } else if (placeInPosition(player, position, board)) {
                    break;
                } else {
                    System.out.println("Position is occupied, choose another position");
                }
            }

            System.out.println("\n------------------------------------");

            if (checkForWin(board)) {
                System.out.println("Player: " + player + " WON!!!");
                printBoard(board);
                break;
            } else {
                if (boardIsFull(board)) {
                    System.out.println("NOBODY WON");
                    break;
                }
            }

            player = alternatePlayers(player);
        }
        sc.close();
    }

    private static int getPosition(Scanner sc) {
        if (sc.hasNextInt()) {
            int r = sc.nextInt();
            sc.nextLine();
            return r;
        } else if (sc.hasNextDouble()) {
            System.out.println("You must provide a Interger");
            sc.nextLine();
            return -1;
        } else {
            System.out.println("You must provide a number no letters allowed");
            sc.nextLine();
            return -1;
        }
    }

    private static boolean boardIsFull(String[] board) {
        for (String position : board) {
            if (position.equals("-")) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkForWin(String[] board) {
        // Verify rows
        for (int i = 0; i <= 6; i += 3) {
            if (board[i].equals(board[i + 1]) && board[i].equals(board[i + 2]) && !board[i].equals("-")) {
                return true;
            }
        }

        // Verify Columns
        for (int i = 0; i <= 2; i++) {
            if (board[i].equals(board[i + 3]) && board[i].equals(board[i + 6]) && !board[i].equals("-")) {
                return true;
            }
        }

        // Verify Diagonals
        if (board[0].equals(board[4]) && board[0].equals(board[8]) && !board[0].equals("-")
                || board[2].equals(board[4]) && board[2].equals(board[6]) && !board[2].equals("-")) {
            return true;
        }
        return false;
    }

    private static String alternatePlayers(String player) {
        if (player.equals("X")) {
            return "O";
        } else {
            return "X";
        }
    }

    private static String sortPlayer() {
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        if (randomNumber == 0) {
            return "X";
        } else if (randomNumber == 1) {
            return "O";
        } else {
            return "-";
        }
    }

    private static void printBoardPositions() {
        System.out.println("BOARD POSITIONING");
        System.out.println("  0 | 1 | 2");
        System.out.println("------------");
        System.out.println("  3 | 4 | 5");
        System.out.println("------------");
        System.out.println("  6 | 7 | 8");
    }

    public static void printBoard(String[] p) {
        System.out.println("CURRENT BOARD");
        System.out.println("  " + p[0] + " | " + p[1] + " | " + p[2]);
        System.out.println("------------");
        System.out.println("  " + p[3] + " | " + p[4] + " | " + p[5]);
        System.out.println("------------");
        System.out.println("  " + p[6] + " | " + p[7] + " | " + p[8]);
    }

    public static boolean placeInPosition(String player, int position, String[] board) {
        if (!isOccupied(position, board)) {
            board[position] = player;
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOccupied(int position, String[] board) {
        if (board[position].equals("-")) {
            return false;
        } else {
            return true;
        }
    }
}
