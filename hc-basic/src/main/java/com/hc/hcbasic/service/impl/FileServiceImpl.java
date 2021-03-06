/*
package com.hc.distributed.service.impl;

import CheckException;
import ErrorException;
import com.hc.distributed.utils.FastDFSUtil;
import FileInfo;
import com.hc.distributed.service.FileService;
import FileVerifyUtil;
import VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private FastDFSUtil fastDFSUtil;


    @Override
    public String[] uploadFile(MultipartFile multipartFile) {
        log.info("上传文件-->>进入");
        if (!VerifyUtil.checkNull(multipartFile)) {
            throw new CheckException("文件不能为空");
        }
        try {
            return fastDFSUtil.upload(multipartFile);
        } catch (MyException | IOException e) {
            e.printStackTrace();
            throw new ErrorException(e.getLocalizedMessage());
        }
    }

    @Override
    public byte[] downloadFile(HttpServletRequest request, FileInfo fileInfo, HttpServletResponse response) {
        isNotEmpty(fileInfo);
        try {
            return fastDFSUtil.download(fileInfo.getGroup(), fileInfo.getFilePath());
        } catch (IOException | MyException e) {
            e.printStackTrace();
            throw new ErrorException(e.getMessage());
        }
    }

    @Override
    public FileInfo getFileInfo(FileInfo fileInfo) {
        return null;
    }

    @Override
    public void deleteFile(FileInfo fileInfo)  {
        isNotEmpty(fileInfo);
        try {
            fastDFSUtil.delete(fileInfo.getGroup(), fileInfo.getFilePath());
        } catch (IOException | MyException e) {
            e.printStackTrace();
            throw new ErrorException(e.getMessage());
        }
    }

    private void isNotEmpty(FileInfo fileInfo) {
        if (!FileVerifyUtil.checkNull(fileInfo) || !FileVerifyUtil.checkNull(fileInfo.getFilePath(), fileInfo.getGroup())) {
            throw new CheckException("文件信息不能为空");
        }
    }
}
*/
