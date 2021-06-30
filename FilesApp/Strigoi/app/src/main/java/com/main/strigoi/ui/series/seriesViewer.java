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

public class seriesViewer extends Fragment {
    private seriesViewModel seriesViewModel;
    private FragmentSeriesViewBinding binding;
    private Activity activity;
    private Bitmap image;
    private getSeriesInfo getInfo;
    private int strigoiNum;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public seriesViewer(int strigoiNum) {
        this.strigoiNum = strigoiNum;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = getActivity();

        seriesViewModel = new ViewModelProvider(this).get(seriesViewModel.class);

        binding = FragmentSeriesViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView seriesName = binding.seriesName;
        TextView authorName = binding.authorName;
        TextView creationDate = binding.dateCreated;
        TextView strigoiNumText = binding.strigoiNumber;
        strigoiNumText.setText("#" + strigoiNum);
        ImageView seriesImage = binding.seriesImage;

        Thread setSeriesInfo = new Thread(() -> {
            getInfo = new getSeriesInfo(strigoiNum);

            getInfo.run();
            try {
                URL url = new URL(getInfo.imageURL);
                image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                activity.runOnUiThread(() -> {
                    seriesImage.setImageBitmap(image);
                    seriesName.setText(getInfo.seriesName);
                    authorName.setText(getInfo.creatorName);
                    creationDate.setText(getInfo.creationDate);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Reset View once every so often.

            while (true) {
                if (binding != null) {
                    activity.runOnUiThread(() -> {
                        seriesImage.setImageBitmap(image);
                        seriesName.setText(getInfo.seriesName);
                        authorName.setText(getInfo.creatorName);
                        creationDate.setText(getInfo.creationDate);
                    });
                }

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

             */
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
