package com.mgaebz.cromulence

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class InsultActionsBottomDialogFragment : BottomSheetDialogFragment() {

    private var selectedInsultView: LinearLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        selectedInsultView = activity?.findViewById(R.id.selected_insult)

        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.setDimAmount(0.0f)
        return dialog
    }

    override fun setupDialog(dialog: Dialog?, style: Int) {
        selectedInsultView?.animate()?.alpha(1.0f)
        selectedInsultView?.visibility = View.VISIBLE
    }

    override fun onDismiss(dialog: DialogInterface?) {
        selectedInsultView?.animate()?.alpha(0.0f)
        selectedInsultView?.visibility = View.GONE

        super.onDismiss(dialog)
    }
}