package es.plexus.shared.bus.query;

import es.plexus.shared.bus.query.exception.QueryHandlerExecutionError;

public interface QueryBus {
    <R> R ask(Query query) throws QueryHandlerExecutionError;
}
