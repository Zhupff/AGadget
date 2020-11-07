package gadget.route

import gadget.base.plugin.asm.BaseGadgetTransformer
import jdk.internal.org.objectweb.asm.ClassReader
import jdk.internal.org.objectweb.asm.ClassWriter
import jdk.internal.org.objectweb.asm.tree.ClassNode
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
internal class GadgetRouteTransformer : BaseGadgetTransformer() {

    private val mRouteTableList: CopyOnWriteArrayList<String> = CopyOnWriteArrayList()

    override fun transformClass(classBytes: ByteArray): ByteArray {
        val cr = ClassReader(classBytes)
        val cn = ClassNode()
        cr.accept(cn, ClassReader.EXPAND_FRAMES)

        if (cn.name.startsWith(GadgetRouteConstant._GadgetRoutePackage)) {
            if (cn.name == GadgetRouteConstant._G_Router) {
                val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
                val cv = GadgetRouteCV(cw, mRouteTableList)
                cr.accept(cv, ClassReader.EXPAND_FRAMES)
                return cw.toByteArray()
            } else if (cn.interfaces.contains(GadgetRouteConstant._TaG_Route)) {
                mRouteTableList.add(cn.name)
            }
        }
        return super.transformClass(classBytes)
    }
}