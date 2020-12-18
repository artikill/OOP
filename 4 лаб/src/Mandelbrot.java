import java.awt.geom.Rectangle2D;

/**
 * этот класс является подклассом FractalGenerator.
 * он используется для вычисления фрактала мондельброта
 */
public class Mandelbrot extends FractalGenerator
{
    /**
     * Константа с максимальным количеством итераций
     */
    public static final int MAX_ITERATIONS = 2000;
    
    /**
     * метод позволяет генератору
     * фракталов определить наиболее «интересную» область комплексной плоскости
     * для конкретного фрактала. Обратите внимание на то, что методу в качестве
     * аргумента передается прямоугольный объект, и метод должен изменить поля
     * прямоугольника для отображения правильного начального диапазона для фрактала.
     * Значения x и y будут равны -2 и -1.5 соответственно, а ширина и высота будут равны 3.
     */
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }
    
    /**
     * Этот Метод реализует
     * итеративную функцию для фрактала Мандельброта.
     */
    public int numIterations(double x, double y)
    {

        int iteration = 0;

        double zreal = 0;
        double zimaginary = 0;
        
        /**
         * Функция для фрактала Мандельброта имеет вид: Zn = Zn-1^2
         * где все значения — это комплексные числа, которые представлены
         * переменными zreal И zimaginary. z0 = 0, и с - определенная точка фрактала, которую
         * мы отображаем на экране.
         * Вычисления повторяются до тех пор, пока Z^2 > 4, или пока
         * число итераций не достигнет максимального значения.
                  */
        while (iteration < MAX_ITERATIONS &&
               zreal * zreal + zimaginary * zimaginary < 4)
        {
            double zrealUpdated = zreal * zreal - zimaginary * zimaginary + x;
            double zimaginaryUpdated = 2 * zreal * zimaginary + y;
            zreal = zrealUpdated;
            zimaginary = zimaginaryUpdated;
            iteration += 1;
        }
        
        /**
         * Если достигнуто максимальное количество итераций, верните -1,
         * чтобы указать, что точка не вышла за пределы границы.
         */
        if (iteration == MAX_ITERATIONS)
        {
            return -1;
        }
        
        return iteration;
    }

}