package com.example;

import java.util.Scanner;

public class ReservationSystem {
    public static void main(String[] args) {
        Seat seat = new Seat();
        Scanner scanner = new Scanner(System.in);
        boolean running =true;

        while (running) {
            System.out.println("Choose option:\n" +
                    "1. Check if seat is avalible.\n" +
                    "2. Reserve seat.\n" +
                    "3. Cancel reservation.\n" +
                    "4. Show seats\n" +
                    "5. Quit.");
            if (!scanner.hasNextInt()){
                scanner.next();
                continue;
            }
            int option = scanner.nextInt();
            if (option<1||option>5) throw new IllegalArgumentException("Bad choice in menu!");
            if (option==1){
                System.out.println("Enter row and collumn to check if seat is avalible:");
                if (seat.isSeatAvailable(scanner.nextInt(), scanner.nextInt())){
                    System.out.println("Seat is available.");
                }
                else {
                    System.out.println("Seat is taken.");
                }
            }
            if (option==2){
                System.out.println("Enter row and collumn to reserve seat:");
                boolean success = seat.reserveSeat(scanner.nextInt(), scanner.nextInt());
                System.out.println(success ? "Seat reserved!" : "Seat already taken!"   );

            }
            if (option==3){
                System.out.println("Enter row and collumn to cancel reservation of the seat:");
                boolean canceled=seat.cancelReservation(scanner.nextInt(), scanner.nextInt());
                System.out.println(canceled?"Reservation canceled." : "Seat was not reserved).");
            }
            if (option==4){
                seat.printSeats();
            }
            if (option==5){
                System.out.println("Thanks, see you soon!");
                running=false;
            }
        }
    scanner.close();
    }
}
