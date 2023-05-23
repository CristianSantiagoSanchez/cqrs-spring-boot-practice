package es.plexus.shared.exception;

import es.plexus.shared.query.Query;

public final class QueryNotRegisteredError extends Exception {
    public QueryNotRegisteredError(Class<? extends Query> query) {
        super(String.format("The query <%s> hasn't a query handler associated", query.toString()));
    }
}