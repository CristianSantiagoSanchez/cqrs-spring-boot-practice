package es.plexus.shared.bus.query;
public interface QueryHandler<Q extends Query, R extends Response> {
    R handle(Q query);
}