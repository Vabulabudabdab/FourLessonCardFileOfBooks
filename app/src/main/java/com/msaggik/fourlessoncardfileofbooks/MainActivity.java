package com.msaggik.fourlessoncardfileofbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity{

    // поля
    private int id = 0;
    private EditText inputName;
    private EditText inputAuthor;
    private TextView output;
    private Button button;
    private Book[] books; // контейнер (массив) для книг

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // привязка разметки к полям
        inputName = findViewById(R.id.inputName);
        inputAuthor = findViewById(R.id.inputAuthor);
        output = findViewById(R.id.output);
        button = findViewById(R.id.button);

        // создание контейнера для 100 книг
        books = new Book[100];

        // обработка нажатия кнопки
        button.setOnClickListener(listener);
    }
    // создание слушателя
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // очистка окна вывода
            output.setText("");
            // формирование данных сущности введённых пользователем
            String name = inputName.getText().toString();
            String author = inputAuthor.getText().toString();
            id++;
            // создание сущности книга
            Book book = new Book(id, author, name);

            // добавление книги в контейнер массива
            books[id-1] = book;

            // создание потока сущностей книг
            Stream<Book> bookStream = Arrays.stream(books);

            // фильт не нулевых ячеек массива и вывод книг на экран
            bookStream.filter( s -> s != null )
                    .sorted(Comparator.comparing(s -> s.getAuthor()))
                    .forEach(s -> output.append(s.getId() + " Автор " + s.getAuthor() + ", книга " + s.getName() + "\n"));
        }
    };
}
//Необходимоспомощью Stream API добавить сортировку по авторам и вывести отсортированные данные на экран

//  listGame.stream()
//          .filter(elem -> elem.getManufacturer().equals("Valve"))
//          .sorted(Comparator.comparing(elem -> elem.getTitle()))
//          .forEach(game -> System.out.println(game.getTitle()));
//
//          }
