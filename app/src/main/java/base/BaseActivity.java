package base;

import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
   protected AppCompatActivity activity;

    public BaseActivity() {
        activity = this;
    }

    public AlertDialog showMessage(String message, String posActionName){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);

        builder.setPositiveButton(posActionName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });

     return builder.show();
    }
    public AlertDialog showMessage(String message, String posActionName, DialogInterface.OnClickListener onPosAction){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(posActionName,onPosAction);

     return builder.show();
    }

    public AlertDialog showMessage(int message, int posActionName, DialogInterface.OnClickListener onPosAction,boolean isCancelable){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(posActionName,onPosAction);
        builder.setCancelable(isCancelable);

     return builder.show();
    }





}
