<%-- 
    Document   : exito2
    Created on : 12/11/2018, 01:07:21 AM
    Author     : zetok
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registro completo</h1>
        <ul>
            <li>No_Cedula_P: <c:out value="${No_Cedula_P}"/></li>
            <li>No_Empleado: <c:out value="${No_Empleado}"/></li>
            <li>Nombre: <c:out value="${Nombre}"/></li>
            <li>Apellido_P: <c:out value="${Apellido_P}"/></li>
            <li>Apellido_M: <c:out value="${Apellido_M}"/></li>
            <li>Telefono: <c:out value="${Telefono}"/></li>
            <li>Correo: <c:out value="${Correo}"/></li>
            <li>Contraseña: <c:out value="${Contrasena}"/></li>
        </ul>
    </body>
</html>
