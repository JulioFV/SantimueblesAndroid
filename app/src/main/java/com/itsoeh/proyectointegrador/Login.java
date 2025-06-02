package com.itsoeh.proyectointegrador;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.itsoeh.proyectointegrador.modelo.MUsuario;
import com.itsoeh.proyectointegrador.volley.VolleySingleton;
import com.itsoeh.proyectointegrador.volley.API;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {
    private NavController navegador;
    private Bundle paquete;
   private MUsuario objUsuario;
    private CardView btnEntrar;
    private EditText txtUsuario,txtContrasenia;
    private String usuario, contrasenia;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navegador = Navigation.findNavController(view);
        btnEntrar=view.findViewById(R.id.loginButton);
        txtContrasenia=view.findViewById(R.id.Login_txt_Contrasenia);
        txtUsuario=view.findViewById(R.id.login_txtNombre);

        paquete = new Bundle();
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validarCampos(usuario,contrasenia);
                clicEntrar();
            }
        });



    }
    private void clicEntrar() {
        String correo = this.txtUsuario.getText().toString();
        this.clicLogin(correo);
    }

 /*   private void validarCampos (String usuario, String contrasenia){
        if (!usuario.isEmpty() && !contrasenia.isEmpty()) {
            String correou =this.txtUsuario.getText().toString();
            String contraseniau = this.txtContrasenia.getText().toString();
            this.clicLogin(correo,contrasenia);
        } else {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Error")
                    .setMessage("Por favor, rellena todos los campos")
                    .setPositiveButton("Aceptar", null)
                    .show();
        }
    }*/
 private void clicLogin(String correo) {
     // Para mostrar diálogos de error convenientemente:
     AlertDialog.Builder msg = new AlertDialog.Builder(this.getContext());

     // 1. Mostrar un ProgressDialog (diálogo de “cargando”)
     ProgressBar progressBar = new ProgressBar(this.getContext());
     progressBar.setIndeterminate(true);
     AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
     builder.setTitle("Por favor, espera");
     builder.setMessage("Conectándose con el servidor...");
     builder.setView(progressBar);
     builder.setCancelable(false);
     AlertDialog dialog = builder.create();
     dialog.show();

     RequestQueue colaDeSolicitudes = VolleySingleton
             .getInstance(this.getContext())
             .getRequestQueue();

     StringRequest solicitud = new StringRequest(
             Request.Method.POST,
             API.LOGIN,
             new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {
                     dialog.dismiss(); // cerramos el diálogo de “cargando”
                     try {
                         Log.e("PASO 1", response);

                         JSONObject contenido = new JSONObject(response);

                         // 2. Validar campo "error"
                         boolean huboError = contenido.optBoolean("error", true);
                         if (huboError) {
                             mostrarDialogoError("Error", contenido.optString("aviso", "Error en el servidor"));
                             return;
                         }

                         // 3. Obtener el JSONArray "contenido" y verificar longitud
                         JSONArray array = contenido.optJSONArray("contenido");
                         if (array == null || array.length() == 0) {
                             mostrarDialogoError("Error", "El usuario no existe");
                             return;
                         }

                         // 4. Si sí hay al menos un elemento, parseamos el primero
                         //    (asumiendo que sólo hay un usuario para ese correo)
                         JSONObject pos = array.getJSONObject(0);
                         MUsuario obj = new MUsuario();
                         obj.setId_trabajador(pos.optInt("id_trabajador", -1));
                         obj.setNombre(pos.optString("nombre", ""));
                         obj.setNumTrabajador(pos.optString("num_trabajador", ""));
                         obj.setCorreo(pos.optString("correo", ""));
                         obj.setContrasenia(pos.optString("contrasenia", ""));

                         // 5. Comparar la contraseña
                         String passIngresada = txtContrasenia.getText().toString().trim();
                         if (passIngresada.isEmpty()) {
                             mostrarDialogoError("Error", "Debes ingresar contraseña");
                             return;
                         }

                         if (passIngresada.equals(obj.getContrasenia())) {
                             // Asegúrate de que 'paquete' y 'navegador' estén inicializados
                             Bundle paquete = new Bundle();
                             paquete.putSerializable("user", obj);

                             navegador.navigate(R.id.action_login_to_menu, paquete);
                         } else {
                             mostrarDialogoError("Error", "Contraseña incorrecta");
                         }

                     } catch (Exception ex) {
                         Log.e("PASO 5", ex.getMessage(), ex);
                         mostrarDialogoError("Error", "La información no se pudo leer");
                     }
                 }
             },
             new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {
                     dialog.dismiss();
                     mostrarDialogoError("Error", "No se pudo conectar con el servidor");
                 }
             }
     ) {
         @Override
         protected Map<String, String> getParams() {
             Map<String, String> param = new HashMap<>();
             param.put("correo", correo);
             return param;
         }
     };

     // 6. Enviar la solicitud Volley
     colaDeSolicitudes.add(solicitud);
 }

    /**
     * Método auxiliar para mostrar un AlertDialog de error con un mensaje dado.
     */
    private void mostrarDialogoError(String titulo, String mensaje) {
        AlertDialog.Builder msg = new AlertDialog.Builder(this.getContext());
        msg.setTitle(titulo);
        msg.setMessage(mensaje);
        msg.setPositiveButton("Aceptar", null);
        AlertDialog dialogError = msg.create();
        dialogError.show();
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}