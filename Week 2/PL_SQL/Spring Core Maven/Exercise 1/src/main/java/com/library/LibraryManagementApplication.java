package com.library;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        @SuppressWarnings("unused")
		BookService bookService = (BookService) context.getBean("bookService");

        System.out.println("BookService bean is configured and ready to use.");
        context.close();
    }
}
