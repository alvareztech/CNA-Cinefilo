package tech.alvarez.cinefilo.util;

import android.util.Patterns;

import java.util.regex.Pattern;

public class Util {

    public static boolean esNombreValido(String name) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        return patron.matcher(name).matches();
    }

    public static boolean esTelefonoValido(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    public static boolean esEmailValido(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
