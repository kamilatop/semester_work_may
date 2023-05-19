package ru.itis.dmitrieva.services;

import ru.itis.dmitrieva.dto.ClassDto;

import java.util.List;

public interface ClassService {
    List<ClassDto> findClassesByAccountsId(Long id);
}
