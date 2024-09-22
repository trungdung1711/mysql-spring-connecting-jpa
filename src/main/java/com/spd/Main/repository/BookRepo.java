package com.spd.Main.repository;

import org.springframework.data.repository.CrudRepository;

import com.spd.Main.domain.Book;

public interface BookRepo extends CrudRepository<Book, String>
{    
};