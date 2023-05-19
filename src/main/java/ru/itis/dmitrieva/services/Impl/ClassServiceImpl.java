package ru.itis.dmitrieva.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.itis.dmitrieva.dto.ClassDto;
import ru.itis.dmitrieva.repositories.ClassRepository;
import ru.itis.dmitrieva.services.ClassService;

import java.util.List;

import static ru.itis.dmitrieva.dto.ClassDto.from;

@Service
@RequiredArgsConstructor
@Log4j2
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    @Override
    public List<ClassDto> findClassesByAccountsId(Long id) {
        return from(classRepository.findClassesByAccountsId(id));
    }
}
//