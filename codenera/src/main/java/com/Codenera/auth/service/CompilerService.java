package com.Codenera.auth.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.janino.SimpleCompiler;
import org.springframework.stereotype.Service;

@Service
public class CompilerService {

    public String compileAndRunJavaCode(String code, String inputData) {
        try {
            // Get the name of the main class from the code
            String mainClassName = getMainClassName(code);

            SimpleCompiler compiler = new SimpleCompiler();

            // Compile the code
            compiler.cook(code);

            // Load the compiled class
            Class<?> compiledClass = compiler.getClassLoader().loadClass(mainClassName);

            // Execute the code with input data
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));

            // Pass the inputData to the program via System.in
            System.setIn(new ByteArrayInputStream(inputData.getBytes()));

            compiledClass.getDeclaredMethod("main", String[].class).invoke(null, new Object[] { null });

            // Reset System.in and System.out to their original values
            System.setIn(System.in);
            System.setOut(originalOut);

            // Return the output from the execution
            return outputStream.toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "{\"error\": \"Class not found. Please check your code and make sure you have a 'public class " + getMainClassName(code) + "' in the code.\"}";
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return "{\"error\": \"No 'public static void main(String[] args)' method found in the class " + getMainClassName(code) + ". Please make sure your code contains the main method.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }

    private String getMainClassName(String code) {
        // Use regex to find the name of the class containing the main method
        Pattern pattern = Pattern.compile("public\\s+class\\s+(\\w+)\\s*\\{\\s*public\\s+static\\s+void\\s+main\\s*\\(String\\[]");
        Matcher matcher = pattern.matcher(code);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            // If no main class is found, return a default name "MainClass"
            return "MainClass";
        }
    }
}
