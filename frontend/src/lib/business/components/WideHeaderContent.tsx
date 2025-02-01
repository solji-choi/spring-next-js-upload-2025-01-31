"use client";

import Link from "next/link";

import { useGlobalLoginMember } from "@/stores/auth/loginMember";

import { Button } from "@/components/ui/button";

import {
  MonitorCog,
  NotebookTabs,
  Pencil,
  TableOfContents,
  UserRoundSearch,
} from "lucide-react";

import LoginButton from "./LoginButton";
import Logo from "./Logo";
import MeMenuButton from "./MeMenuButton";
import ThemeToggleButton from "./ThemeToggleButton";

export default function WideHeaderContent({
  className,
}: {
  className?: string;
}) {
  const { isLogin, isAdminPage, isUserPage } = useGlobalLoginMember();

  return (
    <div className={`${className} container mx-auto py-2`}>
      {isUserPage && (
        <>
          <Button variant="link" asChild>
            <Logo text />
          </Button>
          <Button variant="link" asChild>
            <Link href="/post/list">
              <TableOfContents /> 글
            </Link>
          </Button>
          {isLogin && (
            <Button variant="link" asChild>
              <Link href="/post/write">
                <Pencil /> 작성
              </Link>
            </Button>
          )}
          {isLogin && (
            <Button variant="link" asChild>
              <Link href="/post/mine">
                <NotebookTabs /> 내글
              </Link>
            </Button>
          )}
        </>
      )}

      {isAdminPage && (
        <>
          <Button variant="link" asChild>
            <Link href="/adm">
              <MonitorCog /> 관리자 홈
            </Link>
          </Button>
          <Button variant="link" asChild>
            <Link href="/adm/member/list">
              <UserRoundSearch /> 회원관리
            </Link>
          </Button>
          <Button variant="link" asChild>
            <Logo text />
          </Button>
        </>
      )}

      <div className="flex-grow"></div>
      {!isLogin && <LoginButton />}
      {isLogin && <MeMenuButton />}
      <ThemeToggleButton />
    </div>
  );
}
