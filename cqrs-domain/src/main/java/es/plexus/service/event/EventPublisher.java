package es.plexus.service.event;

import es.plexus.entity.event.BookSwapEvent;

public interface EventPublisher {

    public void publish(BookSwapEvent event);
}
