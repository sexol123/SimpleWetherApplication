package kh.sergeimaleev.mywetherapplication.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kh.sergeimaleev.mywetherapplication.R
import kh.sergeimaleev.mywetherapplication.databinding.FragmentMainBinding
import kh.sergeimaleev.mywetherapplication.presentation.base.BaseFragment
import kh.sergeimaleev.mywetherapplication.presentation.base.TagNamed
import kh.sergeimaleev.mywetherapplication.presentation.history.HistoryFragment
import kh.sergeimaleev.mywetherapplication.presentation.map.MapFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>(), TagNamed<Fragment> {

    override val mLayoutId: Int = R.layout.fragment_main
    override val mViewModel: MainViewModel by viewModel()
    override val tagName: Int = R.string.map
    override val item: Fragment = this
    private val pagerAdapter: MainViewPagerAdapter by lazy {
        MainViewPagerAdapter(
            context!!,
            parentFragmentManager,
            arrayListOf(MapFragment(), HistoryFragment())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        fragment_main_viewpager.adapter = pagerAdapter
    }


}