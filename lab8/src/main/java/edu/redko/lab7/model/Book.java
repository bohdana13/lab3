package edu.redko.lab7.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/*
  @author User
  @project lab7
  @class Book
  @version 1.0.0
  @since 24.04.2025 - 22.14
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Document





public class Book {

    @Id
    private String id;
    private String name;
    private String code;
    private String description;
    private LocalDateTime createDate;
    private List<LocalDateTime> updateDate;

    public Book(String id, String name, String code, String description) {
        this.id = id;
        this.name=name;
        this.code=code;
        this.description=description;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book item = (Book) o;
        return getId().equals(item.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof Book book)) return false;
//        return Objects.equals(getId(), book.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(getId());
//    }
}
