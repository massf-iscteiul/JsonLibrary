import plugins.ActionsPlugin
import java.io.File
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.isAccessible

@Target(AnnotationTarget.PROPERTY)
annotation class Inject

@Target(AnnotationTarget.PROPERTY)
annotation class Injectadd

class Injector {

    companion object {
        val map: MutableMap<String, List<KClass<*>>> = mutableMapOf()

        init {
            val scanner =
                Scanner(File("src/di.properties"))
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                val parts = line.split("=")
                map[parts[0]] = parts[1].split(",").map { Class.forName(it).kotlin }
            }
            scanner.close()
        }

        fun create(jTree: JTree): JTree {
            val type = jTree::class
            type.declaredMemberProperties.forEach {
                if (it.hasAnnotation<Inject>()) {
                    it.isAccessible = true
                    val key = type.simpleName + "." + it.name
                    val obj = map[key]!!.first().createInstance()
                    (it as KMutableProperty<*>).setter.call(jTree, obj)
                }
                else if (it.hasAnnotation<Injectadd>()){
                    it.isAccessible = true
                    val key = type.simpleName + "." + it.name
                    val actions = mutableListOf<ActionsPlugin>()
                    map[key]!!.forEach { it2 -> actions.add(it2.createInstance() as ActionsPlugin)}
                    (it as KMutableProperty<*>).setter.call(jTree, actions)
                }
            }
            return jTree
        }
    }

}