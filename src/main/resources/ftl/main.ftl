
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
    <h1><label id="title">Welcome to Blood Torrent</label></h1> <br/>
    <h1>Welcome to Blood Torrent</h1>
    ID : <input type="text" name="id" value="" />&nbsp;&nbsp;&nbsp;&nbsp;
    PWD : <input type="password" name="pwd" /> &nbsp;&nbsp;
    <a href="/login" ><input type="button" value="Log in" name="login"></a>
        <br/>
        <br/>
    <a href="/user"><input type="button" value="Register donor"/></a> &nbsp;
    <a href="/requestForBlood"><input type="button" value="Request blood"/></a>  <br/>
</body>
