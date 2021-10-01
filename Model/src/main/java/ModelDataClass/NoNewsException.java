package ModelDataClass;

public class NoNewsException extends Exception{
    public NoNewsException () {
        super("Ошибка создания новости или данных для неё");
    }
}

