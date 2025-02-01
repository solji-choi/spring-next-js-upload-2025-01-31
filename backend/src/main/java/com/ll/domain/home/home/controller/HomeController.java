package com.ll.domain.home.home.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Tag(name = "HomeController", description = "홈 컨트롤러")
@Controller
public class HomeController {
    @GetMapping(value = "/", produces = "text/html;charset=utf-8")
    @ResponseBody
    @Operation(summary = "메인페이지", description = "API 서버 메인 페이지")
    public String main() {
        return "API 서버 입니다.";
    }

    @GetMapping("/session")
    @ResponseBody
    @Operation(summary = "세션 확인")
    public String session(HttpSession session) {
        String sessionDump = Stream.iterate(
                        session.getAttributeNames().asIterator(),
                        Iterator::hasNext,
                        it -> it
                ).flatMap(it -> Stream.of(it.next()))
                .map(attributeName -> {
                    Object attributeValue = session.getAttribute(attributeName);
                    return attributeName + " = " + attributeValue;
                })
                .collect(Collectors.joining("\n", "Session Attributes:\n", ""));

        // 완성된 세션 정보 반환
        return sessionDump;
    }
}
