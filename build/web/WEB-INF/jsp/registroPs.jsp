<%-- 
    Document   : tiporegistro
    Created on : 11/11/2018, 01:10:16 AM
    Author     : Zeth
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--  <%@page contentType="text/html" pageEncoding="UTF-8"%>  --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es_MX">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" type="image/png" href="<c:url value="/resource/imagenes/iconos/favicon.png" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/registroP_1.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/modal.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
        <title>Nutri-Coach</title>
    </head>
    <body>
        <header>
            <div class="container">
                <div id="pleca">
                    <div id="logoSEP">
                        <a href="https://www.gob.mx/sep">
                            <img src="<c:url value="/resource/imagenes/logo-sep.png" />" alt="Secretar�a de Educaci�n P�blica">
                        </a>
                    </div>
                    <div id="logoIPN">
                        <a href="https://www.ipn.mx">
                            <p>Instituto Polit�cnico Nacional</p>
                            "La T�cnica al Servicio de la Patria"
                        </a>
                        <img src="<c:url value="/resource/imagenes/logo-ipn.jpg" />" alt="Instituto Polit�cnico Nacional">
                    </div>
                </div>
            </div>
            <!--Fin container-->
        </header>    
                    
        <div id="barra">
            <div class="container">
                 <a href="<c:url value="/inicio.htm" />"><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
            </div>  
        </div>  
        
        <a class="regresar" href="<c:url value="/preregistro.htm" />">Regresar</a>
        
        <div id="contenido">
            <div class="container">                  
                <center><h1>Registro para el psic�logo</h1>
                    <br/>
                    <p>Favor de introducir sus datos correspondientes para complerar su registro:</p>
                </center>
                <div id="formulario">
                     <form:form method="post" commandName="Psicologo">
                    
                  
                    
                    <form:input path="no_cedula" placeholder="No. de c�dula profesional" />
                       <form:errors path="no_cedula"/>
                        <p>
                        <form:input path="no_empleado" placeholder="No. de empleado" />
                       <form:errors path="no_empleado" />
                        </p>
                        <p>
                        <form:input path="nombre" placeholder="Nombre"/>
                        <form:errors path="nombre" />
                        </p>
                         <p>
                        <form:input path="ap_uno" placeholder="Primer apellido"/>
                        <form:errors path="ap_uno" />
                        </p>
                        <p>
                        <form:input path="ap_dos" placeholder="Segundo apellido"/>
                        <form:errors path="ap_dos" />
                        </p>
                        <p>
                        <form:input path="telefono"  placeholder="Tel�fono"/>
                        <form:errors path="telefono" />
                        </p>
                        <p>
                        <form:input path="correo" type="email" placeholder="Correo"/>
                        <form:errors path="correo" />
                        </p>
                        <p>
                        <form:password path="contrase�a"  placeholder="Contrase�a"/>
                        <form:errors path="contrase�a" />
                        </p>
                        <p>
                         <form:password showPassword="true" path="contrase�a2"  placeholder="Repetir contrase�a"/>
                        <form:errors path="contrase�a2" />
                        </p>
                        <input type="checkbox" class="check" value="first_checkbox" required>
                        <center>
                            <p class="modalBtn" style="color: black;">He leido y acepto los t�rminos y condiciones.</p>
                        </center>
                        <br/>
                        <form:button id="button" type="submit">Registrar</form:button>
                    </form:form>
                </div>
                
            </div>
            <!--Fin container-->
        </div>
        <!--Fin contenido-->
        
        <footer>
            <div class="container">
                <center>
                    <p id="visita">Visita nuestras redes sociales</p>
                </center>
                <hr>
                <ul id="redes">
                    <li>
                        <a href="https://www.facebook.com/escomipnmx/">
                            <img src="<c:url value="/resource/imagenes/iconos/facebook.png" />" />
                        </a>
                    </li>
                    <li>
                        <a href="https://twitter.com/escomunidad">
                            <img src="<c:url value="/resource/imagenes/iconos/twitter.png" />" />
                        </a>
                    </li>
                    <li>
                        <a href="https://www.instagram.com/ipn_oficial/">
                            <img src="<c:url value="/resource/imagenes/iconos/instagram.png" />" />
                        </a>
                    </li>
                </ul>
                <!--Fin Redes sociales-->   
                <p>� 2018 ESCOMPROJECT - Todos Los Derechos Reservados</p>
                <br/>
                <p>
                    <a class="hiperfooter" href="<c:url value="/tyc.htm" />">T�rminos y condiciones</a>
                    <a  class="hiperfooter" href="<c:url value="/politicas.htm" />">Pol�ticas de privacidad</a>
                </p>
                <br/>
                <!--Fin Modal T�rminos y condiciones-->
            </div>
        </footer>  
                
        <div id="simpleModal" class="modal">
            <span class="closeBtn">&times;</span>
            <div class="modal-content">
                <div id="terminos">
                                    <center><h1 id="tycN">T�rminos y condiciones de uso del sitio web Nutri-Coach.com</h1></center>
                                    <center><h1 id="tycA">T�rminos y condiciones Nutri-Coach.com</h1></center>
                <div id="texto">
                    <p id="parrafo">
                        Los siguientes t�rminos y condiciones rigen el uso que usted le d� a este sitio web y a cualquiera de los contenidos disponibles por o a trav�s de este sitio web, incluyendo cualquier contenido derivado del mismo. 
                            Nutri-Coach ha puesto a su disposici�n el Sitio Web. Podemos cambiar los T�rminos y Condiciones de vez en cuando, en cualquier momento sin ninguna notificaci�n, s�lo publicando los cambios en el Sitio Web. 
                            AL USAR EL SITIO WEB, USTED ACEPTA Y EST� DE ACUERDO CON ESTOS T�RMINOS Y CONDICIONES EN LO QUE SE REFIERE A SU USO DEL SITIO WEB. Si usted no est� de acuerdo con estos T�rminos y Condiciones, no puede tener acceso
                            al mismo ni usar el Sitio Web de ninguna otra manera.  
                        <br/>
                        <br/>
                            1. <b>Derechos de Propiedad.</b> Entre usted y Nutri-Coach, Nutri-Coach es due�o �nico y exclusivo, de todos los derechos, t�tulo e intereses en y del Sitio Web, de todo el contenido (incluyendo, por ejemplo, audio, fotograf�as,
                            ilustraciones, gr�ficos, otros medios visuales, videos, copias, textos, software, t�tulos, archivos, etc.), c�digos, datos y materiales del mismo, el aspecto y el ambiente, el dise�o y la organizaci�n del Sitio Web y la
                            compilaci�n de los contenidos, c�digos, datos y los materiales en el Sitio Web, incluyendo pero no limitado a, cualesquiera derechos de autor, derechos de marca, derechos de patente, derechos de base de datos, derechos morales,
                            derechos sui generis y otras propiedades intelectuales y derechos patrimoniales del mismo. Su uso del Sitio Web no le otorga propiedad de ninguno de los contenidos, c�digos, datos o materiales a los que pueda acceder
                            en o a trav�s del Sitio Web.
                        <br/>
                        <br/>
                            2. <b>Licencia Limitada.</b> Usted puede acceder y ver el contenido del Sitio Web desde su computadora o desde cualquier otro aparato y, a menos de que se indique de otra manera en estos T�rminos y Condiciones o en el Sitio Web, 
                            sacar copias o impresiones individuales del contenido del Sitio Web para su uso personal, interno �nicamente. El uso del Sitio Web y de los servicios que se ofrecen en o a trav�s del Sitio Web, son s�lo para su uso personal, no comercial.
                        <br/>
                        <br/>
                            3. <b>Uso Prohibido.</b> Cualquier distribuci�n, publicaci�n o explotaci�n comercial o promocional del Sitio Web, o de cualquiera de los contenidos, c�digos, datos o materiales en el Sitio Web, est� estrictamente prohibida,
                            a menos de que usted haya recibido el previo permiso expreso por escrito del personal autorizado de Nutri-Coach o de alg�n otro poseedor de derechos aplicable. A no ser como est� expresamente permitido en el presente contrato,
                            usted no puede descargar, informar, exponer, publicar, copiar, reproducir, distribuir, transmitir, modificar, ejecutar, difundir, transferir, crear trabajos derivados de, vender o de cualquier otra manera explotar cualquiera de
                            los contenidos, c�digos, datos o materiales en o disponibles a trav�s del Sitio Web. Usted se obliga adem�s a no alterar, editar, borrar, quitar, o de otra manera cambiar el significado o la apariencia de, o cambiar el prop�sito
                            de, cualquiera de los contenidos, c�digos, datos o materiales en o disponibles a trav�s del Sitio Web, incluyendo, sin limitaci�n, la alteraci�n o retiro de cualquier marca comercial, marca registrada, logo, marca de servicios
                            o cualquier otro contenido de propiedad o notificaci�n de derechos de propiedad. Usted reconoce que no adquiere ning�n derecho de propiedad al descargar alg�n material con derechos de autor de o a trav�s del Sitio Web.
                            Si usted hace otro uso del Sitio Web, o de los contenidos, c�digos, datos o materiales que ah� se encuentren o que est�n disponibles a trav�s del Sitio Web, a no ser como se ha estipulado anteriormente, usted puede violar las
                            leyes de derechos de autor y otras leyes de los Estados Unidos y de otros pa�ses, as� como las leyes estatales aplicables, y puede ser sujeto a responsabilidad legal por dicho uso no autorizado.   
                        <br/>
                        <br/>
                            4. <b>Marcas Comerciales.</b> Las marcas comerciales, logos, marcas de servicios, marcas registradas (conjuntamente las "Marcas Comerciales") expuestas en el Sitio Web o en los contenidos disponibles a trav�s del Sitio Web son Marcas
                            Comerciales de Nutri-Coach registradas y no registradas y otras, y no pueden ser usadas con respecto a productos y/o servicios que no est�n relacionados, asociados o patrocinados por sus poseedores de derechos y que puedan causar
                            confusi�n a los clientes, o de alguna manera que denigre o desacredite a sus poseedores de derechos. Todas las Marcas Comerciales que no sean de Nutri-Coach que aparezcan en el sitio Web o en o a trav�s de los servicios del Sitio Web,
                            si las hubiera, son propiedad de sus respectivos due�os. Nada que est� contenido en el Sitio Web deber� ser interpretado como otorgando, por implicaci�n, desestimaci�n, o de otra manera, alguna licencia o derecho para usar alguna Marca
                            Comercial expuesta en el Sitio Web sin el permiso escrito de Nutri-Coach o de terceros que puedan ser due�os de dicha Marca Comercial. El mal uso de las Marcas Comerciales expuestas en el Sitio Web o en o a trav�s de cualquiera de los
                            servicios del Sitio Web est� estrictamente prohibido.
                        <br/>
                        <br/>
                            5. <b>Informaci�n del Usuario</b>. En el curso del uso que usted haga del Sitio Web y/o de los servicios puestos a su disposici�n en o a trav�s del Sitio Web, se le puede pedir que nos proporcione cierta informaci�n personalizada
                            (dicha informaci�n en lo sucesivo "Informaci�n del Usuario"). Las pol�ticas de uso y recopilaci�n de informaci�n de Nutri-Coach con respecto a la privacidad de dicha Informaci�n del Usuario se establecen en la Pol�tica de Privacidad del
                            Sitio Web, la cual est� incorporada al mismo como referencia para todos los prop�sitos. Usted reconoce y acepta ser el �nico responsable de la exactitud del contenido de la Informaci�n del Usuario.
                        <br/>
                        <br/>
                            6. <b>Materiales Presentados.</b> A menos que se solicite espec�ficamente, no pedimos ni deseamos recibir ninguna informaci�n confidencial, secreta o patrimonial, ni otro material de usted a trav�s del Sitio Web, por correo electr�nico
                            o de cualquier otra manera. Cualquier informaci�n, trabajos creativos, demostraci�n, ideas, sugerencias, conceptos, m�todos, sistemas, dise�os, planes, t�cnicas u otros materiales que nos haya mandado o presentado (incluyendo, por ejemplo
                            y sin limitaci�n, aquello que usted presenta o env�a a nuestros grupos de chateo, tablas de mensajes y/o a nuestros ?blogs?, o que nos manda v�a correo electr�nico) ("Materiales Presentados") se considerar� como no confidencial o secreto
                            y que puede ser usado por nosotros de cualquier manera consistente con la Pol�tica de Privacidad del Sitio Web. Al presentarnos o mandarnos Materiales Presentados, usted: (i) representa y garantiza que los Materiales Presentados son originales
                            suyos, que ninguna otra persona tiene ning�n derecho sobre ellos, y que cualquier "derecho moral" sobre los Materiales Presentados ha sido renunciado, y (ii) usted nos concede, a nosotros y a nuestros afiliados, derecho y licencia libres de
                            regal�as, sin restricciones, mundiales, perpetuos, irrevocables, no exclusivos y totalmente transferibles, que pueden ser cedidos y sub-licenciados, para usar, copiar, reproducir, modificar, adaptar, publicar, traducir, crear trabajos derivados
                            de, distribuir, ejecutar, exponer e incorporar en otros trabajos cualquiera de los Materiales Presentados (todos o en parte) en cualquier forma, medio o tecnolog�a no conocida o por desarrollar, incluyendo prop�sitos promocionales y/o comerciales.
                            No podemos ser responsables de conservar ning�n Material Presentado proporcionado por usted y podemos borrar o destruir dicho Material Presentado en cualquier momento.
                        <br/>
                        <br/>
                            7. <b>Conducta Prohibida del Usuario.</b> Usted garantiza y est� de acuerdo en que, mientras use el Sitio Web y los diversos servicios y art�culos que se ofrecen en o a trav�s del Sitio Web, usted no: (a) personalizar� a
                            ninguna persona o entidad ni desvirtuar� su afiliaci�n con alguna otra persona o entidad; (b)  insertar� su propio anuncio, posicionamiento de marca o alg�n otro contenido promocional o el de un tercero  en cualquiera de
                            los contenidos, materiales o servicios o materiales del Sitio Web (por ejemplo, sin limitaci�n, en una actualizaci�n RSS o en un programa de radio grabado (podcast) recibido de Nutri-Coach o de alg�n otro modo a trav�s
                            del Sitio Web), ni usar�, redistribuir�, republicar� o explotar� dichos contenidos o servicios con cualquier otro prop�sito adicional comercial o promocional; ni (c) intentar� ganar acceso no autorizado a otros sistemas
                            de c�mputo a trav�s del Sitio Web. Usted no: (i) participar� en navegar por la red, en "raspar (scraping) la pantalla", "raspar (scraping) la base da datos", en recolectar direcciones de correo electr�nico, direcciones
                            inal�mbricas u otra informaci�n personal o de contactos, o cualquier otro medio autom�tico de obtener listas de usuarios u otra informaci�n de o a trav�s del sitio Web o de los servicios ofrecidos en o a trav�s del Sitio
                            Web, incluyendo, sin limitaci�n, cualquier informaci�n que se encuentre en alg�n servidor o base de datos relacionada con el Sitio Web o los servicios ofrecidos en o a trav�s del Sitio Web; (ii) obtendr� o intentar� obtener
                            acceso no autorizado a los sistemas de c�mputo, materiales o informaci�n por cualquier medio; (iii) usar� el Sitio Web o los servicios puestos a su disposici�n en o a trav�s del Sitio Web de alguna manera con la intenci�n
                            de interrumpir, da�ar, deshabilitar, sobrecargar o deteriorar el Sitio Web o dichos servicios, incluyendo, sin limitaci�n, mandar mensajes masivos no solicitados o "inundar" servidores con solicitudes; (iv) usar� el Sitio
                            Web o los servicios o art�culos del Sitio Web en violaci�n de la propiedad intelectual o de otros derechos legales o patrimoniales de Time Inc o de alg�n tercero; ni (v) usar� el Sitio Web o los servicios del Sitio Web en
                            violaci�n de cualquier ley aplicable. Usted se obliga, adem�s, a no intentar (o alentar o apoyar el intento de otro) a embaucar, destruir, decodificar, o de otro modo alterar o interferir con el Sitio Web o con los servicios
                            del Sitio Web, o con cualquier contenido del mismo, o hacer cualquier uso no autorizado del mismo. Usted se obliga a no usar el Sitio Web de alguna manera que pudiera da�ar, deshabilitar, sobrecargar o deteriorar el Sitio Web
                            o interferir con que cualquier otra persona pueda usar o disfrutar del Sitio Web o de cualquiera de sus servicios. Usted no obtendr� ni intentar� obtener alg�n material o informaci�n a trav�s de cualquier medio que no haya
                            sido estipulado o puesto a la disposici�n del p�blico intencionalmente a trav�s del Sitio Web.
                        <br/>
                        <br/>
                            8. <b>Foros P�blicos.</b> Nutri-Coach puede, de vez en cuando, tener servicios de mensajer�a, servicios de chateo, tableros de noticias, blogs, otros foros y otros servicios disponibles en o a trav�s del Sitio Web. Adem�s
                            de cualquier otra normatividad y regulaci�n que podamos publicar con respecto a un servicio en particular, usted se obliga a no cargar, informar, transmitir, distribuir o de otra manera publicar a trav�s del Sitio Web o de
                            cualquier servicio o art�culo puesto a la disposici�n en o a trav�s del Sitio Web, cualquier material que (i) restrinja o inhiba a cualquier otro usuario de usar y disfrutar del Sitio Web o de los servicios del Sitio Web
                            , (ii) sea fraudulento, ilegal, amenazante, abusivo, hostigante, calumnioso, difamatorio, obsceno, vulgar, ofensivo, pornogr�fico, profano, sexualmente expl�cito o indecente, (iii) constituya o aliente conductas que pudieran
                            constituir una ofensa criminal, dar lugar a responsabilidad civil o de otro modo violar cualquier ley local, estatal, nacional o internacional, (iv) viole, plagie o infrinja los derechos de terceros incluyendo, sin limitaci�n
                            , derechos de autor, marcas comerciales, secretos comerciales, confidencialidad, contratos, patentes, derechos de privacidad o publicidad o cualquier otro derecho de propiedad, (v) contenga un virus, elemento de espionaje u otro
                            componente da�ino, (vi) contenga enlaces fijos, publicidad, cartas de cadenas o esquemas de pir�mides de cualquier tipo, o (vii) constituya o contenga indicaciones de origen, endosos o declaraciones de hechos falsos o enga�osos.
                            Usted adem�s se obliga a no personificar a cualquier otra persona o entidad, ya sea real o ficticia, incluyendo cualquier persona de Nutri-Coach Usted tampoco puede ofrecer comprar o vender alg�n producto o servicio en o a trav�s
                            de sus comentarios presentados en nuestros foros. Solamente usted es responsable del contenido y de las consecuencias de cualquiera de sus actividades.
                        <br/> 
                        <br/>
                            9. <b>Derecho de Monitoreo y Control Editorial.</b> Nutri-Coach se reserva el derecho, pero no tiene la obligaci�n, de monitorear y/o revisar todos los materiales enviados al Sitio Web o a trav�s de los servicios o art�culos del
                            Sitio Web por los usuarios, y Nutri-Coach no es responsable de dichos materiales enviados por los usuarios. Sin embargo, Nutri-Coach se reserva el derecho en todo momento de divulgar cualquier informaci�n que sea necesaria para
                            satisfacer cualquier ley, reglamento o solicitud gubernamental, o de editar, rehusarse a colocar o a quitar cualquier informaci�n o materiales, todos o en parte, que a discreci�n �nicamente de Nutri-Coach sean censurables o en
                            violaci�n de estos T�rminos de Uso, de las pol�ticas de Time Inc o de la ley aplicable. Tambi�n podemos imponer l�mites sobre ciertos art�culos de los foros o restringir su acceso a parte o a todos los foros sin notificaci�n o
                            sanci�n, si creemos que usted est� en incumplimiento de las directrices establecidas en este p�rrafo, nuestros t�rminos y condiciones o la ley aplicable, o por cualquier otra raz�n sin notificaci�n o responsabilidad.
                        <br/>
                        <br/>

                            10. <b>Informaci�n Privada o Delicada en Foros P�blicos.</b> Es importante recordar que los comentarios presentados en un foro pueden ser registrados y almacenados en m�ltiples lugares, tanto en nuestro Sitio Web como en otra parte
                            en Internet, los cuales pueden ser accesibles durante mucho tiempo y no se tiene control sobre qui�n los leer� eventualmente. Es por lo tanto importante que tenga usted cuidado y sea selectivo acerca de la informaci�n personal
                            que divulgue acerca de usted y de otros, y en especial, no debe divulgar informaci�n delicada, patrimonial o confidencial en sus comentarios en nuestros foros p�blicos.
                        <br/>
                        <br/>
                            11. <b>Enlaces con el Sitio Web.</b> Usted est� de acuerdo en que si incluye un enlace (link) de cualquier otro sitio web al Sitio Web, dicho enlace se abrir� en una nueva ventana navegadora y se enlazar� con la versi�n completa
                            de una p�gina formateada HTML de este Sitio Web. Usted no tiene permitido enlazarse directamente a ninguna imagen almacenada en el Sitio Web o en nuestros servicios, como ser�a usar un m�todo de enlace "en-l�nea" para provocar
                            que la imagen almacenada por nosotros fuera expuesta en otro sitio web. Usted se obliga a no descargar o usar im�genes almacenadas en este Sitio Web en otro sitio web, con cualquier prop�sito, incluyendo, sin limitaci�n, publicar
                            dichas im�genes en otro sitio web. Usted se obliga a no enlazarse de cualquier otro sitio web a este Sitio Web de tal manera que el Sitio Web, o cualquier p�gina del Sitio Web, est� "enmarcado", rodeado u ofuscado por los contenidos,
                            materiales o posicionamientos de marca de cualquier tercero. Nos reservamos todos nuestros derechos bajo la ley para insistir en que cualquier enlace al Sitio Web sea descontinuado y a revocar su derecho a enlazarse al Sitio Web de
                            cualquier otro sitio web, en cualquier momento en el que le mandemos notificaci�n por escrito.
                        <br/>
                        <br/>
                            12. <b>Indemnizaci�n.</b> Usted se obliga a defender, indemnizar y a sacar a Nutri-Coach y a los directores, funcionarios, empleados y agentes de Nutri-Coach y sus afiliados en paz y a salvo de cualquier demanda, responsabilidad,
                            costos y gastos, de cualquier naturaleza, incluyendo honorarios de abogados, en que incurriera como resultado del uso del Sitio Web, su colocaci�n o transmisi�n de cualquier mensaje, contenido, informaci�n, software u otros materiales
                            a trav�s del Sitio Web, o su incumplimiento o violaci�n de la ley o de estos T�rminos y Condiciones. Nutri-Coach se reserva el derecho, a su propio costo, de asumir la defensa y control exclusivos de cualquier asunto de otra manera
                            sujeto a indemnizaci�n por parte suya, y en dicho caso, usted se obliga a cooperar con Nutri-Coach en la defensa de dicha demanda.
                        <br/>
                        <br/>
                            13. <b>�rdenes de Productos y Servicios.</b> Podemos poner ciertos productos a la disposici�n de visitantes y registrados del Sitio Web. Si usted ordena cualquier producto, por el presente documento usted representa y garantiza
                            que tiene 18 a�os de edad o m�s. Se obliga a pagar la totalidad de los precios de cualquier compra que haga, ya sea con tarjeta de cr�dito/d�bito concurrente con su orden en l�nea o por otro medio de pago aceptable para Nutri-Coach
                            Usted se obliga a pagar todos los impuestos aplicables. Si el pago no es recibido por nosotros de parte del emisor de su tarjeta de cr�dito o d�bito o de sus agentes, usted se obliga a pagar todas las cantidades debidas al
                            momento de la reclamaci�n por nuestra parte. Algunos productos que usted compra y/o descarga en o a trav�s del Sitio Web pueden estar sujetos a t�rminos y condiciones adicionales que le ser�n presentados al momento de dicha compra o descarga.
                        <br/>
                        <br/>
                            14. <b>Sitios Web de Terceros.</b> Usted puede enlazar (link) del Sitio Web a sitios web de terceros y terceros pueden enlazarse al Sitio Web ("Sitios Enlazados"). Usted reconoce y est� de acuerdo en que nosotros no
                            tenemos responsabilidad sobre la informaci�n, contenido, productos, servicios, anuncios, c�digos u otros materiales que puedan o no puedan ser proporcionados por o a trav�s de los Sitios Enlazados, a�n si son propiedad
                            de o son dirigidos por afiliados nuestros. Los enlaces (links) a Sitios Enlazados no constituyen un aval o patrocinio nuestro de dichos sitios web o de la informaci�n, contenido, productos, servicios, anuncios, c�digos
                            u otros materiales presentados en o a trav�s de dichos sitios web. La inclusi�n de cualquier enlace a dichos sitios en nuestro Sitio no implica el aval, patrocinio o recomendaci�n de ese sitio de Nutri-Coach. Nutri-Coach
                            rechaza cualquier responsabilidad por los enlaces (1) de otro sitio web a este Sitio Web y (2) a otro sitio web de este Sitio Web. Nutri-Coach no puede garantizar los est�ndares de cualquier sitio web al cual se le proporcionen
                            enlaces en este Sitio Web, ni ser� Nutri-Coach responsable de los contenidos de dichos sitios, o de cualquier enlace subsecuente. Nutri-Coach no representa o garantiza que los contenidos del sitio web de alg�n tercero sean exactos,
                            que cumplan con la ley estatal o federal, o que cumplan con las leyes de derechos de autor o con otras leyes de propiedad intelectual. Nutri-Coach tampoco es responsable de cualquier forma de transmisi�n recibida de cualquier sitio
                            web enlazado. Cualquier confianza depositada en los contenidos de un sitio web de terceros es hecha por su propio riesgo y usted asume todas las responsabilidades y consecuencias que resulten de dicha confianza.
                        <br/>
                        <br/>
                        15. <b>Agente de Derechos de Autor.</b>
                        <br/>
                        <br/>
                        Respetamos los derechos de propiedad intelectual de otros y requerimos que las personas que usen el Sitio Web, o los servicios o art�culos puestos a su disposici�n en o a trav�s del Sitio Web, hagan lo mismo.
                        Si usted cree que su trabajo ha sido copiado de un modo que constituya una infracci�n a los derechos de autor, por favor mande la siguiente informaci�n a Agente de Derechos de Autor de Nutri-Coach, designado
                        como tal de conformidad con el art�culo 11 de la Ley Federal de Derechos de Autor, nombrado abajo:
                        <br/>
                        <br/>
                    </p>
                            <ul>
                                <li><p>Su direcci�n, n�mero de tel�fono y direcci�n de correo electr�nico;</p></li>
                                <li><p>Una descripci�n del trabajo con derechos de autor que usted reclama ha sido infringida;</p></li>
                                <li><p>Una descripci�n de donde se localiza el supuesto material infringido;</p></li>
                                <li><p>Una declaraci�n suya de que usted cree de buena fe que el uso en disputa no est� autorizado por el due�o de los derechos de autor, su agente o la ley;</p></li>
                                <li><p>Una firma electr�nica o f�sica de la persona autorizada para actuar en nombre del due�o del inter�s de los derechos de autor; y</p></li>
                                <li><p>Una declaraci�n suya, hecha bajo pena de perjurio, de que la informaci�n antes mencionada en su notificaci�n es exacta y de que usted es el due�o de los derechos de autor o est� autorizado para actuar
                                    en nombre del due�o de los derechos de autor.</p></li>
                            </ul>
                    <p id="parrafo">
                        <br/>
                        <br/>
                            Agente de Derechos de Autor: <br/>
                            Leonardo Miguel Aguirre Hern�ndez <br/>
                            Nutri-Coach  <br/>
                            Tel�fono: 55-6787-0223 <br/>
                            e-mail: leonardomaguirreh@gmail.com <br/>
                        <br/>
                        <br/>
                            Para Servicio al Cliente no se dirija al Agente de Derechos de Autor, por favor dir�jase a: <br/>
                            Nutri-Coach <br/>
                            Escuela Superior de C�mputo <br/>
                            Delegaci�n Gustavo A. Madero, CDMX  <br/>
                            Tel: 55 5729 6000 <br/>
                        <br/>
                        <br/>
                        16. <b>RENUNCIA DE GARANT�AS.</b> EL SITIO WEB, INCLUYENDO, SIN LIMITACI�N, TODOS LOS SERVICIOS, CONTENIDOS, FUNCIONES Y MATERIALES PROPORCIONADOS A TRAV�S DEL SITIO WEB, SON PROPORCIONADOS "TAL COMO EST�N", "COMO SE PONEN A LA DISPOSICI�N",
                        SIN GARANT�A DE NING�N TIPO, YA SEA EXPRESA O IMPL�CITA, INCLUYENDO, SIN LIMITACI�N, CUALQUIER GARANT�A DE INFORMACI�N, DATOS, SERVICIOS DE PROCESAMIENTO DE DATOS, ACCESO ILIMITADO O ININTERRUMPIDO, CUALQUIER GARANT�A CON RESPECTO A LA DISPONIBILIDAD,
                        INTERPRETABILIDAD, NO INTERPRETABILIDAD, EXACTITUD, PRECISI�N, RECTITUD, PERFECCI�N, CONCLUSI�N, UTILIDAD, O CONTENIDO DE LA INFORMACI�N, Y CUALQUIER GARANT�A DE T�TULO, NO-INFRACCI�N, COMERCIABILIDAD O APTITUD PARA UN PROP�SITO EN PARTICULAR,
                        Y EN EL PRESENTE RENUNCIAMOS A CUALQUIERA Y A TODAS DICHAS GARANT�AS, EXPRESAS E IMPL�CITAS. NO GARANTIZAMOS QUE EL SITIO WEB O LOS SERVICIOS, CONTENIDOS, FUNCIONES O MATERIALES PROPORCIONADOS A TRAV�S DEL SITIO WEB SER�N OPORTUNOS, SEGUROS, ININTERRUMPIDOS
                        O LIBRES DE ERROR, O QUE LOS DEFECTOS SER�N CORREGIDOS. NO DAMOS GARANT�A DE QUE EL SITIO WEB O DE QUE LOS SERVICIOS PROPORCIONADOS CUMPLIR�N CON LOS REQUERIMIENTOS DE LOS USUARIOS. NING�N CONSEJO, RESULTADOS O INFORMACI�N, YA SEA ORAL O ESCRITO, 
                        QUE USTED OBTENGA DE NOSOTROS A TRAV�S DEL SITIO WEB CREAR� ALGUNA GARANT�A QUE NO HAYA SIDO ESTABLECIDA EXPRESAMENTE EN EL PRESENTE. NUTRI-COACH TAMPOCO ASUME NINGUNA RESPONSABILIDAD, Y NO SER� RESPONSABLE DE CUALQUIER DA�O A, O DE CUALQUIER VIRUS QUE
                        PUDIERA INFECTAR SU EQUIPO A CAUSA DE SU ACCESO A, USO DE, O NAVEGACI�N EN EL SITIO WEB O POR DESCARGAR CUALQUIERA DE LOS MATERIALES, DATOS, TEXTOS, IM�GENES, CONTENIDOS DE VIDEO O CONTENIDOS DE AUDIO DEL SITIO WEB. SI USTED NO EST� SATISFECHO CON EL
                        SITIO WEB, SU �NICA SOLUCI�N ES DESCONTINUAR USANDO EL SITIO WEB.
                        TRATAMOS DE ASEGURAR QUE LA INFORMACI�N PUBLICADA EN EL SITIO WEB ES CORRECTA Y ACTUALIZADA. NOS RESERVAMOS EL DERECHO DE CAMBIAR O HACER CORRECCIONES A CUALQUIER INFORMACI�N PROPORCIONADA EN EL SITIO WEB EN CUALQUIER MOMENTO Y SIN NING�N AVISO PREVIO.
                        NUTRI-COACH NI AVALA NI ES RESPONSABLE DE LA EXACTITUD O VERACIDAD DE CUALQUIER OPINI�N, CONSEJO O DECLARACI�N EN EL SITIO WEB, NI DE CUALQUIER PUBLICACI�N OFENSIVA, DIFAMATORIA, OBSCENA, INDECENTE, ILEGAL O VIOLATORIA HECHA EN EL MISMO POR CUALQUIER
                        PERSONA A NO SER UN EMPLEADO PORTAVOZ AUTORIZADO DE NUTRI-COACH EN SU CAR�CTER OFICIAL (INCLUYENDO, SIN LIMITACI�N, OTROS USUARIOS DEL SITIO WEB). ES SU RESPONSABILIDAD EVALUAR LA EXACTITUD, CONCLUSI�N O UTILIDAD DE CUALQUIER INFORMACI�N, OPINI�N, CONSEJO U OTRO
                        CONTENIDO DISPONIBLE A TRAV�S DEL SITIO WEB. POR FAVOR BUSQUE EL CONSEJO DE PROFESIONALES, SEG�N SEA APROPIADO, CON RESPECTO A LA EVALUACI�N DE CUALQUIER INFORMACI�N, OPINI�N, CONSEJO U OTRO CONTENIDO ESPEC�FICO, INCLUYENDO, PERO NO LIMITADO A, INFORMACI�N, OPINI�N,
                        CONSEJO U OTRO CONTENIDO FINANCIERO, DE SALUD O DE ESTILO DE VIDA.
                        PREVIO A LA REALIZACI�N DE UNA COMPRA O VENTA DE CUALQUIER VALOR O INVERSI�N, LE ACONSEJAMOS QUE CONSULTE CON SU CORREDOR O CON OTRO ASESOR FINANCIERO PARA VERIFICAR PRECIOS Y OTRA INFORMACI�N. NO TENDREMOS NINGUNA RESPONSABILIDAD DE DECISIONES DE INVERSI�N BASADAS EN,
                        O DE RESULTADOS OBTENIDOS DE, EL CONTENIDO PROPORCIONADO EN EL PRESENTE. NADA CONTENIDO EN EL SITIO WEB SER� INTERPRETADO COMO CONSEJO DE INVERSI�N. NUTRI-COACH NO ES UN CORREDOR DE BOLSA REGISTRADO NI UN CONSULTOR DE INVERSIONES Y NO DA CONSEJOS SOBRE INVERSIONES NI RECOMIENDA UN PRODUCTO SOBRE OTRO.
                        SIN LIMITACI�N DE LO ANTES MENCIONADO EN ESTA SECCI�N, NUTRI-COACH, Y SUS AFILIADOS, PROVEEDORES Y PERSONAS QUE OTORGAN LAS LICENCIAS NO DAN GARANT�AS NI REPRESENTACIONES CON RESPECTO A CUALQUIER PRODUCTO O SERVICIO ORDENADO O PROPORCIONADO V�A EL SITIO WEB, Y
                        EN EL PRESENTE RENUNCIAN, Y USTED EN EL PRESENTE DESISTE, DE CUALQUIERA Y DE TODAS LAS GARANT�AS Y REPRESENTACIONES HECHAS A LITERATURA EN PRODUCTOS O SERVICIOS, A PREGUNTAS HECHAS FRECUENTEMENTE EN DOCUMENTOS Y DE ALGUNA OTRA MANERA EN EL SITIO WEB O EN CORRESPONDENCIA CON NUTRI-COACH 
                        O CON SUS AGENTES. CUALQUIER PRODUCTO O SERVICIO ORDENADO O PROPORCIONADO V�A EL SITIO WEB ES PROPORCIONADO POR NUTRI-COACH "TAL COMO EST�", EXCEPTO AL GRADO, SI SE DIERA EL CASO, DE ESTAR DE OTRO MODO ESTABLECIDO POR ESCRITO, EN UN CONTRATO DE LICENCIA O VENTA CELEBRADO POR SEPARADO
                        ENTRE USTED Y NUTRI-COACH O SU PROVEEDOR O LA PERSONA QUE OTORGA LA LICENCIA.
                        <br/>
                        <br/>
                        17. <b>L�MITE DE RESPONSABILIDAD.</b> EN NING�N CASO, INCLUYENDO PERO NO LIMITADO A NEGLIGENCIA, SER� RESPONSABLE NUTRI-COACH, O CUALQUIERA DE SUS DIRECTORES, FUNCIONARIOS, EMPLEADOS, AGENTES O PROVEEDORES, DEL CONTENIDO O DE LOS SERVICIOS (CONJUNTAMENTE, LAS "ENTIDADES PROTEGIDAS") DE
                        CUALQUIER DA�O DIRECTO, INDIRECTO, ESPECIAL, INCIDENTAL, CONSECUENTE, EJEMPLAR O PUNITIVO COMO RESULTADO DE, O DIRECTA O INDIRECTAMENTE RELACIONADO CON, EL USO DE, O LA INCAPACIDAD DE USAR EL SITIO WEB O LOS CONTENIDOS, MATERIALES Y FUNCIONES RELACIONADOS CON EL MISMO, SU PROVISI�N DE INFORMACI�N V�A
                        EL SITIO WEB, NEGOCIOS PERDIDOS O VENTAS PERDIDAS, A�N CUANDO DICHA ENTIDAD PROTEGIDA HAYA SIDO ASESORADA SOBRE LA POSIBILIDAD DE DICHOS DA�OS. ALGUNAS JURISDICCIONES NO PERMITEN LA LIMITACI�N O EXCLUSI�N DE RESPONSABILIDAD POR DA�OS INCIDENTALES O INDIRECTOS AS� QUE ALGUNOS DE LAS LIMITACIONES ARRIBA
                        MENCIONADAS NO APLICAN A ALGUNOS USUARIOS. EN NING�N CASO SER�N RESPONSABLES LAS ENTIDADES PROTEGIDAS POR O EN RELACI�N CON CUALQUIER CONTENIDO PUBLICADO, TRANSMITIDO, INTERCAMBIADO O RECIBIDO POR O EN NOMBRE DE CUALQUIER USUARIO O DE OTRA PERSONA EN O A TRAV�S DEL SITIO WEB. EN NING�N CASO LA SUMA
                        TOTAL DE LAS RESPONSABILIDADES DE LAS ENTIDADES PROTEGIDAS HACIA USTED, POR TODOS LOS DA�OS, P�RDIDAS Y CAUSAS DE ACCI�N PENAL (YA SEA POR CONTRATO O POR AGRAVIO, INCLUYENDO PERO NO LIMITADO A, NEGLIGENCIA O DE ALGUNA OTRA MANERA) QUE RESULTEN DE LOS T�RMINOS Y CONDICIONES O DEL USO QUE USTED HAGA
                        DEL SITIO WEB, DEBER� EXCEDER EN LA SUMA TOTAL, LA CANTIDAD, SI LA HUBIERA, PAGADA POR USTED A NUTRI-COACH POR EL USO DEL SITIO WEB O POR LA COMPRA DE PRODUCTOS V�A EL SITIO WEB.
                        <br/>
                        <br/> 
                        18. <b>Ataques Fotosensibles.</b> Un muy peque�o porcentaje de personas pueden experimentar un ataque al ser expuestas a ciertas im�genes visuales, como luces o dibujos intermitentes que pueden aparecer en juegos de video o en otros contenidos electr�nicos o en l�nea. A�n personas que
                        no tienen historia de ataques o de epilepsia pueden tener una condici�n no diagnosticada que puede causar estos "ataques epil�pticos fotosensibles" al estar viendo juegos de video u otros contenidos electr�nicos. Estos ataques tienen una variedad de s�ntomas, incluyendo mareos, desorientaci�n,
                        confusi�n, p�rdida moment�nea de conciencia, crispaci�n de ojos o cara, visi�n alterada o tirones o sacudidas de brazos o piernas. Si usted experimenta cualquiera de los s�ntomas anteriormente mencionados, o si usted o su familia tiene historia de ataques o epilepsia, debe de dejar
                        inmediatamente de usar el Sitio Web y consultar un doctor.
                        <br/>
                        <br/>  
                        19. <b>Leyes Aplicables.</b> Nosotros controlamos y operamos el Sitio Web desde nuestras oficinas en M�xico. No representamos a los materiales en el Sitio Web como apropiados o disponibles para su uso en otros lugares. Las personas que escojan acceder al sitio Web desde otros lugares lo har�n
                        por su propia iniciativa y son responsables del cumplimiento de las leyes locales, si y al grado en que las leyes locales sean aplicables. Todas las partes sujetas a estos t�rminos y condiciones renuncian a sus derechos respectivos a un juicio con jurado.
                        <br/>
                        <br/>  
                        20. <b>T�rmino.</b> Nutri-Coach puede terminar, cambiar, suspender o descontinuar cualquier aspecto del Sitio Web o de los servicios del Sitio Web en cualquier momento. Nutri-Coach puede restringir, suspender o terminar su acceso al Sitio Web y/o a sus servicios si creemos que usted est� en
                        incumplimiento de nuestros t�rminos y condiciones o de la ley aplicable, o por cualquier otra raz�n sin notificaci�n o responsabilidad. Nutri-Coach mantiene una pol�tica que estipula la terminaci�n, en circunstancias apropiadas, de los privilegios de uso del Sitio Web para usuarios que son
                        violadores repetitivos de los derechos de propiedad intelectual.
                        <br/>
                        <br/> 
                        21. <b>Cambios de T�rminos de Uso.</b>  Nutri-Coach se reserva el derecho, a su sola discreci�n, de cambiar, modificar, a�adir o quitar cualquier porci�n de los T�rminos y Condiciones, toda o en parte, en cualquier momento. Los cambios en los T�rminos y Condiciones ser�n efectivos cuando
                        se publiquen. La continuaci�n del uso del Sitio Web y/o de los servicios puestos a disposici�n en o a trav�s del Sitio Web despu�s de haber sido publicado cualquier cambio, ser� considerado como aceptaci�n de esos cambios.
                        <br/>
                        <br/>  
                        22. <b>Miscel�neos.</b> Los T�rminos y Condiciones y la relaci�n entre usted y nosotros, ser�n regidos por las leyes de la CDMX, M�xico, sin consideraci�n a conflictos de disposiciones de ley. Usted se obliga a que cualquier causa de acci�n legal que resulte bajo los T�rminos y Condiciones
                        ser� iniciada y o�da en la corte apropiada en CDMX, M�xico. Usted se obliga a someterse a la jurisdicci�n personal y exclusiva de las cortes localizadas dentro de la CDMX. Nuestra falla al ejercer y hacer valer cualquier derecho o disposici�n de los T�rminos y Condiciones no constituir� una
                        renuncia a dicho derecho o disposici�n. Si cualquier disposici�n de los T�rminos y Condiciones es encontrada inv�lida por una corte de jurisdicci�n competente, las partes, sin embargo, est�n de acuerdo en que la corte deber� esforzarse en aplicar las intenciones de las partes como est�n reflejadas
                        en la disposici�n, y las otras disposiciones de los T�rminos y Condiciones permanecen vigentes.
                        <br/>
                        <br/>  
                        Estos T�rminos y Condiciones fueron corregidos 04/09/20018.
                    </p>
                </div>
                    <!--Fin T�rminos y condiciones-->
                </div>
            </div>
        </div>
                    <script src="<c:url value="/resource/scrips/modalScript.js" />"></script>          
    </body>
</html>
