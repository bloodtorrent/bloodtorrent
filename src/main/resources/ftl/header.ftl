<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel='stylesheet' type='text/css' href='../css/main_style.css'/>
    <link rel='stylesheet' type='text/css' href='../css/bloodtorrent.css'/>
    <link href='../css/fonts_googleapis.css' rel='stylesheet' type='text/css' />
    <style type='text/css'>
        .wsite-elements div.paragraph, .wsite-elements p, .wsite-elements .product-block .product-title, .wsite-elements .product-description, .wsite-elements .wsite-form-field label, .wsite-elements .wsite-form-field label, #wsite-content div.paragraph, #wsite-content p, #wsite-content .product-block .product-title, #wsite-content .product-description, #wsite-content .wsite-form-field label, #wsite-content .wsite-form-field label, .blog-sidebar div.paragraph, .blog-sidebar p, .blog-sidebar .wsite-form-field label, .blog-sidebar .wsite-form-field label {}
        #wsite-content div.paragraph, #wsite-content p, #wsite-content .product-block .product-title, #wsite-content .product-description, #wsite-content .wsite-form-field label, #wsite-content .wsite-form-field label, .blog-sidebar div.paragraph, .blog-sidebar p, .blog-sidebar .wsite-form-field label, .blog-sidebar .wsite-form-field label {}
        .wsite-elements h2, .wsite-elements .product-long .product-title, .wsite-elements .product-large .product-title, .wsite-elements .product-small .product-title, #wsite-content h2, #wsite-content .product-long .product-title, #wsite-content .product-large .product-title, #wsite-content .product-small .product-title, .blog-sidebar h2 {}
        #wsite-content h2, #wsite-content .product-long .product-title, #wsite-content .product-large .product-title, #wsite-content .product-small .product-title, .blog-sidebar h2 {}
        #wsite-title {color:#990000 !important;}
    </style>
</head>
<div id="header-wrap">
    <div class="page">
        <div id="header-container">
            <table id="header">
                <tr>
                    <td id="logo"><span class='wsite-logo'><a href='/'><span id="wsite-title">${printStrings.logoTitle}</span></a></span></td>
                    <td id="header-right">
                        <table>
                            <tr>
                                <td class="phone-number"></td>
                                <td class="social"></td>
                                <td class="search"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <div id="topnav">
                <ul>
                    <li <#if templateName?contains('main')>id='active'</#if>><a href='/'>${printStrings.menuBarHome}</a></li>
                    <li <#if templateName?contains('donorRegister')>id='active'</#if>><a href='/user'>${printStrings.menuBarRegister}</a></li>
                    <li <#if templateName?contains('bloodRequest')>id='active'</#if>><a href='/requestForBlood'>${printStrings.menuBarRequest}</a></li>
                </ul>
                <div style="clear:both"></div>
            </div>
        </div>
        <div id="banner">
            <div class="wsite-header"></div>
        </div>
    </div>
</div>