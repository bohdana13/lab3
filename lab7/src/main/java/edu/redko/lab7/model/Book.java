package edu.redko.lab7.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public Book( String name, String code, String description) {
        this.name=name;
        this.code=code;
        this.description=description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
