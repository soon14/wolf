package study.daydayup.wolf.business.account.auth.agent.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.api.service.auth.AuthService;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.common.util.DateUtil;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * study.daydayup.wolf.business.account.auth.agent.controller
 *
 * @author Wingle
 * @since 2019/12/4 5:49 下午
 **/
@RestController
public class LogoutController extends AuthController {
    @Resource
    private Session session;

    @RequestMapping("/auth/logout")
    public Result logout() {
        session.destroy();

       return Result.ok();
    }

    @RequestMapping("/auth/show")
    public String show() {
        Long accountId = (Long) session.get("accountId");
        Long orgId = (Long)session.get("orgId");
        LocalDateTime expiredAt = DateUtil.asLocalDateTime((Date)session.get("expiredAt"));
        LocalDateTime now = LocalDateTime.now();
        Date dNow = new Date();

        return "accountId:" + accountId + "; orgId:" + orgId
                + "\nexpiredAt:" + expiredAt
                + "\nnow:" + now
                + "\ndateNow:" + DateUtil.asLocalDateTime(dNow);
    }
}
