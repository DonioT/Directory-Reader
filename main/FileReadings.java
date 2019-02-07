/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Donio
 */
public class FileReadings {
     private long characterCount;
     private long lineCount;
     private long whiteSpaceCount;
     private long emptyLineCount;
     private long wordCount;
     
     public void setCharacterCount(long val){
         this.characterCount = val;
     }
     public void setLineCount(long val){
         this.lineCount = val;
     }
     public void setWhiteSpaceCount(long val){
         this.whiteSpaceCount = val;
     }
     public void setEmptyLineCount(long val){
         this.emptyLineCount = val;
     }
     public void setWordCount(long val){
         this.wordCount = val;
     }
     
     public long getCharacterCount(){
         return this.characterCount;
     }
     public long getLineCount(){
         return this.lineCount;
     }
     public long getWhiteSpaceCount(){
         return this.whiteSpaceCount;
     }
     public long getEmptyLineCount(){
         return this.emptyLineCount;
     }
     public long getWordCount(){
         return this.wordCount;
     }
}
