package th.co.toei.exvalidations

import android.view.ViewGroup
import android.widget.EditText
import androidx.viewbinding.ViewBinding

class Validator<T : ViewBinding>(private val binding: T) {

    fun isValid(): Boolean {
        var isValid = true
        binding.root.apply {
            val rootView = this as ViewGroup
            for (i in 0 until rootView.childCount) {
                val view = getChildAt(i)
                if (view is EditText) {
                    val rules = view.getTag(R.id.tag_edit_text) as? List<ValidationRule> ?: continue
                    for (rule in rules) {
                        if (!rule.validate(view.text.toString())) {
                            view.error = rule.errorMessage
                            isValid = false
                        }
                    }
                }
            }
        }
        return isValid
    }

    data class ValidationRule(val errorMessage: String, val validator: (String) -> Boolean) {
        fun validate(input: String) = validator(input)
    }
}