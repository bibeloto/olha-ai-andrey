package test.fragmentonactivityresultusingkotlin

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

/**
 * Created by renan on 02/07/15.
 */
public class MyFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.my_fragment, container, false)

        var button1: Button = root.findViewById(R.id.my_fragment_button) as Button
        button1.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(getActivity().getApplicationContext(), javaClass<Activity2>());
                startActivityForResult(intent, 123)
            }
        })

        var buttonPalete: Button = root.findViewById(R.id.my_fragment_palette_test_button) as Button
        buttonPalete.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(getActivity().getApplicationContext(), javaClass<PaletteTestActivity>());
                startActivityForResult(intent, 123)
            }
        })

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 123){
                Toast.makeText(getActivity(), "Fragment: onActivityResult called!", Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
