import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

/**
 * Этот класс позволяет нам выводить фракталы.
 * Он является производным от javax.swing.JComponent.
 */
class JImageDisplay extends JComponent
{
    /**
     * Экземпляр буферизованного изображения.
     * Управляет изображением, содержимое которого мы можем записать.
     */ 
    private BufferedImage displayImage;
    
    /**
      * Конструктор JImageDisplay должен принимать целочисленные
     * значения ширины и высоты, и инициализировать объект BufferedImage новым
     * изображением с этой шириной и высотой, и типом изображения
     * TYPE_INT_RGB.
      */
    public JImageDisplay(int width, int height) {
        displayImage = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
        
        /** 
         * Вызов метода setPreferredSize()
         * родительского класса  с указанной шириной и высотой.
         */
        Dimension imageDimension = new Dimension(width, height);
        super.setPreferredSize(imageDimension);
        
    }
    /**
     * Вызов метода суперкласса paintComponent (g) так, чтобы объекты
     * отображались правильно. Далее мы можем нарисовать изображение в компоненте
     */

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(displayImage, 0, 0, displayImage.getWidth(), displayImage.getHeight(),null);
    }
    /**
     *  устанавливаем все пиксели изображения в черный цвет
     */
    public void clearImage()
    {
        int[] blankArray = new int[getWidth() * getHeight()];
        displayImage.setRGB(0, 0, getWidth(), getHeight(), blankArray, 0, 1);
    }
    /**
     * устанавливаем все пиксели изображения в определенный цвет
     */
    public void drawPixel(int x, int y, int rgbColor)
    {
        displayImage.setRGB(x, y, rgbColor);
    }
}