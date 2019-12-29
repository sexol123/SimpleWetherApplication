package kh.sergeimaleev.mywetherapplication.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<V : ViewModel, T : ViewDataBinding> : Fragment() {
    protected val classTag = this::class.java.name
    protected abstract val mLayoutId: Int
    protected abstract val mViewModel: V
    protected lateinit var mDataBinding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = DataBindingUtil.inflate(inflater, mLayoutId, container, false)
        return mDataBinding.root
    }
}