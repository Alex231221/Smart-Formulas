package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class playActivity extends AppCompatActivity {
    String[] question_list = {"Прибор для измерения сил",
            "Прибор для измерения атмосферного давления ","Единица массы"
            ,"Единица измерения давления",
           "Единица измерения механической работы ",
            "Что такое объем?",
            "Что такое масса?",
            "Что такое давление?",
            "Какова природа звука?",
            "Что такое тепловое излучение?"

    };
    String[] choose_list = {"динамометр","вольтметр","термометр","амперметр",
            "манометр","барометр ","ваттметры","мультиметры",
            "герц","ньютон","метр","кг",
            "ом","Па","тесла","ватт",
            "кулон","кельвин","Дж","секунда",
            " это сила, действующая на тело, погруженное в жидкость или газ.","это пространство, занимаемое телом.","это количество вещества в теле.","это отраженный звук, который возвращается к слушателю после отражения от препятствия.",
            "это энергия, связанная с положением объекта в поле силы.","это количество вещества в теле."," это энергия движения.","это произведение силы на плечо.",
            "это колебания упругих сред, передающиеся от источника к приемнику в виде звуковой волны.","это сопротивление, возникающее при движении тела по поверхности другого тела.","Перемешивание воздуха в атмосфере","это сила, приходящаяся на единицу площади.",
            "это форма энергии, связанная с кинетической энергией частиц вещества.","это сопротивление, возникающее при движении тела по поверхности другого тела.","это колебания упругих сред, передающиеся от источника к приемнику в виде звуковой волны.","это способность материала передавать тепло.",
            "это область, в которой магнитное воздействие ощущается на других магнитных объектах.","это направленное движение заряженных частиц в проводнике."," это устройство, создающее магнитное поле при прохождении электрического тока.","это передача энергии в виде электромагнитных волн.",




    };
    String[] correct_list = {"динамометр","барометр","кг","Па","Дж","это пространство, занимаемое телом.","это количество вещества в теле.","это сила, приходящаяся на единицу площади.","это колебания упругих сред, передающиеся от источника к приемнику в виде звуковой волны.","это передача энергии в виде электромагнитных волн."};


    TextView cpt_question , text_question;
    Button btn_choose1 , btn_choose2 , btn_choose3 , btn_choose4 ,  btn_next;


    int currentQuestion =  0  ;
    int scorePlayer =  0  ;
    boolean isclickBtn = false;
    String valueChoose = "";
    Button btn_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        cpt_question = findViewById(R.id.cpt_question);
        text_question = findViewById(R.id.text_question);

        btn_choose1 = findViewById(R.id.btn_choose1);
        btn_choose2 = findViewById(R.id.btn_choose2);
        btn_choose3 = findViewById(R.id.btn_choose3);
        btn_choose4 = findViewById(R.id.btn_choose4);

        btn_next = findViewById(R.id.btn_next);

        findViewById(R.id.image_back).setOnClickListener(
                a-> finish()
        );
        remplirData();
        btn_next.setOnClickListener(
                view -> {
                        if (isclickBtn){
                            isclickBtn = false;

                            if(!valueChoose.equals(correct_list[currentQuestion])){
                                Toast.makeText(playActivity.this , "error",Toast.LENGTH_LONG).show();
                                btn_click.setBackgroundResource(R.drawable.background_btn_erreur);

                            }else {
                                Toast.makeText(playActivity.this , "correct",Toast.LENGTH_LONG).show();
                                btn_click.setBackgroundResource(R.drawable.background_btn_correct);

                                scorePlayer++;
                            }
                            new Handler().postDelayed(() -> {
                                if(currentQuestion!=question_list.length-1){
                                    currentQuestion = currentQuestion + 1;
                                    remplirData();
                                    valueChoose = "";
                                    btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);

                                }else {
                                    Intent intent  = new Intent(playActivity.this , ResulteActivity.class);
                                    intent.putExtra("Result" , scorePlayer);
                                    startActivity(intent);
                                    finish();
                                }

                            },2000);

                        }else {
                            Toast.makeText(playActivity.this ,  "You must choose one.",Toast.LENGTH_LONG).show();
                        }
                }
        );


    }

    void remplirData(){
        cpt_question.setText((currentQuestion+1) + "/" + question_list.length);
        text_question.setText(question_list[currentQuestion]);

        btn_choose1.setText(choose_list[4 * currentQuestion]);
        btn_choose2.setText(choose_list[4 * currentQuestion+1]);
        btn_choose3.setText(choose_list[4 * currentQuestion+2]);
        btn_choose4.setText(choose_list[4 * currentQuestion+3]);

    }

    public void ClickChoose(View view) {
        btn_click = (Button)view;

        if (isclickBtn) {
            btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);
        }
        chooseBtn();


    }
    void chooseBtn(){

        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color);
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }
}