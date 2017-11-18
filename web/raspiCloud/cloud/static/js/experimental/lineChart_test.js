google.charts.setOnLoadCallback(drawLineChart);

function drawLineChart() {
  /*var data = google.visualization.arrayToDataTable([
    ['Year', 'Sales'],
    ['2004',  1000],
    ['2005',  1170],
    ['2006',  660],
    ['2007',  1030]
  ]);*/

  var jsonData = $.ajax({
                      url: "get_json_lineChart",
                      dataType: "json",
                      async: false
                      }).responseText;
  var data = new google.visualization.DataTable(jsonData);

  var options = {
    title: 'Company Performance',
    curveType: 'function',
    legend: { position: 'bottom' },
    animation:{
        duration: 0,
        easing: 'linear',
      }
  };

  var chart = new google.visualization.LineChart(document.getElementById('lineChart_div'));

  chart.draw(data, options);

  setInterval(function() {
    jsonData = $.ajax({
                      url: "get_json_lineChart",
                      dataType: "json",
                      async: false
                      }).responseText;
    data = new google.visualization.DataTable(jsonData);
    chart.draw(data, options);
  }, 2000);
}