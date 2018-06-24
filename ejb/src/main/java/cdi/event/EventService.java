package cdi.event;

import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@PermitAll
@RunAs("User")
public class EventService {

    @Inject
    @WBModificationEvent
    Event<ModificationEvent> modificationEvent;

    public void produceModificationEvent(String eventName) {
        System.out.format("Fire modification event: %s!", eventName);
        ModificationEvent event = new ModificationEvent();
        modificationEvent.fire(event);
    }
}
