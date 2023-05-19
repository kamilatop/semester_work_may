package ru.itis.dmitrieva.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post {
    public enum State {
       DELETED, PUBLISHED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_email")
    private Account account;

    private LocalDate createdAt;

    @Column
    private String title;

    @Column
    private String text;

    @Enumerated(value = EnumType.STRING)
    private State state;

}
