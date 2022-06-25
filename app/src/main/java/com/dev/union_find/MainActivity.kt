package com.dev.union_find

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dev.union_find.Fragments.CheckFragment
import com.dev.union_find.databinding.ActivityMainBinding
import com.dev.union_find.model.quickFind

class MainActivity : AppCompatActivity() {
    var obj = quickFind()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFrag(CheckFragment())
    }

    fun replaceFrag(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.viewPager, fragment)
        fragmentTransaction.commit()
    }

}