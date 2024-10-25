package cl.inacap.verduritas;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailField, passwordField;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // El diseño que crearemos luego

        mAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                signIn(email, password);
            }
        });
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login exitoso, redirigir al usuario a la actividad principal
                            Intent intent = new Intent(LoginActivity.this, ListaCultivosActivity.class);
                            startActivity(intent);
                            finish(); // Cerrar la actividad de login
                        } else {
                            // Si el login falla, mostrar mensaje
                            Toast.makeText(LoginActivity.this, "Authentication Failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
