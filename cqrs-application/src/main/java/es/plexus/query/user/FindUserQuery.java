package es.plexus.query.user;

import es.plexus.shared.bus.query.Query;

public class FindUserQuery implements Query {
    private final long id;
    public FindUserQuery(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
}
