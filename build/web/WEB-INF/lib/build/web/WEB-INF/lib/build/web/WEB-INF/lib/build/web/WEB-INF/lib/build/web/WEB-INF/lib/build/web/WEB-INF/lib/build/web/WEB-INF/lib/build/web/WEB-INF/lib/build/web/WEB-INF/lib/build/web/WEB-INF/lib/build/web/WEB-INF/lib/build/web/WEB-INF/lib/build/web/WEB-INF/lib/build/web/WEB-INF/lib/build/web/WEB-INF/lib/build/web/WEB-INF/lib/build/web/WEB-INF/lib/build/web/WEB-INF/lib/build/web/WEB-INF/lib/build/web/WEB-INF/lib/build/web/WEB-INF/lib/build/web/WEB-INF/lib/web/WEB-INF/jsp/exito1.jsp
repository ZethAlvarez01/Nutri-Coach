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
            <li>No_Cedula_P: <c:out value="${no_cedula}"/></li>
            <li>No_Empleado: <c:out value="${no_empleado}"/></li>
            <li>Nombre: <c:out value="${nombre}"/></li>
            <li>Apellido_P: <c:out value="${ap_uno}"/></li>
            <li>Apellido_M: <c:out value="${ap_dos}"/></li>
            <li>Institucion: <c:out value="${institucion}"/></li>
            <li>Telefono: <c:out value="${telefono}"/></li>
            <li>Domicilio_C: <c:out value="${consultorio}"/></li>
            <li>Correo: <c:out value="${correo}"/></li>
            <li>Contraseña: <c:out value="${contraseña}"/></li>
        </ul>
    </body>
</html>
