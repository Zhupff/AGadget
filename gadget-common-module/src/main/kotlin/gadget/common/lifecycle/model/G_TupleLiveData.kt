package gadget.common.lifecycle.model

import androidx.lifecycle.LiveData
import gadget.common.model.*

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */

class G_SingleLiveData<A>(
    private var mLData: G_Single<A>
) : LiveData<G_Single<A>>(), G_LiveData {

    fun getLData(): G_Single<A> = mLData

    fun setLData(data: G_Single<A>) {
        mLData = data
    }

    fun getFirstValue(): A = mLData.first

    override fun notifyDataChange() {
        postValue(mLData)
    }

    override fun notifyDataChangeNow() {
        setValue(mLData)
    }

}


class G_DoubleLiveData<A, B>(
    private var mLData: G_Double<A, B>
) : LiveData<G_Double<A, B>>(), G_LiveData {

    fun getLData(): G_Double<A, B> = mLData

    fun setLData(data: G_Double<A, B>) {
        mLData = data
    }

    fun getFirstValue(): A = mLData.first

    fun getSecondValue(): B = mLData.second

    override fun notifyDataChange() {
        postValue(mLData)
    }

    override fun notifyDataChangeNow() {
        setValue(mLData)
    }
}


class G_TribleLiveData<A, B, C>(
    private var mLData: G_Trible<A, B, C>
) : LiveData<G_Trible<A, B, C>>(), G_LiveData {

    fun getLData(): G_Trible<A, B, C> = mLData

    fun setLData(data: G_Trible<A, B, C>) {
        mLData = data
    }

    fun getFirstValue(): A = mLData.first

    fun getSecondValue(): B = mLData.second

    fun getThirdValue(): C = mLData.third

    override fun notifyDataChange() {
        postValue(mLData)
    }

    override fun notifyDataChangeNow() {
        setValue(mLData)
    }
}


class G_QuadraLiveData<A, B, C, D>(
    private var mLData: G_Quadra<A, B, C, D>
) : LiveData<G_Quadra<A, B, C, D>>(), G_LiveData {

    fun getLData(): G_Quadra<A, B, C, D> = mLData

    fun setLData(data: G_Quadra<A, B, C, D>) {
        mLData = data
    }

    fun getFirstValue(): A = mLData.first

    fun getSecondValue(): B = mLData.second

    fun getThirdValue(): C = mLData.third

    fun getFourthValue(): D = mLData.fourth

    override fun notifyDataChange() {
        postValue(mLData)
    }

    override fun notifyDataChangeNow() {
        setValue(mLData)
    }
}


class G_PentaLiveData<A, B, C, D, E>(
    private var mLData: G_Penta<A, B, C, D, E>
) : LiveData<G_Penta<A, B, C, D, E>>(), G_LiveData {

    fun getLData(): G_Penta<A, B, C, D, E> = mLData

    fun setLData(data: G_Penta<A, B, C, D, E>) {
        mLData = data
    }

    fun getFirstValue(): A = mLData.first

    fun getSecondValue(): B = mLData.second

    fun getThirdValue(): C = mLData.third

    fun getFourthValue(): D = mLData.fourth

    fun getFifthValue(): E = mLData.fifth

    override fun notifyDataChange() {
        postValue(mLData)
    }

    override fun notifyDataChangeNow() {
        setValue(mLData)
    }
}


























