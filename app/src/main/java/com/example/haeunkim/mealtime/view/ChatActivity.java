package com.example.haeunkim.mealtime.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;

import com.example.haeunkim.mealtime.R;
import com.example.haeunkim.mealtime.databinding.ChatBinding;
import com.example.haeunkim.mealtime.model.ChatService;
import com.example.haeunkim.mealtime.viewmodel.ChatAdapter;
import com.example.haeunkim.mealtime.viewmodel.ChatViewModel;

public class ChatActivity extends AppCompatActivity {

    private ChatBinding binding;
    private ChatViewModel viewModel;

    RecyclerView recyclerChat;
    ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.initDataBinding();

       // Set scrolling down automatically
        scroll = binding.scrollChat;
        scrollToBottom();

        viewModel.onCreate();

    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.chat);
        recyclerChat = binding.recycleChat;
        viewModel = new ChatViewModel(this, this.recyclerChat);
        binding.setViewModel(viewModel);
    }

    private void scrollToBottom() {
        this.scroll.post(() ->
                    scroll.fullScroll(ScrollView.FOCUS_DOWN));
    }

}
