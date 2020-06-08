package com.splitwise.io;


import com.splitwise.process.TransactionProcesser;

import java.io.*;

public class InputTxtReader {
    TransactionProcesser processor;
    public InputTxtReader(){
        processor = new TransactionProcesser();
    }
    public void readInputTxtFile(String fileName) {
        if(fileName==null && fileName.isEmpty()){
            System.err.println("FileName is empty or Null . Aborting!!!");
            return;
        }
       try {
           File file = new File(fileName);
           BufferedReader br = new BufferedReader(new FileReader(file));
           String st;
           while ((st = br.readLine()) != null) {
               //System.out.println(st);
                processor.process(st);
           }
       }catch(Exception e){
           System.err.println("Exception while reading a file"+e);
       }
    }

}

