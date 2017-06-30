package com.example.macas.appcadastro;


//******************************************************
 //Instituto Federal de São Paulo - Campus Sertãozinho
 //Disciplina......: M4DADM
 //Programação de Computadores e Dispositivos Móveis
 //Aluno...........: Renata Maçãs
 //******************************************************



import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;



public class Main2Activity extends AppCompatActivity {

    private DBHelper dh;
    EditText etNome, etEndereco, etEmpresa;
    Button btinserir, btlistar, btTelaPrincipal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.dh = new DBHelper(this);
        etNome = (EditText) findViewById(R.id.etNome);
        etEndereco = (EditText) findViewById(R.id.etEndereco);
        etEmpresa = (EditText) findViewById(R.id.etEmpresa);

        btinserir = (Button) findViewById(R.id.btinserir);
        btlistar = (Button) findViewById(R.id.btlistar);
        btTelaPrincipal = (Button) findViewById(R.id.btTelaPrincipal);

        btinserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNome.getText().length()>0 && etEndereco.getText().length()>0 && etEmpresa.getText().length()>0){
                    dh.insert(etNome.getText().toString(), etEndereco.getText().toString(), etEmpresa.getText().toString());
                    AlertDialog.Builder adb = new AlertDialog.Builder(Main2Activity.this);
                    adb.setTitle("Sucesso");
                    adb.setMessage("Cadastro Realizado");
                    adb.show();

                    etNome.setText(" ");
                    etEndereco.setText(" ");
                    etEmpresa.setText(" ");
                }
                else{
                    AlertDialog.Builder adb = new AlertDialog.Builder(Main2Activity.this);
                    adb.setTitle("Erro");
                    adb.setMessage("Cadastro Não Realizado todos os campos devem ser preenchidos");
                    adb.show();
                }

            }
        });

        btlistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Contato> contatos = dh.queryGetAll();
                if (contatos == null) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Main2Activity.this);
                    adb.setTitle("Erro");
                    adb.setMessage("Não há registros");
                    adb.show();
                    return;
                }
                for (int i=0; i<contatos.size(); i++) {
                    Contato contato = (Contato) contatos.get(i);
                    AlertDialog.Builder adb = new AlertDialog.Builder(Main2Activity.this);
                    adb.setTitle("Registro " + i);
                    adb.setMessage("Nome: "+ contato.getNome()+"\n E-mail: "+ contato.getEndereco()+"\n CPF: "+ contato.getEmpresa());
                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    adb.show();
                }
            }
        });

        btTelaPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaPrincipal();
            }

            void chamaTelaPrincipal() {
                Intent intent = new Intent();
                intent.setClass(Main2Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
