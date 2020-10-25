package gadget.dor

import gadget.base.plugin.asm.BaseGadgetTransformer
import jdk.internal.org.objectweb.asm.ClassReader
import jdk.internal.org.objectweb.asm.ClassWriter
import jdk.internal.org.objectweb.asm.tree.ClassNode
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
internal class GadgetDoRTransformer : BaseGadgetTransformer(), GadgetDoRConstant {

    private val mDorList: CopyOnWriteArrayList<String> = CopyOnWriteArrayList()

    override fun transformClass(classBytes: ByteArray): ByteArray {
        val cr = ClassReader(classBytes)
        val cn = ClassNode()
        cr.accept(cn, ClassReader.EXPAND_FRAMES)

        if (cn.name == _G_DoR) {
            val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
            val cv = GadgetDoRCV(cw, mDorList)
            cr.accept(cv, ClassReader.EXPAND_FRAMES)
            return cw.toByteArray()
        } else if (cn.interfaces.contains(_TaG_DoR)) {
            mDorList.add(cn.name)
        }
        return super.transformClass(classBytes)
    }
}