import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertarDatos {

    // Datos simulados (similar a los de TMP_LLENAR_CAMPOS)
    private static Object[][] datosTmp = {
        {1, "COMP001", "Compañía Alpha", "Descripción Alpha", 1, 1, "v1.0", "Versión inicial", 1, "Versión personalizada para Alpha", "APP001", "Aplicación Uno", "Descripción de la aplicación uno"},
        {2, "COMP002", "Compañía Beta", "Descripción Beta", 2, 2, "v2.0", "Segunda versión", 2, "Versión personalizada para Beta", "APP002", "Aplicación Dos", "Descripción de la aplicación dos"}
    };

    public static void main(String[] args) {

        // Leer datos de conexión
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el host (localhost por defecto): ");
        String host = scanner.nextLine();
        if (host.isEmpty()) {
            host = "localhost";
        }

        System.out.print("Introduce el usuario: ");
        String user = scanner.nextLine();

        System.out.print("Introduce la contraseña: ");
        String password = scanner.nextLine();

        System.out.print("Introduce el nombre de la base de datos: ");
        String database = scanner.nextLine();

        // URL de conexión
        String url = "jdbc:mysql://" + host + "/" + database + "?useSSL=false";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión exitosa a la base de datos");

            for (Object[] registro : datosTmp) {
                insertarRegistro(connection, registro);
            }

            System.out.println("Inserciones completadas correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al conectar o insertar los datos: " + e.getMessage());
        }
    }

    private static void insertarRegistro(Connection connection, Object[] registro) throws SQLException {

        // Inserción en la tabla company
        String sqlCompany = "INSERT INTO company (id_company, codigo_company, name_company, description_company) "
                + "VALUES (?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE codigo_company = VALUES(codigo_company), name_company = VALUES(name_company), description_company = VALUES(description_company)";

        try (PreparedStatement stmt = connection.prepareStatement(sqlCompany)) {
            stmt.setInt(1, (Integer) registro[0]);
            stmt.setString(2, (String) registro[1]);
            stmt.setString(3, (String) registro[2]);
            stmt.setString(4, (String) registro[3]);
            stmt.executeUpdate();
        }

        // Inserción en la tabla application
        String sqlApplication = "INSERT INTO application (app_id, app_code, app_name, app_description) "
                + "VALUES (?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE app_code = VALUES(app_code), app_name = VALUES(app_name), app_description = VALUES(app_description)";

        try (PreparedStatement stmt = connection.prepareStatement(sqlApplication)) {
            stmt.setInt(1, (Integer) registro[5]);
            stmt.setString(2, (String) registro[10]);
            stmt.setString(3, (String) registro[11]);
            stmt.setString(4, (String) registro[12]);
            stmt.executeUpdate();
        }

        // Inserción en la tabla version
        String sqlVersion = "INSERT INTO version (version_id, app_id, version, version_description) "
                + "VALUES (?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE version = VALUES(version), version_description = VALUES(version_description)";

        try (PreparedStatement stmt = connection.prepareStatement(sqlVersion)) {
            stmt.setInt(1, (Integer) registro[4]);
            stmt.setInt(2, (Integer) registro[5]);
            stmt.setString(3, (String) registro[6]);
            stmt.setString(4, (String) registro[7]);
            stmt.executeUpdate();
        }

        // Inserción en la tabla version_company
        String sqlVersionCompany = "INSERT INTO version_company (version_company_id, company_id, version_id, version_company_description) "
                + "VALUES (?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE version_company_description = VALUES(version_company_description)";

        try (PreparedStatement stmt = connection.prepareStatement(sqlVersionCompany)) {
            stmt.setInt(1, (Integer) registro[8]);
            stmt.setInt(2, (Integer) registro[0]);
            stmt.setInt(3, (Integer) registro[4]);
            stmt.setString(4, (String) registro[9]);
            stmt.executeUpdate();
        }
    }
}
