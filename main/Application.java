/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.sql.SQLException;
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
    
    public String getDirectory(){
    return _DIRECTORY;
        }

    public Application(String directory){
        fileList = new File(directory).listFiles();
    }
    public File[] getFileList(){
        return fileList;
    }
    
    public void setTotalLineCount(Application obj){
        totalLineCount = obj.totalLineCount;
    }
    public long getTotalLineCount(){
        return totalLineCount;
    }
    public void setTotalCharacterCount(Application obj){
        totalCharacterCount = obj.totalCharacterCount;
    }
    public long getTotalCharacterCount(){
        return totalCharacterCount;
    }
    
    
    
    public static void main(String[] args) throws InterruptedException, ExecutionException, SQLException {
          
         Application x = new Application(_DIRECTORY);
         x = x.getTotals(x);
         x.setTotalCharacterCount(x);
         x.setTotalLineCount(x);
         
         Database db = new Database(x);
         System.out.println(db.insert());
    }
    
    public Application getTotals(Application obj) throws InterruptedException, ExecutionException{
         ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Callable<FileReadings>> tasks = new ArrayList<>();
         for (File file : getFileList()) {
           tasks.add(new Process(file));
        }
   
      List<Future<FileReadings>> responses = executor.invokeAll(tasks);
     
      for (Future<FileReadings> response : responses) {
           obj.totalCharacterCount += response.get().characterCount;
           obj.totalLineCount += response.get().lineCount;
      }
          System.out.println("Total lines in all documents: " + totalLineCount);
          System.out.println("Total characters in all documents: " + totalCharacterCount);
           
          executor.shutdown();
          return obj;
    }
}
