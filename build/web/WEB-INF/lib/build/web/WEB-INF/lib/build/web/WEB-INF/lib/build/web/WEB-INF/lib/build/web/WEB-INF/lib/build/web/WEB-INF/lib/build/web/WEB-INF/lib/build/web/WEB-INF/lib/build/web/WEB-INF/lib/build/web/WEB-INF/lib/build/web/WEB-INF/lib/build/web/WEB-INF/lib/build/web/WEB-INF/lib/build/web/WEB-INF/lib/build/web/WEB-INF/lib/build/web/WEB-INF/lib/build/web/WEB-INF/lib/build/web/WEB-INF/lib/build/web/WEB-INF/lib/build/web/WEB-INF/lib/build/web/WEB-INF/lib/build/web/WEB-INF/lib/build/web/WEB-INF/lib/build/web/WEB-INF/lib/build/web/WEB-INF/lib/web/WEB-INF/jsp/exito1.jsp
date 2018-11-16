<%-- 
    Document   : exito
    Created on : 11/11/2018, 01:48:16 AM
    Author     : Zeth
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es_MX">
    <head>
        <meta charset="utf-8" />
        <title>Registro</title>
    </head>
    <body>
        <h1>Registro completo</h1>
        <ul>
            <li>No_Cedula_P: <c:out value="${No_Cedula_P}"/></li>
            <li>No_Empleado: <c:out value="${No_Empleado}"/></li>
            <li>Nombre: <c:out value="${Nombre}"/></li>
            <li>Apellido_P: <c:out value="${Apellido_P}"/></li>
            <li>Apellido_M: <c:out value="${Apellido_M}"/></li>
            <li>Institucion: <c:out value="${Institucion}"/></li>
            <li>Telefono: <c:out value="${Telefono}"/></li>
            <li>Domicilio_C: <c:out value="${Domicilio_C}"/></li>
            <li>Correo: <c:out value="${Correo}"/></li>
            <li>Contraseña: <c:out value="${Contrasena}"/></li>
        </ul>
    </body>
</html>
