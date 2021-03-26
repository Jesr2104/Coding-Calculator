// Generated by view binder compiler. Do not edit!
package com.just_jump.coding_calculator.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.just_jump.coding_calculator.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogInfoRuleOfThreeInverseBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView Title;

  @NonNull
  public final ImageView exampleRuleOfThree;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final TextView textExplanation;

  private DialogInfoRuleOfThreeInverseBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView Title, @NonNull ImageView exampleRuleOfThree, @NonNull ImageView imageView,
      @NonNull TextView textExplanation) {
    this.rootView = rootView;
    this.Title = Title;
    this.exampleRuleOfThree = exampleRuleOfThree;
    this.imageView = imageView;
    this.textExplanation = textExplanation;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogInfoRuleOfThreeInverseBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogInfoRuleOfThreeInverseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_info_rule_of_three_inverse, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogInfoRuleOfThreeInverseBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Title;
      TextView Title = rootView.findViewById(id);
      if (Title == null) {
        break missingId;
      }

      id = R.id.exampleRuleOfThree;
      ImageView exampleRuleOfThree = rootView.findViewById(id);
      if (exampleRuleOfThree == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = rootView.findViewById(id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.textExplanation;
      TextView textExplanation = rootView.findViewById(id);
      if (textExplanation == null) {
        break missingId;
      }

      return new DialogInfoRuleOfThreeInverseBinding((ConstraintLayout) rootView, Title,
          exampleRuleOfThree, imageView, textExplanation);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}