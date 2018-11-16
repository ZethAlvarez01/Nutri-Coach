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
            <li>No_Boleta: <c:out value="${no_boleta}"/></li>
            <li>Nombre: <c:out value="${nombre}"/></li>
            <li>Apellido_P: <c:out value="${ap_uno}"/></li>
            <li>Apellido_M: <c:out value="${ap_dos}"/></li>
            <li>Sexo: <c:out value="${sexo}"/></li>
            <li>Fecha_Nacimiento: <c:out value="${fecha_n}"/></li>
            <li>Telefono: <c:out value="${telefono}"/></li>
            <li>Domicilio: <c:out value="${domicilio}"/></li>
            <li>Correo: <c:out value="${correo}"/></li>
            <li>Contraseña: <c:out value="${contraseña}"/></li>
        </ul>
    </body>
</html>
