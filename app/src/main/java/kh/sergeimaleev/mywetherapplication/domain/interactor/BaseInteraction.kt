package kh.sergeimaleev.mywetherapplication.domain.interactor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseInteraction {
    protected val mSuperVisor = SupervisorJob()
    protected val mScope = CoroutineScope(Dispatchers.IO + mSuperVisor)
}