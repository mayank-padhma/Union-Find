package com.dev.union_find.Fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.dev.union_find.MainActivity
import com.dev.union_find.R
import com.dev.union_find.model.quickFind
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class CheckFragment : Fragment() {

    var ch = false
    lateinit var imageButton: ImageButton
    lateinit var firstNode: TextInputLayout
    lateinit var secondNode: TextInputLayout
    lateinit var textView: TextView
    lateinit var subText: TextView
    lateinit var checkButton: Button
    lateinit var input1: TextInputEditText
    lateinit var input2: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_check, container, false)
        // Inflate the layout for this fragment

        input1 = view.findViewById(R.id.input1)
        input2 = view.findViewById(R.id.input2)
        val obj = (activity as MainActivity).obj
        firstNode= view.findViewById(R.id.firstNode1)
        secondNode = view.findViewById(R.id.secondNode2)
        textView = view.findViewById(R.id.confirmText)
        subText = view.findViewById(R.id.subText)
        checkButton = view.findViewById(R.id.checkButton)
        imageButton = view.findViewById(R.id.addNew)
        imageButton.setOnClickListener{
            (activity as MainActivity).replaceFrag(AddFragment())
        }
        checkButton.setOnClickListener{
            var str1 = firstNode.editText?.text.toString()
            var str2 = secondNode.editText?.text.toString()
            var num1: Int = -1
            var num2: Int = -1
            if(str1!="" && str2!=""){
                num1 = str1.toInt()
                num2 = str2.toInt()
            }
            if(checkInput(num1, num2)){
                if(obj.isConnected(num1, num2))
                    textView.setText("($num1,$num2) are connected nodes")
                else
                    textView.setText("($num1,$num2) are not connected")
                textView.visibility = View.VISIBLE
                subText.visibility = View.VISIBLE
                checkButton.visibility = View.GONE
                secondNode.clearFocus()
                firstNode.clearFocus()
                ch = true
            }
        }
        input1.setOnFocusChangeListener { view, b->
            if(ch == true)
            reset()
            ch = false
        }
        input2.setOnFocusChangeListener{ view, b->
            if(ch == true)
                reset()
            ch = false
        }
        return view
    }

    private fun reset(){
        if(checkButton.visibility == View.GONE){
            textView.visibility = View.GONE
            subText.visibility = View.GONE
            checkButton.visibility = View.VISIBLE
            firstNode.editText?.text?.clear()
            secondNode.editText?.text?.clear()
        }
    }

    private fun checkInput(a: Int, b: Int) : Boolean{
        if(a!=null && b!=null && a<=20 && b<=20 && a>=0 && b>=0){
            return true;
        }else{
            var ch = false
            if(a==null || a>20 || a<0){
                ch = true
                firstNode.requestFocus()
            }
            if(b==null || b>20 || b<0){
                if(ch==false){
                    secondNode.requestFocus()
                }
            }
            Toast.makeText(context, "Please enter valid values", Toast.LENGTH_SHORT).show()
            return false;
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CheckFragment().apply {
            }
    }
}