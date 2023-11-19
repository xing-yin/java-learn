package org.example;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.example.data.Author;
import org.example.data.Book;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2023/11/16
 **/
public class OptionalDemo {

  public static void main(String[] args) {
//    Optional<Author> authorOptional = getAuthor();
//    authorOptional.ifPresent(author1 -> System.out.println(author1.getName()));

//    Optional<Author> authorOptional = getAuthor2();
//    authorOptional.ifPresent(author1 -> System.out.println(author1.getName()));

//    Optional<Author> authorOptional3 = getAuthor();
//    Author author = authorOptional3.orElseGet(
//        () -> new Author(1L, "默认", 36, "一个从菜刀中明悟哲理的祖安人", null));
//    System.out.println(author.getName());

//    Optional<Author> authorOptional = getAuthor();
//    try {
//      Author author = authorOptional.orElseThrow((Supplier<Throwable>) () -> new
//          RuntimeException("author为空"));
//      System.out.println(author.getName());
//    } catch (Throwable throwable) {
//      throwable.printStackTrace();
//    }

//    Optional<Author> authorOptional = getAuthor();
//    Optional<Author> author = authorOptional.filter(author1 -> author1.getAge() > 18);
//    author.ifPresent(author1 -> System.out.println(author1.getName()));
//
//    Optional<Author> authorOptional4 = getAuthor();
//    if (authorOptional4.isPresent()) {
//      System.out.println(authorOptional4.get().getName());
//    }

    Optional<Author> authorOptional = getAuthor();
    Optional<List<Book>> books = authorOptional.map(author -> author.getBooks());
    books.ifPresent(books1 -> books1.forEach(book -> System.out.println(book.getName())));
  }

  private static Optional<Author> getAuthor() {
    Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
    author = null;
    return Optional.ofNullable(author);
  }

  private static Optional<Author> getAuthor2() {
    Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
    return Optional.of(null);
  }

}
