package ru.frostdelta.scraddon;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.sql.*;
import java.util.HashMap;

public class Network {

    public static String url, username, password;
    private static HashMap<String, PreparedStatement> preparedStatements = new HashMap<>();
    private static Connection connection;

    public static void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }
        if (connection != null && !connection.isClosed()) {
            return;
        }
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url +
                "?useUnicode=true&characterEncoding=UTF-8", username, password);
    }

    public static Integer id(SCRDatabase database) {
        try {
            Statement statement = connection.createStatement();
            try (ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM " + "`" + database.getTable() + "`")) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static void handle(String player, String itemID, String amount) {
        try {
            Statement statement = connection.createStatement();
            SCRDatabase database = new SCRDatabase(SCRAddon.SCRConfig);
            ItemStack item = new ItemStack(Material.getMaterial(itemID), Integer.parseInt(amount));
            Integer id = id(database);
            String type = "item";
            String itemData = item.getType().name() + ":" + item.getData().getData();
            String name = item.getType().name().toUpperCase().replaceAll("_", " ");
            int itemAmount = item.getAmount();
            String extra = "";
            String server = database.getServer();

            statement.executeUpdate("INSERT INTO " + "`" + database.getTable() + "` (" + database.getColumniD() + ", " + database.getColumnType() + ", " + database.getColumnItem() + ", " + database.getColumnName() + ", " + database.getColumnPlayer() + ", " + database.getColumnAmount()
                    + ", " + database.getColumnExtra() + ", " + database.getColumnServer() +") VALUES (" + id + ", " + type + ", " + itemData + ", " + name + ", " + player + ", " + itemAmount + ", " + extra + ", " + server + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
