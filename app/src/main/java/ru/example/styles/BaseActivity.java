package ru.example.styles;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    //имя настроек
    private static final String NameSharedPreference = "LOGIN";

    //имя параметра в настройках
    private static final String AppTheme = "APP_THEME";

    static final int MyUglyCodeStyle = 0;
    static final int AppThemeLightCodeStyle = 1;
    static final int AppThemeCodeStyle = 2;
    static final int AppThemeDarkCodeStyle = 3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.MyUglyStyle));
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
    int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        //читаем тему, если настройки нет, берется по умолчанию
        return sharedPref.getInt(AppTheme, codeStyle);
    }

    //сохранение настроек
    void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        //настройки сохраняются через спецкласс editor
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(AppTheme, codeStyle);
        editor.apply();
    }
}
