/*
package com.hc.distributed.utils;


import CheckException;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.util.annotation.Nullable;

import java.io.File;
import java.io.IOException;

@Slf4j
public class FastDFSUtil {

    private TrackerClient trackerClient = new TrackerClient();
    private TrackerServer trackerServer = trackerClient.getConnection();
    private StorageServer storageServer = null;
    private StorageClient storageClient = new StorageClient(trackerServer, storageServer);

    //初始化分布式文件系统配置；
    static {
        try {
            String filePath = new File("E:\\JavaWeb\\workPlace\\distributed\\src\\main\\resources\\fdfs.conf").getAbsolutePath();
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            log.error("DFS Init Fail!", e);
        }
    }

    public FastDFSUtil() throws IOException {
    }


    public String[] upload(@Nullable MultipartFile file) throws MyException, IOException {
        if (!VerifyUtil.checkNull(file))
            throw new CheckException("文件不能为空");

        try {
            String fileName = file.getOriginalFilename() + "";
            String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);

            // 设置元信息
            NameValuePair[] metaList = new NameValuePair[2];
            metaList[0] = new NameValuePair("fileName", fileName);
            metaList[1] = new NameValuePair("fileExtName", fileExtName);

            log.info("upload file-->> fileName = {}, fileExtName = {}", fileName, fileExtName);

            return storageClient.upload_file(file.getBytes(), fileExtName, metaList);
        }  finally {
            close();
        }
    }


    public byte[] download(String group, String filePath) throws IOException, MyException {
        try {
            return storageClient.download_file(group, filePath);
        } finally {
            close();
        }
    }

    public FileInfo getFileInfo(String groupName, String filePath) throws IOException, MyException {
        try {
            return storageClient.get_file_info(groupName, filePath);
        } finally {
            close();
        }
    }

    */
/**
     * 获取文件的原始类型
     * @return
     *//*

    public NameValuePair[] getFileMate(String groupName, String filePath) throws IOException, MyException {
        try {
            return storageClient.get_metadata(groupName, filePath);
        } finally {
            close();
        }
    }

    public int delete(String groupName, String filePath) throws IOException, MyException {
        try {
            return storageClient.delete_file(groupName, filePath);
        } finally {
        }
    }


    private void close() {
        try {
            if (null != storageServer)
                storageServer.close();
            if (null != trackerServer)
                trackerServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/
