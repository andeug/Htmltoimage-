/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

/**
 *
 * @author Andrew
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import javax.imageio.IIOImage;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JEditorPane;

public class Test {

    public static void main(String[] args) {
        String html = "<h1>Hello, world.</h1>Etc. Etc.";

        int width = 1024, height = 1024;
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics graphics = image.createGraphics();
        JEditorPane jep = new JEditorPane("text/html", html);
        jep.setSize(width, height);
        jep.print(graphics);

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "png", os);
            OutputStream outputStream;
            ImageWriter imageWriter;
            ImageOutputStream imageOutputStream;
            try (InputStream inputStream = new ByteArrayInputStream(os.toByteArray())) {
                outputStream = new FileOutputStream("/home/mspace/Pictures/nduchtest.png");
                float imageQuality = 0.0f;
                //Creating the buffered image
                BufferedImage imagetwr = ImageIO.read(inputStream);
                //Get image writers
                Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("png");
                if (!imageWriters.hasNext()) {
                    throw new IllegalStateException("Writers Not Found!!");
                }
                imageWriter = (ImageWriter) imageWriters.next();
                imageOutputStream = ImageIO.createImageOutputStream(outputStream);
                imageWriter.setOutput(imageOutputStream);
                ImageWriteParam param = imageWriter.getDefaultWriteParam();

                // Check if canWriteCompressed is true
                if (param.canWriteCompressed()) {
                    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    param.setCompressionQuality(imageQuality);
                }
                //Created image
                imageWriter.write(null, new IIOImage(imagetwr, null, null), param);
                // close all streams
            }
            outputStream.close();
            imageOutputStream.close();
            imageWriter.dispose();

               //ImageIO.write(image, "png", new File("/home/mspace/Pictures/nduchtest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
////            ImageIO.write(image, "png", new File("/home/mspace/Pictures/Image.png"));
////            System.out.println(  ImageIO.write(image, "png", new File("/home/mspace/Pictures/Image.png")));
//            System.out.println( image);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
