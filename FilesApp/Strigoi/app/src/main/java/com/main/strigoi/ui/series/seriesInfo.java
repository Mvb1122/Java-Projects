package com.main.strigoi.ui.series;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.main.strigoi.databinding.FragmentSeriesViewBinding;
import com.main.strigoi.mDB.getSeriesInfo;

import java.io.IOException;
import java.net.URL;

public class seriesInfo extends Fragment {
    private seriesViewModel seriesViewModel;
    private FragmentSeriesViewBinding binding;
    private Activity activity;
    private int strigoiNum;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public seriesInfo(int strigoiNum) {
        this.strigoiNum = strigoiNum;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = getActivity();

        seriesViewModel = new ViewModelProvider(this).get(seriesViewModel.class);

        binding = FragmentSeriesViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView seriesName = binding.seriesName;
        TextView authorName = binding.authorName;
        TextView creationDate = binding.dateCreated;
        ImageView seriesImage = binding.seriesImage;

        Thread setSeriesInfo = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                getSeriesInfo getInfo = new getSeriesInfo(strigoiNum);
                getInfo.run();
                try {
                    // TODO: Adjust seriesInfo.json schema to hold a URL for series icon.
                    URL url = new URL("https://ihaveawebsite.tk/favicon.ico");
                    Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    activity.runOnUiThread(() -> {
                        seriesImage.setImageBitmap(image);
                        seriesName.setText(getInfo.seriesName);
                        authorName.setText(getInfo.creatorName);
                        creationDate.setText(getInfo.creationDate);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        setSeriesInfo.start();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
