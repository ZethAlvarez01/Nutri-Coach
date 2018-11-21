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
        <title>Nutri-Coach</title>
    </head>
    <body>
        <h1>Solicitar recuperar contraseña</h1>
        <ul>
            <li>Correo de recuperacion: <c:out value="${correo}"/></li>
        </ul>
    </body>
</html>
