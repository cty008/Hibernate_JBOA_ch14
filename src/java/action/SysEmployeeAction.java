package action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import pojo.SysEmployee;
import service.SysEmployeeService;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class SysEmployeeAction extends ActionSupport {

    private SysEmployeeService sysEmployeeService;
    private SysEmployee sysEmployee;



    //登陆
    public String login() throws Exception {

        HttpServletResponse response=ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();

        sysEmployee = this.sysEmployeeService.loginService(this.sysEmployee);
        if (sysEmployee!=null){
            //将用户名存入session
            ActionContext.getContext().getSession().put("user",sysEmployee);

            if (sysEmployee.getStatus().toString().trim().equals("离职")){
                out.println("<script type='text/javascript'>"+"alert('登陆失败,该用户已离职');"+"location.href='login.jsp'; </script>");
                return Action.NONE;
            }else{
                return Action.SUCCESS;
            }
        }else {
            out.println("<script type='text/javascript'>"+"alert('用户名或密码错误,登陆失败');"+"location.href='login.jsp'; </script>");
            return Action.NONE;
        }
    }

    //退出
    public String logout()throws Exception{
        ActionContext.getContext().getSession().remove("sysEmployee");
        return Action.LOGIN;
    }


    public SysEmployeeService getSysEmployeeService() {
        return sysEmployeeService;
    }
    public void setSysEmployeeService(SysEmployeeService sysEmployeeService) {
        this.sysEmployeeService = sysEmployeeService;
    }
    public SysEmployee getSysEmployee() {
        return sysEmployee;
    }
    public void setSysEmployee(SysEmployee sysEmployee) {
        this.sysEmployee = sysEmployee;
    }


}
