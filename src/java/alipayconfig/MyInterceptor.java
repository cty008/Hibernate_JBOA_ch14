package alipayconfig;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import pojo.SysEmployee;

/**
 * 自定义拦截类
 */
public class MyInterceptor extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        ActionProxy proxy = invocation.getProxy();
        String actionName = proxy.getActionName();
        String namespace = proxy.getNamespace();
        String url = namespace + actionName;
        System.out.println(url);
        //获取保存到session回话作用域里 登陆人的姓名
        SysEmployee user =(SysEmployee) ActionContext.getContext().getSession().get("user");
        if(user == null){
            //没有登录，跳转到登录页面
            return "login";
        }
        //放行
        return invocation.invoke();

    }
}
