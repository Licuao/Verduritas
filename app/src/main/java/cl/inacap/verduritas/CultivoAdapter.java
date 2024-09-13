package cl.inacap.verduritas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CultivoAdapter extends ArrayAdapter<Cultivo> {
    private final DatabaseHelper databaseHelper;

    public CultivoAdapter(Context context, ArrayList<Cultivo> cultivos, DatabaseHelper dbHelper) {
        super(context, 0, cultivos);
        this.databaseHelper = dbHelper;
    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Cultivo cultivo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_cultivo, parent, false);
        }

        // Referencias a los elementos de la vista
        TextView nombreTextView = convertView.findViewById(R.id.nombreTextView);
        TextView fechaCosechaTextView = convertView.findViewById(R.id.fechaCosechaTextView);
        Button btnEliminar = convertView.findViewById(R.id.btnEliminar);

        // Configurar los datos
        assert cultivo != null;
        nombreTextView.setText(cultivo.getTipo());
        fechaCosechaTextView.setText("Fecha de Cosecha: " + new SimpleDateFormat("dd/MM/yyyy").format(cultivo.getFechaCosecha()));

        // Manejar el evento de eliminación
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Eliminar el cultivo de la base de datos
                databaseHelper.eliminarCultivo(cultivo);

                // Remover el cultivo de la lista y notificar el cambio
                remove(cultivo);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}