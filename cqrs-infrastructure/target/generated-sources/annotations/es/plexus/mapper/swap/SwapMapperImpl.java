package es.plexus.mapper.swap;

import es.plexus.entity.book.Book;
import es.plexus.entity.swap.Swap;
import es.plexus.entity.user.User;
import es.plexus.entity.user.UserRol;
import es.plexus.jpa.book.BookJpa;
import es.plexus.jpa.swap.SwapJpa;
import es.plexus.jpa.user.UserJpa;
import es.plexus.jpa.user.UserRolJpa;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-16T14:00:46+0200",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class SwapMapperImpl implements SwapMapper {

    @Override
    public SwapJpa toJpa(Swap swap) {
        if ( swap == null ) {
            return null;
        }

        SwapJpa swapJpa = new SwapJpa();

        swapJpa.setId( swap.getId() );
        swapJpa.setBooksOrigin( bookListToBookJpaList( swap.getBooksOrigin() ) );
        swapJpa.setBooksTarget( bookListToBookJpaList( swap.getBooksTarget() ) );
        swapJpa.setUserOrigin( userToUserJpa( swap.getUserOrigin() ) );
        swapJpa.setUserTarget( userToUserJpa( swap.getUserTarget() ) );
        swapJpa.setSwapDate( swap.getSwapDate() );
        swapJpa.setSwapState( swap.getSwapState() );

        return swapJpa;
    }

    @Override
    public Swap toEntity(SwapJpa swapJpa) {
        if ( swapJpa == null ) {
            return null;
        }

        Swap swap = new Swap();

        swap.setId( swapJpa.getId() );
        swap.setBooksOrigin( bookJpaListToBookList( swapJpa.getBooksOrigin() ) );
        swap.setBooksTarget( bookJpaListToBookList( swapJpa.getBooksTarget() ) );
        swap.setUserOrigin( userJpaToUser( swapJpa.getUserOrigin() ) );
        swap.setUserTarget( userJpaToUser( swapJpa.getUserTarget() ) );
        swap.setSwapDate( swapJpa.getSwapDate() );
        swap.setSwapState( swapJpa.getSwapState() );

        return swap;
    }

    @Override
    public List<SwapJpa> toListJpa(List<Swap> swaps) {
        if ( swaps == null ) {
            return null;
        }

        List<SwapJpa> list = new ArrayList<SwapJpa>( swaps.size() );
        for ( Swap swap : swaps ) {
            list.add( toJpa( swap ) );
        }

        return list;
    }

    @Override
    public List<Swap> toListEntity(List<SwapJpa> swapJpas) {
        if ( swapJpas == null ) {
            return null;
        }

        List<Swap> list = new ArrayList<Swap>( swapJpas.size() );
        for ( SwapJpa swapJpa : swapJpas ) {
            list.add( toEntity( swapJpa ) );
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

    protected BookJpa bookToBookJpa(Book book) {
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

    protected List<BookJpa> bookListToBookJpaList(List<Book> list) {
        if ( list == null ) {
            return null;
        }

        List<BookJpa> list1 = new ArrayList<BookJpa>( list.size() );
        for ( Book book : list ) {
            list1.add( bookToBookJpa( book ) );
        }

        return list1;
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

    protected Book bookJpaToBook(BookJpa bookJpa) {
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

    protected List<Book> bookJpaListToBookList(List<BookJpa> list) {
        if ( list == null ) {
            return null;
        }

        List<Book> list1 = new ArrayList<Book>( list.size() );
        for ( BookJpa bookJpa : list ) {
            list1.add( bookJpaToBook( bookJpa ) );
        }

        return list1;
    }
}
