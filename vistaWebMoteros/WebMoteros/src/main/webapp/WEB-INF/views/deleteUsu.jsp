<!DOCTYPE html>
<html>
<head>
    <title>Eliminar Usuario</title>
</head>
<body>
    <h1>Eliminar Usuario</h1>

    <!-- Mostrar mensaje de éxito o error basado en parámetros de la URL -->
    <c:if test="${not empty param.success}">
        <p style="color: green;">Usuario eliminado exitosamente.</p>
    </c:if>
    <c:if test="${not empty param.error}">
        <p style="color: red;">Error al eliminar el usuario.</p>
    </c:if>

    <form action="/deleteUsu" method="DELETE">
        <!-- Campo oculto para simular una solicitud DELETE -->
        <input type="hidden" name="_method" value="DELETE">

        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required>
        <br>
        <label for="contrasenya">Contraseña:</label>
        <input type="password" id="contrasenya" name="contrasenya" required>
        <br>
        <button type="submit">Eliminar Usuario</button>
    </form>
</body>
</html>
