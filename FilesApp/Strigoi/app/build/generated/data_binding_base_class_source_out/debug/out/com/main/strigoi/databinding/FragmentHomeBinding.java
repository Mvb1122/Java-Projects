// Generated by view binder compiler. Do not edit!
package com.main.strigoi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.main.strigoi.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FrameLayout contentFragment;

  @NonNull
  public final FloatingActionButton createSeriesButton;

  @NonNull
  public final Button editUserButton;

  @NonNull
  public final TextView userInfoText;

  @NonNull
  public final ConstraintLayout userLayout;

  private FragmentHomeBinding(@NonNull ConstraintLayout rootView,
      @NonNull FrameLayout contentFragment, @NonNull FloatingActionButton createSeriesButton,
      @NonNull Button editUserButton, @NonNull TextView userInfoText,
      @NonNull ConstraintLayout userLayout) {
    this.rootView = rootView;
    this.contentFragment = contentFragment;
    this.createSeriesButton = createSeriesButton;
    this.editUserButton = editUserButton;
    this.userInfoText = userInfoText;
    this.userLayout = userLayout;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.contentFragment;
      FrameLayout contentFragment = rootView.findViewById(id);
      if (contentFragment == null) {
        break missingId;
      }

      id = R.id.createSeriesButton;
      FloatingActionButton createSeriesButton = rootView.findViewById(id);
      if (createSeriesButton == null) {
        break missingId;
      }

      id = R.id.editUserButton;
      Button editUserButton = rootView.findViewById(id);
      if (editUserButton == null) {
        break missingId;
      }

      id = R.id.userInfoText;
      TextView userInfoText = rootView.findViewById(id);
      if (userInfoText == null) {
        break missingId;
      }

      ConstraintLayout userLayout = (ConstraintLayout) rootView;

      return new FragmentHomeBinding((ConstraintLayout) rootView, contentFragment,
          createSeriesButton, editUserButton, userInfoText, userLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
