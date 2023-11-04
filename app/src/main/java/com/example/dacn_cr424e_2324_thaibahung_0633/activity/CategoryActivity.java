package com.example.dacn_cr424e_2324_thaibahung_0633.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dacn_cr424e_2324_thaibahung_0633.Adapter.CategoryAdapter;
import com.example.dacn_cr424e_2324_thaibahung_0633.DAO.CategoryDAO;
import com.example.dacn_cr424e_2324_thaibahung_0633.DAO.QuizDAO;
import com.example.dacn_cr424e_2324_thaibahung_0633.Model.Category;
import com.example.dacn_cr424e_2324_thaibahung_0633.R;
import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity implements CategoryAdapter.CategoryClicked {
    CategoryDAO categoryDAO;
    QuizDAO quizDAO;
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Category> categories;
    private ArrayList<Integer> quizzesHasDone;
    private ArrayList<Integer> quizzesDeadAndTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_category);
        categoryDAO = new CategoryDAO(this);
        quizDAO = new QuizDAO(this);

        categories = categoryDAO.getAllCategory();
        quizzesHasDone = quizDAO.countQuizHasDone();
        quizzesDeadAndTotal = quizDAO.countTotalQuiz();
        Log.d("quizzesDeadAndTotal", "abc:" + quizzesDeadAndTotal.size());
        recyclerView = findViewById(R.id.category_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new CategoryAdapter(this, categories, quizzesHasDone, quizzesDeadAndTotal);
        recyclerView.setAdapter(myAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Học lý thuyết");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClickCategory(int idCategory, String categoryContent) {
        Intent intent = new Intent(CategoryActivity.this, QuizPracticeActivity.class);
        intent.putExtra("idCategory", idCategory);
        intent.putExtra("categoryContent", categoryContent);
        startActivity(intent);
    }
}
