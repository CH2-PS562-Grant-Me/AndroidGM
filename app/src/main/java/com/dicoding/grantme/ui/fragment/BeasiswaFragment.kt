package com.dicoding.grantme.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.grantme.databinding.FragmentBeasiswaBinding
import com.dicoding.grantme.di.Injection
import com.dicoding.grantme.ui.ViewModelFactory
import com.dicoding.grantme.ui.adapter.BeasiswaAdapter
import com.dicoding.grantme.ui.detailBeasiswa.DetailActivity
import com.dicoding.grantme.ui.main.MainActivity
import com.dicoding.grantme.ui.main.MainViewModel


class BeasiswaFragment : Fragment() {
    private lateinit var beaAdapter: BeasiswaAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentBeasiswaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBeasiswaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userRepository = Injection.provideRepository(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(userRepository))[MainViewModel::class.java]

        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            if (!user.isLogin) {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            } else {
                showList()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            if (user.isLogin) {
                viewModel.getAllScholarship("Bantuan")
            }
        }
    }

    private fun showList() {
        beaAdapter = BeasiswaAdapter { bea ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.SISA, bea.tanggalPendaftaran)
            intent.putExtra(DetailActivity.DIBUAT, bea.createdAt)
            intent.putExtra(DetailActivity.NAME, bea.nama)
            intent.putExtra(DetailActivity.LINK, bea.link)
            intent.putExtra(DetailActivity.DESCRIPTION, bea.deskripsi)
            intent.putExtra(DetailActivity.PICTURE, bea.imgUrl)
            startActivity(intent)
        }

        binding.rvBeasiswa.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = beaAdapter
        }
        viewModel.getAllScholarship("Prestasi")

        viewModel.scholarships.observe(viewLifecycleOwner) { response ->
            Log.d("List Story", "onCreate: $response")
            response?.let {
                beaAdapter.submitList(it.data)
            }
        }
    }
}