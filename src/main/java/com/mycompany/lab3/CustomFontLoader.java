package com.mycompany.lab3;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class CustomFontLoader {
    public static Font loadCustomFont(int size, String fontName) {
        try (InputStream fontStream = CustomFontLoader.class.getClassLoader().getResourceAsStream(fontName)) {
            if (fontStream == null) {
                System.err.println("Файл шрифта не найден: " + fontName);
                return null;
            }

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            return customFont.deriveFont(Font.PLAIN, size);
        } catch (FontFormatException e) {
            System.err.println("Неверный формат файла шрифта: " + fontName);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла шрифта: " + fontName);
            e.printStackTrace();
        }
        return null;
    }
}