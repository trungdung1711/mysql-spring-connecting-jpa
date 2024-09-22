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


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTests 
{   
    private AuthorRepo underTest;
    
    
    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepo atrp)
    {
        underTest = atrp;
    }


    @Test
    public void testAuthorDatabaseInclude()
    {
        Author author = TestDataUtil.createAuthorObjectA();
        underTest.save(author);
        Optional<Author> resultAuthorOptional = underTest.findById(author.getId());
        Assertions.assertThat(resultAuthorOptional).isPresent();
        Assertions.assertThat(resultAuthorOptional.get()).isEqualTo(author);
    }


    @Test
    public void testMultipleAuthorDatabaseInclude()
    {
        Author authorA = TestDataUtil.createAuthorObjectA();
        Author authorB = TestDataUtil.createAuthorObjectB();
        Author authorC = TestDataUtil.createAuthorObjectC();

        underTest.save(authorA);
        underTest.save(authorB);
        underTest.save(authorC);

        Iterable<Author> result =  underTest.findAll();
        Assertions.assertThat(result)
        .hasSize(3)
        .containsExactly(authorA, authorB, authorC);
    }


    @Test
    public void testUpdateAuthorDatabaseInclude()
    {
        Author author = TestDataUtil.createAuthorObjectA();
        underTest.save(author);
        author.setAge(100);

        underTest.save(author);

        Optional<Author> result =  underTest.findById(author.getId());
        Assertions.assertThat(result)
        .isPresent();

        Assertions.assertThat(result.get())
        .isEqualTo(author);

        Assertions.assertThat(result.get().getAge())
        .isEqualTo(100);
    }


    // @Test
    // public void testDeleteAuthorDatabaseInclude()
    // {
    //     Author author = TestDataUtil.createAuthorObjectA();
    //     underTest.create(author);
    //     underTest.delete(author.getId());

    //     Optional<Author> result = underTest.findOne(author.getId());
    //     Assertions.assertThat(result).isEmpty();
    // }
}