package topic.hook.event_callback;

/**
 * @author Alan Yin
 * @date 2021/4/25
 */

public class Event {

    public Event(String eventName) {
        this.eventName = eventName;
    }

    private String eventName;

    public String getEventName() {
        return eventName;
    }
}
