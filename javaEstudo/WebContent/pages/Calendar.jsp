<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="../CSS/fullcalendar.min.css" rel="stylesheet" />
<link href="../CSS/fullcalendar.print.min.css" rel="stylesheet"
	media="print" />
<link rel="stylesheet" type="text/css" href="../CSS/Calendar.css">
<title>Calendário</title>
</head>
<body>
	<div>
		<div>
			<h1 align="center">Calendário</h1>
		</div>
		<div id='calendar'></div>
	</div>
</body>
<script src="../script/moment.min.js"></script>
<script src="../script/jquery.min.js"></script>
<script src="../script/fullcalendar.min.js"></script>
<script>
	$(document).ready(function() {
		$.get("buscarCalendarioDatas", function(response) {// inicio ajax get Servlet
			var datas = JSON.parse(response);
			$("#calendar").fullCalendar({
				header : {
					left : 'prev,next today',
					center : 'title',
					right : 'month,basicWeek,basicDay'
				},
				defaultDate : '2020-04-27',
				navLinks : true, // can click day/week names to navigate views
				editable : true,
				eventLimit : true, // allow "more" link when too many events
				events : datas
			});
		});/// FIM do ajax GET
	});
</script>
</html>