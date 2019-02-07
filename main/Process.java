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
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

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
        long charCount = 0;
        long whiteSpaceCount = 0;
        long emptyLineCount = 0;
        long lineCount = 0;
        long wordCount = 0;
        
        String line;
        try{
            lineCount = Files.lines(file.toPath()).count();
            emptyLineCount = Files.lines(file.toPath()).filter(i -> i.trim().isEmpty()).count();
            BufferedReader reader = Files.newBufferedReader(file.toPath());
              while((line = reader.readLine()) != null) {
                   if(!line.trim().isEmpty()){
                         wordCount += line.split("\\W+").length;
                   }
                  
                  whiteSpaceCount += line.chars().filter(i -> i  == ' ').count();
                  charCount += line.length();
         }
       
              System.out.println("total words in this document" + wordCount);
              obj.setLineCount(lineCount);
              obj.setCharacterCount(charCount);
              obj.setEmptyLineCount(emptyLineCount);
              obj.setWhiteSpaceCount(whiteSpaceCount);
              obj.setWordCount(wordCount);
            
    } catch(IOException ex){
          ex.printStackTrace();
        }
        return obj;
    }
    
    public FileReadings call() throws Exception {
        object = GeneralRead(file, object);
        return object;
    }
}
