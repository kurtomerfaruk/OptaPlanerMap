var map ;
document.addEventListener("DOMContentLoaded", function() {
     createMap();

    var markers = [];
    window.updateMap = function(customers) {
        createMap();
        markers.forEach(function(marker) {
            map.removeLayer(marker);
        });
        markers = [];

        customers.forEach(function(customer) {
            var marker = L.marker([customer.latitude, customer.longitude]).addTo(map);
            markers.push(marker);
        });

        var latlngs = customers.map(function(customer) {
            return [customer.latitude, customer.longitude];
        });
        
        var polyline = L.polyline(latlngs, {color: 'blue'}).addTo(map);
        map.fitBounds(polyline.getBounds());
    };
});

function createMap(){
    map = L.map('map').setView([38.9637, 35.2433], 6);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors'
    }).addTo(map);
}