package gadget.common.lifecycle.model

import androidx.lifecycle.LiveData

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
open class G_MapLiveData<K, V>(
    protected open var mLData: Map<K, V> = mapOf()
) : LiveData<Map<K, V>>(), G_LiveData {

    open fun getLData(): Map<K, V> = mLData

    open fun setLData(data: Map<K, V>) {
        mLData = data
    }

    open fun getMapSize(): Int = mLData.size

    override fun notifyDataChange() {
        postValue(mLData)
    }

    override fun notifyDataChangeNow() {
        setValue(mLData)
    }
}