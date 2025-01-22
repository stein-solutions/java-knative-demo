package solutions.stein.demo.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppStartStatResponse {

    private long startTimestamp;
    private long initializedTimestamp;
    private long requestReceivedTimestamp;

    private String podName;
    private String podNamespace;
    
}
