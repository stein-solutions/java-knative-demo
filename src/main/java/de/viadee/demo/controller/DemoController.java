package de.viadee.demo.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController
public class DemoController {
 

    private long customStartTime;

    @PostConstruct
    public void updateCustomStartTime() {
        this.customStartTime = Instant.now().getEpochSecond() * 1000;
    }

    @GetMapping("/demo/example")
    String example() {
        return "Hallo";
    }

    @GetMapping("")
    String halloWelt() {
        var date = new Date(customStartTime);
        var formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Hallo Welt! <br /> App started at: " + formatter.format(date);
    }
}
