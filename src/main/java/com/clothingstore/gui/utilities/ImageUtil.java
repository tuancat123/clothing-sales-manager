package com.clothingstore.gui.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtil {

  public static BufferedImage loadImage(String filePath) throws IOException {
    Path path = Path.of(filePath);
    byte[] bytes = Files.readAllBytes(path);
    return ImageIO.read(new ByteArrayInputStream(bytes));
  }

  public static String toBase64(BufferedImage image) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ImageIO.write(image, "png", byteArrayOutputStream);
    byte[] bytes = byteArrayOutputStream.toByteArray();
    return Base64.getEncoder().encodeToString(bytes);
  }

  public static BufferedImage fromBase64(String base64) throws IOException {
    byte[] bytes = Base64.getDecoder().decode(base64);
    return ImageIO.read(new ByteArrayInputStream(bytes));
  }

  public static Image decodeFromBase64(String base64) throws IOException {
    byte[] bytes = Base64.getDecoder().decode(base64);
    return ImageIO.read(new ByteArrayInputStream(bytes));
  }

  public static Image decodeFromBase64(
      String base64,
      String filePathPlaceholder) {
    Image image = null;
    try {
      image = decodeFromBase64(base64);
    } catch (Exception e) {
      image = new ImageIcon(filePathPlaceholder).getImage();
    }
    return image;
  }
}