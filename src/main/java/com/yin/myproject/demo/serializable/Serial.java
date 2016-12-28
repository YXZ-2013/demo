package com.yin.myproject.demo.serializable;

import java.io.*;

/**
 * Created by Eason on 2016/12/28.
 */
public class Serial {
    public static void writeObject(Object obj, String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            fos.flush();
            oos.close();
            fos.close();
            System.out.println("序列化成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readObject(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object temp = ois.readObject();
            ois.close();
            fis.close();
            if (temp != null) {
                System.out.println("反序列化成功");
                return temp;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
