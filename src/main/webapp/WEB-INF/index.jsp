<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro e inicio de sesion</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h2>Registrate</h2>
				<form:form action="/registro" method="post" modelAttribute="nuevoUsuario">
					<div>
						<form:label path="nombre">Nombre</form:label>
						<form:input path="nombre" class="form-control"/>
						<form:errors path="nombre" class="text-danger"/>
					</div>
					<div>
						<form:label path="email">E-mail</form:label>
						<form:input path="email" class="form-control"/>
						<form:errors path="email" class="text-danger"></form:errors>
					</div>
					<div>
						<form:label path="contrasena">Contaseña</form:label>
						<form:password path="contrasena" class="form-control"></form:password>
						<form:errors path="contrasena" class="text-danger"></form:errors>
					</div>
					<div>
						<form:label path="confirmacion">Confirmacion</form:label>
						<form:password path="confirmacion" class="form-control"/>
						<form:errors path="confirmacion" class="text-danger"></form:errors>
					</div>
					<input type="submit" class="btn btn-success mt-3" value="Registrarme">
				</form:form>
			</div>
			<div class="col-6">
				<h2>Inicia sesion</h2>
				<p class="text-danger">${error_login}</p> <!-- atributo que usamos en flash -->
				<form action="/login" method="post">
					<div>
						<label>E-mail</label>
						<input type="text" class="form-control" name="email" />
					</div>
					<div>
						<label>Password</label>
						<input type="password" class="form-control" name="contrasena" />
					</div>
					<input type="submit" class="btn btn-info mt-3" value="Inicia Sesion" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>