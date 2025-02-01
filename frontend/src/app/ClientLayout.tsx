"use client";

import * as React from "react";
import { useEffect } from "react";

import { ThemeProvider as NextThemesProvider } from "next-themes";

import Link from "next/link";

import client from "@/lib/backend/client";

import NarrowHeaderContent from "@/lib/business/components/NarrowHeaderContent";
import WideHeaderContent from "@/lib/business/components/WideHeaderContent";

// 로그인 한 회원의 정보는 전역상태로 관리하는 것이 좋다.
// LoginMemberContext : 이 컴포넌트의 하위 클라이언트 컴포넌트는 Context의 value 값을 `use` 함수로 얻을 수 있다.
// useLoginMember : 현재 로그인한 회원의 정보(상태), 그리고 해당 정보와 관련된 함수들을 리턴해준다.
import { LoginMemberContext, useLoginMember } from "@/stores/auth/loginMember";

import { Button } from "@/components/ui/button";

import { Copyright, LogIn, MonitorCog } from "lucide-react";

export function ClientLayout({
  children,
}: React.ComponentProps<typeof NextThemesProvider>) {
  // 훅을 통해서 로그인 한 회원의 정보(state)와 관련된 함수들을 얻는다.
  const {
    loginMember,
    setLoginMember,
    isLoginMemberPending,
    setNoLoginMember,
    isLogin,
    isAdmin,
    logout,
    logoutAndHome,
    isAdminPage,
    isUserPage,
  } = useLoginMember();

  const loginMemberContextValue = {
    loginMember,
    setLoginMember,
    isLoginMemberPending,
    setNoLoginMember,
    isLogin,
    isAdmin,
    logout,
    logoutAndHome,
    isAdminPage,
    isUserPage,
  };

  useEffect(() => {
    const fetchMember = () => {
      // useEffect 의 인자는 async 함수를 넣을 수 없다.
      // 그래서 아래에서 프로미스 문법사용
      // 참고로 현재 브라우저에 쿠키가 있어도 http only 이기 때문에 로그인한 회원의 정보는 이렇게 확인해야 한다.
      client.GET("/api/v1/members/me").then((res) => {
        if (res.error) {
          // accessToken 쿠키와 apiKey 쿠키 중 둘다 없거나 둘다 만료된 경우 여기가 실행된다.
          setNoLoginMember();
        } else {
          // accessToken 쿠키 or apiKey 쿠키 중 하나가 유효하다면 여기가 실행된다.
          setLoginMember(res.data);
        }
      });
    };

    fetchMember();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // isLoginMemberPending 의 시작상태는 true 이다.
  // 해당값이 true 라는 것은 아직 로그인 상태인지 아닌지 판별되기 전이라는 의미이다.
  // setLoginMember, setNoLoginMember, removeLoginMember 함수가 호출되면 해당값이 false 로 변경된다.
  if (isLoginMemberPending) {
    return (
      <div className="flex-1 flex justify-center items-center text-muted-foreground">
        인증 정보 로딩중...
      </div>
    );
  }
  return (
    <NextThemesProvider
      attribute="class"
      defaultTheme="system"
      enableSystem
      disableTransitionOnChange
    >
      <LoginMemberContext value={loginMemberContextValue}>
        {/* 이 안의 모든 곳에서 `loginMemberContextValue` 변수를 `use` 함수를 통해서 접근할 수 있다. */}
        {/* 하지만 여기서는 어짜피 useLoginMember 함수의 실행결과가 바로 있기 때문에 딱히 `use` 를 사용할 필요가 없다. */}
        <header>
          <NarrowHeaderContent className="flex sm:hidden" />
          <WideHeaderContent className="sm:flex hidden" />
        </header>
        <main className="flex-1 flex flex-col">{children}</main>
        <footer className="p-2 flex justify-center">
          {isUserPage && (
            <>
              <Button variant="link" asChild>
                <Link href="/">
                  <Copyright /> 2025 글로그
                </Link>
              </Button>
            </>
          )}

          {isAdminPage && (
            <>
              <Button variant="link" asChild>
                <Link href="/adm">
                  <MonitorCog /> 글로그 관리자 페이지
                </Link>
              </Button>
            </>
          )}

          {!isLogin && (
            <Button variant="link" asChild>
              <Link href="/adm/member/login">
                <LogIn /> 관리자 로그인
              </Link>
            </Button>
          )}
        </footer>
      </LoginMemberContext>
    </NextThemesProvider>
  );
}
