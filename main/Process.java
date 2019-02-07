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
    
    public FileReadings GeneralRead(File file, FileReadings obj){
        int count = 0;
        String line;
        try{
            obj.lineCount = Files.lines(file.toPath()).count();
            BufferedReader reader = Files.newBufferedReader(file.toPath());
              while((line = reader.readLine()) != null) {
                  count += line.length();
         }
            obj.characterCount = count;
            
    } catch(IOException ex){
          ex.printStackTrace();
        }
        return obj;
    }
   
    public FileReadings call() throws Exception {
        object = GeneralRead(file, object);
        System.out.println("There were: " + object.characterCount + " characters in: " + file.getName());
        System.out.println("There were: " + object.lineCount + " lines in: " + file.getName());
        return object;
    }
}
