<%-- 
    Created on : 12/11/2018, 11:07:05 PM
    Author     : Zeth
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es_MX">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Nutri-Coach</title>
    </head>
    <body>
        <h1>Recuperar contraseña</h1>
        
        <form:form>
                    <form:input id="correo" path="correo" placeholder="Correo"/>
                    <form:button id="btn" class="boton" type="submit">Iniciar sesión</form:button>
        </form:form>
    </body>
</html>
