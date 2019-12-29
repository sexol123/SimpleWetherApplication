package kh.sergeimaleev.mywetherapplication.presentation.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.maps.model.LatLng
import kh.sergeimaleev.mywetherapplication.R
import kh.sergeimaleev.mywetherapplication.databinding.FragmentHistoryBinding
import kh.sergeimaleev.mywetherapplication.presentation.base.BaseFragment
import kh.sergeimaleev.mywetherapplication.presentation.base.TagNamed
import kh.sergeimaleev.mywetherapplication.presentation.map.MapViewModel
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<HistoryViewModule, FragmentHistoryBinding>(),
    TagNamed<Fragment> {
    override val mLayoutId: Int = R.layout.fragment_history
    override val mViewModel: HistoryViewModule by viewModel()
    override val tagName: Int = R.string.history
    override val item: Fragment = this
    private val mapViewModel: MapViewModel by sharedViewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerAdapter()
    }

    private fun initRecyclerAdapter() {
        val mAdapter = HistoryAdapter(
            arrayListOf(), ::onItemClickAction
        )
        fragment_history_list.layoutManager = LinearLayoutManager(requireContext())
        fragment_history_list.adapter = mAdapter

        mViewModel.historyItems.observe(viewLifecycleOwner, Observer {
            mAdapter.update(it)
        })
    }

    private fun onItemClickAction(lat: Double, lon: Double) {
        mapViewModel.moveToPoint(LatLng(lat, lon))
        goToFirstTab()
    }

    private fun goToFirstTab() {
        val v = parentFragment?.view?.findViewById<ViewPager>(R.id.fragment_main_viewpager)
        v?.setCurrentItem(0, true)
    }
}