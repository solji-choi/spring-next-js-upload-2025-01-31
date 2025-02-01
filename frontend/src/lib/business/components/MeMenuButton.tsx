"use client";

import Image from "next/image";
import Link from "next/link";

import { useGlobalLoginMember } from "@/stores/auth/loginMember";

import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";

import { LogOut, MonitorCog, User } from "lucide-react";

export default function MeMenuButton() {
  const { isAdmin, loginMember, logoutAndHome } = useGlobalLoginMember();

  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <Button variant="link">
          <Image
            className="w-8 h-8 rounded-full object-cover"
            src={loginMember.profileImgUrl}
            width={32}
            height={32}
            quality={100}
            alt={""}
          />
        </Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent align="end">
        <DropdownMenuItem>
          <Button variant="link" className="w-full justify-start" asChild>
            <Link href="/member/me">
              <User /> {loginMember.nickname}
            </Link>
          </Button>
        </DropdownMenuItem>
        {isAdmin && (
          <DropdownMenuItem>
            <Button variant="link" className="w-full justify-start" asChild>
              <Link href="/adm">
                <MonitorCog /> 관리자 홈
              </Link>
            </Button>
          </DropdownMenuItem>
        )}
        <DropdownMenuItem>
          <Button
            variant="link"
            className="w-full justify-start"
            onClick={logoutAndHome}
          >
            <LogOut /> 로그아웃
          </Button>
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  );
}
