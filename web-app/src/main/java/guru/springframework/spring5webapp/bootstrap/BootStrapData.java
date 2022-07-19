package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher pub = new Publisher();
        pub.setName("SFG Publishig");
        pub.setCity("St Petersburg");
        pub.setState("FL");
        publisherRepository.save(pub);



        Author eric = new Author("Eric", "Evans");
        Book d1 = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(d1);
        d1.getAuthors().add(eric);
        d1.setPublisher(pub);
        pub.getBooks().add(d1);

        authorRepository.save(eric);
        bookRepository.save(d1);
        publisherRepository.save(pub);

        Author rod = new Author("Rod", "Johnson");
        Book d2 = new Book("J2EE DEVELOPMET WITHOUT EJB", "456789");
        rod.getBooks().add(d2);
        d2.getAuthors().add(rod);
        d2.setPublisher(pub);
        pub.getBooks().add(d2);

        authorRepository.save(rod);
        bookRepository.save(d2);
        publisherRepository.save(pub);

        System.out.println("Started i bootstrap");
        System.out.println("Number of books :"+bookRepository.count());
        System.out.println("Publisher Number of books :"+pub.getBooks().size());
    }
}
