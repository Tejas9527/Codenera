package com.Codenera.auth.web;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Codenera.auth.model.FileData;
import com.Codenera.auth.model.FileTest;
import com.Codenera.auth.model.User;
import com.Codenera.auth.repository.FileDataRepository;
import com.Codenera.auth.repository.FileTestRepository;
import com.Codenera.auth.service.SecurityService;
import com.Codenera.auth.service.UserService;
import com.Codenera.auth.validator.UserValidator;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private FileDataRepository fileDataRepository;
    
    @Autowired
    private FileTestRepository fileTestRepository;

    @RequestMapping("/")
    public String welcomePage() {
        return "index";
    }

    // Handle the form submission to select the login type
    @RequestMapping("/select-login")
    public String selectLogin(@RequestParam String loginType) {
        if ("user".equals(loginType)) {
            return "redirect:/login";
        } else if ("student".equals(loginType)) {
            return "redirect:/student-login";
        }

        // In case of invalid loginType, redirect back to the index page
        return "redirect:/";
    }

    // Handle GET request for user registration form
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    // Handle POST request for user registration
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        // Validate user input using UserValidator
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration"; // If there are validation errors, return to the registration form
        }
        // Save the user details and automatically log in the user
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/login"; // Redirect to login page after successful registration
    }

    // Handle GET request for login page
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    // Handle GET request for the welcome page
    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("fileData", new FileData());
        return "welcome";
    }

    // Handle POST request for file upload

 // Handle POST request for uploading assignment
    @PostMapping("/upload-assignment")
    public String uploadAssignment(@RequestParam("assignmentFile") MultipartFile assignmentFile,
                                   @RequestParam("newDateColumn") @DateTimeFormat(pattern = "yyyy-MM-dd") String newDateColumn,
                                   Model model) throws ParseException {
        if (assignmentFile.isEmpty()) {
            model.addAttribute("error", "Please select an assignment file to upload.");
            return "welcome"; // Return to the welcome page with the error message
        }

        try {
            // Save AssignmentData to the "Assignment" table
            FileData assignmentData = new FileData();
            assignmentData.setFileName(assignmentFile.getOriginalFilename());
            assignmentData.setData(assignmentFile.getBytes());
            assignmentData.setNewDateColumn(newDateColumn);
            fileDataRepository.save(assignmentData);

            model.addAttribute("message", "Assignment uploaded successfully.");
        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload the assignment.");
        }

        return "redirect:/welcome"; // Redirect to the welcome page after successful upload
    }

 // Handle POST request for uploading test
    @PostMapping("/upload-test")
    public String uploadTest(@RequestParam("testFile") MultipartFile testFile,
                             @RequestParam("newDateColumn") @DateTimeFormat(pattern = "yyyy-MM-dd") String newDateColumn,
                             Model model) throws ParseException {
        if (testFile.isEmpty()) {
            model.addAttribute("error", "Please select a test file to upload.");
            return "welcome"; // Return to the welcome page with the error message
        }

        try {
            // Save FileTest to the "Test" table
            FileTest testData = new FileTest();
            testData.setFileName(testFile.getOriginalFilename());
            testData.setData(testFile.getBytes());
            testData.setNewDateColumn(newDateColumn);
            fileTestRepository.save(testData);

            model.addAttribute("message", "Test uploaded successfully.");
        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload the test.");
        }

        return "redirect:/welcome"; // Redirect to the welcome page after successful upload
    }


    
}
