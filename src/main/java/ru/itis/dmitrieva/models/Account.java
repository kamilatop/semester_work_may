package ru.itis.dmitrieva.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;


// представляет сущность аккаунт, которая является моделью данных для таблицы в бд
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity//является сущность JPA, то есть таблицу в бд
@Table(name = "accounts") //указывает имя таблицы, с которой будет связана свщность аккаунт
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum Role {
        USER, ADMIN
    };

    public enum State {
        NOT_CONFIRMED, CONFIRMED, DELETED, BANNED
    };

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    private String faculty_name;

    private String avatar;

    @Column(length = 64)
    private String confirmCode;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "class_id", referencedColumnName = "id"))
    private List<Class> classes;
}
