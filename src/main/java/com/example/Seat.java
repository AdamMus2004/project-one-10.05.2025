package com.example;
import java.io.*;

public class Seat {
    private int[][] seats= new int[10][10];
    public Seat() {
        loadFromFile();
    }
    void loadFromFile(){
        File file = new File("cinema");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader("cinema"))){
            for (int i = 0; i < 10; i++) {
                String line = reader.readLine();
                if (line!=null&&line.length()>=10) {
                    for (int j = 0; j < 10; j++) {
                        seats[i][j]=Character.getNumericValue(line.charAt(j));
                    }
                }

            }
        }catch (IOException e){
            System.out.println("Error with loading file"+e.getMessage());
        }

    }
    void saveToFile(){
        try (PrintWriter writer=new PrintWriter (new FileWriter("cinema"))){
            for (int[]row:seats){
                for (int seat:row){
                    writer.print(seat);
                }
                writer.println();
            }
        }catch (IOException e){
            System.out.print("Error in saving cinema file"+e.getMessage());
        }
    }
    boolean isSeatAvailable(int row, int col)
    {
        if (row>10||col>10||row<1||col<1) {
            throw new IllegalArgumentException("Rows and columns must be between 1 and 10.");
        }

        return seats[row-1][col-1]==0;
    }
    boolean reserveSeat(int row, int col){
        if (row>10||col>10||row<1||col<1) {
            throw new IllegalArgumentException("Rows and columns must be between 1 and 10.");
        }
        if (seats[row-1][col-1]==0) {
            seats[row - 1][col - 1] = 1;
            saveToFile();
            return true;
        }
        else return false;

    }
    boolean cancelReservation(int row, int col){
        if (row>10||col>10||row<1||col<1) {
            throw new IllegalArgumentException("Rows and columns must be between 1 and 10.");
        }
        if (seats[row-1][col-1]==1) {
            seats[row - 1][col - 1] = 0;
            saveToFile();
            return true;
        }
        else return false;
    }
    void printSeats(){
        for (int i = 0; i < 10; i++) {
            System.out.print("Rząd: "+(i+1));
            for (int j = 0; j < 10; j++) {

                if (seats[i][j]==1){

                    System.out.print("|X| ");
                }
                else {
                    System.out.print("|O| ");
                }
            }
            System.out.println();
        }
    }

}
