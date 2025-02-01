"use client";

import RequireAdmin from "@/lib/auth/components/RequireAdmin";

// /adm 경로로 접속하기 위해서는 관리자 로그인이 필요하도록 세팅하기 위해서
import ClientPage from "./ClientPage";

export default function Page() {
  return (
    <RequireAdmin>
      <ClientPage />
    </RequireAdmin>
  );
}
