package com.dicoding.grantme.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.grantme.databinding.FragmentBerandaBinding
import com.dicoding.grantme.di.Injection
import com.dicoding.grantme.ui.ViewModelFactory
import com.dicoding.grantme.ui.adapter.RecomAdapter
import com.dicoding.grantme.ui.detailBeasiswa.DetailActivity
import com.dicoding.grantme.ui.main.MainViewModel
import com.dicoding.grantme.ui.welcome.WelcomeActivity

class BerandaFragment : Fragment() {
    private lateinit var recomAdapter: RecomAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentBerandaBinding // Replace with your actual binding class

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBerandaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userRepository = Injection.provideRepository(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(userRepository))[MainViewModel::class.java]

        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            if (!user.isLogin) {
                startActivity(Intent(requireContext(), WelcomeActivity::class.java))
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
                viewModel.getAllScholarship()
            }
        }
    }

    private fun showList() {
        recomAdapter = RecomAdapter { recom ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.ID, recom.id)
            intent.putExtra(DetailActivity.NAME, recom.nama)
            intent.putExtra(DetailActivity.DESCRIPTION, recom.deskripsi)
            intent.putExtra(DetailActivity.PICTURE, recom.imgUrl)
            startActivity(intent)
        }

        binding.rvScholar.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recomAdapter
        }

        viewModel.getAllScholarship() // Where is `token` defined?

     viewModel.preResponse.observe(viewLifecycleOwner) { response ->
            Log.d("List Story", "onCreate: $response")
            response?.let {
                recomAdapter.submitList(it.dataPre)
            }
        }
    }
}