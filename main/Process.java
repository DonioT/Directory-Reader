/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Donio
 */
public class Process implements Callable<FileReadings> {
    
    private FileReadings object;
    private File file;
    public Process(File file){
        FileReadings obj = new FileReadings();
        this.object = obj;
        
        this.file = file;
    }
    
    public void CountCharacters(File file){
        int count = 0;
        try {
            
            BufferedReader reader = Files.newBufferedReader(file.toPath());
            while(reader.read() != -1){
                count++;
            }
            object.characterCount = reader.read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            object.characterCount = count;
    }
    public void CountLines(File file){
        try {
            Stream<String> text = Files.lines(file.toPath());
            object.lineCount = text.count();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   
    public FileReadings call() throws Exception {
        CountCharacters(this.file);
        CountLines(this.file);
        System.out.println("THERE WERE: " + object.characterCount + " CHARACTERS IN: " + file.getName());
        System.out.println("THERE WERE: " + object.lineCount + " LINES IN: " + file.getName());
        return object;
    }
}
