package cinema;
import java.util.Scanner;
public class Cinema {
    private static void countOfSeats(char[][] countOfSeats, int row, int seat) {
        int sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < seat; j++) {
                if (countOfSeats[i][j] == 'B') {
                    sum++;
                }
            }
        }
        System.out.println("Number of purchased tickets: " + sum);
    }

    private static void percentageOfSeat(char[][] cinemaTickedPercentage, int row, int seat) {
        double sum = 0;
        double per = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < seat; j++) {
                if (cinemaTickedPercentage[i][j] == 'B') {
                    sum++;
                    per = (sum / (row * seat)) * 100;
                }
            }
        }
        System.out.printf("Percentage: %.2f%%%n", per);
    }

    private static void sumAllOfSeatInCinema(int rows, int seats) {
        int sumSales;
        int total_seats = rows * seats;
        int first_half = (rows / 2) * seats;
        int second_half = total_seats - first_half;
        if (rows * seats <= 60) {
            sumSales = rows * seats * 10;
        } else {
            sumSales = (first_half * 10) + (second_half * 8);
        }
        System.out.println("Total income: $" + sumSales);
    }

    public static int tickedSum(char[][] cinemaPrice, int row, int seat) {
        int currentIncome = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < seat; j++) {
                if (cinemaPrice[i][j] == 'B') {
                    currentIncome = currentIncome + (i <= row / 3 ? 10 : 8);
                }
            }
        }
        System.out.println("Current income: $" + currentIncome);
        return currentIncome;
    }

    private static void tickedPrice(char[][] cinemaPrice,int row) {
        int ticketPrice = 0;
        int cinema = cinemaPrice.length * cinemaPrice[0].length;
        if (cinema <= 60) {
            ticketPrice = 10;
        } else {
            int tickedPrice2 = cinemaPrice.length / 2;
            if (row > tickedPrice2) {
                ticketPrice = 8;
            } else {
                ticketPrice = 10;
            }

        }
        System.out.println("Ticket price: $"+ticketPrice);
    }

    private static void buyTicked(char[][] cinemaSeat, int rows, int seats) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int each = scanner.nextInt();
        if (row > rows || each > seats || row < 0 || each < 0){
            System.out.println();
            System.out.println("Wrong input!");
            System.out.println();
            buyTicked(cinemaSeat, rows, seats);
            return;
        }
        if (cinemaSeat[row - 1][each - 1] == 'B') {
            System.out.println();
            System.out.println("That ticket has already been purchased!");
            buyTicked(cinemaSeat, rows, seats);
        } else {
            cinemaSeat[row - 1][each - 1] = 'B';
            tickedPrice(cinemaSeat, row);
        }

        try {
            cinemaSeat[row - 1][each - 1] = 'B';
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input!");
        }

    }
    private static void showTheSeats(char[][] cinemaSeats, int seat) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1 ; i <= seat; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < cinemaSeats.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < cinemaSeats[i].length; j++ ) {
                System.out.print(cinemaSeats[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Write your code here


        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        char[][] cinemaSeat = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinemaSeat[i][j] = 'S';
            }
        }

        int value1 = -1;
        while (value1 != 0) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int value = scanner.nextInt();
            System.out.println();
            switch (value) {
                case 1:
                    showTheSeats(cinemaSeat, seats);
                    break;
                case 2:
                    buyTicked(cinemaSeat, rows, seats);
                    break;
                case 3:
                    countOfSeats(cinemaSeat, rows, seats);
                    percentageOfSeat(cinemaSeat, rows, seats);
                    tickedSum(cinemaSeat, rows, seats);
                    sumAllOfSeatInCinema(rows, seats);
                    break;
                case 0:
                    return;
                default:
                    return;
            }
        }
    }
}

