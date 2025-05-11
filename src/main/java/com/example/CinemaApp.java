package com.example;

import java.util.Scanner;

public class CinemaApp {
    private final Seat seat;
    private final Scanner scanner;

    public CinemaApp() {
        this.seat = new Seat();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1 -> handleCheckSeat();
                case 2 -> handleReserveSeat();
                case 3 -> handleCancelReservation();
                case 4 -> handleShowSeats();
                case 5 -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private void showMenu() {
        System.out.println("Choose option:\n" +
                "1. Check if seat is avalible.\n" +
                "2. Reserve seat.\n" +
                "3. Cancel reservation.\n" +
                "4. Show seats\n" +
                "5. Quit.");

    }
    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a number:");
            scanner.next();
        }
        return scanner.nextInt();
    }
    private int[] getRowAndColumn() {
        System.out.print("Enter row: ");
        int row = scanner.nextInt();
        System.out.print("Enter column: ");
        int col = scanner.nextInt();
        return new int[]{row, col};
    }


    private void handleCheckSeat() {
        int[] coords = getRowAndColumn();
        if (seat.isSeatAvailable(coords[0], coords[1])){
            System.out.println("Seat is available.");
        }
        else {
            System.out.println("Seat is taken.");
        }
    }

    private void handleReserveSeat() {
        int[] coords = getRowAndColumn();
        boolean success = seat.reserveSeat(coords[0], coords[1]);
        System.out.println(success ? "Seat reserved!" : "Seat already taken!"   );
    }

    private void handleCancelReservation() {
        int[] coords = getRowAndColumn();
        boolean canceled=seat.cancelReservation(coords[0], coords[1]);
        System.out.println(canceled?"Reservation canceled." : "Seat was not reserved).");
    }

    private void handleShowSeats() {
        seat.printSeats();
    }

    public static void main(String[] args) {
        new CinemaApp().run();
    }
}
