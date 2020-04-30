<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="../scriptGanttView/jquery-ui-1.8.4.css" />
<link rel="stylesheet" type="text/css"
	href="../scriptGanttView/reset.css" />
<link rel="stylesheet" type="text/css"
	href="../scriptGanttView/jquery.ganttView.css" />
<title>Gantt View</title>
</head>
<body>
	<h1>Gantt View</h1>
	<div id="ganttChart"></div>
	<br />
	<br />
	<div id="eventMessage"></div>
</body>
<script type="text/javascript" src="../scriptGanttView/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../scriptGanttView/date.js"></script>
<script type="text/javascript"
	src="../scriptGanttView/jquery-ui-1.8.4.js"></script>
<script type="text/javascript"
	src="../scriptGanttView/jquery.ganttView.js"></script>
<script type="text/javascript">

     // API jquery.ganttView.js altera-mos Date.parse para new Date
	$(document).ready(function() {
		$.get("buscarDatasPlanejamento", function(response) {// inicio ajax get Servlet
			var ganttDataResposta = JSON.parse(response);
			var ganttData = "[";
		$.each(ganttDataResposta, function(index, projeto){ // for dos projetos
			ganttData += "{ \"id\": \"" + projeto.id + "\", \"name\": \"" + projeto.nome + "\", \"series\": [";
			$.each(projeto.series, function(idx, serie){// for das series
		       var cores = "#3366FF,#00CC00".split(',');
			   var cor;
			   if (idx === 0){
				   cor = "#CC##CC";   
			   }else{
				   cor = Number.isInteger(idx / 2) ? cores[0] : cores[1];
			   }
			   var dataInicial = serie.dataInicial.split('-');
			   var dataFinal = serie.dataFinal.split('-');
			   
			   ganttData += "{ \"name\": \"" + serie.nome + "\", \"start\":\"" + new Date(dataInicial[0],dataInicial[1],dataInicial[2]) + "\", \"end\": \"" + 
				              new Date(dataFinal[0],dataFinal[1],dataFinal[2]) + "\" , \"color\": \"" + 
				              cor + "\", \"projeto\": \"" + projeto.id + "\", \"serie\": \"" + serie.id + "\" }";
			   if (idx < projeto.series.length - 1){
				   ganttData +=",";
			   } 
		      }); // fom for da series
		      ganttData += "]}";
		      if (index < ganttDataResposta.length - 1){
	    	      ganttData += ",";
		      }
		      
			}); // fom for dos projetos
		ganttData += "]";
		ganttData = JSON.parse(ganttData);
		// processa dados gant view fim
			$("#ganttChart").ganttView(
					{
						data : ganttData,
						slideWidth : 400,
						behavior : {
							onClick : function(data) {
								var msg = "Evento de clique: { start: "
										+ data.start.toString("MM/d/yyyy") + ", end: "
										+ data.end.toString("MM/d/yyyy") + " }";
								$("#eventMessage").text(msg);
							},
							onResize : function(data) {
								var msg = "Evento de redimencionar: { start: "
										+ data.start.toString("MM/d/yyyy") + ", end: "
										+ data.end.toString("MM/d/yyyy") + " }";
								var start = data.start.toString("yyyy-MM-d");
					    	    var end = data.end.toString("yyyy-MM-d");
								$.post("buscarDatasPlanejamento", { start: start, end: end, serie: data.serie, projeto: data.projeto})
								$("#eventMessage").text(msg);
							},
							onDrag : function(data) {
								var msg = "Evento de arrastar: { start: "
										+ data.start.toString("MM/d/yyyy") + ", end: "
										+ data.end.toString("MM/d/yyyy") + " }";
								var start = data.start.toString("yyyy-MM-d");
							    var end = data.end.toString("yyyy-MM-d");
								$.post("buscarDatasPlanejamento", { start: start, end: end, serie: data.serie, projeto: data.projeto})
								$("#eventMessage").text(msg);
							}
						}
					});
			$("#ganttChart").ganttView("setSlideWidth", 1030);
		});/// FIM do ajax GET
	});
</script>
</html>