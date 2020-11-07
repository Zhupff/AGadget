package gadget.common.lifecycle.model

import androidx.lifecycle.LiveData
import gadget.common.model.*

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */

class G_MutSingleLiveData<A>(
    private var mLData: G_MutSingle<A>
) : LiveData<G_MutSingle<A>>(), G_LiveData {

    fun getLData(): G_MutSingle<A> = mLData

    fun setLData(data: G_MutSingle<A>) {
        mLData = data
    }

    fun getFirstValue(): A = mLData.first

    fun setFirstValue(a: A) {
        mLData.first = a
    }

    override fun notifyDataChange() {
        postValue(mLData)
    }

    override fun notifyDataChangeNow() {
        setValue(mLData)
    }
}


class G_MutDoubleLiveData<A, B>(
    private var mLData: G_MutDouble<A, B>
) : LiveData<G_MutDouble<A, B>>(), G_LiveData {

    fun getLData(): G_MutDouble<A, B> = mLData

    fun setLData(data: G_MutDouble<A, B>) {
        mLData = data
    }

    fun getFirstValue(): A = mLData.first

    fun setFirstValue(a: A) {
        mLData.first = a
    }

    fun getSecondValue(): B = mLData.second

    fun setSecondValue(b: B) {
        mLData.second = b
    }

    override fun notifyDataChange() {
        postValue(mLData)
    }

    override fun notifyDataChangeNow() {
        setValue(mLData)
    }
}


class G_MutTribleLiveData<A, B, C>(
    private var mLData: G_MutTrible<A, B, C>
) : LiveData<G_MutTrible<A, B, C>>(), G_LiveData {

    fun getLData(): G_MutTrible<A, B, C> = mLData

    fun setLData(data: G_MutTrible<A, B, C>) {
        mLData = data
    }

    fun getFirstValue(): A = mLData.first

    fun setFirstValue(a: A) {
        mLData.first = a
    }

    fun getSecondValue(): B = mLData.second

    fun setSecondValue(b: B) {
        mLData.second = b
    }

    fun getThirdValue(): C = mLData.third

    fun setThirdValue(c: C) {
        mLData.third = c
    }

    override fun notifyDataChange() {
        postValue(mLData)
    }

    override fun notifyDataChangeNow() {
        setValue(mLData)
    }
}


class G_MutQuadraLiveData<A, B, C, D>(
    private var mLData: G_MutQuadra<A, B, C, D>
) : LiveData<G_MutQuadra<A, B, C, D>>(), G_LiveData {

    fun getLData(): G_MutQuadra<A, B, C, D> = mLData

    fun setLData(data: G_MutQuadra<A, B, C, D>) {
        mLData = data
    }

    fun getFirstValue(): A = mLData.first

    fun setFirstValue(a: A) {
        mLData.first = a
    }

    fun getSecondValue(): B = mLData.second

    fun setSecondValue(b: B) {
        mLData.second = b
    }

    fun getThirdValue(): C = mLData.third

    fun setThirdValue(c: C) {
        mLData.third = c
    }

    fun getFourthValue(): D = mLData.fourth

    fun setFourthValue(d: D) {
        mLData.fourth = d
    }

    override fun notifyDataChange() {
        postValue(mLData)
    }

    override fun notifyDataChangeNow() {
        setValue(mLData)
    }
}


class G_MutPentaLiveData<A, B, C, D, E>(
    private var mLData: G_MutPenta<A, B, C, D, E>
) : LiveData<G_MutPenta<A, B, C, D, E>>(), G_LiveData {

    fun getLData(): G_MutPenta<A, B, C, D, E> = mLData

    fun setLData(data: G_MutPenta<A, B, C, D, E>) {
        mLData = data
    }

    fun getFirstValue(): A = mLData.first

    fun setFirstValue(a: A) {
        mLData.first = a
    }

    fun getSecondValue(): B = mLData.second

    fun setSecondValue(b: B) {
        mLData.second = b
    }

    fun getThirdValue(): C = mLData.third

    fun setThirdValue(c: C) {
        mLData.third = c
    }

    fun getFourthValue(): D = mLData.fourth

    fun setFourthValue(d: D) {
        mLData.fourth = d
    }

    fun getFifthValue(): E = mLData.fifth

    fun setFifthValue(e: E) {
        mLData.fifth = e
    }

    override fun notifyDataChange() {
        postValue(mLData)
    }

    override fun notifyDataChangeNow() {
        setValue(mLData)
    }
}












