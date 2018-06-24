package cdi.event;

import java.util.Date;

public class ModificationEvent {

    private Date timestamp;

    public ModificationEvent() {
        this.timestamp = new Date();
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
