package gadget.common.lifecycle.model

import androidx.lifecycle.LiveData

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
open class G_ListLiveData<T>(
    protected open var mLData: List<T> = listOf()
) : LiveData<List<T>>(), G_LiveData {

    open fun getLData(): List<T> = mLData

    open fun setLData(data: List<T>) {
        mLData = data
    }

    open fun getListSize(): Int = mLData.size

    override fun notifyDataChange() {
        postValue(mLData)
    }

    override fun notifyDataChangeNow() {
        setValue(mLData)
    }
}