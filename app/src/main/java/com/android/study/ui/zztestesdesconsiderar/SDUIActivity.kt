package com.android.study.ui.zztestesdesconsiderar//package com.android.study.ui.features
//
//import android.content.Context
//import android.os.Bundle
//import android.util.TypedValue
//import android.view.View
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.constraintlayout.widget.ConstraintLayout
//import com.android.study.databinding.SduiactivityBinding
//import com.android.study.ui.model.LayoutComponent
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.Types
//
//
//class SDUIActivity: AppCompatActivity() {
//    private lateinit var binding: SduiactivityBinding
//
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = SduiactivityBinding.inflate(layoutInflater)
//        setContentView(this.binding.rootLayout)
//
//        val jsonTemplate = loadJsonFromAssets(this, "sdui.json")
//        jsonTemplate?.let {
//            val components = parseJsonWithMoshi(it) ?: emptyList()
//            renderLayout(components, binding.rootLayout)
//        }
//
//    }
//
//    fun loadJsonFromAssets(context: Context, fileName: String): String? {
//        return try {
//            context.assets.open(fileName).bufferedReader().use { it.readText() }
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//            null
//        }
//    }
//
//    fun parseJsonWithMoshi(json: String): List<LayoutComponent>? {
//        val moshi = Moshi.Builder().build()
//        val type = Types.newParameterizedType(List::class.java, LayoutComponent::class.java)
//        val adapter = moshi.adapter<List<LayoutComponent>>(type)
//        return adapter.fromJson(json)
//    }
//
//    fun renderLayout(components: List<LayoutComponent>, rootView: ConstraintLayout) {
//        val viewsMap = mutableMapOf<String, View>()
//
//        components.forEach { component ->
//            val view = when (component.type) {
//                "TEXT" -> TextView(rootView.context).apply {
//                    text = component.content
//                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f) // Define o tamanho do texto
//                    layoutParams = ConstraintLayout.LayoutParams(
//                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
//                        ConstraintLayout.LayoutParams.WRAP_CONTENT
//                    )
//                }
//                "BUTTON" -> Button(rootView.context).apply {
//                    text = component.text
//                    layoutParams = ConstraintLayout.LayoutParams(
//                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
//                        ConstraintLayout.LayoutParams.WRAP_CONTENT
//                    )
//                }
//                else -> null
//            }
//
//            view?.let {
//                it.id = View.generateViewId()
//                viewsMap[component.id] = it // Mapeia o componente pelo ID
//                rootView.addView(it)
//
//                val layoutParams = ConstraintLayout.LayoutParams(
//                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
//                    ConstraintLayout.LayoutParams.WRAP_CONTENT
//                )
//
//                component.constraints.leftToLeft?.let { ref ->
//                    layoutParams.leftToLeft = viewsMap[ref]?.id ?: rootView.id
//                }
//
//                component.constraints.leftToRight?.let { ref ->
//                    layoutParams.leftToRight = viewsMap[ref]?.id ?: rootView.id
//                }
//
//                component.constraints.rightToLeft?.let { ref ->
//                    layoutParams.rightToLeft = viewsMap[ref]?.id ?: rootView.id
//                }
//
//                component.constraints.rightToRight?.let { ref ->
//                    layoutParams.rightToRight = viewsMap[ref]?.id ?: rootView.id
//                }
//
//
//                // Configuração das constraints
//                component.constraints.topToTop?.let { ref ->
//                    layoutParams.topToTop = viewsMap[ref]?.id ?: rootView.id
//                }
//
//                component.constraints.topToBottom?.let { ref ->
//                    layoutParams.topToBottom = viewsMap[ref]?.id ?: rootView.id
//                }
//
//                component.constraints.bottomToTop?.let { ref ->
//                    layoutParams.bottomToTop = viewsMap[ref]?.id ?: rootView.id
//                }
//
//                component.constraints.bottomToBottom?.let { ref ->
//                    layoutParams.bottomToBottom = viewsMap[ref]?.id ?: rootView.id
//                }
//
//                component.constraints.startToStart?.let { ref ->
//                    layoutParams.startToStart = viewsMap[ref]?.id ?: ConstraintLayout.LayoutParams.PARENT_ID
//                }
//
//                component.constraints.startToEnd?.let { ref ->
//                    layoutParams.startToEnd = viewsMap[ref]?.id ?: ConstraintLayout.LayoutParams.PARENT_ID
//                }
//
//                component.constraints.endToStart?.let { ref ->
//                    layoutParams.endToStart = viewsMap[ref]?.id ?: ConstraintLayout.LayoutParams.PARENT_ID
//                }
//
//                component.constraints.endToEnd?.let { ref ->
//                    layoutParams.endToEnd = viewsMap[ref]?.id ?: ConstraintLayout.LayoutParams.PARENT_ID
//                }
//
//                // Defina margens se necessário
//                layoutParams.setMargins(8, 8, 8, 8) // Ajuste as margens conforme necessário
//                it.layoutParams = layoutParams
//            }
//        }
//        binding.rootLayout.requestLayout()
//    }
//
//
//}