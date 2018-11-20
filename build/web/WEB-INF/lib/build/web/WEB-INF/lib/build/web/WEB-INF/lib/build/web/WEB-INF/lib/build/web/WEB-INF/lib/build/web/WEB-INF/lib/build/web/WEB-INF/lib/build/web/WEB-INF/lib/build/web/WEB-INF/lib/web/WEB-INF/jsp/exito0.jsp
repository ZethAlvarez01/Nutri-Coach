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
            <li>Usuario: <c:out value="${Usuario}"/></li>
            <li>Contraseña: <c:out value="${Pass}"/></li>
        </ul>
    </body>
</html>
