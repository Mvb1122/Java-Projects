// Generated by view binder compiler. Do not edit!
package com.main.strigoi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.main.strigoi.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDashboardBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FrameLayout dashboardFragment;

  @NonNull
  public final TextView loadingText;

  private FragmentDashboardBinding(@NonNull ConstraintLayout rootView,
      @NonNull FrameLayout dashboardFragment, @NonNull TextView loadingText) {
    this.rootView = rootView;
    this.dashboardFragment = dashboardFragment;
    this.loadingText = loadingText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDashboardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_dashboard, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDashboardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.dashboardFragment;
      FrameLayout dashboardFragment = ViewBindings.findChildViewById(rootView, id);
      if (dashboardFragment == null) {
        break missingId;
      }

      id = R.id.loadingText;
      TextView loadingText = ViewBindings.findChildViewById(rootView, id);
      if (loadingText == null) {
        break missingId;
      }

      return new FragmentDashboardBinding((ConstraintLayout) rootView, dashboardFragment,
          loadingText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
