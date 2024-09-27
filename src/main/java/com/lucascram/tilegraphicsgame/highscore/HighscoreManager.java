/* (C)2024 */
package com.lucascram.tilegraphicsgame.highscore;

import com.lucascram.tilegraphicsgame.io.DebugDumper;
import com.lucascram.tilegraphicsgame.io.FileReader;
import com.lucascram.tilegraphicsgame.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighscoreManager {

    private static final String PATH = "res/cfg/scores.txt";

    private List<Scorecard> scoreList;

    public HighscoreManager() {
        scoreList = new ArrayList<Scorecard>();
        readInScores();
    }

    private void readInScores() {
        String line = null;
        FileReader fileReader = new FileReader();
        try {
            fileReader.openFile(PATH);
        } catch (FileNotFoundException e) {
            DebugDumper.handleException(
                    null, DebugDumper.ERR_MESSAGE_SCOREFILE, e.getStackTrace(), e);
        }

        while (true) {
            line = fileReader.getNextLine();
            if (line == null || line.equals("")) {
                break;
            }
            String[] contents = new String[2];
            contents = line.split(";");
            String name = contents[0];
            int score = Integer.parseInt(contents[1]);
            scoreList.add(new Scorecard(name, score));
        }

        Collections.sort(scoreList);

        try {
            fileReader.closeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addScore(String name, int score) {
        scoreList.add(new Scorecard(name, score));
    }

    public Scorecard getScorecard(int index) {
        try {
            return scoreList.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void writeScoresToFile() {
        FileWriter fileWriter = new FileWriter();
        try {
            fileWriter.openFile(PATH);
        } catch (FileNotFoundException e) {
            DebugDumper.handleException(
                    null, DebugDumper.ERR_MESSAGE_SCOREFILE, e.getStackTrace(), e);
        }

        Collections.sort(scoreList);
        for (int i = 0; i < scoreList.size(); i++) {
            Scorecard card = scoreList.get(i);
            String toWrite = card.getPlayerName() + ";" + card.getScore();
            fileWriter.writeLineToFile(toWrite);
        }

        try {
            fileWriter.closeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int listSize() {
        return scoreList.size();
    }
}
