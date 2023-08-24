package com.Codenera.auth.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.Codenera.auth.model.CodeForm;
import com.Codenera.auth.model.CompileRequest;
import com.Codenera.auth.model.FileData;
import com.Codenera.auth.model.Student;
import com.Codenera.auth.repository.FileDataRepository;
import com.Codenera.auth.service.CompilerService;
import com.Codenera.auth.service.PdfGenerationService;
import com.Codenera.auth.service.StudentService;
import com.Codenera.auth.validator.StudentValidator;



@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentValidator studentValidator;

    @Autowired
    private CompilerService compilerService;
    
    @Autowired
    private FileDataRepository fileDataRepository;
    
    @Autowired
    private PdfGenerationService pdfGenerationService;

    // Handle GET request for the student login page
    @GetMapping("/student-login")
    public String studentLogin(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Invalid username or password.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "student_login";
    }

    // Handle POST request for student login
    @PostMapping("/student-login")
    public String studentLogin(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        Student student = studentService.findByUsername(username);

        if (student == null || !BCrypt.checkpw(password, student.getPassword())) {
            model.addAttribute("error", "Invalid username or password.");
            return "student_login";
        }

        // Set the logged-in student in the session for further usage, if needed.
        session.setAttribute("loggedInStudent", student);

        return "redirect:/student-dashboard";
    }

    @GetMapping("/student-dashboard")
    public String studentDashboard(Model model, HttpSession session) {
        // Retrieve the logged-in student from the session if needed.
        Student loggedInStudent = (Student) session.getAttribute("loggedInStudent");
        model.addAttribute("loggedInStudent", loggedInStudent);
        List<FileData> fileList = fileDataRepository.findAll();
        model.addAttribute("fileList", fileList);
        model.addAttribute("compileRequest", new CompileRequest());

        return "student_dashboard";
    }
    @PostMapping("/compile")
    public String compileCode(@ModelAttribute("compileRequest") CompileRequest compileRequest, Model model) {
        String result = compilerService.compileAndRunJavaCode(compileRequest.getCode(), compileRequest.getInputData());
        model.addAttribute("compilerResult", result);
        
        return "compilertest";
    }


    @PostMapping("/generate-pdf")
    public void generatePdf(@ModelAttribute CodeForm codeForm, HttpServletResponse response) {
        try {
            String code = codeForm.getCode();
            byte[] pdfBytes = pdfGenerationService.generatePdf(code);
            
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"code.pdf\"");
            response.getOutputStream().write(pdfBytes);
            response.getOutputStream().flush();
        } catch (Exception e) {
        }
    }



    
    @GetMapping("/fileList")
    public String fileList(Model model) {
        List<FileData> fileList = fileDataRepository.findAll();
        model.addAttribute("fileList", fileList);
        return "file_list";
    }
    
    // Handle GET request to view a specific file
    @GetMapping("/fileList/{fileId}")
    public void viewFile(@PathVariable Long fileId, HttpServletResponse response) throws IOException {
        FileData fileData = fileDataRepository.findById(fileId).orElse(null);
        if (fileData != null) {
            response.setContentType("application/pdf"); // Set the appropriate content type (e.g., image/jpeg for images)
            response.setContentLength(fileData.getData().length);
            response.getOutputStream().write(fileData.getData());
            response.getOutputStream().flush();
        }
    }

    // Handle GET request to download a specific file
    @GetMapping("/download/{fileId}")
    public void downloadFile(@PathVariable Long fileId, HttpServletResponse response) throws IOException {
        FileData fileData = fileDataRepository.findById(fileId).orElse(null);
        if (fileData != null) {
            // Set the appropriate content type based on the file type (e.g., application/pdf for PDF files)
            response.setContentType("application/octet-stream");
            response.setContentLength(fileData.getData().length);

            // Set the Content-Disposition header to prompt a download dialog with the file name
            String headerValue = "attachment; filename=\"" + fileData.getFileName() + "\"";
            response.setHeader("Content-Disposition", headerValue);

            // Write the file data to the response output stream
            response.getOutputStream().write(fileData.getData());
            response.getOutputStream().flush();
        }
    }


    // Handle GET request for student logout
    @GetMapping("/student-logout")
    public String studentLogout(HttpSession session) {
        // Invalidate the session and clear session attributes.
        session.invalidate();
        return "redirect:/student-login";
    }

}
