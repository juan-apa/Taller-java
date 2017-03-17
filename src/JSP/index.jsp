<!--

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id='resultados' scope='application' class='java.util.ArrayList' />

 -->

<html lang="es">
  <head>
    <meta charset="utf-8" />
    <meta name="author" content="Juan Aparicio, Emiliano Fernandez, Guillermo Gandolfo" />
    <link rel="stylesheet" href="css/styles.css" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <title>Listado de Excursiones por codigo</title>
  </head>
  <body>
    <div class="main-wraper">
      <header>
        <h1>Listados de Excursiones</h1>
        <figure class="logo-figure">
          <a href="#">
            <img id="logo" src="images/logo.png" alt="">
          </a>
        </figure>
      </header>
      <main>
        <div class="div-tabla left">
          <table>
            <tr>
      				<td><b> Autor </b></td>
      				<td><b> Mensaje </b></td>
      			</tr>
            <c:forEach items="${resultados}" var="i" >
      				<tr>
      					<td> ${i.codigo} </td>
      					<td> ${i.destino} </td>
      					<td> ${i.hPartida} </td>
      					<td> ${i.hLlegada} </td>
      					<td> ${i.precioBase} </td>
      					<td> ${i.asientosDisp} </td>
      				</tr>
      			</c:forEach>

          </table>
        </div>
        <div class="div-forms right">
          <div class="div-form-codigo">
            <form class="form-codigo" action="index.html" method="post">

            </form>
          </div>
          <div class="div-form-precio">
            <form class="form-precio" action="index.html" method="post">

            </form>
          </div>
        </div>
      </main>
    </div>
  </body>
</html>
