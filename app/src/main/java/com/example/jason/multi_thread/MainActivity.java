package com.example.jason.multi_thread;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Context;

import java.io.*;


class addFile implements Runnable {
    private Thread t;
    private String filename;
    Context context;

    addFile(String name) {
        filename = name;
    }

    public void run() {
        try {
            File file = new File(context.getFilesDir(), filename);
            // Let the thread sleep for a while.
            Thread.sleep(250);
        } catch (InterruptedException e) {
            System.out.println("Thread " + filename + " interrupted.");
        }
    }

    public void start () {
        if (t == null) {
            t = new Thread (this, filename);
            t.start ();
        }
    }
}



public class MainActivity extends AppCompatActivity {
    String filename = "numbers.txt";
    Context context;
    addFile R1 = new addFile(filename);
    Thread threadOne = new Thread(R1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createButton = (Button) findViewById(R.id.createButton);

        createButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                threadOne.start();File file = new File(context.getFilesDir(), filename);
            }
        });


        Button loadButton = (Button) findViewById(R.id.loadButton);

        loadButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                try {
                    //Create object of FileReader
                    FileReader inputFile = new FileReader("numbers.txt");

                    //Instantiate the BufferedReader Class
                    BufferedReader bufferReader = new BufferedReader(inputFile);

                    //Variable to hold the one line data
                    String line;

                    // Read file line by line and print on the console
                    while ((line = bufferReader.readLine()) != null)   {
                        System.out.println(line);
                    }
                    //Close the buffer reader
                    bufferReader.close();
                }catch(Exception e){
                    System.out.println("Error while reading file line by line:" + e.getMessage());
                }

            }
        });
    }
}
