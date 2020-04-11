<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../scriptGanttView/jquery-ui-1.8.4.css" />
<link rel="stylesheet" type="text/css" href="../scriptGanttView/reset.css" />
<link rel="stylesheet" type="text/css" href="../scriptGanttView/jquery.ganttView.css" />
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
<script type="text/javascript" src="../scriptGanttView/jquery-ui-1.8.4.js"></script>
<script type="text/javascript" src="../scriptGanttView/jquery.ganttView.js"></script>
<script type="text/javascript">
    $(function() {
        var ganttData = [ {
            id : 1,
            name : "Projeto java web",
            series : [ {
                name : "Planejado",
                start : new Date(2019, 00, 01),
                end : new Date(2019, 02, 03),
                color : "#B0E0E6"
            }, {
                name : "Atual",
                start : new Date(2019, 01, 02),
                end : new Date(2019, 02, 09),
                color : "#E0FFFF"
            }, {
                name : "Projetado",
                start : new Date(2019, 01, 14),
                end : new Date(2019, 02, 02),
                color : "#F0FFFF"
            } ]
        } ];
        $("#ganttChart").ganttView(
                {
                    data : ganttData,
                    slideWidth : 400,
                    behavior : {
                        onClick : function(data) {
                            var msg = "Evento de clique: { start: "
                                    + data.start.toString("M/d/yyyy")
                                    + ", end: " + data.end.toString("M/d/yyyy")
                                    + " }";
                            $("#eventMessage").text(msg);
                        },
                        onResize : function(data) {
                            var msg = "Evento de redimencionar: { start: "
                                    + data.start.toString("M/d/yyyy")
                                    + ", end: " + data.end.toString("M/d/yyyy")
                                    + " }";
                            $("#eventMessage").text(msg);
                        },
                        onDrag : function(data) {
                            var msg = "Evento de arrastar: { start: "
                                    + data.start.toString("M/d/yyyy")
                                    + ", end: " + data.end.toString("M/d/yyyy")
                                    + " }";
                            $("#eventMessage").text(msg);
                        }
                    }
                });
        $("#ganttChart").ganttView("setSlideWidth", 1030);
    });
</script>
</html>