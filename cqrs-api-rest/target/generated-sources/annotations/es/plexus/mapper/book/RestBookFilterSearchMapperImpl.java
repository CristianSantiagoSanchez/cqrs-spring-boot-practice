package es.plexus.mapper.book;

import es.plexus.dto.book.BookFilterSearchDto;
import es.plexus.entity.book.Book;
import es.plexus.entity.user.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-16T14:01:06+0200",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class RestBookFilterSearchMapperImpl implements RestBookFilterSearchMapper {

    @Override
    public BookFilterSearchDto toDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookFilterSearchDto bookFilterSearchDto = new BookFilterSearchDto();

        bookFilterSearchDto.setUserId( bookUserId( book ) );
        bookFilterSearchDto.setId( book.getId() );
        bookFilterSearchDto.setTitle( book.getTitle() );
        bookFilterSearchDto.setAuthor( book.getAuthor() );
        bookFilterSearchDto.setEditorial( book.getEditorial() );
        bookFilterSearchDto.setIsbn( book.getIsbn() );

        return bookFilterSearchDto;
    }

    @Override
    public List<BookFilterSearchDto> toListDto(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<BookFilterSearchDto> list = new ArrayList<BookFilterSearchDto>( books.size() );
        for ( Book book : books ) {
            list.add( toDto( book ) );
        }

        return list;
    }

    private long bookUserId(Book book) {
        if ( book == null ) {
            return 0L;
        }
        User user = book.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }
}
