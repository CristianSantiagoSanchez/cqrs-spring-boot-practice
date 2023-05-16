package es.plexus.mapper.swap;

import es.plexus.dto.book.BookDto;
import es.plexus.dto.swap.SwapDto;
import es.plexus.dto.user.UserDto;
import es.plexus.entity.book.Book;
import es.plexus.entity.swap.Swap;
import es.plexus.entity.user.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-16T14:01:07+0200",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class RestSwapMapperImpl implements RestSwapMapper {

    @Override
    public Swap toEntity(SwapDto swapDto) {
        if ( swapDto == null ) {
            return null;
        }

        Swap swap = new Swap();

        swap.setId( swapDto.getId() );
        swap.setBooksOrigin( bookDtoListToBookList( swapDto.getBooksOrigin() ) );
        swap.setBooksTarget( bookDtoListToBookList( swapDto.getBooksTarget() ) );
        swap.setUserOrigin( userDtoToUser( swapDto.getUserOrigin() ) );
        swap.setUserTarget( userDtoToUser( swapDto.getUserTarget() ) );
        swap.setSwapState( swapDto.getSwapState() );

        return swap;
    }

    @Override
    public SwapDto toDto(Swap swap) {
        if ( swap == null ) {
            return null;
        }

        SwapDto swapDto = new SwapDto();

        swapDto.setId( swap.getId() );
        swapDto.setUserOrigin( userToUserDto( swap.getUserOrigin() ) );
        swapDto.setUserTarget( userToUserDto( swap.getUserTarget() ) );
        swapDto.setBooksOrigin( bookListToBookDtoList( swap.getBooksOrigin() ) );
        swapDto.setBooksTarget( bookListToBookDtoList( swap.getBooksTarget() ) );
        swapDto.setSwapState( swap.getSwapState() );

        return swapDto;
    }

    @Override
    public List<SwapDto> toListDto(List<Swap> swaps) {
        if ( swaps == null ) {
            return null;
        }

        List<SwapDto> list = new ArrayList<SwapDto>( swaps.size() );
        for ( Swap swap : swaps ) {
            list.add( toDto( swap ) );
        }

        return list;
    }

    @Override
    public List<Swap> toLisEntity(List<SwapDto> swapDtos) {
        if ( swapDtos == null ) {
            return null;
        }

        List<Swap> list = new ArrayList<Swap>( swapDtos.size() );
        for ( SwapDto swapDto : swapDtos ) {
            list.add( toEntity( swapDto ) );
        }

        return list;
    }

    protected Book bookDtoToBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDto.getId() );
        book.setTitle( bookDto.getTitle() );
        book.setAuthor( bookDto.getAuthor() );
        book.setEditorial( bookDto.getEditorial() );
        book.setIsbn( bookDto.getIsbn() );

        return book;
    }

    protected List<Book> bookDtoListToBookList(List<BookDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Book> list1 = new ArrayList<Book>( list.size() );
        for ( BookDto bookDto : list ) {
            list1.add( bookDtoToBook( bookDto ) );
        }

        return list1;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setUsername( userDto.getUsername() );
        user.setName( userDto.getName() );
        user.setSurnames( userDto.getSurnames() );
        user.setEmail( userDto.getEmail() );
        user.setBirthdate( userDto.getBirthdate() );
        user.setSignUpDate( userDto.getSignUpDate() );
        user.setDescription( userDto.getDescription() );

        return user;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setName( user.getName() );
        userDto.setSurnames( user.getSurnames() );
        userDto.setEmail( user.getEmail() );
        userDto.setBirthdate( user.getBirthdate() );
        userDto.setSignUpDate( user.getSignUpDate() );
        userDto.setDescription( user.getDescription() );

        return userDto;
    }

    protected BookDto bookToBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setId( book.getId() );
        bookDto.setTitle( book.getTitle() );
        bookDto.setAuthor( book.getAuthor() );
        bookDto.setEditorial( book.getEditorial() );
        bookDto.setIsbn( book.getIsbn() );

        return bookDto;
    }

    protected List<BookDto> bookListToBookDtoList(List<Book> list) {
        if ( list == null ) {
            return null;
        }

        List<BookDto> list1 = new ArrayList<BookDto>( list.size() );
        for ( Book book : list ) {
            list1.add( bookToBookDto( book ) );
        }

        return list1;
    }
}
