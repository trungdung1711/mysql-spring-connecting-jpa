package com.spd.Main;

import com.spd.Main.domain.Author;
import com.spd.Main.domain.Book;

public final class TestDataUtil 
{
    private TestDataUtil()
    {

    }


    public static Author createAuthorObjectA() 
    {
        return Author
        .builder()
        .id(1L)
        .name("JK Rowling")
        .age(40)
        .build();
    }


    public static Author createAuthorObjectB() 
    {
        return Author
        .builder()
        .id(2L)
        .name("Trung Dung")
        .age(50)
        .build();
    }


    public static Author createAuthorObjectC() 
    {
        return Author
        .builder()
        .id(3L)
        .name("Domison")
        .age(60)
        .build();
    }


    public static Book createBookObjectA(Author author)
    {
        return Book.builder()
        .isbn("HP-101")
        .title("Harry Potter")
        .author(author)
        .build();
    }


    public static Book createBookObjectB(Author author)
    {
        return Book.builder()
        .isbn("LA-101")
        .title("Linear Algebra")
        .author(author)
        .build();
    }


    public static Book createBookObjectC(Author author)
    {
        return Book.builder()
        .isbn("Ph-101")
        .title("General Physics")
        .author(author)
        .build();
    }
}