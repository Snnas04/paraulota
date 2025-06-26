# POC Archetype Hexagonal

El proyecto tiene la funcionalidad de probar el arquetipo hexagonal

## Ejecutar el proyecto

Como primer paso se debe instalar el certificado HTTPS (en formato .cer) en un trustore de java. Para ello se tiene que ejecutar el siguiente comando:

```
keytool.exe -import -trustcacerts -alias riu -file <path al certificado>  -keystore <path al truststore> -storepass <password del truststore>
```

Luego procede a compilar la solución:

```
mvn clean package
```

Finalmente se puede ejecutar la solución con el siguiente comando

```
java -jar ArchetypeHexagonal-1.0-SNAPSHOT.jar -Djavax.net.ssl.trustStore="<path al truststore>" -Djavax.net.ssl.trustStorePassword="<password al truststore>"
```

### endpoints

1. GET "http://localhost:8081/actuator" -> Endpoint sin seguridad con la información de actuator
2. GET "http://localhost:8081/users" -> Endpoint SIN seguridad para listar los usuarios creados
3. GET "http://localhost:8081/users/{userName}" -> Obtiene un usuario por username SIN seguridad
4. POST "http://localhost:8081/users (Body: {"username":"","name":""}) -> Endpoint SIN seguridad para crear un usuario
5. GET "http://localhost:8081/secured/users" -> Endpoint CON seguridad para listar los usuarios creados
3. GET "http://localhost:8081/users/{userName}" -> Obtiene un usuario por username CON seguridad
6. POST "http://localhost:8081/secured/users (Body: {"username":"","name":""}) -> Endpoint CON seguridad para crear un usuario
7.
### Seguridad

Los endpoints securizados esperan un header con los siguientes parámetros
- Autorization: "Bearer --token--"
- client_id: "--client id--"

Por default, todo endopoint que este por delante de /secured/ esperarán los headers previamente mencionados