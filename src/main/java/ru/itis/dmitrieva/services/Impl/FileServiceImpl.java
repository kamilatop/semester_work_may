package ru.itis.dmitrieva.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dmitrieva.helper.exceptions.AccountNotExistsException;
import ru.itis.dmitrieva.models.Account;
import ru.itis.dmitrieva.models.File;
import ru.itis.dmitrieva.repositories.AccountRepository;
import ru.itis.dmitrieva.repositories.FileRepository;
import ru.itis.dmitrieva.services.FileService;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class FileServiceImpl implements FileService {

    private final AccountRepository accountRepository;
    private final FileRepository fileRepository;

    @Value("${files.storage.path}")
    private String storagePath;

    @Value("${files.url}")
    private String filesUrl;

    @Override
    @Transactional
    public void uploadAvatar(MultipartFile multipartFile, Long accountId) {
        try {
            String extension = Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            String storageFileName = UUID.randomUUID() + extension;
            File fileInfo = File.builder()
                    .type(multipartFile.getContentType())
                    .originalFileName(multipartFile.getOriginalFilename())
                    .storageFileName(storageFileName)
                    .size(multipartFile.getSize())
                    .build();

            Files.copy(multipartFile.getInputStream(), Paths.get(storagePath, fileInfo.getStorageFileName()));
            fileRepository.save(fileInfo);

            Account account = accountRepository.findById(accountId).orElseThrow(AccountNotExistsException::new);
            account.setAvatar(filesUrl + fileInfo.getStorageFileName());
            accountRepository.save(account);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void getFileByStorageName(String storageFileName, HttpServletResponse response) {
        File fileInfo = fileRepository.findByStorageFileName(storageFileName).orElseThrow(IllegalArgumentException::new);
        response.setHeader("Content-Disposition", "filename=\"" + fileInfo.getOriginalFileName() + "\"");
        try {
            IOUtils.copy(new FileInputStream(storagePath + "/" + fileInfo.getStorageFileName()), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
