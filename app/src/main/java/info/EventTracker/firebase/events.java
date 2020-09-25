package info.EventTracker.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class events extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference reference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.video_recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Events");

    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<user, holder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<user, holder>(

                        user.class,
                        R.layout.data,
                        holder.class,
                        reference
                ) {
                    @Override
                    protected void populateViewHolder(holder viewHolder, user user, int i) {

                        holder.setView(getApplicationContext(),
                                user.getEventName(),user.getCollegeName(),user.getDate(),user.getDescription());
                    }
                };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
