package com.example;

import java.io.*;

public class Seat {
    private final File file;
    private final int[][] seats = new int[10][10];

    public Seat(File file, boolean skipLoad) {
        this.file = file;
        if (!skipLoad) {
            loadFromFile();
        }
    }

    public Seat(boolean skipLoad) {
        this(new File("cinema"), skipLoad);
    }

    public Seat() {
        this(new File("cinema"), false);
    }

    void loadFromFile() {
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (int i = 0; i < 10; i++) {
                String line = reader.readLine();
                if (line != null && line.length() >= 10) {
                    for (int j = 0; j < 10; j++) {
                        seats[i][j] = Character.getNumericValue(line.charAt(j));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error with loading file: " + e.getMessage());
        }
    }

    void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (int[] row : seats) {
                for (int seat : row) {
                    writer.print(seat);
                }
                writer.println();
            }
        } catch (IOException e) {
            System.out.print("Error in saving cinema file: " + e.getMessage());
        }
    }

    boolean isSeatAvailable(int row, int col) {
        validateSeat(row, col);
        return seats[row - 1][col - 1] == 0;
    }

    boolean reserveSeat(int row, int col) {
        validateSeat(row, col);
        if (seats[row - 1][col - 1] == 0) {
            seats[row - 1][col - 1] = 1;
            saveToFile();
            return true;
        } else {
            return false;
        }
    }

    boolean cancelReservation(int row, int col) {
        validateSeat(row, col);
        if (seats[row - 1][col - 1] == 1) {
            seats[row - 1][col - 1] = 0;
            saveToFile();
            return true;
        } else {
            return false;
        }
    }

    void printSeats() {
        for (int i = 0; i < 10; i++) {
            System.out.print("Rząd: " + (i + 1));
            for (int j = 0; j < 10; j++) {
                System.out.print(seats[i][j] == 1 ? "|X| " : "|O| ");
            }
            System.out.println();
        }
    }

    private void validateSeat(int row, int col) {
        if (row < 1 || row > 10 || col < 1 || col > 10) {
            throw new IllegalArgumentException("Rows and columns must be between 1 and 10.");
        }
    }
}
