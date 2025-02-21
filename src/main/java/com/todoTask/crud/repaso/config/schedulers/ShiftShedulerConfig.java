package com.todoTask.crud.repaso.config.schedulers;

import com.todoTask.crud.repaso.repositories.ShiftRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class ShiftShedulerConfig {

    Logger log = LoggerFactory.getLogger(ShiftShedulerConfig.class);

    @Autowired
    private ShiftRepository shiftRepository;

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        return scheduler;
    }

    @Scheduled(cron = "0 0 22 * * SUN") //Execute the task on SUNDAY / at 0 seg / 0 min 22 hours / * every day of month / * every month of year

    public void limpiarTurnos() {
        try {
            log.info("Cleaning shifts registrations...");

            shiftRepository.deleteAll();

            log.info("Shifts already cleaned.");
        } catch (Exception e) {
            log.error("Error cleaning shifts: " + e.getMessage());
        }
    }
}
