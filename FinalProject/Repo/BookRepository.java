package com.example.FinalProject.Repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.FinalProject.model.*;

public interface BookRepository extends PagingAndSortingRepository<BookInfo, Integer>,CrudRepository<BookInfo, Integer>{
	
	

}
