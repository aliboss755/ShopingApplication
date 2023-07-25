package com.example.shopingapplication.fragments.loginReister

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.shopingapplication.data.User
import com.example.shopingapplication.databinding.FragmentRegisterBinding
import com.example.shopingapplication.util.Resource
import com.example.shopingapplication.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@Suppress("DEPRECATION")
@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private val TAG = "TAG"
    private lateinit var binding: FragmentRegisterBinding

    private val viewModel by activityViewModels<RegisterViewModel>()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.apply {
            BtRegisterRegister.setOnClickListener {
                val user = User(
                    EtFirstNameRegister.text.toString().trim(),
                    EtLastNameRegister.text.toString().trim(),
                    EtEmailRegister.text.toString().trim()
                )
                val password = EtPasswordRegister.text.toString()
                viewModel.createAccountWithEmailAndPassword(user, password)
            }

        }

        lifecycleScope.launchWhenStarted {
            viewModel.register.collect {
                when (it) {
                    is Resource.Loading -> {
                        binding.BtRegisterRegister.startAnimation()
                    }

                    is Resource.Success -> {
                        Log.d(TAG, it.data.toString())
                        binding.BtRegisterRegister.revertAnimation()

                    }

                    is Resource.Error -> {
                        Log.e(TAG, it.message.toString())
                        binding.BtRegisterRegister.revertAnimation()
                    }

                    else -> Unit
                }
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}