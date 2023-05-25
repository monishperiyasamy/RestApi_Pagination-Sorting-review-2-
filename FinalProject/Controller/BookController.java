package com.example.FinalProject.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FinalProject.Service.LibraryService;
import com.example.FinalProject.model.BookInfo;

@RestController
@RequestMapping
public class BookController {
	
	@Autowired
	private LibraryService b_service;
	
	@GetMapping("/")
	public Iterable<BookInfo> getAllBookDetails()
	{
	   return b_service.retrive();
		
	}
	
	@GetMapping("/getbyId/{id}")
	public ResponseEntity<BookInfo> getBookDetailById(@PathVariable("id") int id)
	{
		Optional<BookInfo> book =b_service.retrive1(id);
		if(book.isPresent())
		{
			return new ResponseEntity<>(book.get(),HttpStatus.OK );
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/posting")
	
	public String saveBookDetails(@RequestBody BookInfo book)
	{
		Optional<BookInfo> book_details = b_service.create(book);
		if(book_details.isPresent())
		{
			return "The book data has been added successfully";
		}
		else
		{
			return "Book already exist in records";
		}
	
	}
	@PutMapping("/update/{id}")
	public String updateBookDetails(@RequestBody BookInfo book)
	{
		Optional<BookInfo> book_update = b_service.update(book);
				if(book_update.isEmpty())
				{
					return "The book data does not exit in records";
				}
				else
				{
					return " The book data has been updated successfully";
					
				}
		
	}
	@DeleteMapping("/delete/{id}")
		public String deleteBookById(@PathVariable("id") int id)
		{
			return b_service.delete(id);
		}
	
	//Paging
	@GetMapping("/pagingBook/{page_num}/{record_count}")
	 
	public List<BookInfo> pagingBook(@PathVariable int page_num, @PathVariable int record_count)
	{
		return b_service.paging(page_num,record_count);
	}
	
	//Sorting (ascending)
	@GetMapping("/sortLaptopAscen/{field}")
	public Iterable<BookInfo> sortingBook(@PathVariable String field)
	{
		return b_service.sortingBook(field);
	}
	
	
	//Sorting (descending)
	@GetMapping("/sortLaptop/{field}")
	public Iterable<BookInfo> desc(@PathVariable String field)
	{
		return b_service.sorting(field);
	}
	
	//PaginationAndSorting(ASCENDING)
	
	 @GetMapping("/pagingSortingLaptop/{offset}/{pageSize}/{field}")
	  public List<BookInfo>pagingSortingLaptop(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field) 
	  {
		 return b_service.pagingSortingLaptop(offset,pageSize,field);
	  }
		 
	 
	 
	 //PaginationAndSorting(DESCENDING)
	 @GetMapping("/pagingSortingLaptopD/{offset}/{pageSize}/{field}")
	  public List<BookInfo>pagingSortingLaptopD(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field) 
	  {
		 return b_service.pagingSortingLaptopDesC(offset,pageSize,field);
	  }
		 

	
		
	
	
		
	
	

}

