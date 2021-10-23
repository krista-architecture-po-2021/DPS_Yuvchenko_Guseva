package ModelDataClass;

public class NoDataFileException extends Exception{
    public NoDataFileException() {
        super("Ошибка создания новости или данных для неё");
    }
}

