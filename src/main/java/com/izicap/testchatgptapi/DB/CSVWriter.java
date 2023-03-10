package com.izicap.testchatgptapi.DB;

import java.io.*;

public class CSVWriter implements IDBWriter {
    private static final String separator = ";";
    private static final String fileName = "questions_answers.csv";

    //You could also use Files.write for Java 7+
    private FileWriter writer;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;

    private CSVWriter() {
        this.connect();
    }

    @Override
    public void connect() {
        // Already connected
        if(printWriter != null)
            return;

        //Make sure that DATA_PATH is in environment variables
        String fullPath, dataPath = System.getenv("DATA_PATH");
        fullPath = dataPath == null ? fileName : dataPath + "/" + fileName;

        //Check if it exists
        File file = new File(fullPath);
        boolean addHeader = !file.exists() || file.length() == 0;

        try {
            writer = new FileWriter(fullPath, true);
            bufferedWriter = new BufferedWriter(writer);
            printWriter = new PrintWriter(bufferedWriter);
            if(addHeader) printWriter.println("Question" + separator + "answer");
        } catch (IOException ex) {
          ex.printStackTrace();
        }
    }

    @Override
    public void put(String qst, String ans) {
        printWriter.println(qst + separator + ans);
        printWriter.flush();
    }

    @Override
    public void close() {
        try {
            writer.close();
            bufferedWriter.close();
            printWriter.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Singleton
    private static CSVWriter instance = null;
    public static CSVWriter getInstance() {
        if(instance == null) {
            instance = new CSVWriter();
        }
        return instance;
    }
}
