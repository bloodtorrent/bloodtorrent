<style>
    #mapCanvas {
        width: 100%;
        height: 400px;
        float: left;
    }
    #infoPanel {
        display:none;
    }
</style>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=en"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script type="text/javascript" language="javascript">

//<![CDATA[
var currentLatLng;
var originalLatLng;

$(function() {

    $( "#searchButton" )
        .button()
        .width(100)
        .height(15)
        .click(function( event ) {
            searchAddress(document.getElementById('search_address').value);
    });

   $( "#map_dialog" ).dialog({
        autoOpen: false,
        minHeight: 600,
        minWidth: 600,
        modal: true,
        open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
        buttons: {
            "Save" : {
              id: "saveLocation",
              text : "Save",
              click: function(){
                  originalLatLng = currentLatLng;
                  $("#lat").val(originalLatLng.lat());
                  $("#lng").val(originalLatLng.lng());
                  $("#messageLabel").text("Your map location is saved");
                  $("#messageLabel").css("color","blue");
                  $("#lng").val(originalLatLng.lng());
                  $("#isMapExcuted").val("Y");
                  $( this ).dialog( "close" );
              }
            },
            "Cancel" : {
                id : "cancelSaveLocation",
                text : "Cancel",
                click : function() {
                    $( "#dialog_confirm" ).dialog( "open");
                }
            }
        }
    });

    $("#popupMap").click(function(e){
        var address = $("textarea[name='address']").val();
        var city = $("input[type='text'][name='city']").val();
        var state = $("select[name='state']").val();

        //address, state, city
        if ( address == "" || city == "" || state == "" ) {
            alert("Please provide the address, city and state before using map.");
            return;
        }
        searchLocation();

        $( "#map_dialog" ).dialog( "open" );
    });

    $( "#dialog_confirm" ).dialog({
        autoOpen: false,
         buttons: {
            "Yes": {
                id : "closeMap",
                text : "Yes",
                click : function() {
                    $( this ).dialog( "close" );
                    currentLatLng = originalLatLng;
                    $( "#map_dialog"  ).dialog( "close" );
                }
            },
             "No" : {
                 id : "cancelCloseMap",
                 text : "No",
                 click : function() {
                    $( this ).dialog( "close" );
                 }
             }
         }
    });
    $("#dialog_confirm").html('Do you wish to proceed without specifying exact location?');
    $("#dialog_confirm").css('font-size','62.5%');
    $("#dialog_confirm").dialog();
});



//Search bar
var searchLocation = function(){
    addressToSearch = $("#orginalAddress").val() +"," + $("#city").val() +"," + $("#state").val() +"," +"India";
    document.getElementById('search_address').value = addressToSearch;
    currentLat = $("#lat").val();
    currentLng = $("#lng").val();
    if(currentLat != "" && currentLng != ""){
        originalLatLng = new google.maps.LatLng(currentLat, currentLng);
    }
    searchAddress(addressToSearch);
    return currentLatLng;
}

var geocoder = new google.maps.Geocoder();

var searchAddress = function(fullAddress) {
    geocoder.geocode(
        {
            'address': fullAddress,
            'location': originalLatLng,

        },
        function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                var loc = results[0].geometry.location;
                if(typeof originalLatLng == 'undefined') {
                    currentLatLng = loc;
                }else{
                    currentLatLng = originalLatLng;
                }
            }else {
                //alert("Not found: " + status);
            }

            init(currentLatLng);
        }
    );
};

function geocodePosition(pos) {
    geocoder.geocode({
        latLng: pos,
        language: 'en'
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

    currentLatLng = latLng;
}

function updateMarkerAddress(str) {
    document.getElementById('address').innerHTML = str;
}

function init(latLng) {
    var map = new google.maps.Map(document.getElementById('mapCanvas'), {
        zoom: 14,
        center: latLng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var marker = new google.maps.Marker({
        position: latLng,
        title: 'Your Location',
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


//]]>
</script>
<div id="map_dialog" title="Map">
    <div id="searchAddress">
        <H4>Please set exact location in this map. It will help us to provide you more accurate notification to donate</H4>
        <input type="text" id="search_address" size="40%" value="India" onkeydown="if(event.keyCode == 13) document.getElementById('searchButton').click()" />
        <input type="text" id="searchButton" value="search"/>
    </div>
    <div id="mapCanvas" ></div>
    <div id="infoPanel">
        <b>Current position:</b>
        <div id="info"></div>
        <b>Closest matching address:</b>
        <div id="address"></div>
    </div>
    <div id="dialog_confirm" title="Set Location">
    </div>
</div>