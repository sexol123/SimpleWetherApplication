package kh.sergeimaleev.mywetherapplication.presentation.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kh.sergeimaleev.mywetherapplication.presentation.base.TagNamed

class MainViewPagerAdapter(
    private val context: Context,
    fm: FragmentManager,
    private val listFragment: ArrayList<TagNamed<Fragment>> = arrayListOf(),
    behavior: Int = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) : FragmentPagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment = listFragment[position].item
    override fun getCount(): Int = listFragment.size
    override fun getPageTitle(position: Int): CharSequence? {
        listFragment[position].apply {
            return context.getText(tagName)
        }
    }

    fun addFragment(fragment: TagNamed<Fragment>) {
        listFragment.add(fragment)
    }

    fun updateData(newListFragment: List<TagNamed<Fragment>>) {
        this.listFragment.clear()
        this.listFragment.addAll(newListFragment)
        this.notifyDataSetChanged()
    }
}