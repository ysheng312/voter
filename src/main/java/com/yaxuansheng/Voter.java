package com.yaxuansheng;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;

/**
 * Created by Yaxuan on 1/18/2016.
 */
public class Voter {
    public static void main(String[] args) throws IOException {
        String driverPath = generate();
        System.setProperty("webdriver.chrome.driver", driverPath);

        // repeatly vote at most 10 times in half hour intervals
        Timer time = new Timer(); // Instantiate Timer Object
        VoterTask st = new VoterTask();
        time.schedule(st, 0, (1000 * 60 * 30)); // Create Repetitively task for every half hour

        //for demo only.
        for (; ; ) {
            try {
                Thread.sleep(1000 * 60 * 30);
            } catch (InterruptedException e) {
                System.out.println("Application Terminates");
                System.exit(0);
            }
        }
    }

    private static String generate() throws IOException {
        InputStream src = (Voter.class).getClassLoader().getResource("chromedriver").openStream();
        File exeTempFile = File.createTempFile("chromedriver", "");
        FileOutputStream out = new FileOutputStream(exeTempFile);
        byte[] temp = new byte[32768];
        int rc;
        while ((rc = src.read(temp)) > 0)
            out.write(temp, 0, rc);
        src.close();
        out.close();
        exeTempFile.deleteOnExit();

        return exeTempFile.getPath();
    }
}
