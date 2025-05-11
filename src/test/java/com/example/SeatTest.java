package com.example;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.plugins.tiff.BaselineTIFFTagSet;

import static org.junit.jupiter.api.Assertions.*;

public class SeatTest {

    private File tempFile;
    @BeforeEach
    public void setUp() throws IOException{
        tempFile=File.createTempFile("cinema","txt");
        tempFile.deleteOnExit();
        try (PrintWriter writer = new PrintWriter(new FileWriter(tempFile))){
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    writer.print("0");
                }
                writer.println();
            }
        }
    }
    @Test
    public void testLoadFromFile(){
        Seat seat = new Seat(tempFile,false);

        assertTrue(seat.isSeatAvailable(1,1));
        assertTrue(seat.isSeatAvailable(10,10));
    }
    @Test
    public void testSaveToFile() throws IOException{
        Seat seat = new Seat(tempFile, false);
        seat.reserveSeat(1,1);
        seat.reserveSeat(1,10);
        seat.saveToFile();

        try(BufferedReader reader = new BufferedReader(new FileReader(tempFile))){

            String line = reader.readLine();
            assertTrue(line.charAt(0)=='1');
            assertTrue(line.charAt(9)=='1');
            assertFalse(line.charAt(1)=='1');
        }
    }
    @AfterEach
    public void tearDown(){
        if (tempFile.exists()){
            tempFile.delete();
        }
    }

    @Test
    public void testIsSeatAvailable_whenSeatIsFree_returnsTrue() {
        Seat seat = new Seat(true);
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                assertTrue(seat.isSeatAvailable(i,j));
            }
        }

    }
    @Test
    public void testReserveSeat_whenSeatIsFree_returnsTrue(){
        Seat seat = new Seat(true);
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                seat.reserveSeat(i,j);
                assertFalse(seat.isSeatAvailable(i,j));
            }
        }

    }
    @Test
    public void testCancelReservation_whenSeatIsTaken_returnsTrue(){
        Seat seat = new Seat(true);
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                seat.reserveSeat(i,j);
                seat.cancelReservation(i,j);
                assertTrue(seat.isSeatAvailable(i,j));
            }
        }
    }
    @Test
    public void testThrowIllegalArgumentException_whenSeatIsOffTheScale(){
            Seat seat= new Seat(true);

            assertThrows(IllegalArgumentException.class,()-> {
            seat.reserveSeat(11, 1);
        });
        }
    @Test
    public void testThrowIllegalArgumentException_when_isSeatAvailable_SeatIsOffTheScale(){
        Seat seat = new Seat(true);
        assertThrows(IllegalArgumentException.class,()->{
            seat.isSeatAvailable(0,0);
        });

    }
    @Test
    public void testInteractionReserveSeat_whenSeatReserved_returnsTrueReturnsFalse(){
        Seat seat = new Seat(true);
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                assertTrue(seat.reserveSeat(i,j));
                assertFalse(seat.reserveSeat(i,j));
            }

        }
    }
    @Test
    public void testInteractionCancelReservation_whenSeatIsNotReserved_returnsFalse(){
    Seat seat = new Seat(true);
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                assertFalse(seat.cancelReservation(i,j));
            }
        }
    }


}