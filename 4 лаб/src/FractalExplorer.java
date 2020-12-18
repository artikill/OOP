import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;

/**
 *  Этот класс позволяет нам исследовать различные области фрактала,
 *  путем его создания, отображения через
 * графический интерфейс Swing и обработки событий,
 * вызванных взаимодействием приложения с пользователем
 */
public class FractalExplorer
{
    /**
     * Целое число «размер экрана», которое
     * является шириной и высотой отображения в пикселях
     */
    private int displaySize;
    
    /**
     *
     * Ссылка JImageDisplay, для обновления отображения в разных
     * методах в процессе вычисления фрактала
     */
    private JImageDisplay display;
    
    /**
     * Объект FractalGenerator. Будет использоваться ссылка на базовый
     * класс для отображения других видов фракталов в будущем.
     */
    private FractalGenerator fractal;
    
    /**
     * Объект Rectangle2D.Double, указывающий диапазона комплексной
     * плоскости, которая выводится на экран.
     */
    private Rectangle2D.Double range;
    
    /**
     * A constructor that takes a display-size, stores it, and
     * initializes the range and fractal-generator objects.
     */
    public FractalExplorer(int size) {
        /** Размер дислея экрана **/
        displaySize = size;
        
        /** инициализирует объекты диапазона и фрактального генератора. **/
        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);
    
    }
    /**
     * метод createAndShowGUI (), который инициализирует
     * графический интерфейс Swing: JFrame, содержащий объект JimageDisplay, и
     * кнопку для сброса отображения.
     */
    public void createAndShowGUI()
    {
        /** Set the frame to use a java.awt.BorderLayout for its contents. **/
        display.setLayout(new BorderLayout());
        JFrame myframe = new JFrame("Fractal Explorer");
        
        /** 
         * добавить объект отображения изображения в позицию
         * BorderLayout.CENTER
         */
        myframe.add(display, BorderLayout.CENTER);
        
        /** Создание кнопки ресета. */
        JButton resetButton = new JButton("Reset Display");
        
        /** Экземпляр ResetHandler на кнопке сброса
         */
        ResetHandler handler = new ResetHandler();
        resetButton.addActionListener(handler);
        
        /** Добавление кнопки сброса на позицию BorderLayout.SOUTH. */
        myframe.add(resetButton, BorderLayout.SOUTH);
        
        /** Экземпляр MouseHandler в компоненте фрактального отображения.
         */
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);
        
        /** обеспечить операцию закрытия окна по умолчанию
         */
        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /**
         * Данные операции правильно разметят содержимое окна, сделают его
         * видимым, и затем запретят изменение размеров окна.
         */
        myframe.pack();
        myframe.setVisible(true);
        myframe.setResizable(false);
    }
    
    /**
     * вспомогательный метод с типом доступа private для вывода на экран фрактала.
     * Этот метод циклически проходит через каждый пиксель в отображении
     * и вычисляет количество итераций для соответствующих координат в
     * области отображения фрактала.
     */
    private void drawFractal()
    {
        /** циклически проходит через каждый пиксель в отображении */
        for (int x=0; x<displaySize; x++){
            for (int y=0; y<displaySize; y++){
                
                /**
                 * Находит соответствующие координаты xCoord и yCoord
                 * в области отображения фрактала
                 */
                double xCoord = fractal.getCoord(range.x,
                range.x + range.width, displaySize, x);
                double yCoord = fractal.getCoord(range.y,
                range.y + range.height, displaySize, y);
                
                /**
                 * Подсчет числа итераций
                 */
                int iteration = fractal.numIterations(xCoord, yCoord);
                
                /** Если число итераций равно -1
                 *  установите пиксель в черный цвет
                 */
                if (iteration == -1){
                    display.drawPixel(x, y, 0);
                }
                
                else {
                    /**
                     * OИначе нужно выбрать
                     * значение цвета, основанное на количестве итераций.
                     */
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                
                    /** обновляем отображение каждого пикселя
                     *  в соответствии с  его цветом*/
                    display.drawPixel(x, y, rgbColor);
                }
                
            }
        }
        /**
         *После того, как вы закончили отрисовывать все пиксели, вам
         * необходимо обновить JimageDisplay в соответствии с текущим изображением.
         * Для этого вызываем функцию repaint().
         */
        display.repaint();
    }
    /**
     * Внутренний класс для обработки событий ActionListener от кнопки сброса.
     */
    private class ResetHandler implements ActionListener
    {
        /**
         *Обработчик сбрасывает диапазон
         * до начального, заданного генератором, а затем рисует фрактал.
         */
        public void actionPerformed(ActionEvent e)
        {
            fractal.getInitialRange(range);
            drawFractal();
        }
    }
    /**
     * Внутренний класс для обработки событий MouseListener с дисплея.
     */
    private class MouseHandler extends MouseAdapter
    {
        /**
         * При получении события о щелчке мышью, класс должен
         * отобразить пиксельные кооринаты щелчка в область фрактала, а затем вызвать
         * метод генератора recenterAndZoomRange() с координатами, по которым
         * щелкнули, и масштабом 0.5
         */

        public void mouseClicked(MouseEvent e)
        {
            /** получение координаты X от клика мыши */
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x,
            range.x + range.width, displaySize, x);
            
            /** получение координаты Y от клика мыши */
            int y = e.getY();
            double yCoord = fractal.getCoord(range.y,
            range.y + range.height, displaySize, y);
            
            /**
             *
             * Вызов метода генератора recenterAndZoomRange() с координатами,
             * по которым щелкнули, и масштабом 0.5.

             */
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            
            /**
             * Перерисовка фрактала после изменения отображаемой области
             */
            drawFractal();
        }
    }
    
    /**
     * Статический метод main () для запуска FractalExplorer.
     * Инициализирует новый экземпляр FractalExplorer с размером отображения 800.
     * вызывает метод createAndShowGUI() класса FractalExplorer,
     * а затем вызывает метод drawFractal() класса FractalExplorer для
     * отображения начального представления.
     */
    public static void main(String[] args)
    {
        FractalExplorer displayExplorer = new FractalExplorer(800);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}