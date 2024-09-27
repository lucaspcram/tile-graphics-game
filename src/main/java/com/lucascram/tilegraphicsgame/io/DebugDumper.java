/* (C)2024 */
package com.lucascram.tilegraphicsgame.io;

import com.lucascram.tilegraphicsgame.TileGraphicsGame;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JOptionPane;

public class DebugDumper {

    public static final String DEBUG_FILE_PATH = "debug/debugdump.log";

    public static final String ERR_MESSAGE_GENERIC = "Fatal error in main game thread";
    public static final String ERR_MESSAGE_GAMESTATE = "Fatal state machine error";
    public static final String ERR_MESSAGE_FILEIO = "File error";
    public static final String ERR_MESSAGE_RESOURCE = "Resource not found";
    public static final String ERR_MESSAGE_AUDIO_VOLUME = "Illegal audio volume argument";
    public static final String ERR_MESSAGE_LOOKANDFEEL = "Failed to set system look and feel";
    public static final String ERR_MESSAGE_SCOREFILE = "res/cfg/scores.txt not found";

    // if a path is passed in, prints error message with the passed in path
    public static void handleException(
            String path, String errMessage, StackTraceElement[] stack, Exception e) {
        if (path == null) {
            JOptionPane.showMessageDialog(
                    null, errMessage, TileGraphicsGame.TITLE + " Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            dumpToFile(DebugDumper.DEBUG_FILE_PATH, e.getStackTrace());
            System.exit(1);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    errMessage + ": " + path,
                    TileGraphicsGame.TITLE + " Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            dumpToFile(DebugDumper.DEBUG_FILE_PATH, e.getStackTrace());
            System.exit(1);
        }
    }

    private static void dumpToFile(String path, StackTraceElement[] stack) {
        String exception = "";
        PrintStream outStream = null;

        try {
            outStream = new PrintStream(new FileOutputStream(path));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Debug file error",
                    TileGraphicsGame.TITLE + " Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        for (StackTraceElement s : stack) {
            exception = exception + s.toString() + "\n\t\t";
        }

        outStream.print(exception);
        outStream.close();
    }

    public static void displayDebugDialog(String errMessage, boolean exit) {
        JOptionPane.showMessageDialog(
                null, errMessage, TileGraphicsGame.TITLE + " Error", JOptionPane.ERROR_MESSAGE);
        if (exit) {
            System.exit(1);
        }
    }
}
