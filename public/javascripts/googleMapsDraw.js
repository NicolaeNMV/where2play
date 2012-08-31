   google.load('visualization', '1', {'packages': ['geochart']});
   google.setOnLoadCallback(drawRegionsMap);

   function drawRegionsMap() {
    var data = google.visualization.arrayToDataTable([
      ['Country', 'Popularity'],
      ['UNITED STATES', 314],
      ['FRANCE', 80],
      ['IRELAND', 58],
      ['CHINA', 35],
      ['JAPAN', 32],
      ['GERMANY', 31],
      ['SINGAPORE', 20],
      ['KOREA, REPUBLIC OF', 17],
      ['KOREA, REPUBLIC OF', 17],
      ['UNITED KINGDOM', 15],
      ['NETHERLANDS', 14],
      ['HONG KONG', 10],
      ['RUSSIAN FEDERATION', 8],
      ['AUSTRALIA', 8],
      ['CANADA', 7],
      ['FINLAND', 6],
      ['TURKEY', 5],
      ['SWITZERLAND', 5],
      ['NORWAY', 4],
      ['SWEDEN', 4],
      ['AUSTRIA', 3],
      ['SPAIN', 3],
      ['ITALY', 3],
      ['KAZAKHSTAN', 3],
      ['BRAZIL', 3],
      ['TAIWAN', 2],
      ['SLOVENIA', 2],
      ['DENMARK', 2],
      ['POLAND', 2],
      ['ICELAND', 1],
      ['MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF', 1],
      ['GREECE', 1],
      ['BELGIUM', 1],
      ['SLOVAKIA', 1],
      ['IRAN, ISLAMIC REPUBLIC OF', 1],
      ['LITHUANIA', 1],
      ['INDONESIA', 1],
      ['MONGOLIA', 1],
      ['ISRAEL', 1],
      ['INDIA', 1],
      ['UKRAINE', 1],
      ['ARGENTINA', 1],
      ['PHILIPPINES', 1],
      ['BOSNIA AND HERZEGOVINA', 1]
      ]);

   var options = {};

   var chart = new google.visualization.GeoChart(document.getElementById('chart_div'));
   chart.draw(data, options);
 };