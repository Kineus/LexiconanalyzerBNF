<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User Input Form</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <style>
        html, body {
            min-height: 100%;
            padding: 0;
            margin: 0;
            font-family: Roboto, Arial, sans-serif;
            font-size: 14px;
            color: #666;
            background-color: #e6e6e6; /* Set your desired background color */
        }

        h1 {
            margin: 20px 0;
            font-weight: 400;
            color: #1c87c9;
            text-align: center;
        }

        form {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            box-shadow: 0 2px 5px #f5f5f5;
            background: #f5f5f5;
            border-radius: 10px;
        }

        .container {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 5px;
            font-weight: 500;
        }

        input, textarea {
            width: 100%;
            border-radius: 12px;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #1c87c9;
            outline: none;
            box-sizing: border-box;
        }

        input::placeholder {
            color: #666;
        }

        button {
            width: 100%;
            margin-top: 10px;
            padding: 12px;
            border-radius: 18px;
            border: none;
            background: #1c87c9;
            font-size: 16px;
            font-weight: 400;
            color: #fff;
            cursor: pointer;
        }

        button:hover {
            background: #2371a0;
        }

        /* Additional styles for icons */
        .icon {
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <form action="submitForm" method="post">
        <h1><i class="fas fa-user icon"></i> Lexical Analysis Back-Naur Form</h1>
        <div class="container">
            <!-- Add icons to labels or inputs if desired -->
            <label for="LastName"><i class="fas fa-user icon"></i> Last Name</label>
            <input type="text" id="LastName" name="LastName" placeholder="Last Name" required>

            <label for="FirstName"><i class="fas fa-user icon"></i> First Name</label>
            <input type="text" id="FirstName" name="FirstName" placeholder="First Name" required>

            <label for="OtherName"><i class="fas fa-user icon"></i> Other Name</label>
            <input type="text" id="OtherName" name="OtherName" placeholder="Other Name" required>

            <label for="Address"><i class="fas fa-map-marker-alt icon"></i> Address</label>
            <input type="text" id="Address" name="Address" placeholder="Address" required>

            <label for="Email_address"><i class="fas fa-envelope icon"></i> Email Address</label>
            <input type="email" id="Email_address" name="Email_address" placeholder="Email Address" required>

            <label for="Password"><i class="fas fa-lock icon"></i> Password</label>
            <input type="password" id="Password" name="Password" placeholder="Password" required>

            <label for="Comment"><i class="fas fa-comment icon"></i> Comment</label>
            <textarea id="Comment" name="Comment" placeholder="Comment"></textarea>
        </div>

        <button type="submit"><i class="fas fa-check icon"></i> Submit</button>
    </form>
</body>
</html>
