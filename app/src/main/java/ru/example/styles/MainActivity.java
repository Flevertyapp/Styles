package ru.example.styles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //имя настроек
    private static final String NameSharedPreference = "LOGIN";

    //имя параметра в настройках
    private static final String AppTheme = "APP_THEME";

    private static final int MyUglyCodeStyle = 0;
    private static final int AppThemeLightCodeStyle = 1;
    private static final int AppThemeCodeStyle = 2;
    private static final int AppThemeDarkCodeStyle = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //тема ставится до макета активити
        setTheme(getAppTheme(R.style.MyUglyStyle));
        setContentView(R.layout.activity_main);

        initThemeChooser();
    }

    private void initThemeChooser() { //инициализация кнопок для выбора тем
        initRadioButton(findViewById(R.id.radioButtonMyUglyStyle), MyUglyCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonMaterialDark), AppThemeDarkCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonMaterialLight), AppThemeLightCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonMaterialLightDarkAction), AppThemeCodeStyle);
    }

    //метод для инициализации кнопки передаем кнопку и инт темы
    private void initRadioButton(View button, final int codeStyle) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //сохраняем настройки
                setAppTheme(codeStyle);
                recreate();
            }
        });
    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    //метод возвращает айди темы по входящему инту
    private int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case AppThemeCodeStyle:
                return R.style.AppTheme;
            case AppThemeLightCodeStyle:
                return R.style.AppThemeLight;
            case AppThemeDarkCodeStyle:
                return R.style.AppThemeDark;
            default:
                return R.style.MyUglyStyle;
        }
    }

    //чтение настроек (тема)
    private int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        //читаем тему, если настройки нет, берется по умолчанию
        return sharedPref.getInt(AppTheme, codeStyle);
    }

    //сохранение настроек
    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        //настройки сохраняются через спецкласс editor
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(AppTheme, codeStyle);
        editor.apply();
    }
}