package com.spd.Main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spd.Main.domain.Author;


@Repository
public interface AuthorRepo extends CrudRepository<Author, Long> 
{
};