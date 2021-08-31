package com.learn.controller;

import com.learn.utils.MultipartFileUtil;
import com.learn.utils.R;
import com.learn.utils.RRException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *
 *
 * @author shenyt
 * @email syt12322@163.com
 * @date 2018-03-25 12:13:26
 */
@RestController
@RequestMapping("file")
public class UploadController {

    public static String[] suffixs = {"IMG", "PNG", "JPG", "JPEG", "GIF", "BPM"};

    /**
     *
     */
    @RequestMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("The uploaded file cannot be empty");
        }
        String url = MultipartFileUtil.uploadFile("/cdn", file, request);
        return R.ok().put("url", url);
    }


    /**
     *
     *
     * @param upload
     * @param response
     */
    @ResponseBody
    @RequestMapping("ckEditorUpload")
    public void uploadFile(MultipartFile upload, String CKEditorFuncNum, HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String path = null;

            if (upload != null && !upload.isEmpty()) {

                String url = MultipartFileUtil.uploadFile("/cdn", upload, request);
                path = url;
            }


            //
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + path + "','')");
            out.println("</script>");

        } catch (RuntimeException e) {
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'','" + e.getMessage() + "');");
            out.println("</script>");
        }
    }


}
