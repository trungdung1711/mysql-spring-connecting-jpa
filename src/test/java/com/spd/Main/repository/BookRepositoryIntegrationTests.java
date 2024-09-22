package com.spd.Main.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spd.Main.TestDataUtil;
import com.spd.Main.domain.Author;
import com.spd.Main.domain.Book;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests 
{
    private BookRepo undertest;
    private AuthorRepo authorRepo;


    @Autowired
    public BookRepositoryIntegrationTests(BookRepo brp, AuthorRepo atrp)
    {
        undertest = brp;
        authorRepo = atrp;
    }


    @Test
    public void testBookDatabaseInclude()
    {
        Author author = TestDataUtil.createAuthorObjectA();
        Book book = TestDataUtil.createBookObjectA(author);
        undertest.save(book);
        Optional<Book> result = undertest.findById(book.getIsbn());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(book);
        Assertions.assertThat(result.get().getAuthor()).isEqualTo(author);
    }


    @Test
    public void testMultipleBookDatabaseInclude()
    {
        Author authorA = TestDataUtil.createAuthorObjectA();
        Author authorB = TestDataUtil.createAuthorObjectB();
        Author authorC = TestDataUtil.createAuthorObjectC();

        Book bookA = TestDataUtil.createBookObjectA(authorA);
        Book bookB = TestDataUtil.createBookObjectB(authorB);
        Book bookC = TestDataUtil.createBookObjectC(authorC);

        undertest.save(bookA);
        undertest.save(bookB);
        undertest.save(bookC);

        Iterable<Book> result = undertest.findAll();
        Assertions.assertThat(result)
        .hasSize(3)
        .containsExactly(bookA, bookB, bookC);

        Iterable<Author> sideResult = authorRepo.findAll();
        Assertions.assertThat(sideResult)
        .hasSize(3)
        .containsExactly(authorA, authorB, authorC);
    }


    @Test 
    public void testUpdateBookDatabaseInclude()
    {
        Author authorA = TestDataUtil.createAuthorObjectA();

        Book book = TestDataUtil.createBookObjectA(authorA);
        undertest.save(book);
        book.setTitle("The title has changed");
        undertest.save(book);
        
        Optional<Book> result = undertest.findById(book.getIsbn());
        Assertions.assertThat(result)
        .isPresent();
        Assertions.assertThat(result.get().getTitle())
        .isEqualTo("The title has changed");
    }


    // @Test
    // public void testDeleteBookDatabaseInclude()
    // {
    //     Author author = TestDataUtil.createAuthorObjectA();
    //     authorDAOmysql.create(author);
    //     Book book = TestDataUtil.createBookObjectA();
    //     undertest.create(book);
    //     undertest.delete(book.getIsbn());

    //     Optional<Book> result = undertest.findOne(book.getIsbn());
    //     Assertions.assertThat(result).isEmpty();
    // }
}