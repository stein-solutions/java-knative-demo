package solutions.stein.demo.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import solutions.stein.demo.DemoApplication;

@RestController
public class DemoController {
 

    private static long appStartTime = DemoApplication.AppStartTime;
    private static long timeToInitialization;

    @Value("${K8S_POD_NAMESPACE}")
    private String namespace;

    @Value("${HOSTNAME}")
    private String name;


    @PostConstruct
    public void updateCustomStartTime() {
        DemoController.timeToInitialization = Instant.now().toEpochMilli();
    }

    @GetMapping("/demo/example")
    String example() {
        return "Hallo";
    }

    @GetMapping("")
    String halloWelt() {
        var date = new Date(DemoController.timeToInitialization);
        var formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Hallo Welt! <br /> App started at: " + formatter.format(date);
    }

    @GetMapping("/appStartStats")
    AppStartStatResponse appStartStats() {
        var response = new AppStartStatResponse();
        response.setStartTimestamp(DemoController.appStartTime);
        response.setInitializedTimestamp(DemoController.timeToInitialization);
        response.setRequestReceivedTimestamp(Instant.now().toEpochMilli());
        response.setPodName(name);
        response.setPodNamespace(namespace);
        return response;
    }

    
}
