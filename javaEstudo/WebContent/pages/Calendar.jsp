<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href='/assets/demo-to-codepen.css' rel='stylesheet' />
<link href='https://unpkg.com/@fullcalendar/core@4.4.0/main.min.css' rel='stylesheet' />
<link href='https://unpkg.com/@fullcalendar/daygrid@4.4.0/main.min.css' rel='stylesheet' />
<link href='https://unpkg.com/@fullcalendar/timegrid@4.4.0/main.min.css' rel='stylesheet' />
<script src='/assets/demo-to-codepen.js'></script>
<script src='https://unpkg.com/@fullcalendar/core@4.4.0/main.min.js'></script>
<script src='https://unpkg.com/@fullcalendar/interaction@4.4.0/main.min.js'></script>
<script src='https://unpkg.com/@fullcalendar/daygrid@4.4.0/main.min.js'></script>
<script src='https://unpkg.com/@fullcalendar/timegrid@4.4.0/main.min.js'></script>
<link rel="stylesheet" type="text/css" href="../CSS/Calendar.css">
<title>Calendário</title>
</head>
<body>
    <div>
        <div>
            <h1 align="center">Calendário</h1>
        </div>
        <div id='external-events'>
            <p>
                <strong>Coloque os eventos</strong>
            </p>
            <div class='fc-event'>My Event 1</div>
            <div class='fc-event'>My Event 2</div>
            <div class='fc-event'>My Event 3</div>
            <div class='fc-event'>My Event 4</div>
            <div class='fc-event'>My Event 5</div>
            <p>
                <input type='checkbox' id='drop-remove' /> <label for='drop-remove'>Remover
                    o evento ao arrastar</label>
            </p>
        </div>
        <div id='calendar-container'>
            <div id='calendar'></div>
        </div>
    </div>
</body>
<script type="text/javascript" src="../script/Calendar.js"></script>
</html>