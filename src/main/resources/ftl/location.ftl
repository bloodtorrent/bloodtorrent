<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=en"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <script type="text/javascript">
        var alreadySaved = false;
        //default Latitude and longitude : India
        var currentLat=21.8;
        var currentLng=79.2;
        //Search bar
        window.onload = function(){
            addressToSearch = '${location.address},${location.city},${location.state},India';
            document.getElementById('search_address').value = addressToSearch;
            searchAddress(addressToSearch);
        }

        var searchAddress = function(fullAddress) {
            geocoder.geocode(
                {'address': fullAddress},
                function(results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        var loc = results[0].geometry.location;
                        var bounds = results[0].geometry.bounds;
                        currentLat = loc.lat();
                        currentLng = loc.lng();
                    }
                    else {
                        //alert("Not found: " + status);
                    }
                    init(currentLat, currentLng);
                }
            );
        };
        var geocoder = new google.maps.Geocoder();
        function geocodePosition(pos) {
            geocoder.geocode({
                latLng: pos
                , language: 'en'
            }, function(responses) {
                if (responses && responses.length > 0) {
                    updateMarkerAddress(responses[0].formatted_address);
                } else {
                    updateMarkerAddress('Cannot determine address at this location.');
                }
            });
        }

        function updateMarkerStatus(str) {
            document.getElementById('markerStatus').innerHTML = str;
        }

        function updateMarkerPosition(latLng) {
            document.getElementById('info').innerHTML = [
                latLng.lat(),
                latLng.lng()
            ].join(', ');
        }

        function updateMarkerAddress(str) {
            document.getElementById('address').innerHTML = str;
        }

        var marker;
        function init(currentLat, currentLng) {
            var latLng = new google.maps.LatLng(currentLat, currentLng);
            var map = new google.maps.Map(document.getElementById('mapCanvas'), {
                zoom: 5,
                center: latLng,
                mapTypeId: google.maps.MapTypeId.HYBRID
            });

            marker = new google.maps.Marker({
                position: latLng,
                title: 'Point A',
                map: map,
                draggable: true
            });

            updateMarkerPosition(latLng);
            geocodePosition(latLng);

            google.maps.event.addListener(marker, 'dragstart', function() {
                updateMarkerAddress('Dragging...');
            });

            google.maps.event.addListener(marker, 'drag', function() {
                <!--updateMarkerStatus('Dragging...');-->
                updateMarkerPosition(marker.getPosition());
            });

            google.maps.event.addListener(marker, 'dragend', function() {
                <!--updateMarkerStatus('Drag ended');-->
                geocodePosition(marker.getPosition());
            });
        }

        window.onbeforeunload = function() {
            cancel();
        }

        function cancel(){
            var confirmMessage = "Do you wish to proceed without location setting?";
            if(!confirm(confirmMessage)){
                window.close();
            }
        }

        var applyLocationToOpener = function(){
        $("#var_id")
            var label = window.opener.jQuery("#messageLabel");
            label.value = "Your map location is saved";
        }
    </script>
</head>
<body>
<style>
    #mapCanvas {
    width: 500px;
    height: 400px;
    float: left;
    }
    #infoPanel {
    float: left;
    margin-left: 10px;
    }
    #infoPanel div {
    margin-bottom: 5px;
    }
</style>

<div id="searchAddress">
    <input type="text" id="search_address" size="60%" value="India" onkeydown="if(event.keyCode == 13) document.getElementById('searchButton').click()" />
    <button id="searchButton" onclick="searchAddress(document.getElementById('search_address').value);">Search</button>
</div>
<div id="mapCanvas"></div>
<div id="infoPanel">
    <b>Current position:</b>
    <div id="info"></div>
    <b>Closest matching address:</b>
    <div id="address"></div>
</div>
<div id="submit">
    <button id="submitLocation" onclick="alreadySaved=true; alert('address : '+document.getElementById('address').innerHTML+'\nlatitude:'+currentLat+'\nlongitude:'+currentLng);applyLocationToOpener()">save</button>
    <button id="cancel" onclick="cancel();">cancel</button>
</div>
</body>
</html>