package com.raafat.progresshud;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramijemli.percentagechartview.PercentageChartView;

public class ProgressHUD extends Dialog {

	public static enum Style{
		PERCENT, INDETERMINATE
	}

	public ProgressHUD(Context context) {
		super(context);
	}

	public ProgressHUD(Context context, int theme) {
		super(context, theme);
	}

	public void onWindowFocusChanged(boolean hasFocus){
		try {
			ImageView imageView =  findViewById(R.id.spinnerImageView);
			AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
			spinner.start();
		}catch (ClassCastException e){
			e.printStackTrace();
		}
    }
	
	public void setMessage(CharSequence message) {
		if(message != null && message.length() > 0) {
			findViewById(R.id.message).setVisibility(View.VISIBLE);
			TextView txt = findViewById(R.id.message);
			txt.setText(message);
			txt.invalidate();
		}
	}

	public void setStyle(Style style,int currentPercent,int totlatPercent){
		if(style == Style.PERCENT){
			findViewById(R.id.view_id).setVisibility(View.VISIBLE);
			findViewById(R.id.spinnerImageView).setVisibility(View.GONE);
			PercentageChartView percentageChartView = findViewById(R.id.view_id);
			percentageChartView.setProgress((float)totlatPercent,true);
		}else{
			findViewById(R.id.view_id).setVisibility(View.GONE);
			findViewById(R.id.spinnerImageView).setVisibility(View.VISIBLE);

		}
	}

	public void done(){
		findViewById(R.id.spinnerImageView).setBackgroundResource(R.drawable.ic_done);
		setStyle(ProgressHUD.Style.INDETERMINATE,0,0);
		setMessage("Done!!");

	}

	public void error(String msg){
		findViewById(R.id.spinnerImageView).setBackgroundResource(R.drawable.ic_error);
		setStyle(ProgressHUD.Style.INDETERMINATE,0,0);
		setMessage(msg);
	}
	
	public static ProgressHUD show(Context context, CharSequence message, boolean indeterminate, boolean cancelable,
			OnCancelListener cancelListener) {
		ProgressHUD dialog = new ProgressHUD(context,R.style.ProgressHUD);
		dialog.setTitle("");
		dialog.setContentView(R.layout.progress_hud);
		if(message == null || message.length() == 0) {
			dialog.findViewById(R.id.message).setVisibility(View.GONE);			
		} else {
			TextView txt = dialog.findViewById(R.id.message);
			txt.setText(message);
		}
		dialog.setCancelable(cancelable);
		dialog.setOnCancelListener(cancelListener);
		dialog.getWindow().getAttributes().gravity=Gravity.CENTER;
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();  
		lp.dimAmount=0.4f;
		dialog.getWindow().setAttributes(lp); 
		//dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		dialog.show();
		return dialog;
	}	
}
