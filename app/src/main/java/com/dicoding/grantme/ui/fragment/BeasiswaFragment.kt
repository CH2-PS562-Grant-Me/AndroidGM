package com.dicoding.grantme.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.dicoding.grantme.R
import com.dicoding.grantme.ui.adapter.SectionPagerAdapter
import com.dicoding.grantme.ui.fragment.tagfragment.BantuanFragment
import com.dicoding.grantme.ui.fragment.tagfragment.OrganisasiFragment
import com.dicoding.grantme.ui.fragment.tagfragment.PemerintahFragment
import com.dicoding.grantme.ui.fragment.tagfragment.PrestasiFragment
import com.dicoding.grantme.ui.fragment.tagfragment.SemuaFragment
import com.dicoding.grantme.ui.fragment.tagfragment.SwastaFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class BeasiswaFragment : Fragment() {
    var myFragment: View? = null
    var viewPager: ViewPager? = null
    var tabLayout: TabLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_beasiswa, container, false)
        viewPager = myFragment!!.findViewById(R.id.myPagerView)
        tabLayout = myFragment!!.findViewById(R.id.tabs)
        return myFragment
    }

    //Call onActivity Create method
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViewPager(viewPager)
        tabLayout!!.setupWithViewPager(viewPager)
        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {}
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setUpViewPager(viewPager: ViewPager?) {
        val adapter = SectionPagerAdapter(childFragmentManager)
        adapter.addFragment(SemuaFragment(), "Semua")
        adapter.addFragment(PemerintahFragment(), "Pemerintah")
        adapter.addFragment(SwastaFragment(), "Swasta")
        adapter.addFragment(OrganisasiFragment(), "Organisasi")
        adapter.addFragment(PrestasiFragment(), "Prestasi")
        adapter.addFragment(BantuanFragment(), "Bantuan")
        viewPager!!.adapter = adapter
    }

    companion object {
        val instance: BeasiswaFragment
            get() = BeasiswaFragment()
    }
}
