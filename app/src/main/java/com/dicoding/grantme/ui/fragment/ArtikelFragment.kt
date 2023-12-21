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
import com.dicoding.grantme.databinding.FragmentArtikelBinding
import com.dicoding.grantme.databinding.FragmentBeasiswaBinding
import com.dicoding.grantme.di.Injection
import com.dicoding.grantme.ui.ViewModelFactory
import com.dicoding.grantme.ui.adapter.ArticleAdapter
import com.dicoding.grantme.ui.adapter.BeasiswaAdapter
import com.dicoding.grantme.ui.detailBeasiswa.DetailActivity
import com.dicoding.grantme.ui.main.MainActivity
import com.dicoding.grantme.ui.main.MainViewModel

class ArtikelFragment : Fragment() {
    private lateinit var artAdapter: ArticleAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentArtikelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtikelBinding.inflate(inflater, container, false)
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
       artAdapter = ArticleAdapter { bea ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            startActivity(intent)
        }

        binding.rvArticle.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = artAdapter
        }
        viewModel.getArticle()

        viewModel.article.observe(viewLifecycleOwner) { response ->
            Log.d("List Story", "onCreate: $response")
            response?.let {
                artAdapter.submitList(it.data)
            }
        }
    }
}