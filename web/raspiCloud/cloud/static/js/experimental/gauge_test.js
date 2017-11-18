google.charts.setOnLoadCallback(drawGaugeChart);
function drawGaugeChart() {
  
  var jsonData = $.ajax({
                      url: "get_json_gauge",
                      dataType: "json",
                      async: false
                      }).responseText;
  var data = new google.visualization.DataTable(jsonData);
 
  var options = {
    width: 600, height: 240,
    redFrom: 90, redTo: 100,
    yellowFrom:75, yellowTo: 90,
    minorTicks: 5
  };

  var chart = new google.visualization.Gauge(document.getElementById('gauge_div'));

  chart.draw(data, options);
  
  setInterval(function() {
    jsonData = $.ajax({
                      url: "get_json_gauge",
                      dataType: "json",
                      async: false
                      }).responseText;
    data = new google.visualization.DataTable(jsonData);
    chart.draw(data, options);
  }, 2000);
  
}
