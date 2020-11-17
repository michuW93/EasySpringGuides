package com.example.schedulingTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(fixedRate = 100000)
    public void reportCurrentTime() {
        log.info("Scheduler: the time is now {}", dateFormat.format(new Date()));
    }
}
