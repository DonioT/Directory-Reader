/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Donio
 */
public class Application {

    private long totalCharacterCount;
    private long totalLineCount;
    private final File[] fileList;
    private final static String _DIRECTORY = "src//documents";
    
    public Application(String directory){
        fileList = new File(directory).listFiles();
    }
    public File[] getFileList(){
        return fileList;
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
          
         Application x = new Application(_DIRECTORY);
         x.getTotals();
         
    }
    public void getTotals() throws InterruptedException, ExecutionException{
         ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Callable<FileReadings>> tasks = new ArrayList<>();
         for (File file : getFileList()) {
           tasks.add(new Process(file));
        }
   
      List<Future<FileReadings>> responses = executor.invokeAll(tasks);
     
      for (Future<FileReadings> response : responses) {
           totalCharacterCount += response.get().characterCount;
           totalLineCount += response.get().lineCount;
      }
          
          System.out.println("Total lines in all documents: " + totalLineCount);
          System.out.println("Total characters in all documents: " + totalCharacterCount);
                  
          executor.shutdown();
    }
}
