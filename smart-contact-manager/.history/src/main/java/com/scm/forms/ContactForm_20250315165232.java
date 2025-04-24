package com.scm.forms;

import org.springframework.web.multipart.MultipartFile;

import com.scm.validator.ValidFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ContactForm {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "invalid email")
    private String email;

    @NotBlank(message = "phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "address is required")
    private String address;
    
    private String description;
    private boolean favorite;
    private String websiteLink;
    private String linkedInLink;
    //annotation create karenge file validate
    //size
    //resolution

    @ValidFile(message = "Invalid File")
    private MultipartFile contactImage;
}
