package com.example.datasender;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datasender.WebSocket.WebSocketServerService;
import com.example.datasender.databinding.ActivityMainBinding;

import org.java_websocket.server.WebSocketServer;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private WebSocketServer server;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        server = new WebSocketServerService();
        server.start();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = binding.etData.getText().toString();
                server.broadcast(message);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}