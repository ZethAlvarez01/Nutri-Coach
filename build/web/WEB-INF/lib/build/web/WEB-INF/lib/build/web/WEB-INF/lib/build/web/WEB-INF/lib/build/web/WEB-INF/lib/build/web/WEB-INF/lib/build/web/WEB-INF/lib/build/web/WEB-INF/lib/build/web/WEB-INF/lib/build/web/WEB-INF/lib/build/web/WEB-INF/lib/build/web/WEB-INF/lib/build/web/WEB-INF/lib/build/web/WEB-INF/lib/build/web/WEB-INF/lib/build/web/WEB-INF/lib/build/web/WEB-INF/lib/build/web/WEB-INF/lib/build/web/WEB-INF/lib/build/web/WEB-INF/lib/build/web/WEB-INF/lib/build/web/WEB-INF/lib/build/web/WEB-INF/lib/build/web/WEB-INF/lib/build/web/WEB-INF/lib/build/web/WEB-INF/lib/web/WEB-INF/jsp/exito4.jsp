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
            <li>No_Empleado: <c:out value="${no_empleado}"/></li>
            <li>Nombre: <c:out value="${nombre}"/></li>
            <li>Apellido_P: <c:out value="${apellido_P}"/></li>
            <li>Apellido_M: <c:out value="${apellido_M}"/></li>
            <li>Telefono: <c:out value="${telefono}"/></li>
            <li>Cargo: <c:out value="${cargo}"/></li>
            <li>Correo: <c:out value="${correo}"/></li>
            <li>Contraseña: <c:out value="${contrasena}"/></li>
        </ul>
    </body>
</html>
