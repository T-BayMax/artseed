package com.guangzhou.t.baymax.artseed.ui.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import com.guangzhou.t.baymax.artseed.R
import com.guangzhou.t.baymax.artseed.bean.UserBean
import com.guangzhou.t.baymax.artseed.core.base.CoreBaseMVPActivity
import com.guangzhou.t.baymax.artseed.core.utils.DialogUtils.showLoadingDialog
import com.guangzhou.t.baymax.artseed.core.utils.PreferenceService
import com.guangzhou.t.baymax.artseed.core.utils.StatusBarUtil
import com.guangzhou.t.baymax.artseed.core.utils.ToastUtils.showToast
import com.guangzhou.t.baymax.artseed.core.utils.checkPassword
import com.guangzhou.t.baymax.artseed.core.utils.checkPhoneNumber
import com.guangzhou.t.baymax.artseed.mvp.contract.LoginContract
import com.guangzhou.t.baymax.artseed.mvp.model.LoginModel
import com.guangzhou.t.baymax.artseed.mvp.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_loging.*

class LoginActivity() : CoreBaseMVPActivity<LoginPresenter, LoginModel>(), LoginContract.View {

    lateinit var dialog: Dialog

    override fun getLayoutId(): Int {
        return R.layout.activity_loging
    }

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarUtil.setStatusBar(this)
        initClick()
    }
    var phone:String=""
    var pwd:String=""
    private fun initClick() {
        btn_login.setOnClickListener {
             phone=et_phone.text.toString()
             pwd=et_pwd.text.toString()
            if (checkPhoneNumber(phone)) {
                return@setOnClickListener
            }
            if (checkPassword(pwd)){
                return@setOnClickListener
            }
            dialog=showLoadingDialog(this,resources.getString(R.string.str_loading))
            val fieldMap=HashMap<String,String>(0)
            fieldMap["phone"]=phone
            fieldMap["password"]=pwd
            mPresenter!!.login(fieldMap)
        }
    }

    override fun login(results: UserBean) {
        dialog.dismiss()
        startActivity( MainActivity::class.java)
        this.finish()
    }

    override fun showError(msg: String) {
        dialog.dismiss()
        showToast(this,msg)
    }

}
