package ru.itis.dmitrieva.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //значение первичного ключа автоматом генерирруется бд
    private Long id;

    private String name;

    private String time;

    @ManyToMany(mappedBy = "classes")
    private List<Account> accounts;
}
