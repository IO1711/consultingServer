package com.bilolbek.ConsultingWebsite.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bilolbek.ConsultingWebsite.models.Course;
import com.bilolbek.ConsultingWebsite.models.Resources;
import com.bilolbek.ConsultingWebsite.repositories.CourseRepository;
import com.bilolbek.ConsultingWebsite.repositories.ResourcesRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class FileService {

    private S3Client s3Client;

    @Value("${wasabi.bucketName}")
    private String bucketName;

    @Autowired
    private ResourcesRepo resourcesRepo;

    @Autowired
    private CourseRepository courseRepository;

    public FileService(S3Client s3Client){
        this.s3Client = s3Client;
    }

    /*public ResponseEntity<Map<String, String>> saveResource(MultipartFile file) throws IOException{
        String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(filename)
            .acl("public-read")
            .build();

        try(InputStream inputStream = file.getInputStream()){
            s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, file.getSize()));
        }

        return ResponseEntity.ok(Map.of("message", filename + " saved successfully"));
    }*/

    @Transactional
    public ResponseEntity<Map<String, String>> saveResource(MultipartFile file, long courseId) throws IOException{
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find course with id " + courseId));
        String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(filename)
            .acl("public-read")
            .build();

        try(InputStream inputStream = file.getInputStream()){
            s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, file.getSize()));
        }

        Resources resources = new Resources(filename, course);
        resourcesRepo.save(resources);

        return ResponseEntity.ok(Map.of("message", filename + " saved successfully"));
    }

    public String saveFile(MultipartFile file) throws IOException{
        String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(filename)
            .acl("public-read")
            .build();

        try(InputStream inputStream = file.getInputStream()){
            s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, file.getSize()));
        }

        return filename;
    }

    public ResponseEntity<Resource> getFile(String fileName) {

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(fileName)
            .build();

        ResponseInputStream<GetObjectResponse> getObjectResponse = s3Client.getObject(getObjectRequest);

        InputStream inputStream = getObjectResponse;

        InputStreamResource  resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    public boolean deleteFile(String filename){
        try{
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();

                s3Client.deleteObject(deleteObjectRequest);
                System.out.println("Deleted: " + filename);
                return true;
        }
        catch(S3Exception e){
            System.err.println("Failed to delete file: " + filename);
            System.err.println(e.awsErrorDetails().errorMessage());
            return false;
        }
    }

}
