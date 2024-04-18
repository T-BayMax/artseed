package com.guangzhou.t.baymax.artseed.ui.activity

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.guangzhou.t.baymax.artseed.R
import com.guangzhou.t.baymax.artseed.bean.UserBean
import com.guangzhou.t.baymax.artseed.core.base.CoreBaseMVPActivity
import com.guangzhou.t.baymax.artseed.core.utils.DialogUtils.showLoadingDialog
import com.guangzhou.t.baymax.artseed.core.utils.StatusBarUtil
import com.guangzhou.t.baymax.artseed.core.utils.ToastUtils.showToast
import com.guangzhou.t.baymax.artseed.core.utils.checkPassword
import com.guangzhou.t.baymax.artseed.core.utils.checkPhoneNumber
import com.guangzhou.t.baymax.artseed.mvp.contract.LoginContract
import com.guangzhou.t.baymax.artseed.mvp.model.LoginModel
import com.guangzhou.t.baymax.artseed.mvp.presenter.LoginPresenter

class LoginActivity() : CoreBaseMVPActivity<LoginPresenter, LoginModel>(), LoginContract.View {


    lateinit var btnLogin: Button
    lateinit var etPhone:EditText
    lateinit var etPwd:EditText

    lateinit var dialog: Dialog

    override fun getLayoutId(): Int {
        return R.layout.activity_loging
    }

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarUtil.setStatusBar(this)
        btnLogin=findViewById(R.id.btn_login)
        etPhone=findViewById(R.id.et_phone)
        etPwd=findViewById(R.id.et_pwd)
        initClick()
    }
    var phone:String=""
    var pwd:String=""
    private fun initClick() {
        btnLogin.setOnClickListener {
             phone=etPhone.text.toString()
             pwd=etPwd.text.toString()
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
