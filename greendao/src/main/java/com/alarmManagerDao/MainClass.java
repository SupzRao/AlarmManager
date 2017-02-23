package com.alarmManagerDao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MainClass {
    static Entity user;
    static Entity alarm;

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1042, "com.alarmmanager.model.entity");
        schema.setDefaultJavaPackageDao("com.alarmmanager.model.dao");
        addUser(schema);
        addAlarm(schema);
        new DaoGenerator().generateAll(schema, "app/src/main/java", null, null);
    }

    private static Entity addUser(Schema schema) {
        user = schema.addEntity("Users");
        user.addIdProperty().autoincrement();
        user.addDateProperty("createTime");
        user.addStringProperty("name");
        user.addStringProperty("email");
        user.addStringProperty("image");
        return user;
    }

    public static Entity addAlarm(Schema schema) {
        alarm = schema.addEntity("Alarm");
        alarm.addIdProperty().autoincrement();
        alarm.addStringProperty("time");
        alarm.addStringProperty("tone");
        return alarm;
    }
}
