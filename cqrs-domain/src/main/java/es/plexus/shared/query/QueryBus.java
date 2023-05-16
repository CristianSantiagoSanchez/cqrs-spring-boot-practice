package es.plexus.shared.query;

import es.plexus.shared.exception.QueryHandlerExecutionError;

public interface QueryBus {
    <R> R ask(Query query) throws QueryHandlerExecutionError;
}
