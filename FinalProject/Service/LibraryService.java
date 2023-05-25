package com.example.FinalProject.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.FinalProject.Repo.*;
import com.example.FinalProject.model.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class LibraryService {
	
	@Autowired
	private BookRepository b_repository;
	
	public Iterable<BookInfo> retrive()
	{    
		
		return b_repository.findAll();
	}
	
	public Optional<BookInfo> retrive1(int bookid)
	{
		
		return  b_repository.findById(bookid);
	}
	public Optional<BookInfo >create(BookInfo book)
	{
		if(b_repository.existsById(book.getBookid()))
		{
			return Optional.empty();
			
		}
		else
		{
			return Optional.of(b_repository.save(book));
	}
		
	}
	
	public Optional<BookInfo> update(BookInfo book)
	{
		if(b_repository.existsById(book.getBookid()))
		{
			return Optional.of(b_repository.save(book));
		}
		else
		{
			return Optional.empty();
		}
	}

	public String delete(int id) {
		if(b_repository.existsById(id))
		{
			b_repository.deleteById(id);
			return "The book details of  id "+ id +" deleted successfully";
		}
		else
		{
			return " The employee data does not exit in records";
		}
	}
	
	//Pagination
	public List<BookInfo> paging(int page_num, int record_count)
	{
		Pageable page=PageRequest.of(page_num, record_count);
		Page<BookInfo> pageable=b_repository.findAll(page);
		List<BookInfo> bookList=pageable.getContent();
		return bookList;
	}
	
	//Sorting(descending)
	public Iterable<BookInfo> sorting(String field)
	{
		return b_repository.findAll(Sort.by(Direction.DESC,field));
	}
	
	//Sorting(ascending)
	
	public Iterable<BookInfo> sortingBook(String field)
	{
	return b_repository.findAll(Sort.by(Direction.ASC,field));
	}
	
	
	//PaginationANDsorting(ASCENDING)
	public List<BookInfo>pagingSortingLaptop(int offset,int pageSize,String field){
		
		Pageable paging=PageRequest.of(offset,pageSize).withSort(Sort.by(field));
		Page<BookInfo>BookData= b_repository.findAll(paging);	
		List<BookInfo>BookList=BookData.getContent();
		return BookList;
	}
	
	//PaginationANDsorting(DESCNDING)
		public List<BookInfo>pagingSortingLaptopDesC(int offset,int pageSize,String field){
			
			Pageable pagingD=PageRequest.of(offset,pageSize).withSort(Sort.by(Direction.DESC,field));
			Page<BookInfo>BookDataD= b_repository.findAll(pagingD);	
			List<BookInfo>BookListD=BookDataD.getContent();
			return BookListD;
		}
	
	

}
