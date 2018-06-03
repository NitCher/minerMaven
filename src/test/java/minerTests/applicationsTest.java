package minerTests;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import miner.applications;
import org.junit.After;
import org.junit.Test;

public class applicationsTest {
    Thread thread;
    @Test
    public void testA() throws InterruptedException {
        thread= new Thread(() -> {//создаем поток для запуска окна
            new JFXPanel(); // создаем платформу Javafx Platform
            Platform.runLater(() -> { //запускаем приложение с помощью платформы
                try {
                    new applications().start(new Stage()); // запускаем приложение указывая главное окно
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
        thread.start();//запускаем поток
        Thread.sleep(10000);//ждем 10 секунд и закрываем приложение. Если все пройдет, то тест удался
    }
    @After //выполнить после теста
    public void close(){

        try {
            thread.interrupt();//послать запрос на завершение потока
            thread.join();//подождать пока поток ответит и завершит работу
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread=null;//обнулить переменную потока
    }

}