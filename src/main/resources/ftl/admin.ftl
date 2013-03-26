<head>
    <title>Blood Torrent</title>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <script>
        $(function() {
            $( "input[type=button]" ).button()
        });
    </script>
</head>
<body>
<h1><img src="/images/main_sample.jpg" width="72" height="72" valign="middle" /> <label id="title">Blood Torrent</label></h1> <br/>
<#assign userId=user.id>
Hello ${userId?substring(0, userId?index_of("@")) }
<a href="/logoff">Sign off</a>
<h1>Request Approval</h1>
</body>