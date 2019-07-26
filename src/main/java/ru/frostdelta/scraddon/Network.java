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
            String[] message = itemID.split(":");
            ItemStack item = message.length > 1 ? new ItemStack(Material.getMaterial(message[0].toUpperCase()), Integer.parseInt(amount), Short.parseShort(message[1])) : new ItemStack(Material.getMaterial(message[0].toUpperCase()), Integer.parseInt(amount));
            Integer id = id(database);
            String type = "item";
            String itemData = item.getType().name() + ":" + item.getData().getData();
            String name = item.getType().name().toUpperCase().replaceAll("_", " ");
            int itemAmount = item.getAmount();
            String extra = "";
            String server = database.getServer();
            String sql = "INSERT INTO " + "`" + database.getTable() + "` (" + database.getColumniD() + ", " + database.getColumnType() + ", " + database.getColumnItem() + ", " + database.getColumnPlayer() + ", " + database.getColumnAmount()
                    +") VALUES (" + id + ", " + type + ", " + itemData + ", " + player + ", " + itemAmount + ")";

            System.out.println("DEBUG: " + sql);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
