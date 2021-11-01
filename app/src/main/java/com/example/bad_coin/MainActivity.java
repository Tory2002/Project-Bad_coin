package com.example.bad_coin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout contener;
    int county=0;
    int count_coin=0;
    TextView count_true_coin;
    TextView Answer;
    TextView count_vzv;
    Button true_false;
    Button New_game;
    Button weigh;
    ImageView Box;
    ImageView Ves;
    ImageView Ves1, Ves2;
    ImageView[] ArrayImage = new ImageView[10];
    int[] Libra_coin = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5};


    Random rand = new Random();
    private int xDelta, yDelta;
    //функция которая обеспечивает движение монет
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            final int x = (int) event.getRawX();
            final int y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    ConstraintLayout.LayoutParams IParams = (ConstraintLayout.LayoutParams) v.getLayoutParams();
                    xDelta = x - IParams.leftMargin;
                    yDelta = x - IParams.topMargin;
                    break;
                }
                case MotionEvent.ACTION_UP: {
                count_coin=0;
                    for (int i = 0; i < ArrayImage.length; i++) {

                        if (ArrayImage[i].getLeft() >Box.getLeft() && ArrayImage[i].getLeft()<Box.getLeft()+Box.getWidth() && ArrayImage[i].getTop()>Box.getTop()) {
                            count_coin++;

                        }
                    }
                        count_true_coin.setText("Количество настоящих монет: "+count_coin);
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                   // if (x - xDelta + v.getWidth() <= contener.getMaxWidth() && y - yDelta + v.getHeight() <= contener.getHeight() && x - xDelta >= 0 && y - yDelta >= 0) {
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) v.getLayoutParams();
                        layoutParams.leftMargin =  x -  xDelta;
                        layoutParams.topMargin = y -  yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        v.setLayoutParams(layoutParams);
                   // }
                    break;
                }
            }
            contener.invalidate();
            return true;
        }
    };
//функция создает активити
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Ves1 = (ImageView) findViewById(R.id.ves1);
        Ves2 = (ImageView) findViewById(R.id.Ves2);
        Ves = (ImageView) findViewById(R.id.Libra);
        contener = findViewById(R.id.q);
        count_vzv = (TextView) findViewById(R.id.count_vzv);
        weigh=(Button)findViewById(R.id.weigh) ;
        Answer=(TextView) findViewById(R.id.Answer);
        true_false=(Button)findViewById(R.id.true_false) ;
        //создаем обработчик событий щелчком на кнопку проверить
        true_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                True_false();
            }
        });
        //новая игра
        New_game=(Button)findViewById(R.id.New_game);
        New_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_game();
            }
        });
        count_true_coin=(TextView)findViewById(R.id.count_true_coin);
        Box=(ImageView)findViewById(R.id.Box);
        //для кнопки взвесить
        weigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cell();
            }
        });
//связываем монеты с массивом картинок
        ArrayImage[0] = (ImageView) findViewById(R.id.Coin_1);
        ArrayImage[1] = (ImageView) findViewById(R.id.Coin_2);
        ArrayImage[2] = (ImageView) findViewById(R.id.Coin_3);
        ArrayImage[3] = (ImageView) findViewById(R.id.Coin_4);
        ArrayImage[4] = (ImageView) findViewById(R.id.Coin_5);
        ArrayImage[5] = (ImageView) findViewById(R.id.Coin_6);
        ArrayImage[6] = (ImageView) findViewById(R.id.Coin_7);
        ArrayImage[7] = (ImageView) findViewById(R.id.Coin_8);
        ArrayImage[8] = (ImageView) findViewById(R.id.Coin_9);
        ArrayImage[9] = (ImageView) findViewById(R.id.Coin_10);
        for (int i = 0; i < ArrayImage.length; i++) {
            ArrayImage[i].setOnTouchListener(touchListener);

        }
        New_game = (Button) findViewById(R.id.New_game);
        //вызываем функцию генерирования веса монет
        Generacion();

    }
//генерирование веса монет
    void Generacion() {
        for (int i = 0; i < Libra_coin.length; i++) {
            Libra_coin[i] = 5;
        }

        int n = rand.nextInt(10);//сгенерировть с помощью рандома номер фальшивой монеты
        int v = 1;//сгенерировать случайное число 1 или -1, которое будет указывать фальшивая монета тежелее или легче
        Libra_coin[n] += v;
    }
//взвешивание монет лежащих на каждой из чашечек
    void Cell() {
        int cell1 = 0, cell2 = 0;
        for (int i = 0; i < ArrayImage.length; i++) {

            if (ArrayImage[i].getLeft() >Ves.getLeft()+Ves.getWidth()/2) {
                cell2 += Libra_coin[i];

            } else if (ArrayImage[i].getLeft() >Ves.getLeft()) {
                cell1 += Libra_coin[i];
            }
        }
       // String s = cell1 + "/" + cell2;
       // weigh.setText(s);

        if (cell1 > cell2) {
            Ves1.setVisibility(View.VISIBLE);
            Ves.setVisibility(View.INVISIBLE);
            Ves2.setVisibility(View.INVISIBLE);


        }
        else if(cell1 < cell2){
            Ves2.setVisibility(View.VISIBLE);
            Ves.setVisibility(View.INVISIBLE);
            Ves1.setVisibility(View.INVISIBLE);

        }
        else{
            Ves.setVisibility(View.VISIBLE);
            Ves1.setVisibility(View.INVISIBLE);
            Ves2.setVisibility(View.INVISIBLE);

        }
        county++;

        count_vzv.setText("Количество взвешиваний: "+county);


    }
    //определение выйгрыша
    void True_false(){
        int sum=0;
        for (int i = 0; i < ArrayImage.length; i++) {

            if (ArrayImage[i].getLeft() >Box.getLeft() && ArrayImage[i].getLeft()<Box.getLeft()+Box.getWidth() && ArrayImage[i].getTop()>Box.getTop()) {
                sum+=Libra_coin[i];

            }

        }
        if(sum==45){
            Answer.setText("Головоломка решена: верно!");
            Toast.makeText(getApplicationContext(),"Головоломка решена верно!",Toast.LENGTH_LONG);
        }
        else{
            Answer.setText("Головоломка решена: не верно!");
            Toast.makeText(getApplicationContext(),"Головоломка решена не верно!",Toast.LENGTH_LONG);
        }
    }
//начало новой игры
void new_game(){
    Answer.setText("Головоломка решена:");
    count_true_coin.setText("Количество настоящих монет: ");
    count_vzv.setText("Количество взвешиваний: ");
    Ves.setVisibility(View.VISIBLE);
    Ves1.setVisibility(View.INVISIBLE);
    Ves2.setVisibility(View.INVISIBLE);

    int h = 0, ii=0;
    for (int i = 0; i < ArrayImage.length; i++) {
        if(i%3==0){
            h++;
            ii=0;
        }
        ArrayImage[i].setX(80+ii*ArrayImage[i].getWidth());
        ArrayImage[i].setY(215+h*ArrayImage[i].getHeight());
        ii++;

    }
}
}