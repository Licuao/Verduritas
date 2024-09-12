package cl.inacap.verduritas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListaVerdurasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_verduras);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //rescatar datos de la pagina anterior
        Intent contexto = getIntent();

        String fecha = contexto.getStringExtra("fecha");
        String verdura = contexto.getStringExtra("verdura");
        //pintar datos pagina anterior

            //rescatar textView
        TextView texto = findViewById(R.id.textView);
        //pintar el texto en el textView;

        texto.setText("El"+ verdura+"podra cosecharse en"+fecha);

    }
}