package gadget.common.util

import gadget.common.g.GLock

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */

/**
 * Class for managing a pool of objects.
 */
abstract class GPool<T>(protected open val capacity: Int = 16) {

    protected val pool: Array<Any?> = Array(capacity) { null }
    protected var size: Int = 0

    /**
     * @return An instance from the pool if such, null otherwise.
     */
    abstract fun acquire(): T?

    /**
     * Release an instance to the pool.
     * @param t The instance to release.
     * @return Whether the instance was put in the pool.
     * @throws IllegalStateException If the instance is already in the pool.
     */
    abstract fun release(instance: T): Boolean

    /**
     * @return The capacity of the pool.
     */
    open fun capacity(): Int = capacity

    /**
     * @return The size of the pool.
     */
    open fun size(): Int = size

    /**
     * @return Whether the instance is already in the pool.
     */
    open fun isInPool(instance: T): Boolean {
        for (i in 0 until size) {
            if (pool[i] == instance) {
                return true
            }
        }
        return false
    }
}


/**
 * A non-synchronized pool of objects.
 */
open class GNonSyncPool<T>(capacity: Int = 16) : GPool<T>(capacity) {

    override fun acquire(): T? {
        if (size > 0) {
            val lastIndex = size - 1
            val instance = pool[lastIndex] as T?
            pool[lastIndex] = null
            size -= 1
            return instance
        }
        return null
    }

    override fun release(instance: T): Boolean {
        if (isInPool(instance)) {
            throw IllegalStateException("Already in the pool!")
        }
        if (size < capacity) {
            pool[size] = instance
            size += 1
            return true
        }
        return false
    }
}


/**
 * A synchronized pool of objects.
 */
class GSyncPool<T>(capacity: Int = 16) : GNonSyncPool<T>(capacity) {
    private val lock = GLock("GSyncPool")

    override fun acquire(): T? {
        synchronized(lock) {
            return super.acquire()
        }
    }

    override fun release(instance: T): Boolean {
        synchronized(lock) {
            return super.release(instance)
        }
    }
}