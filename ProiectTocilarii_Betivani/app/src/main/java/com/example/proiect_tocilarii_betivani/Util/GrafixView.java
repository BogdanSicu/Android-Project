package com.example.proiect_tocilarii_betivani.Util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.proiect_tocilarii_betivani.R;

import java.util.List;

public class GrafixView extends View {
    private Context context;
    private Paint paint;
    private List<Rates> rates;


    public GrafixView(Context context, List<Rates> rates) {
        super(context);

        this.context = context;
        this.paint = new Paint();
        paint.setTextSize(50);
        this.rates = rates;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float barHeight = 50;
        float max = 0;
        for(int i = 0; i < rates.size(); i++){
            max = rates.get(i).getCreditRate() > max ? rates.get(i).getCreditRate():max;
            max = rates.get(i).getDepositRate() > max ? rates.get(i).getDepositRate():max;
        }

        //Trasare bare
        int curent = 0;
        int space = 200 ;

        int creditColor = Color.rgb(110, 10, 40);
        int depositColor = Color.rgb(20, 5, 90);

        paint.setColor(creditColor);
        canvas.drawRect(50, 50, 100, 100, paint);
        paint.setColor(Color.BLACK);
        canvas.drawText(getContext().getString(R.string.grafx_credit_rate), 110, 95, paint);

        paint.setColor(depositColor);
        canvas.drawRect(450, 50, 500, 100, paint);
        paint.setColor(Color.BLACK);
        canvas.drawText(getContext().getString(R.string.grafix_deposit_rate), 510, 95, paint);

        for (Rates rate : rates) {
            float credit = rate.getCreditRate();
            float deposit = rate.getDepositRate();

            float x1 = 20;
            float y1 = curent*space + 200;

            float x2c = (credit/max)*(getWidth()-20);
            float y1c = y1 + 5;
            float y2c  = y1c + barHeight;

            float x2d = (deposit/max)*(getWidth()-20);
            float y1d = y2c + 5;
            float y2d  = y1d + barHeight;

            paint.setColor(Color.BLACK);
            canvas.drawText(rate.getBankName(), x1, y1, paint);

            paint.setColor(creditColor);
            canvas.drawRect(x1, y1c, x2c, y2c, paint);

            paint.setColor(depositColor);
            canvas.drawRect(x1, y1d, x2d, y2d, paint);

            paint.setColor(Color.WHITE);
            canvas.drawText(String.valueOf(credit), x1+5, y2c-5, paint);
            canvas.drawText(String.valueOf(deposit), x1+5, y2d-5, paint);

            curent++;
        }
    }
}
