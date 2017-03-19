

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id='resultados' scope='application' class='java.util.ArrayList' />
<jsp:useBean id='mensaje' scope='application' class='java.lang.String' />
<jsp:useBean id='error' scope='application' class='java.lang.String' />
<jsp:useBean id='advertencia' scope='application' class='java.lang.String' />



<html lang="es">
  <head>
    <meta charset="utf-8" />
    <meta name="author" content="Juan Aparicio, Emiliano Fernandez, Guillermo Gandolfo" />
    <link rel="stylesheet" href="css/styles.css" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="js/scripts.js"></script>
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
          <table class="table_clientes" id="table_clientes">
               <thead>
                 <tr>
                   <th>Codigo</th>
                   <th>Destino</th>
                   <th>H. Partida</th>
                   <th>H. Llegada</th>
                   <th>Precio Base</th>
                   <th>Asientos Disp.</th>
                 </tr>
               </thead>
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
          <div class="div-form">
            <form class="form-destino" action="ListadoDestino" method="POST">
            	<div id="elegirListado" class="div-listado">
            		<input type="radio" name="listado" value="destino" id="elegirListado-destino" checked>Destino
            		<br>
      					<input type="radio" name="listado" value="precio" id="elegirListado-precio">Precio
      					<br>
            	</div>
            	<div id="div-destino" class="div-destino">
      					<label for="inp-destino">Destino</label>
      					<input id="inp-destino" type="text" name="destino">
      					<br>
      				</div>
      				<div id="div-precio" class="div-precio">
      					<label for="inp-precio-min">Precio Min . </label>
      					<input id="inp-precio-min" type="number" name="precioMin">
                <br>
                <label for="inp-precio-max">Precio Max.</label>
      					<input id="input-precio-max" type="number" name="precioMax">
      				</div>
              <div class="submitBtn">
                <input type="submit" value="Listar Excursiones">
              </div>
            </form>
          </div>
        </div>
        <c:if test="${error == 'true'}">
        <div class="div-mensaje">
          <div class="mensaje error">
              ${mensaje}
          </div>
        </div>
        </c:if>
        <c:if test="${advertencia == 'true'}">
        <div class="div-mensaje">
          <div class="mensaje advertencia">
              ${mensaje}
          </div>
        </div>
        </c:if>
      </main>
    </div>
  </body>
</html>
