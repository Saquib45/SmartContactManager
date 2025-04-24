package com.scm.validator;


import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 2; // 2MB

    //type
    //height
    //widht

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        
        if(file==null || file.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File can not be empty").addConstraintViolation();
            return false;
        }
        //file size
        if(file.getSize()>MAX_FILE_SIZE) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size can not be more than 2MB").addConstraintViolation();
            return false;
        }

        //resolution

        // try {
        //     BufferedImage bufferedImage2 = ImageIO.read(file.getInputStream());

        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        return true;

    }

    

}
