<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        .full-screen {
            background-color: #6f42c1;
            width: 100vw;
            height: 100vh;
            color: white;
            font-family: 'Arial Black';
            text-align: center;
        }

        .container {
            padding-top: 4em;
            width: 50%;
            display: block;
            margin: 0 auto;
        }

        .error-num {
            font-size: 8em;
        }

        .eye {
            background: #fff;
            border-radius: 50%;
            display: inline-block;
            height: 100px;
            position: relative;
            width: 100px;
        }

        .italic {
            font-style: italic;
        }

        p {
            margin-bottom: 4em;
        }

        a {
            color: white;
            text-decoration: none;
            text-transform: uppercase;
        }
    </style>
    <title>Ooops!!!</title>
</head>
<body>
<div class="full-screen">
    <div class='container'>
        <span class="error-num">5</span>
        <div class='eye'></div>
        <div class='eye'></div>

        <p class="sub-text">Oh eyeballs! Something went wrong. We're <span class="italic">looking</span> to see what
            happened.</p>
        <a href="/welcome">Go back</a>
    </div>
</div>
</body>
</html>
