package kh.sergeimaleev.mywetherapplication.presentation.activity

import kh.sergeimaleev.mywetherapplication.R
import kh.sergeimaleev.mywetherapplication.presentation.base.BaseActivity

class MainActivity : BaseActivity() {

    override val mLayout: Int = R.layout.activity_main
    override val mHostId: Int = R.id.nav_host_fragment

    override fun onCreate() {}
}