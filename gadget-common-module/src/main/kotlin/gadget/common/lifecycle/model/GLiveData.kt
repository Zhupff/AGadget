package gadget.common.lifecycle.model

import androidx.lifecycle.LiveData

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
open class GLiveData<T>(
    private var lData: T
) : LiveData<T>() {

    open fun setLData(data: T) {
        this.lData = data
    }

    open fun getLData(): T = this.lData

    open fun notifyLater() {
        postValue(this.lData)
    }

    open fun notifyNow() {
        setValue(this.lData)
    }
}