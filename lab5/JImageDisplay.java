package lab5;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class JImageDisplay extends JComponent
{
    private BufferedImage bImage;

    /**
     * Создаем изображение
     */
    public JImageDisplay(int width, int height) {
        bImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Dimension imageDimension = new Dimension(width, height);
        super.setPreferredSize(imageDimension);

    }

    /**
     *
     * Implement paintComponent from parent class
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(bImage, 0, 0, bImage.getWidth(),
                bImage.getHeight(), null);
    }

    /**
     * Sets all pixels in the image data to black.
     */
    public void clearImage()
    {
        int[] blankArray = new int[getWidth() * getHeight()];
        bImage.setRGB(0, 0, getWidth(), getHeight(), blankArray, 0, 1);
    }

    /**
     * Sets a pixel to a specific color.
     */
    public void drawPixel(int x, int y, int rgbColor)
    {
        bImage.setRGB(x, y, rgbColor);
    }

    /**
     * Возвращает изображение
     */
    public BufferedImage getImage() {
        return bImage;
    }
}