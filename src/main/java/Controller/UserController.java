package Controller;

import http.HttpHeader;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;
import util.HttpResponseUtils;
import webserver.RequestHandler;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Pattern;

public class UserController implements Controller{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Override
    public HttpResponse makeResponse(HttpRequest httpRequest) throws IOException {
        // Uri  받아옵시다
        String uri = httpRequest.getUri();

        // 회원가입일 때
        // TODO 할 일 enum 으로 구현 가능 할듯

        // TODO 회원가입에 관한 처리 REFACTORING 예정
        // TODO CreateUser class 생성?
        if (uri.startsWith("/user/create")) {
            // TODO

            // 회원 가입 정보들을 Map에 담는 과정
            // uri에서 ? 문자 뒤에 있는 query string을 분리 > query string을 & 문자 스플릿 해서 map 자료구조에 넣어줌
            Map<String, String> params = HttpRequestUtils.parseQueryString(getQueryStringInUri(uri));

            User user = new User(
                    params.get("userId"),
                    params.get("password"),
                    params.get("name"),
                    params.get("email"));

            logger.debug("User : {}", user);

            //TODO 임시 코드 - 회원가입을 성공했다고 회원가입 정보를 응답으로 보내줌
            String successSignUP = "Sign Up Success !" + System.lineSeparator() + user;
            byte[] responseBody = successSignUP.getBytes();
            return new HttpResponse(HttpStatus.OK, responseBody, null);
        }

        //TODO 임시 코드 - return 예외처리 해야됨
        byte[] responseBody = HttpResponseUtils.makeBody(httpRequest, null);
        return new HttpResponse(HttpStatus.OK, responseBody, null);
    }

    public String getQueryStringInUri(String uri){
        return uri.split(Pattern.quote("?"))[1];
    }
}
