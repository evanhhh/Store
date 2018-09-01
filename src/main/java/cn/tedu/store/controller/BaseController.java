package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

public abstract class BaseController {

    public Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

}
