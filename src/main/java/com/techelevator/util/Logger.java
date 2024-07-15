package com.techelevator.util;

import com.techelevator.exceptions.LoggerException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static PrintWriter logWriter;


    public static void log(String message) throws LoggerException {
        File logs = new File("logs");
        logs.mkdir();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        if (logWriter == null) {
            try {

                String destinationPath = "logs/";
                logWriter = new PrintWriter(new FileWriter(destinationPath + "log.txt", true));

            } catch (LoggerException | IOException e) {
                throw new LoggerException("error writing to log file" + e.getMessage());

            }
        }
        logWriter.println(date.format(formatter) + " " + message);
        logWriter.flush();
    }
}