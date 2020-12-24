package labs.lab2;

/*Двумерный класс точки*/
public class Point2d{
    /*Координата Х*/
    private double xCoord;
    /*Координата У*/
    private double yCoord;
    /*Конструктор инициализации*/
    public Point2d(double x, double y){
        xCoord = x;
        yCoord = y;
    }
    /*Конструктор по умолчанию*/
    public Point2d(){
        /* Вызов конструктора с двумя параметрами */
        this (0, 0);
    }
    /*Возвращение координаты X*/
    public double getX(){
        return xCoord;
    }
    /*Возвращение координаты Y*/
    public double getY(){
        return yCoord;
    }
    /*Установка координаты Х*/
    public void setX(double val){
        xCoord = val;
    }
    /*Установка координаты Y*/
    public void setY(double val){
        yCoord = val;
    }
    /*Сравнение точек по координатам*/
    public boolean isEqual( Point2d other){
        return (this.xCoord == other.xCoord & this.yCoord == other.yCoord);
    }
}