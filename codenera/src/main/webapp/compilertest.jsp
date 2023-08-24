<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Code Compiler</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            max-width: 900px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        select, textarea, input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        pre {
            max-width: 100%;
            padding: 20px;
            background-color: #f5f5f5;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            font-size: 14px;
            white-space: pre-wrap;
        }

        h3 {
            text-align: center;
            margin-top: 20px;
            color: #333;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Code Compiler</h1>
        <select name="selectedQuestion" id="selectedQuestion" onchange="updateQuestion()">
            <option value="1">None</option>
            <option value="2">Question 1</option>
            <option value="3">Question 2</option>
            <option value="4">Question 3</option>
        </select>
        <h3 id="questionTitle">Select a question</h3>
        <form method="post" action="/compile">
            <label for="code">Enter Java Code:</label>
            <textarea id="code" name="code" rows="10" cols="80"></textarea>
            <br>
            <label for="inputData">Input Data:</label>
            <input type="text" id="inputData" name="inputData">
            <br>
            <br>
            <input type="submit" value="Compile and Run">
        </form>
        <h3>Compiler Result:</h3>
        <pre>${compilerResult}</pre>
        
        <form action="/generate-pdf" method="post">
        <!--  <textarea name="code" rows="10" cols="80" placeholder="Enter Java Code"></textarea>
        <br> -->
        <input type="submit" value="Generate PDF">
    </form>
    </div>
</body>
</html>
