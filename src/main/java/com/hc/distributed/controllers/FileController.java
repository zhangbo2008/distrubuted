/*
package com.hc.distributed.controllers;


import com.hc.distributed.dtos.ResultBean;
import com.hc.distributed.exceptions.CheckException;
import com.hc.distributed.utils.HdfsUtil;
import com.hc.distributed.model.FileInfo;
import com.hc.distributed.service.FileService;
import com.hc.distributed.utils.DownloadUtil;
import org.apache.hadoop.fs.Path;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService, HdfsUtil hdfsUtil) {
        this.fileService = fileService;
        this.hdfsUtil = hdfsUtil;
    }


    @PostMapping("/file")
    public ResultBean<String[]> uploadPhoto(@RequestParam("file") MultipartFile multipartFile) throws IOException, MyException {
        return new ResultBean<>(fileService.uploadFile(multipartFile));
    }

    @GetMapping("/file")
    public ResultBean<String[]> downloadFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            byte[] bytes = fileService.downloadFile(request, new FileInfo(request.getParameter("group"), request.getParameter("filepath"), ""), response);
            response.getOutputStream().write(bytes);
            // 设置提示保存或打开
            response.setHeader("content-disposition", "attachment;filename=" + DownloadUtil.encodingFilename(request.getHeader("user-agent"), request.getParameter("name")));

        } catch (MyException | IOException e) {
            e.printStackTrace();
        } catch (NullPointerException npe) {
            throw new CheckException("文件不存在");
        }
        return new ResultBean<>();
    }

    @DeleteMapping("/file")
    public ResultBean<String[]> delFile(@RequestBody FileInfo fileInfo) throws IOException, MyException {
        fileService.deleteFile(fileInfo);
        return new ResultBean<>();
    }

    private final HdfsUtil hdfsUtil;

    @PostMapping("/filebyhdfs")
    public ResultBean<String[]> uploadFileByHdfs(@RequestParam("file") MultipartFile multipartFile) {
        try {
            hdfsUtil.getFileStatus(new Path("/file/for mysql.txt"));
//            hdfsUtil.uploadFile(multipartFile, "/file", multipartFile.getOriginalFilename(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultBean<>();
    }
}
*/
