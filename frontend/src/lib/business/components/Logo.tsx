"use client";

import Link from "next/link";

import { Rabbit } from "lucide-react";

export default function Logo({ text, ...props }: { text?: boolean }) {
  return (
    <Link href="/" {...props}>
      <Rabbit />
      {text && <span>글로그</span>}
    </Link>
  );
}
