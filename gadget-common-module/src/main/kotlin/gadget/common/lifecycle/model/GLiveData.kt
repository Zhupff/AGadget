package gadget.common.lifecycle.model

import androidx.lifecycle.LiveData

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
open class GLiveData<T>(
    private var mLData: T
) : LiveData<T>() {

    open fun setLData(data: T) {
        mLData = data
    }

    open fun getLData(): T = mLData

    open fun notifyLater() {
        postValue(mLData)
    }

    open fun notifyNow() {
        setValue(mLData)
    }
}