package es.plexus.mapper.book;

import es.plexus.dto.book.BookDto;
import es.plexus.entity.book.Book;
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
public class RestBookMapperImpl implements RestBookMapper {

    @Override
    public Book toEntity(BookDto bookDto) {
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

    @Override
    public BookDto toDto(Book book) {
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

    @Override
    public List<BookDto> toListDto(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<BookDto> list = new ArrayList<BookDto>( books.size() );
        for ( Book book : books ) {
            list.add( toDto( book ) );
        }

        return list;
    }

    @Override
    public List<Book> toLisEntity(List<BookDto> bookDtos) {
        if ( bookDtos == null ) {
            return null;
        }

        List<Book> list = new ArrayList<Book>( bookDtos.size() );
        for ( BookDto bookDto : bookDtos ) {
            list.add( toEntity( bookDto ) );
        }

        return list;
    }
}
