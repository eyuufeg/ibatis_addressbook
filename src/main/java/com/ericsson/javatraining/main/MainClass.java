package com.ericsson.javatraining.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.javatraining.userinterface.CommandProcess;
import com.ericsson.javatraining.userinterface.CommandProcessInterface;

/**
 * main class of AddressBook program
 * 
 * @author eyuufeg
 * 
 */
public class MainClass {
    private static final Logger logger = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args)  {
        logger.info(" AddressBook application starting");
        final CommandProcessInterface cmdproc = new CommandProcess();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            public void run() {
                cmdproc.process();
            }
        });
        service.shutdownNow();
    }

}