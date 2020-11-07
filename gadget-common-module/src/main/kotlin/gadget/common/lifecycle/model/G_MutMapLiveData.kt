package gadget.common.lifecycle.model

import androidx.lifecycle.LiveData

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
open class G_MutMapLiveData<K, V>(
    protected open var mLData: MutableMap<K, V> = mutableMapOf()
) : LiveData<MutableMap<K, V>>(), G_LiveData {

    open fun getLData(): MutableMap<K, V> = mLData

    open fun setLData(data: MutableMap<K, V>) {
        mLData = data
    }

    open fun getMapSize(): Int = mLData.size

    open fun put(k: K, v: V) {
        mLData.put(k, v)
    }

    override fun notifyDataChange() {
        postValue(mLData)
    }

    override fun notifyDataChangeNow() {
        setValue(mLData)
    }
}