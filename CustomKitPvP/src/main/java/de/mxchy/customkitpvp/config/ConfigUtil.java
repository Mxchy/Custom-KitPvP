package de.mxchy.customkitpvp.config;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

public class ConfigUtil {
    public static Object base64ToObject(String base64){
        try {
            byte[] decodedObject = Base64.getDecoder().decode(base64);



            ByteArrayInputStream in = new ByteArrayInputStream(decodedObject);
            BukkitObjectInputStream inBukkit = new BukkitObjectInputStream(in);

            Object deserializedObject = inBukkit.readObject();
            inBukkit.close();
            in.close();

            return deserializedObject;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static String objectToBase64(Object object){
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            BukkitObjectOutputStream outBukkit = new BukkitObjectOutputStream(out);

            outBukkit.writeObject(object);
            outBukkit.flush();


            byte[] serializedObject = out.toByteArray();
            outBukkit.close();
            out.close();

            return Base64.getEncoder().encodeToString(serializedObject);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
