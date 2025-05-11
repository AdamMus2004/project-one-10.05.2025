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
        return scanner.nextInt();
    }

    private void handleCheckSeat() {
        System.out.println("Enter row and collumn to check if seat is avalible:");
        if (seat.isSeatAvailable(scanner.nextInt(), scanner.nextInt())){
            System.out.println("Seat is available.");
        }
        else {
            System.out.println("Seat is taken.");
        }
    }

    private void handleReserveSeat() {
        System.out.println("Enter row and collumn to reserve seat:");
        boolean success = seat.reserveSeat(scanner.nextInt(), scanner.nextInt());
        System.out.println(success ? "Seat reserved!" : "Seat already taken!"   );
    }

    private void handleCancelReservation() {
        System.out.println("Enter row and collumn to cancel reservation of the seat:");
        boolean canceled=seat.cancelReservation(scanner.nextInt(), scanner.nextInt());
        System.out.println(canceled?"Reservation canceled." : "Seat was not reserved).");
    }

    private void handleShowSeats() {
        seat.printSeats();
    }

    public static void main(String[] args) {
        new CinemaApp().run();
    }
}
