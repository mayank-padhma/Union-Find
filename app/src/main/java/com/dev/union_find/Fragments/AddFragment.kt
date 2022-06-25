package com.dev.union_find.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.dev.union_find.MainActivity
import com.dev.union_find.R
import com.dev.union_find.model.quickFind
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var firstNode: TextInputLayout
    lateinit var secondNode: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val obj = (activity as MainActivity).obj
        val view: View =  inflater.inflate(R.layout.fragment_add, container, false)
        val myToolbar : androidx.appcompat.widget.Toolbar = view.findViewById(R.id.myToolbar)
        val addButton : Button = view.findViewById(R.id.addButton)
        val addMore : Button = view.findViewById(R.id.addMore)
        var textView: TextView = view.findViewById(R.id.confirmText)
        firstNode = view.findViewById(R.id.firstNode)
        secondNode = view.findViewById(R.id.secondNode)
        (activity as MainActivity).setSupportActionBar(myToolbar)
        myToolbar.setNavigationOnClickListener {
            (activity as MainActivity).replaceFrag(CheckFragment())
        }
        addButton.setOnClickListener{
            var str1 = firstNode.editText?.text.toString()
            var str2 = secondNode.editText?.text.toString()
            var num1: Int = -1
            var num2: Int = -1
            if(str1!="" && str2!=""){
                num1 = str1.toInt()
                num2 = str2.toInt()
            }
            if(checkInput(num1, num2)){
                obj.union(num1, num2)
                textView.setText("($num1,$num2) are now connected")
                textView.visibility = View.VISIBLE
                addButton.visibility = View.GONE
                addMore.visibility = View.VISIBLE
                secondNode.clearFocus()
                secondNode.isEnabled = false
                firstNode.isEnabled = false
                firstNode.clearFocus()
            }
        }
        addMore.setOnClickListener{
            addMore.visibility = View.GONE
            addButton.visibility = View.VISIBLE
            textView.visibility = View.GONE
            firstNode.editText?.text?.clear()
            secondNode.editText?.text?.clear()
            secondNode.isEnabled = true
            firstNode.isEnabled = true
            firstNode.requestFocus()
        }

        return view
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

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}