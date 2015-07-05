package test.fragmentonactivityresultusingkotlin;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PaletteTestActivity extends AppCompatActivity {

    private ImageView mRgbImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette_test);

        mRgbImageView = (ImageView) findViewById(R.id.rgb_imageview);

        setRgbInput();
    }

    private void setRgbInput() {
        final EditText r = (EditText) findViewById(R.id.r);
        final EditText g = (EditText) findViewById(R.id.g);
        final EditText b = (EditText) findViewById(R.id.b);

        TextWatcher rgbTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String inputString = editable.toString();
                if(!TextUtils.isEmpty(inputString)){
                    int input = Integer.valueOf(editable.toString());
                    if(input <= 255){
                        String rString = r.getText().toString();
                        String gString = g.getText().toString();
                        String bString = b.getText().toString();

                        int rValue = !TextUtils.isEmpty(rString) ? Integer.valueOf(rString) : 0;
                        int gValue = !TextUtils.isEmpty(gString) ? Integer.valueOf(gString) : 0;
                        int bValue = !TextUtils.isEmpty(bString) ? Integer.valueOf(bString) : 0;
                        updateImageWithRgbValues(rValue, gValue, bValue);
                        return;
                    } else {
                        EditText updatingEditText = null;
                        if(editable == r.getEditableText()){
                            updatingEditText = r;
                        } else if (editable == g.getEditableText()){
                            updatingEditText = g;
                        } else if (editable == b.getEditableText()){
                            updatingEditText = b;
                        }
                        updatingEditText.setText(editable.delete(editable.length() - 1, editable.length()));
                    }
                }
            }
        };

        r.addTextChangedListener(rgbTextWatcher);
        g.addTextChangedListener(rgbTextWatcher);
        b.addTextChangedListener(rgbTextWatcher);
    }

    private void setViewSwatch(TextView textView, Palette.Swatch swatch){
        if(swatch != null) {
            textView.setTextColor(swatch.getTitleTextColor());
            textView.setBackgroundColor(swatch.getRgb());
        } else {
            textView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            textView.setTextColor(getResources().getColor(android.R.color.black));
        }
    }

    private void updateImageWithRgbValues(int r, int g, int b){
        Bitmap myBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        myBitmap.eraseColor(Color.rgb(r,g,b));
        mRgbImageView.setImageDrawable(new BitmapDrawable(getApplicationContext().getResources(), myBitmap));
        updatePalette(myBitmap);
    }

    private void updatePalette(Bitmap bitmap){
        final TextView vibrantTv = (TextView) findViewById(R.id.vibrant_tv);
        final TextView lightVibrantTv = (TextView) findViewById(R.id.light_vibrant_tv);
        final TextView darkVibrantTv = (TextView) findViewById(R.id.dark_vibrant_tv);
        final TextView mutedTv = (TextView) findViewById(R.id.muted_tv);
        final TextView lightMutedTv = (TextView) findViewById(R.id.light_muted_tv);
        final TextView darkMutedTv = (TextView) findViewById(R.id.dark_muted_tv);

        Palette.from(bitmap).generate( new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                setViewSwatch(vibrantTv, palette.getVibrantSwatch() );
                setViewSwatch(lightVibrantTv, palette.getLightVibrantSwatch() );
                setViewSwatch(darkVibrantTv, palette.getDarkVibrantSwatch() );
                setViewSwatch(mutedTv, palette.getMutedSwatch() );
                setViewSwatch(lightMutedTv, palette.getLightMutedSwatch() );
                setViewSwatch(darkMutedTv, palette.getDarkMutedSwatch() );
            }
        });
    }
}
