package info.EventTracker.firebase;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class holder extends RecyclerView.ViewHolder {

static View view;
public holder(@NonNull View itemView) {
        super(itemView);

        view = itemView;
        }

public static void  setView(Context context, String EventName, String CollegeName,
                            String date, String description){

        TextView EventNames= view.findViewById(R.id.EventNames);
        TextView CollegeNames= view.findViewById(R.id.CollegeNames);
        TextView dates= view.findViewById(R.id.dates);
        TextView descriptions= view.findViewById(R.id.descriptions);


        EventNames.setText(EventName);
        CollegeNames.setText(CollegeName);
        dates.setText(date);
        descriptions.setText(description);

        }
}


