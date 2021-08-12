package com.scolley.logging.timezone

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.scolley.logging.R
import com.scolley.logging.databinding.FragmentTimezoneBinding
import com.scolley.logging.login.LoginFragmentDirections
import com.scolley.logging.login.UserManager

class TimeZoneFragment: Fragment() {

    private lateinit var timeZoneViewModel: TimeZoneViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        timeZoneViewModel = ViewModelProvider(this, TimeZoneViewModelFactory())
                .get(TimeZoneViewModel::class.java)

        val binding =  FragmentTimezoneBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = timeZoneViewModel

        timeZoneViewModel.user.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("test","user = $it")
            }
        })

        timeZoneViewModel.timeZoneList.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("test","timeZoneList = $it")
            }
        })

        timeZoneViewModel.updateFailedInfo.observe(viewLifecycleOwner, Observer {
            if (it != null){
                Toast.makeText(this.requireContext(),
                        "Update failed : $it", Toast.LENGTH_SHORT).show()
            }
        })

        timeZoneViewModel.updateSuccessInfo.observe(viewLifecycleOwner, Observer {
            if (it != null){
                Toast.makeText(this.requireContext(),
                        "Update Succeeded : ${it.updatedAt}", Toast.LENGTH_SHORT).show()
            }
        })

        val spinner: Spinner = binding.spinnerTimeZone

        spinner.setSelection(UserManager.user.value!!.timezone)

        ArrayAdapter.createFromResource(
                this.requireContext(),
                R.array.GMT_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        binding.spinnerTimeZone.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                timeZoneViewModel.updateTimezone(position)
            }
        }

//        val dropDownAdapter = ArrayAdapter<String>(
//                requireContext(),
//                android.R.layout.simple_dropdown_item_1line,
//                timeZoneViewModel.timeZoneList.value!!.toTypedArray()
//        )
//
//        binding.autoCompleteTextView.apply {
//            setAdapter(dropDownAdapter)
//            setText(
//                    dropDownAdapter.getItem(timeZoneViewModel.getGMTTimeToPosition(timeZoneViewModel.user.value!!.timezone)),
//                    false
//            )
//            setOnItemClickListener { _, _, position, _ ->
//                timeZoneViewModel.updateTimezone(position)
//            }
//        }
//
        binding.backButton.setOnClickListener {
            findNavController().navigate(TimeZoneFragmentDirections.actionTimeZoneFragmentToTrafficFragment())
        }


        return binding.root
    }


}