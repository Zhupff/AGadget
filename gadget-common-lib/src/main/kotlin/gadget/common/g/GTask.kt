package gadget.common.g

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description: Declare an object of task class.
 */
interface GTask : Runnable {

    /**
     * Be ready to run this task.
     */
    fun ready()

    override fun run() {
        ready()
    }
}