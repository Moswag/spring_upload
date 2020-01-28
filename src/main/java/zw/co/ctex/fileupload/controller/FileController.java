package zw.co.ctex.fileupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.ctex.fileupload.model.Document;
import zw.co.ctex.fileupload.repository.DocumentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author : Webster Moswa
 * @since : 28/01/2020, Tue
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@RestController
public class FileController {

    @Autowired
    DocumentRepository documentRepository;

    @PostMapping("/upload")
    public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileBasePath="/home/schidodo/Work/";
        Path path = Paths.get(fileBasePath + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(fileBasePath)
                .path(fileName)
                .toUriString();
        Document document=new Document();
        document.setDocName(fileName);
        documentRepository.save(document);
        return ResponseEntity.ok(fileDownloadUri);
    }
}
