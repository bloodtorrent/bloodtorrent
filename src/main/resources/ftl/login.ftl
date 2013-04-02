<head>
    <title>Blood Torrent</title>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <script>
        $(function() {
            $( "input[type=button]" ).button()
            $("#signin").click(function(){
                $("#loginForm").submit();
            });
        });
    </script>
</head>
<body>
    <form id = "loginForm" action="/signin" method="post">
        <p>
            SIGN-IN NAME : <input type="text" name="username" />
        </p>
        <p>
            PASSWORD:
            <input type="password" name="password" id="password" />
        </p>
        <p>
            <input type="button" id="signin" value="SIGN IN" />
        </p>
    </form>
</body>