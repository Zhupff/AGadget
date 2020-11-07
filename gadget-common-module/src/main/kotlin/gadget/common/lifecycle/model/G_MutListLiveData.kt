package gadget.common.lifecycle.model

import androidx.lifecycle.LiveData

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
open class G_MutListLiveData<T>(
    protected open var mLData: MutableList<T> = mutableListOf()
) : LiveData<MutableList<T>>(), G_LiveData {

    open fun getLData(): MutableList<T> = mLData

    open fun setLData(listData: MutableList<T>) {
        mLData = listData
    }

    open fun getListSize(): Int = mLData.size

    open fun add(t: T) {
        mLData.add(t)
    }

    open fun add(index: Int, t: T) {
        mLData.add(index, t)
    }

    open fun addAll(t: Collection<T>) {
        mLData.addAll(t)
    }

    open fun addAll(index: Int, t: Collection<T>) {
        mLData.addAll(index, t)
    }

    open fun addAll(t: Iterable<T>) {
        mLData.addAll(t)
    }

    open fun addAll(t: Sequence<T>) {
        mLData.addAll(t)
    }

    open fun addAll(t: Array<out T>) {
        mLData.addAll(t)
    }

    open fun remove(t: T) {
        mLData.remove(t)
    }

    open fun clear() {
        mLData.clear()
    }

    override fun notifyDataChange() {
        postValue(mLData)
    }

    override fun notifyDataChangeNow() {
        setValue(mLData)
    }
}