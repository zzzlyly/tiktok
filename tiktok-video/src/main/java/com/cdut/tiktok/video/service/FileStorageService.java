package com.cdut.tiktok.video.service;

import com.aliyun.oss.OSS;

public class FileStorageService {
    private final OSS ossClient;
    private final String bucketName;

    public FileStorageService(OSS ossClient, String bucketName) {
        this.ossClient = ossClient;
        this.bucketName = bucketName;
    }
}
