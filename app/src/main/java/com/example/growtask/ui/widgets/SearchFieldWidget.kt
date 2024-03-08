package com.example.growtask.ui.widgets

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.growtask.R
import com.example.growtask.databinding.GlobalSearchWidgetBinding

class SearchFieldWidget(
    @get:JvmName("context") var context: Context,
    @get:JvmName("attrs") var attrs: AttributeSet?
) : ConstraintLayout(context, attrs) {


    private var textChangedAction: Runnable? = null
    private var afterTextChangedAction: Runnable? = null

    private lateinit var mBinding: GlobalSearchWidgetBinding

    init {
        initializeView()
        setStyle()
        setInitialView(false)
    }

    fun getData(): String {
        return mBinding.etSearch.text.toString()
    }

    fun setData(string: String) {
        mBinding.etSearch.setText(string)
    }

    fun setInitialView(isFocus: Boolean) {
        if (isFocus) {
            this.background =
                resources.getDrawable(R.drawable.primary_account_blue_background_home_screen)
            mBinding.etSearch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search, 0, 0, 0)
        } else {
            this.background = resources.getDrawable(R.drawable.new_card_background)
            mBinding.etSearch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search, 0, 0, 0)
        }
    }

    private fun initializeView() {
        mBinding = GlobalSearchWidgetBinding.inflate(LayoutInflater.from(context), this, true)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setStyle() {
        mBinding.etSearch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search, 0, 0, 0)
        mBinding.etSearch.height = dpToPixels(40, context)
        mBinding.etSearch.setPadding(
            0,
            dpToPixels(10, context),
            0,
            dpToPixels(10, context)
        )
        /*this.elevation = 2f*/
        mBinding.etSearch.hint = context.getString(R.string.tv_lbl_search)
        mBinding.etSearch.textSize = resources.getDimension(R.dimen.size_6)
        mBinding.etSearch.compoundDrawablePadding = 16
        this.setBackgroundColor(resources.getColor(R.color.white, null))
        this.background = resources.getDrawable(R.drawable.new_card_background, null)
//        this.elevation = 5f
        mBinding.etSearch.isSingleLine = true
        mBinding.etSearch.gravity = Gravity.CENTER_VERTICAL
        mBinding.etSearch.onFocusChangeListener = setFocusChangeListener()
        mBinding.etSearch.maxLines = 1
        mBinding.etSearch.inputType = InputType.TYPE_CLASS_TEXT
        mBinding.etSearch.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        mBinding.etSearch.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
        /*val typeface = resources.getFont(R.font.inter_regular)
        mBinding.etSearch.typeface = typeface*/
        mBinding.etSearch.setOnKeyListener(setKeyPressedListener())
        mBinding.etSearch.addTextChangedListener(setTextWatcher())

        mBinding.ivCancel.setOnClickListener {
            setData("")
        }

        if (getData().isNullOrEmpty()) {
            mBinding.ivCancel.visibility = View.INVISIBLE
        } else {
            mBinding.ivCancel.visibility = View.VISIBLE
        }
    }

    private fun setKeyPressedListener(): OnKeyListener? {
        return OnKeyListener { view, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER ||
                keyCode == EditorInfo.IME_ACTION_SEARCH ||
                keyCode == EditorInfo.IME_ACTION_DONE ||
                keyCode == EditorInfo.IME_ACTION_NEXT ||
                keyCode == EditorInfo.IME_ACTION_PREVIOUS && keyEvent.action == KeyEvent.ACTION_UP
            ) {
                hideKeyboard(this, context)
                mBinding.etSearch.clearFocus()
                if (!this.getData().isNullOrEmpty()) {
                    mBinding.etSearch.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_search,
                        0,
                        0,
                        0
                    )
                }
                return@OnKeyListener true
            }
            return@OnKeyListener false
        }
    }

    private fun setTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                searchString: CharSequence?,
                start: Int,
                before: Int,
                p3: Int
            ) {
                textChangedAction?.run()
                setUIState()
            }

            override fun afterTextChanged(searchString: Editable?) {
            }
        }
    }

    fun setFocusChangeListener(): OnFocusChangeListener {
        return OnFocusChangeListener { view, isFocused ->
            if (isFocused) {
                this.background = resources.getDrawable(
                    R.drawable.primary_account_blue_background_home_screen,
                    null
                )
                mBinding.etSearch.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_search,
                    0,
                    0,
                    0
                )
            } else {
                this.background =
                    resources.getDrawable(R.drawable.new_card_background, null)
                mBinding.etSearch.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_search,
                    0,
                    0,
                    0
                )
            }
        }
    }

    private fun setUIState() {
        if (mBinding.etSearch.text.isNullOrEmpty()) {
            mBinding.etSearch.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_search,
                0,
                0,
                0
            )
            mBinding.ivCancel.visibility = View.INVISIBLE
        } else {
            mBinding.etSearch.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_search,
                0,
                0,
                0
            )
            mBinding.ivCancel.visibility = View.VISIBLE
        }
    }

    fun setTextChangedAction(textChangedAction: Runnable) {
        this.textChangedAction = textChangedAction
    }

    fun setAfterTextChangedAction(afterTextChangedAction: Runnable) {
        this.afterTextChangedAction = afterTextChangedAction
    }

    private fun dpToPixels(dp: Int, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    private fun showKeyboard(clMain: ConstraintLayout, context: Context) {
        clMain.requestFocus()
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(clMain, 0)
    }

    fun hideKeyboard(clMain: ConstraintLayout, context: Context) {
        clMain.requestFocus()
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(clMain.windowToken, 0)
    }
}