package study.daydayup.wolf.business.account.auth.agent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.api.dto.request.SmsRequest;
import study.daydayup.wolf.framework.rpc.Result;

import javax.validation.Valid;

/**
 * study.daydayup.wolf.business.account.auth.agent.controller
 *
 * @author Wingle
 * @since 2019/12/4 5:44 下午
 **/
@RestController
public class SmsController extends AuthController {

    @GetMapping("/auth/sms/login")
    public Result login(@Valid SmsRequest request) {
        return Result.ok();
    }

    @GetMapping("/auth/sms/registerAndLogin")
    public Result registerAndLogin(@Valid SmsRequest request) {
        return Result.ok();
    }

    @GetMapping("/auth/sms/code")
    public Result code(@RequestParam String mobile) {
        return Result.ok();
    }

    @GetMapping("/auth/sms/code")
    public Result voice(@RequestParam String mobile) {
        return Result.ok();
    }
}