package solutions.stein.demo.controller;

public class AppStartStatResponse {

    private long startTimestamp;
    private long initializedTimestamp;
    private long requestReceivedTimestamp;
    
    private String podName;
    private String podNamespace;
    
    public long getStartTimestamp() {
        return startTimestamp;
    }
    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }
    public long getInitializedTimestamp() {
        return initializedTimestamp;
    }
    public void setInitializedTimestamp(long initializedTimestamp) {
        this.initializedTimestamp = initializedTimestamp;
    }

    public long getRequestReceivedTimestamp() {
        return requestReceivedTimestamp;
    }
    public void setRequestReceivedTimestamp(long requestReceivedTimestamp) {
        this.requestReceivedTimestamp = requestReceivedTimestamp;
    }
    public String getPodName() {
        return podName;
    }
    public void setPodName(String podName) {
        this.podName = podName;
    }
    public String getPodNamespace() {
        return podNamespace;
    }
    public void setPodNamespace(String podNamespace) {
        this.podNamespace = podNamespace;
    }
    
}
