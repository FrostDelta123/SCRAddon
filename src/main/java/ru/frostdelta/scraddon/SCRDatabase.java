package ru.frostdelta.scraddon;

import org.bukkit.configuration.file.FileConfiguration;

public class SCRDatabase {

    private String table;
    private String columniD;
    private String columnType;
    private String columnItem;
    private String columnName;
    private String columnPlayer;
    private String columnAmount;
    private String columnExtra;
    private String columnServer;
    private String server;

    public SCRDatabase(FileConfiguration cfg){
        table = cfg.getString("db.table");
        columniD = cfg.getString("db.column.id");
        columnType = cfg.getString("db.column.type");
        columnItem = cfg.getString("db.column.item");
        columnName = cfg.getString("db.column.name");
        columnPlayer = cfg.getString("db.column.player");
        columnAmount = cfg.getString("db.column.amount");
        columnExtra = cfg.getString("db.column.extra");
        columnServer = cfg.getString("db.column.server");
        server = String.valueOf(cfg.getInt("multiserver.server"));
    }

    public String getServer() {
        return server;
    }

    public String getColumnAmount() {
        return columnAmount;
    }

    public String getColumnExtra() {
        return columnExtra;
    }

    public String getColumniD() {
        return columniD;
    }

    public String getColumnItem() {
        return columnItem;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnPlayer() {
        return columnPlayer;
    }

    public String getColumnServer() {
        return columnServer;
    }

    public String getColumnType() {
        return columnType;
    }

    public String getTable() {
        return table;
    }
}
