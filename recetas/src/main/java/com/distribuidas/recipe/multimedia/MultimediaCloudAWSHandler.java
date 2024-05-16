package com.distribuidas.recipe.multimedia;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;

@Component
public class MultimediaCloudAWSHandler implements MultimediaCloudHandler{
    private S3Client s3 = S3Client.builder().region(Region.US_EAST_1).build();
    private String BucketName="adicook.multimedia";
    @Override
    public String GetURlSignedForUpload(String FileName, String userID) {

        S3Presigner presigner = S3Presigner.builder()
                .region(Region.US_EAST_1)
                .build();

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(BucketName)
                .key(userID+"/"+FileName)
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(5))
                .putObjectRequest(objectRequest)
                .build();

        PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);

        return  presignedRequest.url().toString();
    }



}
