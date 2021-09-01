package ru.example.styles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity extends BaseActivity {

    /*    //имя настроек
        private static final String NameSharedPreference = "LOGIN";

        //имя параметра в настройках
        private static final String AppTheme = "APP_THEME";

        private static final int MyUglyCodeStyle = 0;
        private static final int AppThemeLightCodeStyle = 1;
        private static final int AppThemeCodeStyle = 2;
        private static final int AppThemeDarkCodeStyle = 3;*/
    Pattern checkLogin = Pattern.compile("^[A-Z][a-z]{2,}$"); //Первая буква большая латинская, остальные маленькие латинские
    Pattern checkPassword = Pattern.compile("^(?=^.{6,}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$"); //, минимум 6 символов, обязательны маленькая буква, большая буква, цифра

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //тема ставится до макета активити
        /*setTheme(getAppTheme(R.style.MyUglyStyle));*/
        setContentView(R.layout.activity_main);

        initThemeChooser();
        initText();
    }

    private void initText() {
        TextInputEditText login = findViewById(R.id.inputLoginName);
        TextInputEditText password = findViewById(R.id.inputPassword);
        final TextInputLayout layoutLogin = findViewById(R.id.loginName);
        final TextInputLayout layoutPassword = findViewById(R.id.password);

        //проверка при потете фокуса
        login.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                TextView tv = (TextView) v;
                String value = tv.getText().toString();
                if (checkLogin.matcher(value).matches()) {  //проверка по регулярке
                    tv.setError(null);
                } else {
                    tv.setError(getString(R.string.not_name));
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                TextView tv = (TextView) v;
                String value = tv.getText().toString();
                if (checkPassword.matcher(value).matches()) {
                    layoutPassword.setError(null);
                } else {
                    layoutPassword.setError(getString(R.string.weak_password));
                }
            }
        });
    }

    private void initThemeChooser() { //инициализация кнопок для выбора тем
        initRadioButton(findViewById(R.id.radioButtonMyUglyStyle), MyUglyCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonMaterialDark), AppThemeDarkCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonMaterialLight), AppThemeLightCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonMaterialLightDarkAction), AppThemeCodeStyle);

        RadioGroup rg = findViewById(R.id.radioButtons);
        ((MaterialRadioButton) rg.getChildAt(getCodeStyle(MyUglyCodeStyle))).setChecked(true);
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

   /* private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }*/

    /*//метод возвращает айди темы по входящему инту
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
    }*/
}