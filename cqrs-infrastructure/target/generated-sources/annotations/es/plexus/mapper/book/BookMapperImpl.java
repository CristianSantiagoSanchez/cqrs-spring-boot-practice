package es.plexus.mapper.book;

import es.plexus.entity.book.Book;
import es.plexus.entity.user.User;
import es.plexus.entity.user.UserRol;
import es.plexus.jpa.book.BookJpa;
import es.plexus.jpa.user.UserJpa;
import es.plexus.jpa.user.UserRolJpa;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-16T14:00:48+0200",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookJpa toJpa(Book book) {
        if ( book == null ) {
            return null;
        }

        BookJpa bookJpa = new BookJpa();

        bookJpa.setId( book.getId() );
        bookJpa.setTitle( book.getTitle() );
        bookJpa.setAuthor( book.getAuthor() );
        bookJpa.setEditorial( book.getEditorial() );
        bookJpa.setIsbn( book.getIsbn() );
        bookJpa.setUser( userToUserJpa( book.getUser() ) );

        return bookJpa;
    }

    @Override
    public Book toEntity(BookJpa bookJpa) {
        if ( bookJpa == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookJpa.getId() );
        book.setTitle( bookJpa.getTitle() );
        book.setAuthor( bookJpa.getAuthor() );
        book.setEditorial( bookJpa.getEditorial() );
        book.setIsbn( bookJpa.getIsbn() );
        book.setUser( userJpaToUser( bookJpa.getUser() ) );

        return book;
    }

    @Override
    public List<BookJpa> toListJpa(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<BookJpa> list = new ArrayList<BookJpa>( books.size() );
        for ( Book book : books ) {
            list.add( toJpa( book ) );
        }

        return list;
    }

    @Override
    public List<Book> toListEntity(List<BookJpa> bookJpas) {
        if ( bookJpas == null ) {
            return null;
        }

        List<Book> list = new ArrayList<Book>( bookJpas.size() );
        for ( BookJpa bookJpa : bookJpas ) {
            list.add( toEntity( bookJpa ) );
        }

        return list;
    }

    protected UserRolJpa userRolToUserRolJpa(UserRol userRol) {
        if ( userRol == null ) {
            return null;
        }

        UserRolJpa userRolJpa = new UserRolJpa();

        userRolJpa.setId( userRol.getId() );
        userRolJpa.setName( userRol.getName() );

        return userRolJpa;
    }

    protected UserJpa userToUserJpa(User user) {
        if ( user == null ) {
            return null;
        }

        UserJpa userJpa = new UserJpa();

        userJpa.setId( user.getId() );
        userJpa.setUsername( user.getUsername() );
        userJpa.setName( user.getName() );
        userJpa.setSurnames( user.getSurnames() );
        userJpa.setEmail( user.getEmail() );
        userJpa.setPassword( user.getPassword() );
        userJpa.setBirthdate( user.getBirthdate() );
        userJpa.setSignUpDate( user.getSignUpDate() );
        userJpa.setDescription( user.getDescription() );
        userJpa.setUserRol( userRolToUserRolJpa( user.getUserRol() ) );

        return userJpa;
    }

    protected UserRol userRolJpaToUserRol(UserRolJpa userRolJpa) {
        if ( userRolJpa == null ) {
            return null;
        }

        UserRol userRol = new UserRol();

        userRol.setId( userRolJpa.getId() );
        userRol.setName( userRolJpa.getName() );

        return userRol;
    }

    protected User userJpaToUser(UserJpa userJpa) {
        if ( userJpa == null ) {
            return null;
        }

        User user = new User();

        user.setId( userJpa.getId() );
        user.setUsername( userJpa.getUsername() );
        user.setName( userJpa.getName() );
        user.setSurnames( userJpa.getSurnames() );
        user.setEmail( userJpa.getEmail() );
        user.setPassword( userJpa.getPassword() );
        user.setBirthdate( userJpa.getBirthdate() );
        user.setSignUpDate( userJpa.getSignUpDate() );
        user.setDescription( userJpa.getDescription() );
        user.setUserRol( userRolJpaToUserRol( userJpa.getUserRol() ) );

        return user;
    }
}
